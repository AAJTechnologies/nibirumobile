package org.nibiru.mobile.dotnet.ioc;

import org.nibiru.mobile.core.api.geolocation.GeolocationManager;
import org.nibiru.mobile.dotnet.geolocation.DummyGeolocationManager;

import dagger.Module;
import dagger.Provides;

@Module
public class DefaultDotNetHardwareModule {
	@Provides
	public GeolocationManager getGeolocationManager() {
		return new DummyGeolocationManager();
	}
}
