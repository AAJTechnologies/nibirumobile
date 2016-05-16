package ar.com.oxen.nibiru.mobile.android.ioc;

import ar.com.oxen.nibiru.mobile.android.geolocation.AndroidGeolocationManager;
import ar.com.oxen.nibiru.mobile.core.api.geolocation.GeolocationManager;
import dagger.Module;
import dagger.Provides;

@Module
public class DefaultAndroidHardwareModule {
	@Provides
	public GeolocationManager getGeolocationManager(AndroidGeolocationManager manager) {
		return manager;
	}
}
