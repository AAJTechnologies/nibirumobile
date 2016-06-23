package org.nibiru.mobile.vaadin.ioc;

import org.nibiru.mobile.core.api.geolocation.GeolocationManager;
import org.nibiru.mobile.vaadin.geolocation.DummyGeolocationManager;

import dagger.Module;
import dagger.Provides;

@Module
public class DefaultVaadinHardwareModule {
	@Provides
	public GeolocationManager getGeolocationManager(DummyGeolocationManager manager) {
		return manager;
	}
}
