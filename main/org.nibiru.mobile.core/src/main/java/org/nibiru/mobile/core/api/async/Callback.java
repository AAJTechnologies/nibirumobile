package org.nibiru.mobile.core.api.async;

/**
 * A callback for any asynchronous call that can result in success or failure.
 * 
 * @param <T>
 *            The type returned on success
 */
public interface Callback<T> {

	/**
	 * Called when an asynchronous call fails to complete normally.
	 * 
	 * @param reason
	 *            failure encountered
	 */
	void onFailure(Exception reason);

	/**
	 * Called when an asynchronous call completes successfully.
	 * 
	 * @param result
	 *            the value returned
	 */
	void onSuccess(T result);
}
