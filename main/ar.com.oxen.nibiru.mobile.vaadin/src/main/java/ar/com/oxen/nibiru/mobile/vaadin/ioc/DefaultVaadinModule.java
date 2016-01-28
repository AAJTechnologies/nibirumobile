package ar.com.oxen.nibiru.mobile.vaadin.ioc;

import com.google.inject.AbstractModule;
import com.google.inject.servlet.SessionScoped;
import com.vaadin.server.UIProvider;

import ar.com.oxen.nibiru.mobile.core.api.app.Bootstrap;
import ar.com.oxen.nibiru.mobile.core.api.event.EventBus;
import ar.com.oxen.nibiru.mobile.core.api.preferences.Preferences;
import ar.com.oxen.nibiru.mobile.core.api.ui.AlertManager;
import ar.com.oxen.nibiru.mobile.core.api.ui.Looper;
import ar.com.oxen.nibiru.mobile.core.api.ui.place.PlaceManager;
import ar.com.oxen.nibiru.mobile.java.async.AsyncManager;
import ar.com.oxen.nibiru.mobile.java.async.SequentialAsyncManager;
import ar.com.oxen.nibiru.mobile.java.event.guava.GuavaEventBus;
import ar.com.oxen.nibiru.mobile.vaadin.app.NibiruUiProvider;
import ar.com.oxen.nibiru.mobile.vaadin.app.VaadinBootstrap;
import ar.com.oxen.nibiru.mobile.vaadin.preferences.DummyPreferences;
import ar.com.oxen.nibiru.mobile.vaadin.ui.DummyLooper;
import ar.com.oxen.nibiru.mobile.vaadin.ui.ModalWindowAlertManager;
import ar.com.oxen.nibiru.mobile.vaadin.ui.place.UIPlaceManager;

public class DefaultVaadinModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(Bootstrap.class).to(VaadinBootstrap.class);
		bind(AlertManager.class).to(ModalWindowAlertManager.class);
		bind(Looper.class).to(DummyLooper.class);
		bind(PlaceManager.class).to(UIPlaceManager.class).in(SessionScoped.class);
		bind(EventBus.class).to(GuavaEventBus.class).in(SessionScoped.class);
		bind(Preferences.class).to(DummyPreferences.class);
		bind(AsyncManager.class).to(SequentialAsyncManager.class);
		bind(UIProvider.class).to(NibiruUiProvider.class);
	}
}
