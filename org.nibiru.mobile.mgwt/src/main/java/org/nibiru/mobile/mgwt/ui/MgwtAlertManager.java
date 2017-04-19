package org.nibiru.mobile.mgwt.ui;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.NotificationMole;
import com.googlecode.mgwt.ui.client.widget.dialog.ConfirmDialog.ConfirmCallback;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs;

import org.nibiru.mobile.core.api.common.Consumer;
import org.nibiru.mobile.core.api.ui.AlertManager;

import static com.google.common.base.Preconditions.checkNotNull;

public class MgwtAlertManager implements AlertManager {
    @Override
    public void showMessage(String message) {
        Dialogs.alert("", message, null);
    }

    @Override
    public void showNotification(String message) {
        NotificationMole nm = new NotificationMole();
        nm.setAnimationDuration(2000);
        nm.setMessage(message);
        nm.show();
    }

    @Override
    public void showException(Exception exception) {
        showMessage(exception.getClass() + ": " + exception.getMessage());
    }

    @Override
    public void prompt(String title, String message,
                       Consumer<String> callback) {
        checkNotNull(title);
        checkNotNull(message);
        checkNotNull(callback);
        callback.accept(Window.prompt(title + "\n" + message, ""));
    }

    @Override
    public void confirm(String title, String message,
                        Consumer<Boolean> callback) {
        checkNotNull(title);
        checkNotNull(message);
        checkNotNull(callback);
        Dialogs.confirm(title, message, new ConfirmCallback() {
            @Override
            public void onOk() {
                callback.accept(true);
            }

            @Override
            public void onCancel() {
                callback.accept(false);
            }
        });
    }
}
