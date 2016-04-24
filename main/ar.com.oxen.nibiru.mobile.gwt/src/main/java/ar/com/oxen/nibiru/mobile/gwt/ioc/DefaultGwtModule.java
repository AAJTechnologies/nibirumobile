package ar.com.oxen.nibiru.mobile.gwt.ioc;

import javax.inject.Singleton;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.web.bindery.event.shared.SimpleEventBus;

import ar.com.oxen.nibiru.mobile.core.api.event.EventBus;
import ar.com.oxen.nibiru.mobile.core.api.http.HttpManager;
import ar.com.oxen.nibiru.mobile.core.api.preferences.Preferences;
import ar.com.oxen.nibiru.mobile.core.api.ui.DisplayInfo;
import ar.com.oxen.nibiru.mobile.gwt.event.GwtEventBus;
import ar.com.oxen.nibiru.mobile.gwt.http.RequestBuilderHttpManager;
import ar.com.oxen.nibiru.mobile.gwt.preferences.CookiesPreferences;
import ar.com.oxen.nibiru.mobile.gwt.ui.GwtDisplayInfo;

public class DefaultGwtModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(Preferences.class).to(CookiesPreferences.class);
		bind(EventBus.class).to(GwtEventBus.class).in(Singleton.class);
		bind(com.google.web.bindery.event.shared.EventBus.class).to(
				SimpleEventBus.class).in(Singleton.class);
		bind(HttpManager.class).to(RequestBuilderHttpManager.class);
		bind(DisplayInfo.class).to(GwtDisplayInfo.class);
	}
}
