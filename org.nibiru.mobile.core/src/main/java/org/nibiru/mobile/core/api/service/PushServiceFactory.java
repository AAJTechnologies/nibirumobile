package org.nibiru.mobile.core.api.service;

/**
 * Factory for push services.
 */
public interface PushServiceFactory {
    PushService create(String url);
}
