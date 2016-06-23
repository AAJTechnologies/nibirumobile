package org.nibiru.mobile.core.api.service;

import javax.annotation.Nullable;

import org.nibiru.mobile.core.api.async.Callback;

/**
 * A remote service.
 */
public interface RemoteService {
	/**
	 * Invokes a method on a remote service
	 * 
	 * @param method
	 *            The name of the method
	 * @param requestDto
	 *            The DTO used for creating request data
	 * @param responseClass
	 *            The expected response class
	 * @param callback
	 *            A callback for receiving the response
	 */
	<T> void invoke(String method, @Nullable Object requestDto, Class<T> responseClass,
			Callback<T> callback);
}
