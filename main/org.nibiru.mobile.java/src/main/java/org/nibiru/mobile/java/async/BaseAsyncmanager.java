package org.nibiru.mobile.java.async;

import static com.google.common.base.Preconditions.checkNotNull;

import org.nibiru.mobile.core.api.async.Callback;
import org.nibiru.mobile.core.api.ui.Looper;

import com.google.common.base.Supplier;

abstract class BaseAsyncmanager implements AsyncManager {
	private final Looper looper;

	public BaseAsyncmanager(Looper looper) {
		this.looper = checkNotNull(looper);
	}

	<T> void runCallable(Supplier<T> callable, final Callback<T> callback) {
		try {
			final T result = callable.get();
			looper.post(new Runnable() {
				@Override
				public void run() {
					callback.onSuccess(result);
				}
			});
		} catch (final Exception e) {
			looper.post(new Runnable() {
				@Override
				public void run() {
					callback.onFailure(e);
				}
			});
		}
	}
}
