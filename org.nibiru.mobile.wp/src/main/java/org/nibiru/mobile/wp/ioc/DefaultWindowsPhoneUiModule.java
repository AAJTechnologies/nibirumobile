package org.nibiru.mobile.wp.ioc;

import org.nibiru.mobile.core.api.app.Bootstrap;
import org.nibiru.mobile.core.api.ui.AlertManager;
import org.nibiru.mobile.core.api.ui.place.PlaceManager;
import org.nibiru.mobile.wp.app.WindowsPhoneBootstrap;
import org.nibiru.mobile.wp.ui.WindowsPhoneAlertManager;
import org.nibiru.mobile.wp.ui.place.WindowsPhonePlaceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DefaultWindowsPhoneUiModule {
	@Provides
	public AlertManager getAlertManager(WindowsPhoneAlertManager manager) {
		return manager;
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
