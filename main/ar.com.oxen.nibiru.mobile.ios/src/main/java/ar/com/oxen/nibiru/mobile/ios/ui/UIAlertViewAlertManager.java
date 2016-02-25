package ar.com.oxen.nibiru.mobile.ios.ui;

import static com.google.common.base.Preconditions.checkNotNull;

import org.robovm.apple.uikit.UIAlertView;
import org.robovm.apple.uikit.UIAlertViewDelegate;
import org.robovm.apple.uikit.UITextView;

import ar.com.oxen.nibiru.mobile.core.api.async.Callback;
import ar.com.oxen.nibiru.mobile.core.api.ui.AlertManager;

import com.google.common.base.Strings;

// TODO: Internationalization.
public class UIAlertViewAlertManager implements AlertManager {
	@Override
	public void showMessage(String message) {
		checkNotNull(message);
		alertView(null, message).show();
	}

	@Override
	public void showNotification(String message) {
		showMessage(message);
	}

	@Override
	public void showException(Exception exception) {
		checkNotNull(exception);
		alertView(exception.getClass().getName(), exception.getMessage())
				.show();
	}

	@Override
	public void prompt(String title, String message,
			final Callback<String> callback) {
		UIAlertView alertView = alertView(title, message);
		final UITextView textView = new UITextView();
		alertView.addSubview(textView);
		alertView.setDelegate(new SimpleDelegate() {
			@Override
			public void clicked(UIAlertView view, long paramLong) {
				callback.onSuccess(Strings.nullToEmpty(textView.getText()));
			}

			@Override
			public void cancel(UIAlertView view) {
				callback.onSuccess(null);
			}
		});
		alertView.show();
	}

	@Override
	public void confirm(String title, String message,
			final Callback<Boolean> callback) {
		UIAlertView alertView = alertView(title, message);
		alertView.setDelegate(new SimpleDelegate() {
			@Override
			public void clicked(UIAlertView view, long paramLong) {
				callback.onSuccess(true);
			}

			@Override
			public void cancel(UIAlertView view) {
				callback.onSuccess(false);
			}
		});
		alertView.show();
		// TODO Auto-generated method stub

	}

	private UIAlertView alertView(String title, String message) {
		UIAlertView alertView = new UIAlertView();
		alertView.setTitle(message);
		alertView.addButton("Ok");
		alertView.setTitle(title);
		alertView.setMessage(message);
		return alertView;
	}

	private static class SimpleDelegate implements UIAlertViewDelegate {

		@Override
		public void cancel(UIAlertView view) {
			// TODO Auto-generated method stub

		}

		@Override
		public void clicked(UIAlertView view, long paramLong) {
		}

		@Override
		public void didDismiss(UIAlertView view, long paramLong) {
		}

		@Override
		public void didPresent(UIAlertView view) {
		}

		@Override
		public boolean shouldEnableFirstOtherButton(UIAlertView view) {
			return false;
		}

		@Override
		public void willDismiss(UIAlertView view, long paramLong) {
		}

		@Override
		public void willPresent(UIAlertView view) {
		}
	}
}
