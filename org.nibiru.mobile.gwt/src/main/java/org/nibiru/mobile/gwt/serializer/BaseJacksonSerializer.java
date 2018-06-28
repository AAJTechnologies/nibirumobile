package org.nibiru.mobile.gwt.serializer;

import com.github.nmorel.gwtjackson.client.ObjectMapper;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

import org.nibiru.mobile.core.api.serializer.Serializer;
import org.nibiru.mobile.core.api.serializer.TypeLiteral;
import org.nibiru.mobile.core.impl.serializer.BaseSerializer;

import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class BaseJacksonSerializer
        extends BaseSerializer
        implements Serializer {

    private final Map<TypeLiteral<?>, ObjectMapper> mappers;

    protected BaseJacksonSerializer() {
        mappers = Maps.newHashMap();
    }

    protected void addMapper(Class<?> type,
                             ObjectMapper mapper) {
        checkNotNull(type);
        checkNotNull(mapper);
        addMapper(TypeLiteral.create(type), mapper);
    }

    protected void addMapper(TypeLiteral<?> type,
                             ObjectMapper mapper) {
        checkNotNull(type);
        checkNotNull(mapper);
        mappers.put(type, mapper);
    }

    @Override
    protected String perfomrSerialization(Object o) {
        return getMapper(TypeLiteral.create(o.getClass())).write(o);
    }

    @Override
    protected <T> T perfomrDeserialization(String s,
                                           TypeLiteral<T> typeLiteral) {
        return (T) getMapper(typeLiteral).read(s);
    }

    private ObjectMapper getMapper(TypeLiteral<?> typeLiteral) {
        ObjectMapper mapper = mappers.get(typeLiteral);
        Preconditions.checkState(mapper != null, "No getMapper for %s", typeLiteral);
        return mapper;
    }
}
