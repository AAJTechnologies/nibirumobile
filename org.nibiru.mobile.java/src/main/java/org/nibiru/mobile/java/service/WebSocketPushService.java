package org.nibiru.mobile.java.service;

import com.google.common.collect.Sets;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketClient;
import org.eclipse.jetty.websocket.WebSocketClientFactory;
import org.nibiru.async.core.api.loop.Looper;
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
    private final Set<Consumer<String>> callbacks;
    private WebSocket.Connection connection;

    public WebSocketPushService(String url, Looper looper) {
        checkNotNull(url);
        checkNotNull(looper);
        callbacks = Sets.newHashSet();
        new Thread(() -> connect(url, looper)).start();
    }

    @Override
    public void send(String message) {
        checkNotNull(message);
        new Thread(() -> {
            try {
                connection.sendMessage(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    @Override
    public Registration receive(Consumer<String> callback) {
        checkNotNull(callback);
        callbacks.add(callback);
        return () -> callbacks.remove(callback);
    }

    private void connect(String url, Looper looper) {
        try {
            WebSocketClientFactory factory = new WebSocketClientFactory();
            factory.start();

            WebSocketClient client = factory.newWebSocketClient();
            connection = client.open(new URI(url),
                    new WebSocket.OnTextMessage() {
                        public void onOpen(Connection connection) {
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
            throw new RuntimeException(e);
        }
    }
}
