package org.nibiru.mobile.java.service;

import com.google.common.collect.Sets;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketClient;
import org.eclipse.jetty.websocket.WebSocketClientFactory;
import org.nibiru.async.core.api.loop.Looper;
import org.nibiru.async.core.api.promise.Deferred;
import org.nibiru.async.core.api.promise.Promise;
import org.nibiru.mobile.core.api.common.Consumer;
import org.nibiru.mobile.core.api.service.PushService;
import org.nibiru.model.core.api.Registration;

import java.io.IOException;
import java.net.URI;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * WebSocket implementation for {@link PushService}.
 */
public class WebSocketPushService implements PushService<String> {
    private final String url;
    private final Looper looper;
    private final Set<Consumer<String>> callbacks;
    private WebSocket.Connection connection;

    public WebSocketPushService(String url, Looper looper) {
        this.url = checkNotNull(url);
        this.looper = checkNotNull(looper);
        callbacks = Sets.newHashSet();
    }

    @Override
    public Promise<Void, Exception> connect() {
        Deferred<Void, Exception> deferred = Deferred.defer();
        new Thread(() -> {
            WebSocketClientFactory factory = new WebSocketClientFactory();
            try {
                factory.start();
                WebSocketClient client = factory.newWebSocketClient();
                connection = client.open(new URI(url),
                        new WebSocket.OnTextMessage() {
                            public void onOpen(Connection connection) {
                                looper.post(() -> {
                                    deferred.resolve(null);
                                });
                            }

                            public void onClose(int closeCode, String message) {
                            }

                            public void onMessage(String message) {
                                looper.post(() -> {
                                    for (Consumer<String> callback : callbacks) {
                                        callback.accept(message);
                                    }
                                });
                            }
                        }).get(5, TimeUnit.SECONDS);
            } catch (Exception e) {
                looper.post(() -> {
                    deferred.reject(e);
                });
            }
        }).start();
        return deferred.promise();
    }

    @Override
    public void disconnect() {
        if (connection != null) {
            new Thread(() -> connection.close()).start();
        }
    }

    @Override
    public void send(String message) {
        checkNotNull(message);
        if (connection != null && connection.isOpen()) {
            new Thread(() -> {
                try {
                    connection.sendMessage(message);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }

    @Override
    public Registration receive(Consumer<String> callback) {
        checkNotNull(callback);
        callbacks.add(callback);
        return () -> callbacks.remove(callback);
    }
}
