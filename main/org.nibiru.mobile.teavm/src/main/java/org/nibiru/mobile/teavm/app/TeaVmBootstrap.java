package org.nibiru.mobile.teavm.app;

import org.nibiru.mobile.core.api.app.Bootstrap;
import org.nibiru.mobile.core.api.app.EntryPoint;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class TeaVmBootstrap implements Bootstrap {
    private final EntryPoint entryPoint;

    @Inject
    public TeaVmBootstrap(EntryPoint entryPoint) {
        this.entryPoint = checkNotNull(entryPoint);
    }

    @Override
    public void onBootstrap() {
        entryPoint.onApplicationStart();
    }
}
