package org.nibiru.mobile.vaadin.ioc;

import org.nibiru.mobile.core.api.app.Bootstrap;
import org.nibiru.mobile.core.api.event.EventBus;
import org.nibiru.mobile.core.api.preferences.Preferences;
import org.nibiru.mobile.core.api.ui.AlertManager;
import org.nibiru.mobile.core.api.ui.Looper;
import org.nibiru.mobile.core.api.ui.place.PlaceManager;
import org.nibiru.mobile.java.async.AsyncManager;
import org.nibiru.mobile.java.async.SequentialAsyncManager;
import org.nibiru.mobile.java.event.guava.GuavaEventBus;
import org.nibiru.mobile.vaadin.app.NibiruUiProvider;
import org.nibiru.mobile.vaadin.app.VaadinBootstrap;
import org.nibiru.mobile.vaadin.preferences.DummyPreferences;
import org.nibiru.mobile.vaadin.ui.DummyLooper;
import org.nibiru.mobile.vaadin.ui.ModalWindowAlertManager;
import org.nibiru.mobile.vaadin.ui.place.UIPlaceManager;

import com.vaadin.server.UIProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class DefaultVaadinModule {
	@Provides
	public Bootstrap getBootstrap(VaadinBootstrap bootsrap) {
		return bootsrap;
	}

	@Provides
	public AlertManager getAlertManager(ModalWindowAlertManager manager) {
		return manager;
	}

	@Provides
	public Looper getLooper(DummyLooper looper) {
		return looper;
	}

	@Provides
	// Session scoped?
	public PlaceManager getPlaceManager(UIPlaceManager manager) {
		return manager;
	}

	@Provides
	// Session scoped?
	public EventBus getEventBus(GuavaEventBus eventBus) {
		return eventBus;
	}

	@Provides
	public Preferences getPreferences(DummyPreferences preferences) {
		return preferences;
	}

	@Provides
	public AsyncManager getAsyncManager(SequentialAsyncManager manager) {
		return manager;
	}

	@Provides
	public UIProvider getUIProvider(NibiruUiProvider uiProvider) {
		return uiProvider;
	}
}
