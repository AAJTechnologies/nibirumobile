package org.nibiru.mobile.core.impl.serializer;

import org.nibiru.mobile.core.api.serializer.Serializer;
import org.nibiru.mobile.core.api.serializer.TypeLiteral;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class BaseSerializer implements Serializer {
    @Override
    public <T> T deserialize(String data, Class<T> returnType) {
        checkNotNull(data);
        checkNotNull(returnType);
        return deserialize(data, TypeLiteral.create(returnType));
    }
}
