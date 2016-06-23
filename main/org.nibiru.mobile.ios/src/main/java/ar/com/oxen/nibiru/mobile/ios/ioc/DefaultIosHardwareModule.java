package ar.com.oxen.nibiru.mobile.ios.ioc;

import ar.com.oxen.nibiru.mobile.core.api.geolocation.GeolocationManager;
import ar.com.oxen.nibiru.mobile.ios.geolocation.DummyGeolocationManager;
import dagger.Module;
import dagger.Provides;

@Module
public class DefaultIosHardwareModule {
	@Provides
	public GeolocationManager getGeolocationManager() {
		return new DummyGeolocationManager();
	}
}
