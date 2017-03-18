package org.nibiru.mobile.teavm.service;

import com.google.common.collect.Sets;

import org.nibiru.async.core.api.promise.Deferred;
import org.nibiru.async.core.api.promise.Promise;
import org.nibiru.mobile.core.api.common.Consumer;
import org.nibiru.mobile.core.api.service.PushService;
import org.nibiru.model.core.api.Registration;
import org.teavm.jso.JSBody;

import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * WebSocket implementation for {@link PushService}.
 */
public class WebSocketPushService implements PushService<String> {
    private final String url;
    private final Set<Consumer<String>> callbacks;

    public WebSocketPushService(String url) {
        this.url = checkNotNull(url);
        callbacks = Sets.newHashSet();
    }


    @Override
    public Promise<Void, Exception> connect() {
        Deferred<Void, Exception> deferred = Deferred.defer();
        connect(url);
        deferred.resolve(null);
        return deferred.promise();
    }

    @JSBody(params = {"message"}, script = "var $this = this;\n" +
            "this.ws.close();")
    @Override
    public native void disconnect();

    @Override
    public void send(String message) {
        checkNotNull(message);
        sendNative(message);
    }

    @Override
    public Registration receive(Consumer<String> callback) {
        checkNotNull(callback);
        callbacks.add(callback);
        return () -> callbacks.remove(callback);
    }

    @JSBody(params = {"message"}, script = "this.ws.send(message);")
    private native void sendNative(String message);

    @JSBody(params = {"url"}, script = "if ('WebSocket' in Window) {\n" +
            "            this.ws = new WebSocket(url);\n" +
            "        } else if ('MozWebSocket' in $wnd) {\n" +
            "            this.ws = new MozWebSocket(url);\n" +
            "        } else {\n" +
            "            alert('WebSocket is not supported by this browser.');\n" +
            "            return;\n" +
            "        }\n" +
            "        this.ws.onmessage = function (event) {\n" +
            "            javaMethods.get('org.nibiru.mobile.teavm.service.WebSocketPushService::onMessage(Ljava/lang/String;)').invoke(event.data);\n" +
            "        };")
    private native void connect(String url);

    private void onMessage(String message) {
        for (Consumer<String> callback : callbacks) {
            callback.accept(message);
        }
    }
}
