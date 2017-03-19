package org.nibiru.mobile.core.api.service;

/**
 * Factory for basic (just string) push services.
 */
public interface BasicPushServiceFactory {
    PushService<String> create(String url);
}
