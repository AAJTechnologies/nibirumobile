package org.nibiru.mobile.wp.app;

import org.nibiru.async.core.api.promise.Deferred;
import org.nibiru.async.core.api.promise.Promise;
import org.nibiru.mobile.core.api.app.Bootstrap;
import org.nibiru.mobile.core.api.app.EntryPoint;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class WindowsPhoneBootstrap implements Bootstrap {
    private final EntryPoint entryPoint;

    @Inject
    public WindowsPhoneBootstrap(EntryPoint entryPoint) {
        this.entryPoint = checkNotNull(entryPoint);
    }

    @Override
    public Promise<Void, Exception> onBootstrap() {
        entryPoint.onApplicationStart();
        return Deferred.<Void, Exception>defer().promise();
    }
}
