package org.nibiru.mobile.gwt.http;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import org.nibiru.mobile.core.api.async.Deferred;
import org.nibiru.mobile.core.api.async.Promise;
import org.nibiru.mobile.core.api.config.BaseUrl;
import org.nibiru.mobile.core.api.http.HttpException;
import org.nibiru.mobile.core.api.http.HttpManager;
import org.nibiru.mobile.core.api.http.HttpMethod;
import org.nibiru.mobile.core.api.http.HttpRequest;
import org.nibiru.mobile.core.api.http.HttpResponse;
import org.nibiru.mobile.core.api.http.HttpStatus;

import com.google.gwt.http.client.Header;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestBuilder.Method;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;

import java.util.List;
import java.util.Map;

public class RequestBuilderHttpManager implements HttpManager {
    @Inject
    public RequestBuilderHttpManager() {
    }

    @Override
    public Promise<HttpResponse, HttpException> send(HttpRequest request) {
        Deferred<HttpResponse, HttpException> deferred = Deferred.defer();
        try {
            RequestBuilder requestBuilder = new RequestBuilder(method(request.getMethod()),
                    request.getUrl());

            for (Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
                requestBuilder.setHeader(entry.getKey(), entry.getValue());
            }

            requestBuilder.sendRequest(request.getBody(),
                    new RequestCallback() {
                        @Override
                        public void onResponseReceived(Request request,
                                                       Response response) {
                            HttpStatus status = HttpStatus.valueOf(response.getStatusCode());
                            if (status.is4xxClientError() || status.is5xxServerError()) {
                                deferred.reject(new HttpException(status, response.getStatusText()));
                            } else {
                                String text = response.getText();

                                if (text != null && text.length() == 0) {
                                    text = null;
                                }

                                HttpResponse.Builder responseBuilder = HttpResponse.builder(HttpStatus.valueOf(response.getStatusCode()))
                                        .body(text);

                                for (Header header: response.getHeaders()) {
                                    responseBuilder.header(header.getName(), header.getValue());
                                }

                                deferred.resolve(responseBuilder.build());
                            }
                        }

                        @Override
                        public void onError(Request request, Throwable exception) {
                            deferred.reject(new HttpException(exception));
                        }
                    });
        } catch (RequestException e) {
            deferred.reject(new HttpException(e));
        }
        return deferred.promise();
    }

    private static Method method(HttpMethod method) {
        switch (method) {
            case GET:
                return RequestBuilder.GET;
            case POST:
                return RequestBuilder.POST;
            case PUT:
                return RequestBuilder.PUT;
            case DELETE:
                return RequestBuilder.DELETE;
            case HEAD:
                return RequestBuilder.HEAD;
            default:
                throw new IllegalArgumentException("Invalid HTTP method: " + method.getMethod());
        }
    }
}
