package org.nibiru.mobile.java.serializer.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.nibiru.mobile.core.api.serializer.TypeLiteral;
import org.nibiru.mobile.core.impl.serializer.BaseSerializer;

import java.io.IOException;

import javax.annotation.Nullable;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class JacksonSerializer extends BaseSerializer {
    private final ObjectMapper mapper;

    @Inject
    public JacksonSerializer(ObjectMapper mapper) {
        this.mapper = checkNotNull(mapper);
    }

    @Override
    protected String perfomrSerialization(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw exception(object, e);
        }
    }

    @Override
    protected <T> T perfomrDeserialization(@Nullable String data,
                                           TypeLiteral<T> returnType) {
        try {
            return mapper.readValue(data, mapper.getTypeFactory()
                    .constructParametricType(returnType.getType(),
                            returnType.getParameters()));
        } catch (IOException e) {
            throw exception(data, e);
        }
    }

    private RuntimeException exception(Object argument, Exception cause) {
        return new IllegalArgumentException("Invalid object/json: " + argument,
                cause);
    }
}
