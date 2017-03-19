package org.nibiru.mobile.dotnet.ui;

import org.nibiru.mobile.core.api.common.Consumer;
import org.nibiru.mobile.core.api.ui.AlertManager;

import javax.inject.Inject;

import cli.System.Windows.MessageBox;

import static com.google.common.base.Preconditions.checkNotNull;

// TODO: Internationalization.
public class MessageBoxAlertManager implements AlertManager {
    @Inject
    public MessageBoxAlertManager() {
    }

    @Override
    public void showMessage(String message) {
        checkNotNull(message);
        MessageBox.Show("Message", message);
    }

    @Override
    public void showNotification(String message) {
        showMessage(message);
    }

    @Override
    public void showException(Exception exception) {
        checkNotNull(exception);
        MessageBox.Show("Error", exception.getClass().getName() + ": " + exception.getMessage());
    }

    @Override
    public void prompt(String title,
                       String message,
                       Consumer<String> callback) {
        checkNotNull(title);
        checkNotNull(message);
        checkNotNull(callback);
        // TODO: not implemented in windows forms.
        MessageBox.Show(title, message);
    }

    @Override
    public void confirm(String title, String message, Consumer<Boolean> callback) {
        checkNotNull(title);
        checkNotNull(message);
        checkNotNull(callback);
        // TODO: not implemented in windows forms.
        MessageBox.Show(title, message);
    }
}
