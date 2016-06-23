package ar.com.oxen.nibiru.mobile.core.api.http;

/**
 * Callback for processing HTTP messages.
 * 
 * @param <T>
 *            The expected type for the response after parsing it
 */
public interface HttpCallback<T> {
	/**
	 * Builds the request message, usually from some contextual data.
	 * 
	 * @return A string with message body
	 */
	String buildRequest();

	/**
	 * Parses the response text in order to create an object representing the
	 * response.
	 * 
	 * @param responseMessage
	 *            The response body
	 * @return The object resultin from parsing process
	 */
	T parseResponse(String responseMessage);
}
