package ar.com.oxen.nibiru.mobile.gwt.ui;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import ar.com.oxen.nibiru.mobile.core.api.async.Callback;
import ar.com.oxen.nibiru.mobile.core.api.ui.AlertManager;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.NotificationMole;

public class GwtAlertManager implements AlertManager {
	@Inject
	public GwtAlertManager() {
	}

	@Override
	public void showMessage(String message) {
		checkNotNull(message);
		Window.alert(message);
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
		checkNotNull(exception);
		showMessage(exception.getClass() + ": " + exception.getMessage());
	}

	@Override
	public void prompt(String title, String message, Callback<String> callback) {
		checkNotNull(title);
		checkNotNull(message);
		checkNotNull(callback);
		callback.onSuccess(Window.prompt(title + "\n" + message, ""));
	}

	@Override
	public void confirm(String title, String message, Callback<Boolean> callback) {
		checkNotNull(title);
		checkNotNull(message);
		checkNotNull(callback);
		callback.onSuccess(Window.confirm(title + "\n" + message));
	}
}
