package org.nibiru.mobile.core.api.serializer;

import javax.annotation.Nullable;

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
	String serialize(@Nullable Object object);

	/**
	 * Converts from string to object
	 *
	 * @param data
	 *            The string
	 * @param returnType
	 *            The expected return type
	 * @return The object
	 */
	<T> T deserialize(@Nullable String data, Class<T> returnType);

	/**
	 * Converts from string to object
	 *
	 * @param data
	 *            The string
	 * @param returnType
	 *            The expected return type type literal
	 * @return The object
	 */
	<T> T deserialize(@Nullable String data, TypeLiteral<T> returnType);
}
