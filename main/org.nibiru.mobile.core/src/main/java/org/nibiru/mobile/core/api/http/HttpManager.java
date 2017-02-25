package org.nibiru.mobile.core.api.http;

import org.nibiru.mobile.core.api.async.Promise;

import java.util.function.Supplier;

/**
 * Manager for performing requests over HTTP.
 */
public interface HttpManager {
    /**
     * Sends an HTTP request.
     *
     * @param request The request
     */
    Promise<String, HttpException> send(HttpRequest request);
}
