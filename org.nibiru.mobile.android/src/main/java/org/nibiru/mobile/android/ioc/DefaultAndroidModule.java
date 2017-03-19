package org.nibiru.mobile.android.ioc;

import org.nibiru.mobile.android.app.AndroidBootstrap;
import org.nibiru.mobile.android.event.BroadcastEventBus;
import org.nibiru.mobile.android.preferences.SharedPreferencesImpl;
import org.nibiru.mobile.android.ui.AndroidDisplayInfo;
import org.nibiru.mobile.android.ui.DialogAlertManager;
import org.nibiru.mobile.android.ui.HandlerLooper;
import org.nibiru.mobile.android.ui.place.IntentPlaceManager;
import org.nibiru.mobile.core.api.app.Bootstrap;
import org.nibiru.mobile.core.api.event.EventBus;
import org.nibiru.mobile.core.api.preferences.Preferences;
import org.nibiru.mobile.core.api.ui.AlertManager;
import org.nibiru.mobile.core.api.ui.DisplayInfo;
import org.nibiru.mobile.core.api.ui.Looper;
import org.nibiru.mobile.core.api.ui.place.PlaceManager;
import org.nibiru.mobile.java.async.AsyncManager;
import org.nibiru.mobile.java.async.ThreadAsyncManager;

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
