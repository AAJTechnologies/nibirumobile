package org.nibiru.mobile.gwt.ioc;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryMapper;

import org.nibiru.mobile.core.api.app.Bootstrap;
import org.nibiru.mobile.core.api.ui.place.PlaceManager;
import org.nibiru.mobile.gwt.app.GwtPlacesBootstrap;
import org.nibiru.mobile.gwt.ui.place.DefaultActivityMapper;
import org.nibiru.mobile.gwt.ui.place.DefaultPlaceHistoryMapper;
import org.nibiru.mobile.gwt.ui.place.GwtPlaceManager;

import dagger.Module;
import dagger.Provides;

import static com.google.common.base.Preconditions.checkNotNull;

@Module
public class DefaultGwtPlacesModule {
	@Provides
	public Bootstrap getBootstrap(GwtPlacesBootstrap bootstrap) {
		return bootstrap;
	}

	@Provides
	public PlaceManager getPlaceManager(GwtPlaceManager manager) {
		return manager;
	}

	@Provides
	public ActivityMapper getActivityMapper(DefaultActivityMapper mapper) {
		return mapper;
	}

	@Provides
	public PlaceHistoryMapper getPlaceHistoryMapper() {
		return GWT.create(DefaultPlaceHistoryMapper.class);
	}

	@Provides
	public PlaceController placeController(com.google.web.bindery.event.shared.EventBus eventBus) {
		checkNotNull(eventBus);
		return new PlaceController(eventBus);
	}
}
