package ar.com.oxen.nibiru.mobile.ios.ioc;

import javax.inject.Singleton;

import org.robovm.apple.uikit.UINavigationController;
import org.robovm.apple.uikit.UIScreen;
import org.robovm.apple.uikit.UIWindow;

import ar.com.oxen.nibiru.mobile.core.api.app.Bootstrap;
import ar.com.oxen.nibiru.mobile.core.api.event.EventBus;
import ar.com.oxen.nibiru.mobile.core.api.preferences.Preferences;
import ar.com.oxen.nibiru.mobile.core.api.ui.AlertManager;
import ar.com.oxen.nibiru.mobile.core.api.ui.DisplayInfo;
import ar.com.oxen.nibiru.mobile.core.api.ui.Looper;
import ar.com.oxen.nibiru.mobile.core.api.ui.place.PlaceManager;
import ar.com.oxen.nibiru.mobile.ios.app.IosBootstrap;
import ar.com.oxen.nibiru.mobile.ios.preferences.DummyPreferences;
import ar.com.oxen.nibiru.mobile.ios.ui.IOSDisplayInfo;
import ar.com.oxen.nibiru.mobile.ios.ui.NSThreadLooper;
import ar.com.oxen.nibiru.mobile.ios.ui.UIAlertControllerAlertManager;
import ar.com.oxen.nibiru.mobile.ios.ui.place.UINavigationControllerPlaceManager;
import ar.com.oxen.nibiru.mobile.java.async.AsyncManager;
import ar.com.oxen.nibiru.mobile.java.async.ThreadAsyncManager;
import ar.com.oxen.nibiru.mobile.java.event.guava.GuavaEventBus;
import dagger.Module;
import dagger.Provides;

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
		return new UIWindow(UIScreen.getMainScreen().getBounds());
	}

	@Provides
	public UINavigationController getUINavigationController() {
		UINavigationController controller = new UINavigationController();
		controller.setTitle("CHANGE ME");
		return controller;
	}
}
