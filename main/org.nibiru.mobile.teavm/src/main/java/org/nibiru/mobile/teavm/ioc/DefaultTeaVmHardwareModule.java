package org.nibiru.mobile.teavm.ioc;

import org.nibiru.mobile.core.api.geolocation.GeolocationManager;
import org.nibiru.mobile.teavm.geolocation.DummyGeolocationManager;

import dagger.Module;
import dagger.Provides;

@Module
public class DefaultTeaVmHardwareModule {
	@Provides
	public GeolocationManager getBootstrap(DummyGeolocationManager manager) {
		return manager;
	}
}
