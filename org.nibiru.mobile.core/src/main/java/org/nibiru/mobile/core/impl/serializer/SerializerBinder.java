package org.nibiru.mobile.core.impl.serializer;


import org.nibiru.mobile.core.api.serializer.Serializer;
import org.nibiru.model.core.api.Value;
import org.nibiru.model.core.impl.bind.Bind;
import org.nibiru.model.core.impl.java.JavaType;
import org.nibiru.model.core.impl.java.JavaValue;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class SerializerBinder {
    private final Serializer serializer;

    @Inject
    public SerializerBinder(Serializer serializer) {
        this.serializer = checkNotNull(serializer);
    }

    public <T> Value<T> bind(Value<String> stringValue, Class<T> clazz) {
        checkNotNull(stringValue);
        checkNotNull(clazz);

        Value<T> value = JavaValue.of(JavaType.of(clazz));

        Bind.on(stringValue)
                .map((data) -> data != null ? serializer.deserialize(data, clazz) : null)
                .to(value);

        Bind.on(value)
                .map((data) -> data != null ? serializer.serialize(data) : null)
                .to(stringValue);

        return value;
    }
}
