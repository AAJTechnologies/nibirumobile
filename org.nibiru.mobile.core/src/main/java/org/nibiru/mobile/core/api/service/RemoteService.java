package org.nibiru.mobile.core.api.service;

import com.google.common.net.MediaType;

import org.nibiru.async.core.api.promise.Promise;
import org.nibiru.mobile.core.api.http.HttpException;
import org.nibiru.mobile.core.api.http.HttpMethod;

import javax.annotation.Nullable;

/**
 * A remote service.
 */
public interface RemoteService {
    /**
     * Invokes a method on a remote service using POST/JSON content type.
     *
     * @param method        The name of the method
     * @param requestDto    The DTO used for creating request data
     * @param responseClass The expected response class
     */
    <T> Promise<T, HttpException> invoke(String method,
                                         @Nullable Object requestDto,
                                         Class<T> responseClass);

    /**
     * Invokes a method on a remote service allowing full parameterization;
     *
     * @param method        The name of the method
     * @param requestDto    The DTO used for creating request data
     * @param responseClass The expected response class
     * @param httpMethod The HTTP method
     * @param mediaType The media type for request and response
     */
    <T> Promise<T, HttpException> invoke(String method,
                                         @Nullable Object requestDto,
                                         Class<T> responseClass,
                                         HttpMethod httpMethod,
                                         MediaType mediaType);
}
