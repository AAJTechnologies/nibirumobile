package ar.com.oxen.nibiru.mobile.core.api.serializer;

/**
 * Interface for serializing from/to object to/from String.
 */
public interface Serializer {
	/**
	 * Converts from object to string.
	 * 
	 * @param object
	 *            The object
	 * @return The string
	 */
	String serialize(Object object);

	/**
	 * Converts from string to object
	 * 
	 * @param data
	 *            The string
	 * @param returnType
	 *            The expected return type
	 * @return The object
	 */
	<T> T deserialize(String data, Class<T> returnType);

	/**
	 * @return The encoding (json, xml, etc.)
	 */
	String getEncoding();
}
