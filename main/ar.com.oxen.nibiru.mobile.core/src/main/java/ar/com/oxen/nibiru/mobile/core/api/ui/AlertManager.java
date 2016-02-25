package ar.com.oxen.nibiru.mobile.core.api.ui;

import ar.com.oxen.nibiru.mobile.core.api.async.Callback;

/**
 * A manager for showing messages to user.
 */
public interface AlertManager {
	/**
	 * Shows an informative message.
	 * 
	 * @param message
	 *            The message
	 */
	void showMessage(String message);

	/**
	 * Shows a non-blocking notification.
	 * Some platforms might not support it and show a blocking message.
	 * 
	 * @param message
	 *            The message
	 */
	void showNotification(String message);

	/**
	 * Shows an error message.
	 * 
	 * @param exception
	 *            The exception which generated the error
	 */
	void showException(Exception exception);

	/**
	 * Prompts the user for a value.
	 * 
	 * @param title
	 *            The title
	 * @param message
	 *            The message
	 */
	void prompt(String title, String message, Callback<String> callback);

	/**
	 * Asks the user for a confirmation.
	 * 
	 * @param title
	 *            The title
	 * @param message
	 *            The message
	 */
	void confirm(String title, String message, Callback<Boolean> callback);
}
