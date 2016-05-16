package ar.com.oxen.nibiru.mobile.wp.ioc;

import javax.inject.Singleton;

import ar.com.oxen.nibiru.mobile.core.api.app.Bootstrap;
import ar.com.oxen.nibiru.mobile.core.api.ui.AlertManager;
import ar.com.oxen.nibiru.mobile.core.api.ui.Looper;
import ar.com.oxen.nibiru.mobile.core.api.ui.place.PlaceManager;
import ar.com.oxen.nibiru.mobile.gwt.ui.SchedulerLooper;
import ar.com.oxen.nibiru.mobile.wp.app.WindowsPhoneBootstrap;
import ar.com.oxen.nibiru.mobile.wp.ui.WindowsPhoneAlertManager;
import ar.com.oxen.nibiru.mobile.wp.ui.place.WindowsPhonePlaceManager;
import dagger.Module;
import dagger.Provides;

@Module
public class DefaultWindowsPhoneUiModule {
	@Provides
	public AlertManager getAlertManager(WindowsPhoneAlertManager manager) {
		return manager;
	}

	@Provides
	public Looper getLooper(SchedulerLooper looper) {
		return looper;
	}

	@Provides
	public Bootstrap getBootstrap(WindowsPhoneBootstrap bootstrap) {
		return bootstrap;
	}

	@Provides
	@Singleton
	public PlaceManager getPlaceManager(WindowsPhonePlaceManager manager) {
		return manager;
	}
}
