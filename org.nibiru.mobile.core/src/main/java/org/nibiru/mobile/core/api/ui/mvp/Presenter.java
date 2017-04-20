package org.nibiru.mobile.core.api.ui.mvp;

import org.nibiru.mobile.core.api.ui.place.Place;

/**
 * A presenter.
 * 
 * @param <V>
 *            The view type
 */
public interface Presenter<V extends View> {
	/**
	 * @return The associated view
	 */
	V getView();

	/**
	 * Startup method called when the presenter is initialized.
	 * 
	 * @param place
	 *            The calling place
	 */
	void go(Place place);

	/**
	 * Callback method called when entering the presenter.
	 */
	void onActivate();

	/**
	 * Callback method called when leaving the presenter.
	 */
	void onDeactivate();
}
