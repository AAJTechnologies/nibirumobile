package ar.com.oxen.nibiru.mobile.vaadin.ioc;

import ar.com.oxen.nibiru.mobile.core.api.geolocation.GeolocationManager;
import ar.com.oxen.nibiru.mobile.vaadin.geolocation.DummyGeolocationManager;
import dagger.Module;
import dagger.Provides;

@Module
public class DefaultVaadinHardwareModule {
	@Provides
	public GeolocationManager getGeolocationManager(DummyGeolocationManager manager) {
		return manager;
	}
}
