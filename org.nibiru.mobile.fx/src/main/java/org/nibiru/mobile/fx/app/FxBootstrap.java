package org.nibiru.mobile.fx.app;

import org.nibiru.async.core.api.promise.Deferred;
import org.nibiru.async.core.api.promise.Promise;
import org.nibiru.mobile.core.api.app.Bootstrap;
import org.nibiru.mobile.core.api.app.EntryPoint;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class FxBootstrap implements Bootstrap {
    private final EntryPoint entryPoint;

    @Inject
    public FxBootstrap(EntryPoint entryPoint) {
        this.entryPoint = checkNotNull(entryPoint);
    }

    @Override
    public Promise<Void, Exception> onBootstrap() {
        Deferred<Void, Exception> deferred = Deferred.defer();
        entryPoint.onApplicationStart();
        deferred.resolve(null);
        return deferred.promise();
    }
}
