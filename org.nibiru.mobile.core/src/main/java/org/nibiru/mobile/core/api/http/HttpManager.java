package org.nibiru.mobile.core.api.http;


import org.nibiru.async.core.api.promise.Promise;

/**
 * Manager for performing requests over HTTP.
 */
public interface HttpManager {
    /**
     * Sends an HTTP request.
     *
     * @param request The request
     */
    Promise<HttpResponse, HttpException> send(HttpRequest request);
}
