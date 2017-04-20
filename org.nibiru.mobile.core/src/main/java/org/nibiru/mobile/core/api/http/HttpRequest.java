package org.nibiru.mobile.core.api.http;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.net.HttpHeaders;
import com.google.common.net.MediaType;

import java.util.Map;

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
        private String body = "";

        private Builder(String url) {
            this.url = checkNotNull(url);
            headers = Maps.newHashMap();
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

        public Builder contentType(MediaType type) {
            return header(HttpHeaders.CONTENT_TYPE, type.toString());
        }

        public Builder accept(MediaType type) {
            return header(HttpHeaders.ACCEPT, type.toString());
        }

        public Builder body(String body) {
            this.body = checkNotNull(body);
            return this;
        }

        public HttpRequest build() {
            return new HttpRequest(url,
                    method,
                    ImmutableMap.copyOf(headers),
                    body);
        }
    }
}