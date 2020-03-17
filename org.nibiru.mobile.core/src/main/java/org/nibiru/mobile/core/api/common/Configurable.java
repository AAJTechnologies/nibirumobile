package org.nibiru.mobile.core.api.common;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Anything that can be configured.
 *
 * @param <C> The specific configurable type, for using in method chaining.
 */
public interface Configurable<C> {
    /**
     * Reads a parameter.
     *
     * @param key The parameter key
     * @return The parameter value
     */
    <T> T getParameter(String key);

    /**
     * Reads a parameter.
     *
     * @param key The parameter key
     * @return The parameter value
     */
    default <T> T getParameter(Enum<?> key) {
        checkNotNull(key);
        return getParameter(key.toString());
    }

    /**
     * Add/sets a parameter.
     *
     * @param key   The parameter key
     * @param value The parameter value
     * @return The same configurable instance, for method chaining.
     */
    C addParameter(String key, @Nullable Object value);

    /**
     * Add/sets a parameter.
     *
     * @param key   The parameter key
     * @param value The parameter value
     * @return The same configurable instance, for method chaining.
     */
    default C addParameter(Enum<?> key, @Nullable Object value) {
        checkNotNull(key);
        return addParameter(key.toString(), value);
    }
}
