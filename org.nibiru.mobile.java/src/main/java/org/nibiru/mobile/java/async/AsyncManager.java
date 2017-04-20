package org.nibiru.mobile.java.async;

import com.google.common.base.Supplier;

import org.nibiru.async.core.api.promise.Promise;


/**
 * Component responsible for performing async tasks (HTTP calls for example).
 * <p>
 * Some frameworks will require this to be performed in a separated thread,
 * while others require that such task to be performed on the same thread of the
 * caller.
 */
public interface AsyncManager {
	<T, E extends Exception> Promise<T, E> runAsync(Supplier<T> callable);
}