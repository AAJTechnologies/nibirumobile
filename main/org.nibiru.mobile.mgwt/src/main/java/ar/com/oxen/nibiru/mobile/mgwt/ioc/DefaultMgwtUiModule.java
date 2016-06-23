package ar.com.oxen.nibiru.mobile.mgwt.ioc;

import com.googlecode.mgwt.mvp.client.AnimationMapper;

import ar.com.oxen.nibiru.mobile.core.api.ui.AlertManager;
import ar.com.oxen.nibiru.mobile.gwt.app.AppWidgetBootstrap;
import ar.com.oxen.nibiru.mobile.mgwt.app.MgwtAppWidgetBootstrap;
import ar.com.oxen.nibiru.mobile.mgwt.ui.MgwtAlertManager;
import ar.com.oxen.nibiru.mobile.mgwt.ui.place.DefaultAnimationMapper;
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
