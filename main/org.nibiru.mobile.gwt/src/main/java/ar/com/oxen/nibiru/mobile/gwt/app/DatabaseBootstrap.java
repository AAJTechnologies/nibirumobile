package ar.com.oxen.nibiru.mobile.gwt.app;

import ar.com.oxen.nibiru.mobile.core.api.async.Callback;

/**
 * Interface representing database creation process.
 */
public interface DatabaseBootstrap {
	/**
	 * Creates the database.
	 * 
	 * @param callback
	 *            A callback notifying the process end
	 */
	void createDatabase(Callback<Void> callback);
}
