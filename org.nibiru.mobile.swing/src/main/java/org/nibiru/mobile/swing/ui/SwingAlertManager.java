package org.nibiru.mobile.swing.ui;

import com.google.common.base.Strings;

import org.nibiru.mobile.core.api.common.Consumer;
import org.nibiru.mobile.core.api.ui.AlertManager;

import javax.inject.Inject;
import javax.swing.JOptionPane;

import static com.google.common.base.Preconditions.checkNotNull;

// TODO: Internationalization.
public class SwingAlertManager implements AlertManager {
    @Inject
    public SwingAlertManager() {
    }

    @Override
    public void showMessage(String message) {
        checkNotNull(message);
        JOptionPane.showMessageDialog(null,
                message);
    }

    @Override
    public void showNotification(String message) {
        showMessage(message);
    }

    @Override
    public void showException(Exception exception) {
        checkNotNull(exception);
        JOptionPane.showMessageDialog(null,
                exception.getMessage(),
                exception.getClass().getName(),
                JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void prompt(String title, String message, Consumer<String> callback) {
        String result = (String) JOptionPane.showInputDialog(
                null,
                message,
                title,
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");
        callback.accept(Strings.nullToEmpty(result));
    }

    @Override
    public void confirm(String title, String message, Consumer<Boolean> callback) {
        int result = JOptionPane.showConfirmDialog(
                null,
                message,
                title,
                JOptionPane.YES_NO_OPTION);
        callback.accept(result == 0);
    }
}
