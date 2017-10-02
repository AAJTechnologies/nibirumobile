package org.nibiru.mobile.core.api.service;

import com.google.common.net.MediaType;

import org.nibiru.async.core.api.promise.Promise;
import org.nibiru.mobile.core.api.http.HttpException;
import org.nibiru.mobile.core.api.http.HttpMethod;
import org.nibiru.mobile.core.api.http.HttpRequest;
import org.nibiru.mobile.core.api.http.HttpRequest.Builder;

import javax.annotation.Nullable;

/**
 * A remote service.
 */
public interface RemoteService {
    /**
     * Invokes a method on a remote service using POST/JSON content type.
     *
     * @param path          The service path
     * @param requestDto    The DTO used for creating request data
     * @param responseClass The expected response class
     */
    <T> Promise<T, HttpException> invoke(String path,
                                         @Nullable Object requestDto,
                                         Class<T> responseClass);

    /**
     * Invokes a method on a remote service allowing full parameterization;
     *
     * @param path          The service path
     * @param requestDto    The DTO used for creating request data
     * @param responseClass The expected response class
     * @param httpMethod    The HTTP method
     * @param mediaType     The media type for request and response
     */
    <T> Promise<T, HttpException> invoke(String path,
                                         @Nullable Object requestDto,
                                         Class<T> responseClass,
                                         HttpMethod httpMethod,
                                         MediaType mediaType);

    /**
     * Creates an HTTP request builder object,
     * configured according to service type.
     *
     * @param path       The service path
     * @param requestDto The DTO object
     * @return The builder
     */
    Builder requestBuilder(String path, @Nullable Object requestDto);

    /**
     * Geenric invoke method, allowing full control on HTTP request object.
     *
     * @param request       The HTTP request
     * @param responseClass The response type
     * @param <T>           The response type
     * @return The response
     */
    <T> Promise<T, HttpException> invoke(HttpRequest request, Class<T> responseClass);
}
