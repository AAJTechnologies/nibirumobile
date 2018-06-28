package org.nibiru.mobile.gwt.service;

import org.nibiru.mobile.core.api.service.PushServiceFactory;
import org.nibiru.mobile.core.api.service.PushService;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Factory for {@link WebSocketPushService}.
 */
public class WebSocketPushServiceFactory implements PushServiceFactory {
    @Inject
    public WebSocketPushServiceFactory() {
    }

    @Override
    public PushService create(String url) {
        checkNotNull(url);
        return new WebSocketPushService(url);
    }
}
