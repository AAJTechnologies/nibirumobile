package org.nibiru.mobile.gwt.data;


import org.nibiru.async.core.api.loop.Looper;
import org.nibiru.async.core.api.promise.Deferred;
import org.nibiru.async.core.api.promise.Promise;
import org.nibiru.mobile.gwt.app.DatabaseBootstrap;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;


public class DummyDatabaseBootstrap implements DatabaseBootstrap {
    private final Looper looper;

    @Inject
    public DummyDatabaseBootstrap(Looper looper) {
        this.looper = checkNotNull(looper);
    }

    @Override
    public Promise<Void, Exception> createDatabase() {
        Deferred<Void, Exception> deferred = Deferred.defer();
        looper.post(() -> deferred.resolve(null));
        return deferred.promise();
    }
}
