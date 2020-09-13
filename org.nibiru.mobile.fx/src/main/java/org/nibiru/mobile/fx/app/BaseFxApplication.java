package org.nibiru.mobile.fx.app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.nibiru.mobile.core.api.app.Bootstrap;

public abstract class BaseFxApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        Platform.runLater(() -> {
            primaryStage.setMaximized(true);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setFullScreen(true);
            primaryStage.show();
        });

        getBootstrap(primaryStage).onBootstrap();
    }

    protected abstract Bootstrap getBootstrap(Stage primaryStage);
}
