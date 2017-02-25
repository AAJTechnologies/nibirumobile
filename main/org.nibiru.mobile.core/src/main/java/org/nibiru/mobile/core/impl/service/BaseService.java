package org.nibiru.mobile.core.impl.service;

import com.google.common.base.Function;
import com.google.common.net.MediaType;

import static com.google.common.base.Preconditions.checkNotNull;

import org.nibiru.mobile.core.api.async.Promise;
import org.nibiru.mobile.core.api.http.HttpException;
import org.nibiru.mobile.core.api.http.HttpManager;
import org.nibiru.mobile.core.api.http.HttpMethod;
import org.nibiru.mobile.core.api.http.HttpRequest;
import org.nibiru.mobile.core.api.http.HttpRequest.Builder;
import org.nibiru.mobile.core.api.serializer.Serializer;
import org.nibiru.mobile.core.api.service.RemoteService;

import javax.annotation.Nullable;

abstract class BaseService implements RemoteService {
    private final String serviceName;
    private final HttpManager httpManager;
    private final Serializer serializer;

    public BaseService(String serviceName, HttpManager httpManager,
                       Serializer serializer) {
        this.serviceName = checkNotNull(serviceName);
        this.httpManager = checkNotNull(httpManager);
        this.serializer = checkNotNull(serializer);
    }

    @Override
    public <T> Promise<T, HttpException> invoke(String method,
                                                @Nullable Object requestDto,
                                                Class<T> responseClass) {
        return invoke(method,
                requestDto,
                responseClass,
                HttpMethod.POST,
                MediaType.JSON_UTF_8);
    }

    @Override
    public <T> Promise<T, HttpException> invoke(String method,
                                                @Nullable Object requestDto,
                                                Class<T> responseClass,
                                                HttpMethod httpMethod,
                                                MediaType mediaType) {
        checkNotNull(method);
        checkNotNull(responseClass);
        checkNotNull(httpMethod);
        checkNotNull(mediaType);

        return getHttpManager()
                .send(builder(method, requestDto)
                        .method(httpMethod)
                        .accept(mediaType)
                        .contentType(mediaType)
                        .build())
                .map(this::extractResult)
                .map(parse(responseClass));
    }

    protected abstract Builder builder(String method,
                                       @Nullable Object requestDto);

    protected abstract String extractResult(String responseMessage);

    protected String getServiceName() {
        return serviceName;
    }

    protected HttpManager getHttpManager() {
        return httpManager;
    }

    protected Serializer getSerializer() {
        return serializer;
    }

    protected <V> Function<String, V> parse(Class<V> responseClass) {
        return (response) -> response != null
                ? serializer.deserialize(response, responseClass)
                : null;
    }
}
