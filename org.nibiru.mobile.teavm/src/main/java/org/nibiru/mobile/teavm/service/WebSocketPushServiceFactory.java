package org.nibiru.mobile.teavm.service;

import org.nibiru.mobile.core.api.service.BasicPushServiceFactory;
import org.nibiru.mobile.core.api.service.PushService;

import javax.inject.Inject;

/**
 * Factory for {@link WebSocketPushService}.
 */
public class WebSocketPushServiceFactory implements BasicPushServiceFactory {
    @Inject
    public WebSocketPushServiceFactory() {
    }

    @Override
    public PushService<String> create(String url) {
        return new WebSocketPushService(url);
    }
}
