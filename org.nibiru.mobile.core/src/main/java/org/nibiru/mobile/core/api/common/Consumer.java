package org.nibiru.mobile.core.api.common;

/**
 * A consumer used for functional programming.
 * <p>
 * The JDK equivalent class is only available at 1.8.
 */
public interface Consumer<T> {
    void accept(T result);
}
