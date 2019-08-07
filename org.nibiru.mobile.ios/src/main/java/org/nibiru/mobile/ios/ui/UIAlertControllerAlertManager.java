package org.nibiru.mobile.ios.ui;

import com.google.common.base.Strings;

import org.nibiru.mobile.core.api.common.Consumer;
import org.nibiru.mobile.core.api.ui.AlertManager;

import javax.inject.Inject;
import javax.inject.Provider;

import apple.uikit.UIAlertAction;
import apple.uikit.UIAlertController;
import apple.uikit.UIApplication;
import apple.uikit.UINavigationController;
import apple.uikit.UITextField;
import apple.uikit.UITextView;
import apple.uikit.enums.UIAlertActionStyle;
import apple.uikit.enums.UIAlertControllerStyle;

import static com.google.common.base.Preconditions.checkNotNull;

// TODO: Internationalization.
public class UIAlertControllerAlertManager implements AlertManager {
    private final Provider<UINavigationController> navigationControllerProvider;

    @Inject
    public UIAlertControllerAlertManager(Provider<UINavigationController> navigationControllerProvider) {
        this.navigationControllerProvider = checkNotNull(navigationControllerProvider);
    }

    @Override
    public void showMessage(String message) {
        checkNotNull(message);
        show(messageAlertController(null, message));
    }

    @Override
    public void showNotification(String message) {
        showMessage(message);
    }

    @Override
    public void showException(Exception exception) {
        checkNotNull(exception);
        show(messageAlertController(exception.getClass().getName(),
                Strings.nullToEmpty(exception.getMessage())));
    }

    @Override
    public void prompt(String title, String message, Consumer<String> callback) {
        new Prompt(title, message, callback).show();
    }

    @Override
    public void confirm(String title, String message, Consumer<Boolean> callback) {
        UIAlertController alertController = alertController(title, message);
        alertController.addAction(UIAlertAction.actionWithTitleStyleHandler("Ok", UIAlertActionStyle.Default, (UIAlertAction action) -> {
            close();
            callback.accept(true);
        }));
        alertController.addAction(UIAlertAction.actionWithTitleStyleHandler("Cancel", UIAlertActionStyle.Cancel, (UIAlertAction action) -> {
            close();
            callback.accept(false);
        }));
        show(alertController);
    }

    private UIAlertController messageAlertController(String title, String message) {
        UIAlertController alertController = alertController(title, message);
        alertController.addAction(UIAlertAction.actionWithTitleStyleHandler("Ok", UIAlertActionStyle.Default, (UIAlertAction action) -> {
            close();
        }));
        return alertController;
    }

    private static UIAlertController alertController(String title, String message) {
        return UIAlertController.alertControllerWithTitleMessagePreferredStyle(title, message, UIAlertControllerStyle.Alert);
    }

    private void show(UIAlertController alertController) {
        UIApplication.sharedApplication().keyWindow().rootViewController().presentViewControllerAnimatedCompletion(alertController, true, null);
    }

    private void close() {
        navigationControllerProvider.get().popViewControllerAnimated(true);
    }

    private class Prompt {
        private final UIAlertController alertController;
        private UITextField textView;

        private Prompt(String title, String message, Consumer<String> callback) {
            alertController = alertController(title, message);
            alertController.addTextFieldWithConfigurationHandler(uiTextField -> textView = uiTextField);
            alertController.addAction(UIAlertAction.actionWithTitleStyleHandler("Ok", UIAlertActionStyle.Default, (UIAlertAction action) -> {
                close();
                callback.accept(Strings.nullToEmpty(textView.text()));
            }));
            alertController.addAction(UIAlertAction.actionWithTitleStyleHandler("Cancel", UIAlertActionStyle.Cancel, (UIAlertAction action) -> {
                close();
            }));
        }

        private void show() {
            UIAlertControllerAlertManager.this.show(alertController);
        }
    }
}
