package org.nibiru.mobile.java.service;

import com.google.common.base.Preconditions;

import org.nibiru.mobile.core.api.service.BasicPushServiceFactory;
import org.nibiru.mobile.core.api.service.PushService;
import org.nibiru.mobile.core.api.ui.Looper;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Factory for {@link WebSocketPushService}.
 */
public class WebSocketPushServiceFactory implements BasicPushServiceFactory {
    private final Looper looper;

    @Inject
    public WebSocketPushServiceFactory(Looper looper) {
        this.looper = checkNotNull(looper);
    }

    @Override
    public PushService<String> create(String url) {
        return new WebSocketPushService(url, looper);
    }
}
