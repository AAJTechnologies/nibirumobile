package org.nibiru.mobile.fx.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

//        Dialog<> dialog = new Dialog<>();
//        dialog.setTitle(title);
//        dialog.setContentText(message);
//        dialog.getDialogPane()
//                .getButtonTypes()
//                .add(ButtonType.OK);
//        dialog.getDialogPane()
//                .getButtonTypes()
//                .add(ButtonType.CANCEL);
//
//        final Button btOk = (Button) dialog.getDialogPane().
//                lookupButton(ButtonType.OK);
//        btOk.addEventFilter(ActionEvent.ACTION, event -> {
//            Optional<String> result = dialog.getResult()
//            if (result.isPresent()) {
//                callback.accept(result.get());
//            }
//        });
//        dialog.show();
    }

    @Override
    public void confirm(String title,
                        String message,
                        Consumer<Boolean> callback) {
        checkNotNull(title);
        checkNotNull(message);
        checkNotNull(callback);
    }

    private void showAlert(String message,
                           AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(message);
        alert.showAndWait();
    }
}
