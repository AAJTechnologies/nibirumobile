package org.nibiru.mobile.core.api.common;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Anything that can be configured.
 *
 * @param <C> The specific configurable type, for using in method chaining.
 */
public interface Configurable<C, V> {
    /**
     * Reads a parameter.
     *
     * @param key The parameter key
     * @return The parameter value
     */
    <T extends V> T getParameter(@Nonnull String key);

    /**
     * Reads a parameter.
     *
     * @param key The parameter key
     * @return The parameter value
     */
    default <T extends V> T getParameter(@Nonnull Enum<?> key) {
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
    C addParameter(@Nonnull String key,
                   @Nullable V value);

    /**
     * Add/sets a parameter.
     *
     * @param key   The parameter key
     * @param value The parameter value
     * @return The same configurable instance, for method chaining.
     */
    default C addParameter(@Nonnull Enum<?> key,
                           @Nullable V value) {
        checkNotNull(key);
        return addParameter(key.toString(), value);
    }
}
