package org.nibiru.mobile.java.service;

import org.nibiru.async.core.api.loop.Looper;
import org.nibiru.mobile.core.api.service.PushServiceFactory;
import org.nibiru.mobile.core.api.service.PushService;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Factory for {@link WebSocketPushService}.
 */
public class WebSocketPushServiceFactory implements PushServiceFactory {
    private final Looper looper;

    @Inject
    public WebSocketPushServiceFactory(Looper looper) {
        this.looper = checkNotNull(looper);
    }

    @Override
    public PushService create(String url) {
        checkNotNull(url);
        return new WebSocketPushService(url, looper);
    }
}
