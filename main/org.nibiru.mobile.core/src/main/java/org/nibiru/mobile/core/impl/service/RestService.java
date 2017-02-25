package org.nibiru.mobile.core.impl.service;

import com.google.common.net.MediaType;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nullable;

import org.nibiru.mobile.core.api.async.Promise;
import org.nibiru.mobile.core.api.http.HttpException;
import org.nibiru.mobile.core.api.http.HttpManager;
import org.nibiru.mobile.core.api.http.HttpMethod;
import org.nibiru.mobile.core.api.http.HttpRequest;
import org.nibiru.mobile.core.api.http.HttpRequest.Builder;
import org.nibiru.mobile.core.api.serializer.Serializer;

public class RestService extends BaseService {
    public RestService(String serviceName,
                       HttpManager httpManager,
                       Serializer serializer) {
        super(serviceName, httpManager, serializer);
    }

    @Override
    protected Builder builder(String method,
                              @Nullable Object requestDto) {
        return HttpRequest.builder(getServiceName() + "/" + method)
                .body(requestDto != null
                        ? getSerializer().serialize(requestDto)
                        : "");
    }

    @Override
    protected String extractResult(String responseMessage) {
        return responseMessage;
    }
}
