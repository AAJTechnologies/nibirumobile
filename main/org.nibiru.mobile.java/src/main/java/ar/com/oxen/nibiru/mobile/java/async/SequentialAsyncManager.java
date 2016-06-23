package ar.com.oxen.nibiru.mobile.java.async;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import com.google.common.base.Supplier;

import ar.com.oxen.nibiru.mobile.core.api.async.Callback;
import ar.com.oxen.nibiru.mobile.core.api.ui.Looper;

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
