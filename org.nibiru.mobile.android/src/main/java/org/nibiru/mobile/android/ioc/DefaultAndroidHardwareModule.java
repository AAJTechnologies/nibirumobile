package org.nibiru.mobile.android.ioc;

import org.nibiru.mobile.android.geolocation.AndroidGeolocationManager;
import org.nibiru.mobile.core.api.geolocation.GeolocationManager;

import dagger.Module;
import dagger.Provides;

@Module
public class DefaultAndroidHardwareModule {
	@Provides
	public GeolocationManager getGeolocationManager(AndroidGeolocationManager manager) {
		return manager;
	}
}
