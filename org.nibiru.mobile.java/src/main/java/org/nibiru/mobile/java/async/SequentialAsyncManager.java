package org.nibiru.mobile.java.async;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import org.nibiru.mobile.core.api.async.Deferred;
import org.nibiru.mobile.core.api.async.Promise;
import org.nibiru.mobile.core.api.ui.Looper;

import com.google.common.base.Supplier;

public class SequentialAsyncManager extends BaseAsyncmanager {
    @Inject
    public SequentialAsyncManager(Looper looper) {
        super(looper);
    }

    @Override
    public <T, E extends Exception> Promise<T, E> runAsync(Supplier<T> callable) {
        checkNotNull(callable);
        Deferred<T, E> deferred = Deferred.defer();
        runCallable(callable, deferred);
        return deferred.promise();
    }
}
