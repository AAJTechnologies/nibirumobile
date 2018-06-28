package org.nibiru.mobile.core.impl.app;

import org.nibiru.async.core.api.promise.Promise;
import org.nibiru.mobile.core.api.app.Bootstrap;

import static com.google.common.base.Preconditions.checkNotNull;

public class CompoundBootsrap implements Bootstrap {
    private final Iterable<Bootstrap> bootsraps;

    public CompoundBootsrap(Iterable<Bootstrap> bootsraps) {
        this.bootsraps = checkNotNull(bootsraps);
    }

    @Override
    public Promise<Void, Exception> onBootstrap() {
        Promise<Void, Exception> last = null;
        for (Bootstrap bootsrap : bootsraps) {
            if (last != null) {
                last = last.then((dummy) -> bootsrap.onBootstrap());
            } else {
                last = bootsrap.onBootstrap();
            }
        }
        return last;
    }
}
