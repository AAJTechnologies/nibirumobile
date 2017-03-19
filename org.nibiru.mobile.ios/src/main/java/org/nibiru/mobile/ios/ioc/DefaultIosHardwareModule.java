package org.nibiru.mobile.ios.ioc;

import org.nibiru.mobile.core.api.geolocation.GeolocationManager;
import org.nibiru.mobile.ios.geolocation.DummyGeolocationManager;

import dagger.Module;
import dagger.Provides;

@Module
public class DefaultIosHardwareModule {
	@Provides
	public GeolocationManager getGeolocationManager() {
		return new DummyGeolocationManager();
	}
}
