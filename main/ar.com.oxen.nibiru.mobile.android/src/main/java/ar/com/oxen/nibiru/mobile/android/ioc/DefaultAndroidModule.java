package ar.com.oxen.nibiru.mobile.android.ioc;

import ar.com.oxen.nibiru.mobile.android.app.AndroidBootstrap;
import ar.com.oxen.nibiru.mobile.android.event.BroadcastEventBus;
import ar.com.oxen.nibiru.mobile.android.preferences.SharedPreferencesImpl;
import ar.com.oxen.nibiru.mobile.android.ui.AndroidDisplayInfo;
import ar.com.oxen.nibiru.mobile.android.ui.DialogAlertManager;
import ar.com.oxen.nibiru.mobile.android.ui.HandlerLooper;
import ar.com.oxen.nibiru.mobile.android.ui.place.IntentPlaceManager;
import ar.com.oxen.nibiru.mobile.core.api.app.Bootstrap;
import ar.com.oxen.nibiru.mobile.core.api.event.EventBus;
import ar.com.oxen.nibiru.mobile.core.api.preferences.Preferences;
import ar.com.oxen.nibiru.mobile.core.api.ui.AlertManager;
import ar.com.oxen.nibiru.mobile.core.api.ui.DisplayInfo;
import ar.com.oxen.nibiru.mobile.core.api.ui.Looper;
import ar.com.oxen.nibiru.mobile.core.api.ui.place.PlaceManager;
import ar.com.oxen.nibiru.mobile.java.async.AsyncManager;
import ar.com.oxen.nibiru.mobile.java.async.ThreadAsyncManager;
import dagger.Module;
import dagger.Provides;

@Module
public class DefaultAndroidModule {
	@Provides
	public Bootstrap getBootstrap(AndroidBootstrap bootstrap) {
		return bootstrap;
	}

	@Provides
	public AlertManager getAlertManager(DialogAlertManager manager) {
		return manager;
	}

	@Provides
	public Looper getLooper(HandlerLooper looper) {
		return looper;
	}

	@Provides
	public PlaceManager getPlaceManager(IntentPlaceManager manager) {
		return manager;
	}

	@Provides
	public EventBus getEventBus(BroadcastEventBus eventBus) {
		return eventBus;
	}

	@Provides
	public Preferences getPreferences(SharedPreferencesImpl preferences) {
		return preferences;
	}

	@Provides
	public AsyncManager getAsyncManager(ThreadAsyncManager manager) {
		return manager;
	}

	@Provides
	public DisplayInfo getDisplayInfo(AndroidDisplayInfo info) {
		return info;
	}
}
