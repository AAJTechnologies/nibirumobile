package org.nibiru.mobile.ios.ui;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;
import javax.inject.Provider;

import org.nibiru.mobile.core.api.async.Callback;
import org.nibiru.mobile.core.api.ui.AlertManager;
import org.robovm.apple.uikit.UIAlertAction;
import org.robovm.apple.uikit.UIAlertActionStyle;
import org.robovm.apple.uikit.UIAlertController;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UINavigationController;
import org.robovm.apple.uikit.UITextView;

import com.google.common.base.Strings;

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
		show(messageAlertController(exception.getClass().getName(), exception.getMessage()));
	}

	@Override
	public void prompt(String title, String message, final Callback<String> callback) {
		UIAlertController alertController = alertController(title, message);
		final UITextView textView = new UITextView();
		alertController.getView().addSubview(textView);
		alertController.addAction(new UIAlertAction("Ok", UIAlertActionStyle.Default, (UIAlertAction action) -> {
			close();
			callback.onSuccess(Strings.nullToEmpty(textView.getText()));
		}));
		alertController.addAction(new UIAlertAction("Cancel", UIAlertActionStyle.Cancel, (UIAlertAction action) -> {
			close();
		}));
		show(alertController);
	}

	@Override
	public void confirm(String title, String message, final Callback<Boolean> callback) {
		UIAlertController alertController = alertController(title, message);
		alertController.addAction(new UIAlertAction("Ok", UIAlertActionStyle.Default, (UIAlertAction action) -> {
			close();
			callback.onSuccess(true);
		}));
		alertController.addAction(new UIAlertAction("Cancel", UIAlertActionStyle.Cancel, (UIAlertAction action) -> {
			close();
			callback.onSuccess(false);
		}));
		show(alertController);
	}

	private UIAlertController messageAlertController(String title, String message) {
		UIAlertController alertController = alertController(title, message);
		alertController.addAction(new UIAlertAction("Ok", UIAlertActionStyle.Default, (UIAlertAction action) -> {
			close();
		}));
		return alertController;
	}

	private UIAlertController alertController(String title, String message) {
		UIAlertController alertController = new UIAlertController();
		alertController.setTitle(title);
		alertController.setMessage(message);
		return alertController;
	}

	private void show(UIAlertController alertController) {
		UIApplication.getSharedApplication().getKeyWindow().getRootViewController().presentViewController(alertController, true, null);
		//navigationControllerProvider.get().presentViewController(alertController, true, null);
	}

	private void close() {
		navigationControllerProvider.get().popViewController(true);
	}
}
