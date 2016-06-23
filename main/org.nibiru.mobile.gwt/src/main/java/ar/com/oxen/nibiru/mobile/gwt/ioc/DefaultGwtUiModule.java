package ar.com.oxen.nibiru.mobile.gwt.ioc;

import ar.com.oxen.nibiru.mobile.core.api.ui.AlertManager;
import ar.com.oxen.nibiru.mobile.core.api.ui.Looper;
import ar.com.oxen.nibiru.mobile.gwt.app.AppWidgetBootstrap;
import ar.com.oxen.nibiru.mobile.gwt.app.GwtAppWidgetBootstrap;
import ar.com.oxen.nibiru.mobile.gwt.ui.GwtAlertManager;
import ar.com.oxen.nibiru.mobile.gwt.ui.SchedulerLooper;
import dagger.Module;
import dagger.Provides;

@Module
public class DefaultGwtUiModule {
	@Provides
	public AlertManager getAlertManager(GwtAlertManager manager) {
		return manager;
	}

	@Provides
	public Looper getLooper(SchedulerLooper looper) {
		return looper;
	}

	@Provides
	public AppWidgetBootstrap getAppWidgetBootstrap(GwtAppWidgetBootstrap appWidgetBootstrap) {
		return appWidgetBootstrap;
	}
}
