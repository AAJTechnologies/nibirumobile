package org.nibiru.mobile.java.async;

import com.google.common.base.Supplier;

import org.nibiru.async.core.api.loop.Looper;
import org.nibiru.async.core.api.promise.Deferred;

import static com.google.common.base.Preconditions.checkNotNull;

abstract class BaseAsyncmanager implements AsyncManager {
	private final Looper looper;

	public BaseAsyncmanager(Looper looper) {
		this.looper = checkNotNull(looper);
	}

	<V, E extends Exception> void runCallable(Supplier<V> callable,
                                              Deferred<V, E> deferred) {
		try {
			V result = callable.get();
			looper.post(() -> deferred.resolve(result));
		} catch (Exception e) {
			looper.post(() -> deferred.reject((E) e));
		}
	}
}
