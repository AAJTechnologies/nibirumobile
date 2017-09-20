package org.nibiru.mobile.core.impl.service;

import org.nibiru.mobile.core.api.http.HttpManager;
import org.nibiru.mobile.core.api.http.HttpRequest;
import org.nibiru.mobile.core.api.http.HttpRequest.Builder;
import org.nibiru.mobile.core.api.http.HttpResponse;
import org.nibiru.mobile.core.api.serializer.Serializer;

import javax.annotation.Nullable;

public class RestService extends BaseService {
    public RestService(String baseUrl,
                       String serviceName,
                       HttpManager httpManager,
                       Serializer serializer) {
        super(baseUrl, serviceName, httpManager, serializer);
    }

    @Override
    public Builder requestBuilder(String method,
                                  @Nullable Object requestDto) {
        return HttpRequest.builder(getBaseUrl() + getServiceName() + "/" + method)
                .body(requestDto != null
                        ? getSerializer().serialize(requestDto)
                        : null);
    }

    @Override
    protected String extractResult(HttpResponse response) {
        return response.getBody();
    }
}
