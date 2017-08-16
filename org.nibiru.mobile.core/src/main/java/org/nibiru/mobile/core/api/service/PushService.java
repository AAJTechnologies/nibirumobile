package org.nibiru.mobile.core.api.service;

import org.nibiru.async.core.api.promise.Promise;
import org.nibiru.mobile.core.api.common.Consumer;
import org.nibiru.model.core.api.Registration;
import org.nibiru.model.core.api.Value;

/**
 * A push service.
 */
public interface PushService {
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
     * Value for sending/receving data.
     */
    Value<String> getValue();
}
