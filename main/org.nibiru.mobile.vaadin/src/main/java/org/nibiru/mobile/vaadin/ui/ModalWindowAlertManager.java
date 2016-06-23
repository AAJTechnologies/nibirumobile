package org.nibiru.mobile.vaadin.ui;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nullable;

import org.nibiru.mobile.core.api.async.Callback;
import org.nibiru.mobile.core.api.ui.AlertManager;

import com.google.common.base.Strings;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

// TODO: Internationalization.
public class ModalWindowAlertManager implements AlertManager {
	@Override
	public void showMessage(String message) {
		checkNotNull(message);
		showDialog(null, message, null, new Button("Ok"));
	}

	@Override
	public void showNotification(String message) {
		new Notification("", message).show(Page.getCurrent());
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
		final TextField textField = new TextField();
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
		final UI ui = UI.getCurrent();
		final Window dialog = new Window(Strings.isNullOrEmpty(title) ? "Message" : title);
		ui.addWindow(dialog);
		dialog.setResizable(false);
		dialog.setModal(true);
		dialog.setClosable(false);

		VerticalLayout content = new VerticalLayout();
		content.setMargin(true);
		dialog.setContent(content);

		content.addComponent(new Label(message));
		if (extraComponent != null) {
			content.addComponent(extraComponent);
		}
		HorizontalLayout buttonPannel = new HorizontalLayout();
		content.addComponent(buttonPannel);
		content.setComponentAlignment(buttonPannel, Alignment.MIDDLE_CENTER);
		for (Button button : buttons) {
			buttonPannel.addComponent(button);
			button.addClickListener(new Button.ClickListener() {
				public void buttonClick(ClickEvent event) {
					ui.removeWindow(dialog);
				}
			});
		}
	}
}
