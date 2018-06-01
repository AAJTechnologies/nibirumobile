package org.nibiru.mobile.core.api.http;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.net.HttpHeaders;
import com.google.common.net.MediaType;

import java.util.Map;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * An HTTP request.
 */
public class HttpRequest {
    private final String url;
    private final HttpMethod method;
    private final ImmutableMap<String, String> headers;
    private final String body;

    private HttpRequest(String url,
                        HttpMethod method,
                        ImmutableMap<String, String> headers,
                        String body) {
        this.url = url;
        this.method = method;
        this.headers = headers;
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public static Builder builder(String url) {
        return new Builder(url);
    }

    public static class Builder {
        private final String url;
        private HttpMethod method = HttpMethod.GET;
        private final Map<String, String> headers;
        private final Map<String, String> queryParams;
        private String body;

        private Builder(String url) {
            this.url = checkNotNull(url);
            headers = Maps.newHashMap();
            queryParams = Maps.newHashMap();
        }

        public Builder method(HttpMethod method) {
            this.method = checkNotNull(method);
            return this;
        }

        public Builder header(String header, String value) {
            checkNotNull(header);
            checkNotNull(value);
            headers.put(header, value);
            return this;
        }

        public Builder queryParam(String param, String value) {
            checkNotNull(param);
            checkNotNull(value);
            queryParams.put(param, value);
            return this;
        }

        public Builder contentType(MediaType type) {
            return header(HttpHeaders.CONTENT_TYPE, type.toString());
        }

        public Builder accept(MediaType type) {
            return header(HttpHeaders.ACCEPT, type.toString());
        }

        public Builder body(@Nullable String body) {
            this.body = body;
            return this;
        }

        public HttpRequest build() {
            String fullUrl;
            if (queryParams.isEmpty()) {
                fullUrl = url;
            } else {
                String params = Joiner.on('&').join(Iterables
                        .transform(queryParams.entrySet(), (entry) -> entry.getKey() + "=" + entry.getValue()));
                fullUrl = url.indexOf('?') >= 0
                        ? url + "&" + params
                        : url + "?" + params;
            }
            return new HttpRequest(fullUrl,
                    method,
                    ImmutableMap.copyOf(headers),
                    body);
        }
    }
}
