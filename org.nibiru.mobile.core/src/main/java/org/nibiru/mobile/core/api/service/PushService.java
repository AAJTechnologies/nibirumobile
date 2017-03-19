package org.nibiru.mobile.core.api.service;

import org.nibiru.mobile.core.api.common.Consumer;
import org.nibiru.model.core.api.Registration;

/**
 * A push service.
 *
 * @param <T> The message data type.
 */
// TODO: Should this have a disconnect method?
public interface PushService<T> {
    /**
     * Sends a message.
     *
     * @param message The message.
     */
    void send(T message);

    /**
     * Receives a message
     * @param callback
     */
    Registration receive(Consumer<T> callback);
}
