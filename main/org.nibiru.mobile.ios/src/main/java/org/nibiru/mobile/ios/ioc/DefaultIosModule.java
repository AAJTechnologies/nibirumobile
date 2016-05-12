package org.nibiru.mobile.ios.ioc;

import javax.inject.Singleton;

import org.nibiru.mobile.core.api.app.Bootstrap;
import org.nibiru.mobile.core.api.event.EventBus;
import org.nibiru.mobile.core.api.preferences.Preferences;
import org.nibiru.mobile.core.api.ui.AlertManager;
import org.nibiru.mobile.core.api.ui.DisplayInfo;
import org.nibiru.mobile.core.api.ui.Looper;
import org.nibiru.mobile.core.api.ui.place.PlaceManager;
import org.nibiru.mobile.ios.app.IosBootstrap;
import org.nibiru.mobile.ios.preferences.DummyPreferences;
import org.nibiru.mobile.ios.ui.IOSDisplayInfo;
import org.nibiru.mobile.ios.ui.NSThreadLooper;
import org.nibiru.mobile.ios.ui.UIAlertControllerAlertManager;
import org.nibiru.mobile.ios.ui.place.UINavigationControllerPlaceManager;
import org.nibiru.mobile.java.async.AsyncManager;
import org.nibiru.mobile.java.async.ThreadAsyncManager;
import org.nibiru.mobile.java.event.guava.GuavaEventBus;

import dagger.Module;
import dagger.Provides;
import ios.uikit.UINavigationController;
import ios.uikit.UIScreen;
import ios.uikit.UIWindow;

@Module
public class DefaultIosModule {
	@Provides
	public Bootstrap getBootstrap(IosBootstrap bootstrap) {
		return bootstrap;
	}

	@Provides
	public AlertManager getAlertManager(UIAlertControllerAlertManager manager) {
		return manager;
	}

	@Provides
	public Looper getLooper(NSThreadLooper looper) {
		return looper;
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
