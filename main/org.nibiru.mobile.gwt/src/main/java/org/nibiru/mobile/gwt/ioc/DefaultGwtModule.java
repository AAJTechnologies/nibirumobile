package org.nibiru.mobile.gwt.ioc;

import javax.inject.Singleton;

import org.nibiru.mobile.core.api.event.EventBus;
import org.nibiru.mobile.core.api.http.HttpManager;
import org.nibiru.mobile.core.api.preferences.Preferences;
import org.nibiru.mobile.core.api.service.BasicPushServiceFactory;
import org.nibiru.mobile.core.api.ui.DisplayInfo;
import org.nibiru.mobile.gwt.event.GwtEventBus;
import org.nibiru.mobile.gwt.http.RequestBuilderHttpManager;
import org.nibiru.mobile.gwt.preferences.CookiesPreferences;
import org.nibiru.mobile.gwt.service.WebSocketPushServiceFactory;
import org.nibiru.mobile.gwt.ui.GwtDisplayInfo;

import com.google.web.bindery.event.shared.SimpleEventBus;

import dagger.Module;
import dagger.Provides;

@Module
public class DefaultGwtModule {
	@Provides
	public Preferences getPreferences(CookiesPreferences manager) {
		return manager;
	}

	@Provides
	@Singleton
	public EventBus getEventBus(GwtEventBus eventBus) {
		return eventBus;
	}

	@Provides
	@Singleton
	public com.google.web.bindery.event.shared.EventBus getGwtEventBus() {
		return new SimpleEventBus();
	}

	@Provides
	public HttpManager getHttpManager(RequestBuilderHttpManager manager) {
		return manager;
	}

	@Provides
	public DisplayInfo getDisplayInfo(GwtDisplayInfo displayInfo) {
		return displayInfo;
	}

	@Provides
	public BasicPushServiceFactory getBasicPushServiceFactory(WebSocketPushServiceFactory webSocketPushServiceFactory) {
		return webSocketPushServiceFactory;
	}
}
