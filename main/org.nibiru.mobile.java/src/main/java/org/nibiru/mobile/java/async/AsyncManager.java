package org.nibiru.mobile.java.async;

import org.nibiru.mobile.core.api.async.Callback;

import com.google.common.base.Supplier;

/**
 * Component responsible for performing async tasks (HTTP calls for example).
 * <p>
 * Some frameworks will require this to be performed in a separated thread,
 * while others require that such task to be performed on the same thread of the
 * caller.
 */
public interface AsyncManager {
	<T> void runAsync(Supplier<T> callable, Callback<T> callback);
}