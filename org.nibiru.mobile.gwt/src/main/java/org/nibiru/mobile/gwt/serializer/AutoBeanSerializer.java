package org.nibiru.mobile.gwt.serializer;

import com.google.common.collect.Maps;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;

import org.nibiru.mobile.core.api.serializer.TypeLiteral;
import org.nibiru.mobile.core.impl.serializer.BaseSerializer;

import java.util.Map;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

public class AutoBeanSerializer extends BaseSerializer {
    private final AutoBeanFactory autoBeanFactory;
    private final Map<TypeLiteral<?>, Class<? extends CustomSerializer<?>>> customSerializers;

    public AutoBeanSerializer(AutoBeanFactory autoBeanFactory) {
        this.autoBeanFactory = checkNotNull(autoBeanFactory);
        this.customSerializers = Maps.newHashMap();
    }

    @Override
    protected String perfomrSerialization(Object object) {
        return AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(object))
                .getPayload();
    }

    @Override
    protected <T> T perfomrDeserialization(@Nullable String data,
                                           TypeLiteral<T> returnType) {
            Class<? extends CustomSerializer<?>> customSerializer = customSerializers.get(returnType);
            if (customSerializer != null) {
                return (T) AutoBeanCodex.decode(autoBeanFactory,
                        customSerializer,
                        "{\"data\":" + data + "}")
                        .as().getData();
            } else {
                return AutoBeanCodex.decode(autoBeanFactory,
                        returnType.getType(),
                        data)
                        .as();
            }
    }

    public AutoBeanSerializer addCustomSerializer(TypeLiteral<?> type,
                                                  Class<? extends CustomSerializer<?>> serializerClass) {
        customSerializers.put(type, serializerClass);
        return this;
    }
}
