package org.nibiru.mobile.java.async;

import com.google.common.base.Supplier;

import org.nibiru.async.core.api.loop.Looper;
import org.nibiru.async.core.api.promise.Deferred;
import org.nibiru.async.core.api.promise.Promise;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class ThreadAsyncManager extends BaseAsyncmanager {
	@Inject
	public ThreadAsyncManager(Looper looper) {
		super(looper);
	}
	
	@Override
	public <T, E extends Exception> Promise<T, E> runAsync(Supplier<T> callable) {
		checkNotNull(callable);

		Deferred <T, E> deferred = Deferred.defer();
		new Thread(() -> {
            runCallable(callable, deferred);
        }).start();
		return deferred.promise();
	}
}
