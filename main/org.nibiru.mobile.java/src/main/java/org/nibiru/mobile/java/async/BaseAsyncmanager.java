package org.nibiru.mobile.java.async;

import static com.google.common.base.Preconditions.checkNotNull;

import org.nibiru.mobile.core.api.async.Deferred;
import org.nibiru.mobile.core.api.ui.Looper;

import com.google.common.base.Supplier;

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
