package org.nibiru.mobile.fx.app;

import javafx.application.Application;
import javafx.stage.Stage;
import org.nibiru.mobile.core.api.app.Bootstrap;

public abstract class BaseFxApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("CHANGEME");
        primaryStage.setMaximized(true);
        primaryStage.show();

        getBootstrap(primaryStage).onBootstrap();
    }

    protected abstract Bootstrap getBootstrap(Stage primaryStage);
}
