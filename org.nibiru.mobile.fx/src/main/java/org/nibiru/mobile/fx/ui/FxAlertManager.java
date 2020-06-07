package org.nibiru.mobile.fx.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import org.nibiru.mobile.core.api.common.Consumer;
import org.nibiru.mobile.core.api.ui.AlertManager;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

// TODO: Internationalization.
public class FxAlertManager implements AlertManager {
    @Inject
    public FxAlertManager() {
    }

    @Override
    public void showMessage(String message) {
        checkNotNull(message);
        showAlert(message,
                AlertType.NONE);
    }

    @Override
    public void showNotification(String message) {
        showMessage(message);
        showAlert(message,
                AlertType.INFORMATION);
    }

    @Override
    public void showException(Exception exception) {
        checkNotNull(exception);
        showAlert(exception.getMessage(),
                AlertType.ERROR);
    }

    @Override
    public void prompt(String title,
                       String message,
                       Consumer<String> callback) {
        checkNotNull(title);
        checkNotNull(message);
        checkNotNull(callback);

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        TextField field = new TextField();
        alert.setGraphic(field);
        alert.showAndWait();
        if (ButtonType.OK.equals(alert.getResult())) {
            callback.accept(field.getText());
        }
    }

    @Override
    public void confirm(String title,
                        String message,
                        Consumer<Boolean> callback) {
        checkNotNull(title);
        checkNotNull(message);
        checkNotNull(callback);

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
        callback.accept(ButtonType.OK.equals(alert.getResult()));
    }

    private void showAlert(String message,
                           AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(message);
        alert.showAndWait();
    }
}
