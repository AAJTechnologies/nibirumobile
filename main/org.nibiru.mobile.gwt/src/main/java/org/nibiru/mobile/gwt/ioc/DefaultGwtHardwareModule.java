package org.nibiru.mobile.gwt.ioc;

import org.nibiru.mobile.core.api.geolocation.GeolocationManager;
import org.nibiru.mobile.gwt.geolocation.PhoneGapGeolocationManager;

import dagger.Module;
import dagger.Provides;

@Module
public class DefaultGwtHardwareModule {
	@Provides
	public GeolocationManager getBootstrap(PhoneGapGeolocationManager manager) {
		return manager;
	}
}
