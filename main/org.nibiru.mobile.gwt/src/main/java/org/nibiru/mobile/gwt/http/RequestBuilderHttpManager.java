package org.nibiru.mobile.gwt.http;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import org.nibiru.mobile.core.api.async.Deferred;
import org.nibiru.mobile.core.api.async.Promise;
import org.nibiru.mobile.core.api.config.BaseUrl;
import org.nibiru.mobile.core.api.http.HttpException;
import org.nibiru.mobile.core.api.http.HttpManager;
import org.nibiru.mobile.core.api.http.HttpStatus;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;

public class RequestBuilderHttpManager implements HttpManager {
    private final String baseUrl;

    @Inject
    public RequestBuilderHttpManager(@BaseUrl String baseUrl) {
        this.baseUrl = checkNotNull(baseUrl);
    }

    @Override
    public Promise<String, HttpException> send(String url, String request) {
        Deferred<String, HttpException> deferred = Deferred.defer();
        try {
            // TODO: Method and headers are hardcode. Parameterization might be needed in the future.
            RequestBuilder requestBuilder = new RequestBuilder(
                    RequestBuilder.POST, baseUrl + url);
            requestBuilder.setHeader(HttpManager.CONTENT_TYPE_HEADER, HttpManager.APPLICATION_JSON_MIME);
            requestBuilder.setHeader(HttpManager.ACCEPT_HEADER, HttpManager.APPLICATION_JSON_MIME);

            requestBuilder.sendRequest(request,
                    new RequestCallback() {
                        @Override
                        public void onResponseReceived(Request request,
                                                       Response response) {
                            String text = response.getText();

                            if (text != null && text.length() == 0) {
                                text = null;
                            }

                            deferred.resolve(text);
                        }

                        @Override
                        public void onError(Request request, Throwable exception) {
                            deferred.reject(new HttpException(exception));
                        }
                    });
        } catch (RequestException e) {
            throw new RuntimeException(e);
        }
        return deferred.promise();
    }
}
