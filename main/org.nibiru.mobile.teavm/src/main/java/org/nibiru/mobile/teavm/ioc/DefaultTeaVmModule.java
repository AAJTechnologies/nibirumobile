package org.nibiru.mobile.teavm.ioc;

import org.nibiru.mobile.core.api.app.Bootstrap;
import org.nibiru.mobile.core.api.http.HttpManager;
import org.nibiru.mobile.core.api.preferences.Preferences;
import org.nibiru.mobile.core.api.service.BasicPushServiceFactory;
import org.nibiru.mobile.core.api.ui.AlertManager;
import org.nibiru.mobile.core.api.ui.DisplayInfo;
import org.nibiru.mobile.core.api.ui.Looper;
import org.nibiru.mobile.core.api.ui.place.PlaceManager;
import org.nibiru.mobile.teavm.app.TeaVmBootstrap;
import org.nibiru.mobile.teavm.http.XMLHttpRequestHttpManager;
import org.nibiru.mobile.teavm.preferences.CookiesPreferences;
import org.nibiru.mobile.teavm.service.WebSocketPushServiceFactory;
import org.nibiru.mobile.teavm.ui.TeaVmAlertManager;
import org.nibiru.mobile.teavm.ui.TeaVmDisplayInfo;
import org.nibiru.mobile.teavm.ui.TeaVmLooper;
import org.nibiru.mobile.teavm.ui.place.TeaVmPlaceManager;
import org.teavm.jso.ajax.XMLHttpRequest;
import org.teavm.jso.browser.Window;
import org.teavm.jso.dom.html.HTMLDocument;

import dagger.Module;
import dagger.Provides;

@Module
public class DefaultTeaVmModule {
    @Provides
    public Preferences getPreferences(CookiesPreferences manager) {
        return manager;
    }

    // TODO: Try the Guava event bus
//	@Provides
//	@Singleton
//	public EventBus getEventBus(GwtEventBus eventBus) {
//		return eventBus;
//	}

    @Provides
    public HttpManager getHttpManager(XMLHttpRequestHttpManager manager) {
        return manager;
    }

    @Provides
    public DisplayInfo getDisplayInfo(TeaVmDisplayInfo displayInfo) {
        return displayInfo;
    }

    @Provides
    public BasicPushServiceFactory getBasicPushServiceFactory(WebSocketPushServiceFactory webSocketPushServiceFactory) {
        return webSocketPushServiceFactory;
    }

    @Provides
    public Bootstrap getBootstrap(TeaVmBootstrap bootstrap) {
        return bootstrap;
    }

    @Provides
    public PlaceManager getPlaceManager(TeaVmPlaceManager manager) {
        return manager;
    }

    @Provides
    public AlertManager getAlertManager(TeaVmAlertManager manager) {
        return manager;
    }

    @Provides
    public Looper getLooper(TeaVmLooper looper) {
        return looper;
    }

    @Provides
    public Window getWindow() {
        return Window.current();
    }

    @Provides
    public HTMLDocument getHTMLDocument(Window window) {
        return window.getDocument();
    }

    @Provides
    public XMLHttpRequest getXMLHttpRequest() {
        return XMLHttpRequest.create();
    }

}
