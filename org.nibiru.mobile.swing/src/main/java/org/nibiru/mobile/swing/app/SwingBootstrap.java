package org.nibiru.mobile.swing.app;

import org.nibiru.async.core.api.loop.Looper;
import org.nibiru.async.core.api.promise.Deferred;
import org.nibiru.async.core.api.promise.Promise;
import org.nibiru.mobile.core.api.app.Bootstrap;
import org.nibiru.mobile.core.api.app.EntryPoint;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class SwingBootstrap implements Bootstrap {
    private final EntryPoint entryPoint;
    private final Looper looper;

    @Inject
    public SwingBootstrap(EntryPoint entryPoint,
                          Looper looper) {
        this.entryPoint = checkNotNull(entryPoint);
        this.looper = checkNotNull(looper);
    }

    @Override
    public Promise<Void, Exception> onBootstrap() {
        Deferred<Void, Exception> deferred = Deferred.defer();
        looper.post(()->{
            try {
                entryPoint.onApplicationStart();
                deferred.resolve(null);
            } catch (Exception e) {
                deferred.reject(e);
            }
        });
        return deferred.promise();
    }
}
