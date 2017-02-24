package org.nibiru.mobile.core.impl.service;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nullable;

import org.nibiru.mobile.core.api.async.Promise;
import org.nibiru.mobile.core.api.http.HttpException;
import org.nibiru.mobile.core.api.http.HttpManager;
import org.nibiru.mobile.core.api.serializer.Serializer;

public class RestService extends BaseService {
    public RestService(String serviceName,
                       HttpManager httpManager,
                       Serializer serializer) {
        super(serviceName, httpManager, serializer);
    }

    @Override
    public <T> Promise<T, HttpException> invoke(String method, final @Nullable Object requestDto,
                                                final Class<T> responseClass) {
        checkNotNull(method);
        checkNotNull(responseClass);

        return getHttpManager()
                .send(getServiceName() + "/" + method,
                        requestDto != null
                                ? getSerializer().serialize(requestDto)
                                : "")
                .map(parse(responseClass));
    }
}
