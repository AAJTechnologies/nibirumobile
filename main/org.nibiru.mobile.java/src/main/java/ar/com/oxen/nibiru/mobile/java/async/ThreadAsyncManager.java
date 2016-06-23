package ar.com.oxen.nibiru.mobile.java.async;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import ar.com.oxen.nibiru.mobile.core.api.async.Callback;
import ar.com.oxen.nibiru.mobile.core.api.ui.Looper;

import com.google.common.base.Supplier;

public class ThreadAsyncManager extends BaseAsyncmanager {
	@Inject
	public ThreadAsyncManager(Looper looper) {
		super(looper);
	}
	
	@Override
	public <T> void runAsync(final Supplier<T> callable, final Callback<T> callback) {
		checkNotNull(callable);
		checkNotNull(callback);
		new Thread(new Runnable() {
			@Override
			public void run() {
				runCallable(callable, callback);
			}
		}).start();
	}
}
