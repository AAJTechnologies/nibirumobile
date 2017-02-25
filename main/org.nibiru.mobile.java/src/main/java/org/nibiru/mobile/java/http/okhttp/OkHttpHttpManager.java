package org.nibiru.mobile.java.http.okhttp;

import com.google.common.base.MoreObjects;
import com.google.common.net.HttpHeaders;

import org.nibiru.mobile.core.api.async.Promise;
import org.nibiru.mobile.core.api.config.BaseUrl;
import org.nibiru.mobile.core.api.http.HttpException;
import org.nibiru.mobile.core.api.http.HttpManager;
import org.nibiru.mobile.core.api.http.HttpRequest;
import org.nibiru.mobile.core.api.http.HttpStatus;
import org.nibiru.mobile.java.async.AsyncManager;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.google.common.base.Preconditions.checkNotNull;

public class OkHttpHttpManager implements HttpManager {
    private final String baseUrl;
    private final OkHttpClient okHttpClient;
    private final AsyncManager asyncManager;

    @Inject
    public OkHttpHttpManager(@BaseUrl String baseUrl,
                             OkHttpClient okHttpClient,
                             AsyncManager asyncManager) {
        this.baseUrl = checkNotNull(baseUrl);
        this.okHttpClient = checkNotNull(okHttpClient);
        this.asyncManager = checkNotNull(asyncManager);
    }

    @Override
    public Promise<String, HttpException> send(HttpRequest request) {
        return asyncManager.runAsync(() -> runAsync(request));
    }

    private String runAsync(HttpRequest request) {
        try {
            String contentType = MoreObjects.firstNonNull(
                    request.getHeaders().get(HttpHeaders.CONTENT_TYPE),
                    com.google.common.net.MediaType.JSON_UTF_8.toString());

            Request.Builder builder = new Request.Builder()
                    .url(baseUrl + request.getUrl())
                    .method(request.getMethod().getMethod(),
                            RequestBody.create(MediaType.parse(contentType), request.getBody()));
            for (Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
                builder.header(entry.getKey(), entry.getValue());
            }
            Response response = okHttpClient.newCall(builder.build())
                    .execute();
            if (!response.isSuccessful()) {
                throw new HttpException(HttpStatus.valueOf(response.code()), response.message());
            }
            return response.body().string();
        } catch (IOException e) {
            throw new HttpException(e);
        }
    }
}
