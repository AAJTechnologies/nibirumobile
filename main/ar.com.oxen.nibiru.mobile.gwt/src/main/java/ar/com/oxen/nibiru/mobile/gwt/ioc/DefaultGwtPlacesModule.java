package ar.com.oxen.nibiru.mobile.gwt.ioc;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.inject.Provides;

import ar.com.oxen.nibiru.mobile.core.api.app.Bootstrap;
import ar.com.oxen.nibiru.mobile.core.api.ui.place.PlaceManager;
import ar.com.oxen.nibiru.mobile.gwt.app.GwtPlacesBootstrap;
import ar.com.oxen.nibiru.mobile.gwt.ui.place.DefaultActivityMapper;
import ar.com.oxen.nibiru.mobile.gwt.ui.place.DefaultPlaceHistoryMapper;
import ar.com.oxen.nibiru.mobile.gwt.ui.place.GwtPlaceManager;

public class DefaultGwtPlacesModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(Bootstrap.class).to(GwtPlacesBootstrap.class);
		bind(PlaceManager.class).to(GwtPlaceManager.class);

		bind(ActivityMapper.class).to(DefaultActivityMapper.class);
		bind(PlaceHistoryMapper.class).to(DefaultPlaceHistoryMapper.class);
	}

	@Provides
	public PlaceController placeController(com.google.web.bindery.event.shared.EventBus eventBus) {
		checkNotNull(eventBus);
		return new PlaceController(eventBus);
	}
}
