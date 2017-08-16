package org.nibiru.mobile.gwt.app;

import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.EventBus;

import org.nibiru.async.core.api.promise.Deferred;
import org.nibiru.async.core.api.promise.Promise;
import org.nibiru.mobile.core.api.app.Bootstrap;
import org.nibiru.mobile.core.api.app.EntryPoint;
import org.nibiru.mobile.gwt.ui.place.SimplePlace;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class GwtPlacesBootstrap implements Bootstrap {
    private final EntryPoint entryPoint;
    private final EventBus eventBus;
    private final PlaceController placeController;
    private final PlaceHistoryMapper placeHistoryMapper;
    private final AppWidgetBootstrap appWidgetBootstrap;

    @Inject
    public GwtPlacesBootstrap(EntryPoint entryPoint,
                              EventBus eventBus,
                              PlaceController placeController,
                              PlaceHistoryMapper placeHistoryMapper,
                              AppWidgetBootstrap appWidgetBootstrap) {
        this.entryPoint = checkNotNull(entryPoint);
        this.eventBus = checkNotNull(eventBus);
        this.placeController = checkNotNull(placeController);
        this.placeHistoryMapper = checkNotNull(placeHistoryMapper);
        this.appWidgetBootstrap = checkNotNull(appWidgetBootstrap);
    }

    @Override
    public Promise<Void, Exception> onBootstrap() {
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(
                placeHistoryMapper);

        historyHandler.register(placeController, eventBus,
                new SimplePlace(null, 0, placeController));

        RootPanel.get().add(appWidgetBootstrap.createAppWidget());

        historyHandler.handleCurrentHistory();

        entryPoint.onApplicationStart();
        return Deferred.<Void, Exception>defer().promise();
    }
}
