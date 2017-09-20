package org.nibiru.mobile.java.http.okhttp;

import com.google.common.base.MoreObjects;
import com.google.common.net.HttpHeaders;

import org.nibiru.async.core.api.promise.Promise;
import org.nibiru.mobile.core.api.http.HttpException;
import org.nibiru.mobile.core.api.http.HttpManager;
import org.nibiru.mobile.core.api.http.HttpRequest;
import org.nibiru.mobile.core.api.http.HttpResponse;
import org.nibiru.mobile.core.api.http.HttpStatus;
import org.nibiru.mobile.java.async.AsyncManager;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.google.common.base.Preconditions.checkNotNull;

public class OkHttpHttpManager implements HttpManager {
    private final OkHttpClient okHttpClient;
    private final AsyncManager asyncManager;

    @Inject
    public OkHttpHttpManager(OkHttpClient okHttpClient,
                             AsyncManager asyncManager) {
        this.okHttpClient = checkNotNull(okHttpClient);
        this.asyncManager = checkNotNull(asyncManager);
    }

    @Override
    public Promise<HttpResponse, HttpException> send(HttpRequest request) {
        return asyncManager.runAsync(() -> runAsync(request));
    }

    private HttpResponse runAsync(HttpRequest request) {
        try {
            String contentType = MoreObjects.firstNonNull(
                    request.getHeaders().get(HttpHeaders.CONTENT_TYPE),
                    com.google.common.net.MediaType.JSON_UTF_8.toString());

            Request.Builder requestBuilder = new Request.Builder()
                    .url(request.getUrl())
                    .method(request.getMethod().getMethod(),
                            request.getBody() != null
                                    ? RequestBody.create(MediaType.parse(contentType), request.getBody())
                                    : null);
            for (Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
                requestBuilder.header(entry.getKey(), entry.getValue());
            }
            Response response = okHttpClient.newCall(requestBuilder.build())
                    .execute();
            if (!response.isSuccessful()) {
                throw new HttpException(HttpStatus.valueOf(response.code()), response.message());
            }

            HttpResponse.Builder responseBuilder = HttpResponse.builder(HttpStatus.valueOf(response.code()))
                    .body(response.body().string());

            for (Map.Entry<String, List<String>> header : response.headers().toMultimap().entrySet()) {
                responseBuilder.header(header.getKey(), header.getValue().get(0));
            }

            return responseBuilder.build();
        } catch (IOException e) {
            throw new HttpException(e);
        }
    }
}
