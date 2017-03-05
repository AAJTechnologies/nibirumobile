package org.nibiru.mobile.teavm.ui;

import org.nibiru.mobile.core.api.ui.Looper;
import org.teavm.jso.browser.Window;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class TeaVmLooper implements Looper {
    @Inject
    public TeaVmLooper() {
    }

    @Override
    public void post(final Runnable runnable) {
        checkNotNull(runnable);
        Window.setTimeout(() -> {
            runnable.run();
        }, 0);
    }
}
