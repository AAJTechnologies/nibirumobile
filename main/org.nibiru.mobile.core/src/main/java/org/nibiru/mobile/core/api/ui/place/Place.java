package org.nibiru.mobile.core.api.ui.place;

import org.nibiru.mobile.core.api.common.Configurable;
import org.nibiru.mobile.core.api.common.Identifiable;

/**
 * A place.
 */
public interface Place extends Identifiable<String>, Configurable<Place> {
	/**
	 * Navigates to a place (no push).
	 */
	void go();

	/**
	 * Navigates to a place.
	 * 
	 * @param push
	 *            <code>true</code> iff previous place must be kept into the
	 *            stack (allows returning later to the current place using
	 *            {@link PlaceManager#back()}).
	 * @param animated
	 *            <code>true</code> iff the the new screen should be shown using
	 *            an animation (it might not be supported on some platforms).
	 */
	void go(boolean push, boolean animated);
}
