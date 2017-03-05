package org.nibiru.mobile.teavm.http;

import org.nibiru.mobile.core.api.async.Deferred;
import org.nibiru.mobile.core.api.async.Promise;
import org.nibiru.mobile.core.api.http.HttpException;
import org.nibiru.mobile.core.api.http.HttpManager;
import org.nibiru.mobile.core.api.http.HttpRequest;
import org.nibiru.mobile.core.api.http.HttpResponse;
import org.nibiru.mobile.core.api.http.HttpStatus;
import org.teavm.jso.ajax.XMLHttpRequest;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import static com.google.common.base.Preconditions.checkNotNull;

public class XMLHttpRequestHttpManager implements HttpManager {
    private final Provider<XMLHttpRequest> xmlHttpRequestProvider;

    @Inject
    public XMLHttpRequestHttpManager(Provider<XMLHttpRequest> xmlHttpRequestProvider) {
        this.xmlHttpRequestProvider = checkNotNull(xmlHttpRequestProvider);
    }

    @Override
    public Promise<HttpResponse, HttpException> send(HttpRequest request) {
        Deferred<HttpResponse, HttpException> deferred = Deferred.defer();
        XMLHttpRequest xhr = xmlHttpRequestProvider.get();

        for (Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
            xhr.setRequestHeader(entry.getKey(), entry.getValue());
        }

        xhr.onComplete(() -> {
            HttpStatus status = HttpStatus.valueOf(xhr.getStatus());
            if (status.is4xxClientError() || status.is5xxServerError()) {
                deferred.reject(new HttpException(status, xhr.getStatusText()));
            } else {
                String text = xhr.getResponseText();

                if (text != null && text.length() == 0) {
                    text = null;
                }

                HttpResponse.Builder responseBuilder = HttpResponse.builder(HttpStatus.valueOf(xhr.getStatus()))
                        .body(text);

                //TODO: Parse and set response headers
//                        for (Header header : xhr.getAllResponseHeaders()) {
//                            responseBuilder.header(header.getName(), header.getValue());
//                        }

                deferred.resolve(responseBuilder.build());
            }
        });

        xhr.open(request.getMethod().name(), request.getUrl());

        xhr.send(request.getBody());

        return deferred.promise();
    }
}
