package ar.com.oxen.nibiru.mobile.core.api.common;

/**
 * Anything that has an identifier.
 * 
 * @param <T>
 *            The identifier type.
 */
public interface Identifiable<T> {
	/**
	 * @return The identifier
	 */
	T getId();
}
