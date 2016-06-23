package ar.com.oxen.nibiru.mobile.core.api.http;

import ar.com.oxen.nibiru.mobile.core.api.async.Callback;

/**
 * Manager for performing requests over HTTP.
 * 
 */
public interface HttpManager {
	String CONTENT_TYPE_HEADER = "Content-Type";
	String ACCEPT_HEADER = "Accept";
	String APPLICATION_JSON_MIME = "application/json";

	/**
	 * Sends a POST request.
	 * 
	 * @param url
	 *            The URL
	 * @param callback
	 *            A callback for handling the processed response
	 * @param httpCallback
	 *            The callback for message processing
	 */
	<T> void send(String url, final Callback<T> callback,
			final HttpCallback<T> httpCallback);
}
