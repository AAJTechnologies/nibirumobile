package org.nibiru.mobile.android.ui;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import org.nibiru.mobile.core.api.async.Callback;
import org.nibiru.mobile.core.api.ui.AlertManager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class DialogAlertManager implements AlertManager {
	private final Context context;

	@Inject
	public DialogAlertManager(Context context) {
		this.context = checkNotNull(context);
	}

	@Override
	public void showMessage(String message) {
		checkNotNull(message);
		builder(null, message).show();
	}

	@Override
	public void showNotification(String message) {
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}

	@Override
	public void showException(Exception exception) {
		checkNotNull(exception);
		builder(exception.getClass().getName(), exception.getMessage()).show();
	}

	@Override
	public void prompt(String title, String message,
			final Callback<String> callback) {
		checkNotNull(title);
		checkNotNull(message);
		checkNotNull(callback);
		final EditText editText = new EditText(context);
		// TODO: Internationalization
		builder(title, message)
			.setView(editText)
			.setPositiveButton("Ok", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int id) {
					callback.onSuccess(editText.getText().toString());
				}
			})
			.setNegativeButton("Cancel", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int id) {
					callback.onSuccess(null);
				}
			})
			.show();
	}

	@Override
	public void confirm(String title, String message,
			final Callback<Boolean> callback) {
		checkNotNull(title);
		checkNotNull(message);
		checkNotNull(callback);
		// TODO: Internationalization
		builder(title, message)
			.setPositiveButton("Yes", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int id) {
					callback.onSuccess(true);
				}
			})
			.setNegativeButton("No", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int id) {
					callback.onSuccess(false);
				}
			})
			.show();
	}

	private AlertDialog.Builder builder(String title, String message) {
		return new AlertDialog.Builder(context)
			.setTitle(title)
			.setMessage(message);
	}
}
