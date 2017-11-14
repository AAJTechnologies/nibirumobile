package org.nibiru.mobile.ios.ioc;

import org.nibiru.mobile.core.api.app.Bootstrap;
import org.nibiru.mobile.core.api.event.EventBus;
import org.nibiru.mobile.core.api.preferences.Preferences;
import org.nibiru.mobile.core.api.ui.AlertManager;
import org.nibiru.mobile.core.api.ui.DisplayInfo;
import org.nibiru.mobile.core.api.ui.place.PlaceManager;
import org.nibiru.mobile.ios.app.IOSBootstrap;
import org.nibiru.mobile.ios.preferences.DummyPreferences;
import org.nibiru.mobile.ios.ui.IOSDisplayInfo;
import org.nibiru.mobile.ios.ui.UIAlertControllerAlertManager;
import org.nibiru.mobile.ios.ui.place.UINavigationControllerPlaceManager;
import org.nibiru.mobile.java.async.AsyncManager;
import org.nibiru.mobile.java.async.ThreadAsyncManager;
import org.nibiru.mobile.java.event.guava.GuavaEventBus;

import javax.inject.Singleton;

import apple.uikit.UINavigationController;
import apple.uikit.UIScreen;
import apple.uikit.UIWindow;
import dagger.Module;
import dagger.Provides;


@Module
public class DefaultIOSModule {
	@Provides
	public Bootstrap getBootstrap(IOSBootstrap bootstrap) {
		return bootstrap;
	}

	@Provides
	public AlertManager getAlertManager(UIAlertControllerAlertManager manager) {
		return manager;
	}

	@Provides
	@Singleton
	public PlaceManager getPlaceManager(UINavigationControllerPlaceManager manager) {
		return manager;
	}

	@Provides
	@Singleton
	public EventBus getEventBus(GuavaEventBus eventBus) {
		return eventBus;
	}

	@Provides
	@Singleton
	public com.google.common.eventbus.EventBus getGuavaEventBus() {
		return new com.google.common.eventbus.EventBus();
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
	public DisplayInfo getDisplayInfo(IOSDisplayInfo displayInfo) {
		return displayInfo;
	}

	@Provides
	@Singleton
	public UIWindow getWindow() {
		UIWindow mainWindow = UIWindow.alloc().init();
		mainWindow.setBounds(UIScreen.mainScreen().bounds());
		return mainWindow;
	}

	@Provides
	public UINavigationController getUINavigationController() {
		UINavigationController controller = UINavigationController.alloc().init();
		controller.setTitle("CHANGE ME");
		return controller;
	}
}
