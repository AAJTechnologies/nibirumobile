package org.nibiru.mobile.core.api.http;

import org.nibiru.mobile.core.api.async.Promise;

import java.util.function.Supplier;

/**
 * Manager for performing requests over HTTP.
 */
public interface HttpManager {
    String CONTENT_TYPE_HEADER = "Content-Type";
    String ACCEPT_HEADER = "Accept";
    String APPLICATION_JSON_MIME = "application/json";

    /**
     * Sends a POST request.
     *
     * @param url     The URL
     * @param request the request body
     */
    Promise<String, HttpException> send(String url, String request);
}
