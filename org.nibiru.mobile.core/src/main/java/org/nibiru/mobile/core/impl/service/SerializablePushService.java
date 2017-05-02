package org.nibiru.mobile.core.impl.service;

import org.nibiru.async.core.api.promise.Promise;
import org.nibiru.mobile.core.api.common.Consumer;
import org.nibiru.mobile.core.api.serializer.Serializer;
import org.nibiru.mobile.core.api.service.PushService;
import org.nibiru.model.core.api.Registration;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Generic push service that uses serialization .
 */
public class SerializablePushService<T> implements PushService<T> {
    private final PushService<String> pushService;
    private final Serializer serializer;
    private final Class<T> entityClass;

    public SerializablePushService(PushService<String> pushService,
                                   Serializer serializer,
                                   Class<T> entityClass) {
        this.pushService = checkNotNull(pushService);
        this.serializer = checkNotNull(serializer);
        this.entityClass = checkNotNull(entityClass);
    }

    @Override
    public Promise<Void, Exception> connect() {
        return pushService.connect();
    }

    @Override
    public void disconnect() {
        pushService.disconnect();
    }

    @Override
    public void send(T message) {
        pushService.send(serializer.serialize(message));
    }

    @Override
    public Registration receive(Consumer<T> callback) {
        return pushService.receive((String message) -> {
            callback.accept(serializer.deserialize(message, entityClass));
        });
    }
}
