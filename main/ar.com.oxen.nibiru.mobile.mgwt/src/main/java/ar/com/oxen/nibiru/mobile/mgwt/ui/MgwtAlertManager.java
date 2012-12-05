package ar.com.oxen.nibiru.mobile.mgwt.ui;

import ar.com.oxen.nibiru.mobile.core.api.ui.AlertManager;

import com.googlecode.mgwt.ui.client.dialog.Dialogs;

public class MgwtAlertManager implements AlertManager {
	@Override
	public void showMessage(String message) {
		Dialogs.alert("", message, null);
	}

	@Override
	public void showException(Exception exception) {
		this.showMessage(exception.getClass() + ": " + exception.getMessage());
	}
}
