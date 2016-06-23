package ar.com.oxen.nibiru.mobile.core.api.common;

/**
 * Anything that can be configured.
 * 
 * @param <C>
 *            The specific configurable type, for using in method chaining.
 */
public interface Configurable<C> {
	/**
	 * Reads a parameter.
	 * 
	 * @param key
	 *            The parameter key
	 * @return The parameter value
	 */
	<T> T getParameter(String key);

	/**
	 * Reads a parameter.
	 * 
	 * @param key
	 *            The parameter key
	 * @return The parameter value
	 */
	<T> T getParameter(Enum<?> key);

	/**
	 * Add/sets a parameter.
	 * 
	 * @param key
	 *            The parameter key
	 * @param The
	 *            parameter value
	 * @return The same configurable instance, for method chaining.
	 */
	C addParameter(String key, Object value);

	/**
	 * Add/sets a parameter.
	 * 
	 * @param key
	 *            The parameter key
	 * @param The
	 *            parameter value
	 * @return The same configurable instance, for method chaining.
	 */
	C addParameter(Enum<?> key, Object value);
}
