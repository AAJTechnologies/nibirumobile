package ar.com.oxen.nibiru.mobile.gwt.ioc;

import ar.com.oxen.nibiru.mobile.core.api.geolocation.GeolocationManager;
import ar.com.oxen.nibiru.mobile.gwt.geolocation.PhoneGapGeolocationManager;
import dagger.Module;
import dagger.Provides;

@Module
public class DefaultGwtHardwareModule {
	@Provides
	public GeolocationManager getBootstrap(PhoneGapGeolocationManager manager) {
		return manager;
	}
}
