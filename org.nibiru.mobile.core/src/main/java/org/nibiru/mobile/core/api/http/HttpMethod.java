package org.nibiru.mobile.core.api.http;

/**
 * Enum for HTTP methods.
 * <p>OPTIONS is not supported by GWT RequestBuilder</p>
 */
public enum HttpMethod {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    HEAD("HEAD");

    private final String method;

    HttpMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }
}
