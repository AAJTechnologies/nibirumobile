package org.nibiru.mobile.gwt.app;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import org.nibiru.mobile.core.api.app.Bootstrap;
import org.nibiru.mobile.core.api.app.EntryPoint;
import org.nibiru.mobile.gwt.ui.place.SimplePlace;

import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.EventBus;

public class GwtPlacesBootstrap implements Bootstrap {
    private final EntryPoint entryPoint;
    private final EventBus eventBus;
    private final PlaceController placeController;
    private final PlaceHistoryMapper placeHistoryMapper;
    private final AppWidgetBootstrap appWidgetBootstrap;
    private final DatabaseBootstrap databaseBootstrap;

    @Inject
    public GwtPlacesBootstrap(EntryPoint entryPoint,
                              EventBus eventBus,
                              PlaceController placeController,
                              PlaceHistoryMapper placeHistoryMapper,
                              AppWidgetBootstrap appWidgetBootstrap,
                              DatabaseBootstrap databaseBootstrap) {
        this.entryPoint = checkNotNull(entryPoint);
        this.eventBus = checkNotNull(eventBus);
        this.placeController = checkNotNull(placeController);
        this.placeHistoryMapper = checkNotNull(placeHistoryMapper);
        this.appWidgetBootstrap = checkNotNull(appWidgetBootstrap);
        this.databaseBootstrap = checkNotNull(databaseBootstrap);
    }

    @Override
    public void onBootstrap() {
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(
                placeHistoryMapper);

        historyHandler.register(placeController, eventBus,
                new SimplePlace(null, 0, placeController));

        RootPanel.get().add(appWidgetBootstrap.createAppWidget());

        historyHandler.handleCurrentHistory();

        databaseBootstrap.createDatabase()
                .then((result) -> entryPoint.onApplicationStart())
                .capture((reason) -> Window.alert("Database error: " + reason.getMessage()));
    }
}
