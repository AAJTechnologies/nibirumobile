package org.nibiru.mobile.java.service;

import com.google.common.collect.Sets;

import org.java_websocket.client.DefaultWebSocketClientFactory;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.nibiru.mobile.core.api.common.Consumer;
import org.nibiru.mobile.core.api.service.PushService;
import org.nibiru.mobile.core.api.ui.Looper;
import org.nibiru.model.core.api.Registration;

import java.net.URI;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * WebSocket implementation for {@link PushService}.
 */
public class WebSocketPushService implements PushService<String> {
    private final WebSocketClient client;
    private final Set<Consumer<String>> callbacks;

    public WebSocketPushService(String url, Looper looper) {
        checkNotNull(url);
        checkNotNull(looper);
        try {
            callbacks = Sets.newHashSet();
            client = new WebSocketClient(URI.create(url)) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    System.out.println("");
                }

                @Override
                public void onMessage(String message) {
                    looper.post(() -> {
                        for (Consumer<String> callback : callbacks) {
                            callback.accept(message);
                        }
                    });
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {

                }

                @Override
                public void onError(Exception ex) {
                    System.out.println(ex);
                }
            };
            client.connectBlocking();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void send(String message) {
        client.send(message);
    }

    @Override
    public Registration receive(Consumer<String> callback) {
        callbacks.add(callback);
        return () -> callbacks.remove(callback);
    }
}
