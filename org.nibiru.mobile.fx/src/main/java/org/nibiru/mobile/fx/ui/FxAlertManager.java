package org.nibiru.mobile.fx.ui;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.nibiru.mobile.core.api.common.Consumer;
import org.nibiru.mobile.core.api.ui.AlertManager;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

// TODO: Internationalization.
public class FxAlertManager
        implements AlertManager {
    private final Stage primaryStage;

    @Inject
    public FxAlertManager(@Nonnull Stage primaryStage) {
        this.primaryStage = checkNotNull(primaryStage);
    }

    @Override
    public void showMessage(@Nonnull String message) {
        checkNotNull(message);
        buildAlert(message,
                AlertType.NONE)
                .show();
    }

    @Override
    public void showNotification(@Nonnull String message) {
        checkNotNull(message);
        buildAlert(message,
                AlertType.INFORMATION)
                .show();
    }

    @Override
    public void showException(@Nonnull Exception exception) {
        checkNotNull(exception);
        buildAlert(exception.getMessage(),
                AlertType.ERROR)
                .show();
    }

    @Override
    public void prompt(@Nonnull String title,
                       @Nonnull String message,
                       @Nonnull Consumer<String> callback) {
        checkNotNull(title);
        checkNotNull(message);
        checkNotNull(callback);

        TextField field = new TextField();
        buildAlert(title,
                AlertType.CONFIRMATION)
                .headerTex(message)
                .graphic(field)
                .show(alert -> {
                    if (ButtonType.OK.equals(alert.getResult())) {
                        callback.accept(field.getText());
                    }
                });

    }

    @Override
    public void confirm(@Nonnull String title,
                        @Nonnull String message,
                        @Nonnull Consumer<Boolean> callback) {
        checkNotNull(title);
        checkNotNull(message);
        checkNotNull(callback);

        buildAlert(title,
                AlertType.CONFIRMATION)
                .headerTex(message)
                .show(alert -> callback.accept(ButtonType.OK
                        .equals(alert.getResult())));
    }

    private AlertBuilder buildAlert(String message,
                                    AlertType type) {
        return new AlertBuilder(primaryStage,
                message,
                type);
    }

    private static class AlertBuilder {
        private final Alert alert;

        private AlertBuilder(Stage primaryStage,
                             String message,
                             AlertType type) {
            alert = new Alert(type);
            alert.initOwner(primaryStage.getScene()
                    .getWindow());
            alert.setTitle(message);
        }

        private AlertBuilder headerTex(String message) {
            alert.setHeaderText(message);
            return this;
        }

        private AlertBuilder graphic(Node graphic) {
            alert.setGraphic(graphic);
            return this;
        }

        private AlertBuilder show(Consumer<Alert> callback) {
            Platform.runLater(() -> {
                alert.showAndWait();
                callback.accept(alert);
            });
            return this;
        }

        private AlertBuilder show() {
            Platform.runLater(() -> {
                alert.show();
            });
            return this;
        }
    }
}
