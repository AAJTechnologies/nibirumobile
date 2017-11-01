package org.nibiru.mobile.core.impl.serializer;

import com.google.common.base.Strings;

import org.nibiru.mobile.core.api.serializer.Serializer;
import org.nibiru.mobile.core.api.serializer.TypeLiteral;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class BaseSerializer implements Serializer {
    @Override
    public <T> T deserialize(@Nullable String data,
                             Class<T> returnType) {
        checkNotNull(returnType);
        return deserialize(data, TypeLiteral.create(returnType));
    }

    @Override
    public <T> T deserialize(@Nullable String data,
                             TypeLiteral<T> returnType) {
        checkNotNull(returnType);
        if (!Strings.isNullOrEmpty(data) && !data.trim().equalsIgnoreCase("null")) {
            return perfomrDeserialization(data, returnType);
        } else {
            return null;
        }
    }

    protected abstract <T> T perfomrDeserialization(String data,
                                                    TypeLiteral<T> returnType);
}
