package org.nibiru.mobile.gwt.ioc;

import org.nibiru.mobile.core.api.ui.AlertManager;
import org.nibiru.mobile.core.api.ui.Looper;
import org.nibiru.mobile.gwt.app.AppWidgetBootstrap;
import org.nibiru.mobile.gwt.app.GwtAppWidgetBootstrap;
import org.nibiru.mobile.gwt.ui.GwtAlertManager;
import org.nibiru.mobile.gwt.ui.SchedulerLooper;

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
