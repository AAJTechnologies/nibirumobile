package org.nibiru.mobile.core.api.http;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.Map;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * An HTTP response.
 */
public class HttpResponse {
   private final HttpStatus status;
    private final ImmutableMap<String, String> headers;
    private final String body;

    private HttpResponse(HttpStatus status,
                        ImmutableMap<String, String> headers,
                        String body) {
        this.status = status;
        this.headers = headers;
        this.body = body;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    @Nullable
    public String getBody() {
        return body;
    }

    public static Builder builder(HttpStatus status) {
        return new Builder(status);
    }

    public static class Builder {
        private final HttpStatus status;
        private final Map<String, String> headers;
        private String body;

        private Builder(HttpStatus status) {
            this.status = checkNotNull(status);
            headers = Maps.newHashMap();
        }

        public Builder header(String header, String value) {
            checkNotNull(header);
            checkNotNull(value);
            headers.put(header, value);
            return this;
        }

        public Builder body(@Nullable String body) {
            this.body = body;
            return this;
        }

        public HttpResponse build() {
            return new HttpResponse(status,
                    ImmutableMap.copyOf(headers),
                    body);
        }
    }
}
