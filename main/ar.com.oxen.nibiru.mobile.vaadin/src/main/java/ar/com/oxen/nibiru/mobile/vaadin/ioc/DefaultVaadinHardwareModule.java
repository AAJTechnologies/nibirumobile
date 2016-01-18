package ar.com.oxen.nibiru.mobile.vaadin.ioc;

import ar.com.oxen.nibiru.mobile.core.api.geolocation.GeolocationManager;
import ar.com.oxen.nibiru.mobile.vaadin.geolocation.DummyGeolocationManager;

import com.google.inject.AbstractModule;

public class DefaultVaadinHardwareModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(GeolocationManager.class).to(DummyGeolocationManager.class);
	}
}
