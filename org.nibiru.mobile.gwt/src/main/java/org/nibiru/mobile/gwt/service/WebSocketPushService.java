package org.nibiru.mobile.gwt.service;

import com.google.common.collect.Sets;

import org.nibiru.async.core.api.promise.Deferred;
import org.nibiru.async.core.api.promise.Promise;
import org.nibiru.mobile.core.api.common.Consumer;
import org.nibiru.mobile.core.api.service.PushService;
import org.nibiru.model.core.api.Registration;
import org.nibiru.model.core.api.Value;
import org.nibiru.model.core.impl.java.JavaType;
import org.nibiru.model.core.impl.java.JavaValue;

import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * WebSocket implementation for {@link PushService}.
 */
public class WebSocketPushService implements PushService {
    private final String url;
    private final Value<String> value;

    public WebSocketPushService(String url) {
        this.url = checkNotNull(url);
        value = JavaValue.of(JavaType.STRING);
        value.addObserver(() -> sendNative(value.get()));
    }

    @Override
    public Promise<Void, Exception> connect() {
        Deferred<Void, Exception> deferred = Deferred.defer();
        connect(url);
        deferred.resolve(null);
        return deferred.promise();
    }

    private native void connect(String url) /*-{
        var $this = this;
        if ('WebSocket' in $wnd) {
            $this.ws = new WebSocket(url);
        } else if ('MozWebSocket' in $wnd) {
            $this.ws = new MozWebSocket(url);
        } else {
            alert('WebSocket is not supported by this browser.');
            return;
        }
        this.ws.onmessage = function (event) {
            $this.@org.nibiru.mobile.gwt.service.WebSocketPushService::onMessage(Ljava/lang/String;)(event.data);
        };
    }-*/;

    @Override
    public native void disconnect() /*-{
        var $this = this;
        this.ws.close();
    }-*/;

    @Override
    public Value<String> getValue() {
        return value;
    }

    private native void sendNative(String message) /*-{
       this.ws.send(message);
    }-*/;

    private void onMessage(String message) {
        value.set(message);
    }
}
