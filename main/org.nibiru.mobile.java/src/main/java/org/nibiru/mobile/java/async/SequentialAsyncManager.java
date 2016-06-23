package org.nibiru.mobile.java.async;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import org.nibiru.mobile.core.api.async.Callback;
import org.nibiru.mobile.core.api.ui.Looper;

import com.google.common.base.Supplier;

public class SequentialAsyncManager extends BaseAsyncmanager {
	@Inject
	public SequentialAsyncManager(Looper looper) {
		super(looper);
	}

	@Override
	public <T> void runAsync(Supplier<T> callable, Callback<T> callback) {
		checkNotNull(callable);
		checkNotNull(callback);
		runCallable(callable, callback);
	}
}
