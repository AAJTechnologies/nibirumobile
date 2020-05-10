package org.nibiru.mobile.fx.ioc;

import dagger.Module;
import dagger.Provides;
import javafx.stage.Stage;
import org.nibiru.async.core.api.loop.Looper;
import org.nibiru.mobile.core.api.app.Bootstrap;
import org.nibiru.mobile.core.api.event.EventBus;
import org.nibiru.mobile.core.api.preferences.Preferences;
import org.nibiru.mobile.core.api.ui.AlertManager;
import org.nibiru.mobile.core.api.ui.DisplayInfo;
import org.nibiru.mobile.core.api.ui.place.PlaceManager;
import org.nibiru.mobile.java.async.AsyncManager;
import org.nibiru.mobile.java.async.ThreadAsyncManager;
import org.nibiru.mobile.java.event.guava.GuavaEventBus;
import org.nibiru.mobile.java.preferences.JavaPreferences;
import org.nibiru.mobile.fx.app.FxBootstrap;
import org.nibiru.mobile.fx.ui.FxAlertManager;
import org.nibiru.mobile.fx.ui.FxDisplayInfo;
import org.nibiru.mobile.fx.ui.place.FxPlaceManager;

import javax.inject.Singleton;

import static com.google.common.base.Preconditions.checkNotNull;


@Module
public class DefaultFxModule {
    private final Stage primaryStage;

    public DefaultFxModule(Stage primaryStage) {
        this.primaryStage = checkNotNull(primaryStage);
    }

    @Provides
    public Bootstrap getBootstrap(FxBootstrap bootstrap) {
        return bootstrap;
    }

    @Provides
    public AlertManager getAlertManager(FxAlertManager manager) {
        return manager;
    }

    @Provides
    @Singleton
    public PlaceManager getPlaceManager(FxPlaceManager manager) {
        return manager;
    }

    @Provides
    @Singleton
    public EventBus getEventBus(GuavaEventBus eventBus) {
        return eventBus;
    }

    @Provides
    @Singleton
    public com.google.common.eventbus.EventBus getGuavaEventBus() {
        return new com.google.common.eventbus.EventBus();
    }

    @Provides
    public Preferences getPreferences(JavaPreferences preferences) {
        return preferences;
    }

    @Provides
    public AsyncManager getAsyncManager(ThreadAsyncManager manager) {
        return manager;
    }

    @Provides
    public DisplayInfo getDisplayInfo(FxDisplayInfo displayInfo) {
        return displayInfo;
    }

    @Provides
    @Singleton
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
