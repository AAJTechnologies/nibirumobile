package org.nibiru.mobile.java.service;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketClient;
import org.eclipse.jetty.websocket.WebSocketClientFactory;
import org.nibiru.async.core.api.loop.Looper;
import org.nibiru.async.core.api.promise.Deferred;
import org.nibiru.async.core.api.promise.Promise;
import org.nibiru.mobile.core.api.service.PushService;
import org.nibiru.model.core.api.Value;
import org.nibiru.model.core.impl.java.JavaType;
import org.nibiru.model.core.impl.java.JavaValue;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * WebSocket implementation for {@link PushService}.
 */
public class WebSocketPushService implements PushService {
    private final String url;
    private final Looper looper;
    private final Value<String> value;
    private WebSocket.Connection connection;

    public WebSocketPushService(String url, Looper looper) {
        this.url = checkNotNull(url);
        this.looper = checkNotNull(looper);
        value = JavaValue.of(JavaType.STRING);
        value.addObserver(() -> {
            if (connection != null && connection.isOpen()) {
                new Thread(() -> {
                    try {
                        connection.sendMessage(value.get());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }
        });
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
                                    value.set(message);
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
    public Value<String> getValue() {
        return value;
    }
}
