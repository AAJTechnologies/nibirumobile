package org.nibiru.mobile.core.api.service;

import org.nibiru.mobile.core.api.async.Promise;
import org.nibiru.mobile.core.api.http.HttpException;

import javax.annotation.Nullable;

/**
 * A remote service.
 */
public interface RemoteService {
    /**
     * Invokes a method on a remote service
     *
     * @param method        The name of the method
     * @param requestDto    The DTO used for creating request data
     * @param responseClass The expected response class
     */
    <T> Promise<T, HttpException> invoke(String method, @Nullable Object requestDto, Class<T> responseClass);
}
