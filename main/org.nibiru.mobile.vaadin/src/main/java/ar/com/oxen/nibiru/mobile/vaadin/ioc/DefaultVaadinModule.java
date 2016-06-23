package ar.com.oxen.nibiru.mobile.vaadin.ioc;

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
