package org.nibiru.mobile.swing.ioc;

import dagger.Module;
import dagger.Provides;
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
import org.nibiru.mobile.swing.app.SwingBootstrap;
import org.nibiru.mobile.swing.ui.SwingAlertManager;
import org.nibiru.mobile.swing.ui.SwingDisplayInfo;
import org.nibiru.mobile.swing.ui.place.SwingPlaceManager;

import javax.inject.Singleton;
import javax.swing.*;
import java.awt.*;


@Module
public class DefaultSwingModule {
    @Provides
    public Bootstrap getBootstrap(SwingBootstrap bootstrap) {
        return bootstrap;
    }

    @Provides
    public AlertManager getAlertManager(SwingAlertManager manager) {
        return manager;
    }

    @Provides
    @Singleton
    public PlaceManager getPlaceManager(SwingPlaceManager manager) {
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
    public DisplayInfo getDisplayInfo(SwingDisplayInfo displayInfo) {
        return displayInfo;
    }

    @Provides
    @Singleton
    public JFrame getJFrame() {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        return frame;
    }
}
