package ar.com.oxen.nibiru.mobile.gwt.ioc;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryMapper;

import ar.com.oxen.nibiru.mobile.core.api.app.Bootstrap;
import ar.com.oxen.nibiru.mobile.core.api.ui.place.PlaceManager;
import ar.com.oxen.nibiru.mobile.gwt.app.GwtPlacesBootstrap;
import ar.com.oxen.nibiru.mobile.gwt.ui.place.DefaultActivityMapper;
import ar.com.oxen.nibiru.mobile.gwt.ui.place.DefaultPlaceHistoryMapper;
import ar.com.oxen.nibiru.mobile.gwt.ui.place.GwtPlaceManager;
import dagger.Module;
import dagger.Provides;

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
