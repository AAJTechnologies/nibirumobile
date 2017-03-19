package org.nibiru.mobile.dotnet.ioc;

import org.nibiru.mobile.core.api.app.Bootstrap;
import org.nibiru.mobile.core.api.event.EventBus;
import org.nibiru.mobile.core.api.preferences.Preferences;
import org.nibiru.mobile.core.api.ui.AlertManager;
import org.nibiru.mobile.core.api.ui.DisplayInfo;
import org.nibiru.mobile.core.api.ui.Looper;
import org.nibiru.mobile.core.api.ui.place.PlaceManager;
import org.nibiru.mobile.dotnet.app.DotNetBootstrap;
import org.nibiru.mobile.dotnet.preferences.DummyPreferences;
import org.nibiru.mobile.dotnet.ui.DotNetDisplayInfo;
import org.nibiru.mobile.dotnet.ui.DummyLooper;
import org.nibiru.mobile.dotnet.ui.MessageBoxAlertManager;
import org.nibiru.mobile.dotnet.ui.place.DotNetPlaceManager;
import org.nibiru.mobile.java.async.AsyncManager;
import org.nibiru.mobile.java.async.ThreadAsyncManager;
import org.nibiru.mobile.java.event.guava.GuavaEventBus;

import javax.inject.Singleton;

import cli.System.Windows.Window;
import dagger.Module;
import dagger.Provides;


@Module
public class DefaultDotNetModule {
	@Provides
	public Bootstrap getBootstrap(DotNetBootstrap bootstrap) {
		return bootstrap;
	}

	@Provides
	public AlertManager getAlertManager(MessageBoxAlertManager manager) {
		return manager;
	}

	@Provides
	public Looper getLooper(DummyLooper looper) {
		return looper;
	}

	@Provides
	@Singleton
	public PlaceManager getPlaceManager(DotNetPlaceManager manager) {
		return manager;
	}

	@Provides
	@Singleton
	public EventBus getEventBus(GuavaEventBus eventBus) {
		return eventBus;
	}

	@Provides
	public Preferences getPreferences(DummyPreferences preferences) {
		return preferences;
	}

	@Provides
	public AsyncManager getAsyncManager(ThreadAsyncManager manager) {
		return manager;
	}

	@Provides
	public DisplayInfo getDisplayInfo(DotNetDisplayInfo displayInfo) {
		return displayInfo;
	}

	@Provides
	@Singleton
	public Window getWindow() {
		return new Window();
	}
}
