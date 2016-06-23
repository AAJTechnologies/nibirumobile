package org.nibiru.mobile.mgwt.ioc;

import org.nibiru.mobile.core.api.ui.AlertManager;
import org.nibiru.mobile.gwt.app.AppWidgetBootstrap;
import org.nibiru.mobile.mgwt.app.MgwtAppWidgetBootstrap;
import org.nibiru.mobile.mgwt.ui.MgwtAlertManager;
import org.nibiru.mobile.mgwt.ui.place.DefaultAnimationMapper;

import com.googlecode.mgwt.mvp.client.AnimationMapper;

import dagger.Module;
import dagger.Provides;

@Module
public class DefaultMgwtUiModule {
	@Provides
	public AlertManager getAsyncManager(MgwtAlertManager manager) {
		return manager;
	}

	@Provides
	public AnimationMapper getAnimationMapper(DefaultAnimationMapper mapper) {
		return mapper;
	}

	@Provides
	public AppWidgetBootstrap getAppWidgetBootstrap(MgwtAppWidgetBootstrap bootstrap) {
		return bootstrap;
	}
}
