package ar.com.oxen.nibiru.mobile.vaadin.ui;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nullable;

import com.google.common.base.Strings;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import ar.com.oxen.nibiru.mobile.core.api.async.Callback;
import ar.com.oxen.nibiru.mobile.core.api.ui.AlertManager;

// TODO: Internationalization.
public class ModalWindowAlertManager implements AlertManager {
	@Override
	public void showMessage(String message) {
		checkNotNull(message);
		showDialog(null, message, null, new Button("Ok"));
	}

	@Override
	public void showException(Exception exception) {
		checkNotNull(exception);
		showDialog(exception.getClass().getName(), exception.getMessage(), null, new Button("Ok"));
	}

	@Override
	public void prompt(String title, String message, final Callback<String> callback) {
		checkNotNull(title);
		checkNotNull(message);
		checkNotNull(callback);
		TextField textField = new TextField();
		showDialog(title, message, textField, new Button("Ok", new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				callback.onSuccess(Strings.nullToEmpty(textField.getValue()));
			}
		}), new Button("Cancel", new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				callback.onSuccess(null);

			}
		}));
	}

	@Override
	public void confirm(String title, String message, final Callback<Boolean> callback) {
		checkNotNull(title);
		checkNotNull(message);
		checkNotNull(callback);
		showDialog(title, message, null, new Button("Ok", new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				callback.onSuccess(true);
			}
		}), new Button("Cancel", new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				callback.onSuccess(false);

			}
		}));
	}

	private void showDialog(String title, String message, @Nullable Component extraComponent, Button... buttons) {
		UI ui = UI.getCurrent();
		final Window dialog = new Window(title);
		dialog.setModal(true);
		ui.addWindow(dialog);

		VerticalLayout content = new VerticalLayout();
		dialog.setContent(content);

		content.addComponent(new Label(message));
		if (extraComponent != null) {
			content.addComponent(extraComponent);
		}
		HorizontalLayout buttonPannel = new HorizontalLayout();
		content.addComponent(buttonPannel);
		for (Button button : buttons) {
			button.addClickListener(new Button.ClickListener() {
				public void buttonClick(ClickEvent event) {
					ui.removeWindow(dialog);
				}
			});
		}
	}
}
