package org.nibiru.mobile.teavm.ui;

import org.teavm.jso.browser.Window;

import org.nibiru.mobile.core.api.common.Consumer;
import org.nibiru.mobile.core.api.ui.AlertManager;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class TeaVmAlertManager implements AlertManager {
	@Inject
	public TeaVmAlertManager() {
	}

	@Override
	public void showMessage(String message) {
		checkNotNull(message);
		Window.alert(message);
	}

	@Override
	public void showNotification(String message) {
		Window.alert(message); // TODO: This should be a  toas like notification
	}

	@Override
	public void showException(Exception exception) {
		checkNotNull(exception);
		showMessage(exception.getClass() + ": " + exception.getMessage());
	}

	@Override
	public void prompt(String title, String message, Consumer<String> callback) {
		checkNotNull(title);
		checkNotNull(message);
		checkNotNull(callback);
		callback.accept(Window.prompt(title + "\n" + message, ""));
	}

	@Override
	public void confirm(String title, String message, Consumer<Boolean> callback) {
		checkNotNull(title);
		checkNotNull(message);
		checkNotNull(callback);
		callback.accept(Window.confirm(title + "\n" + message));
	}
}
