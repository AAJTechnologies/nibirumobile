package org.nibiru.mobile.core.api.service;

import org.nibiru.async.core.api.promise.Promise;
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
     * Connects to the service.
     *
     * @return A promise to be called when the connection is established.
     */
    Promise<Void, Exception> connect();

    /**
     * Disconects from the service.
     */
    void disconnect();

    /**
     * Sends a message.
     *
     * @param message The message.
     */
    void send(T message);

    /**
     * Receives a message
     *
     * @param callback
     */
    Registration receive(Consumer<T> callback);
}
