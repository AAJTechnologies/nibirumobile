package org.nibiru.mobile.core.api.geolocation;

import org.nibiru.mobile.core.api.async.Callback;

/**
 * Manager for accessing geolocation information.
 */
public interface GeolocationManager {
	/**
	 * Watches position changes.
	 * 
	 * @param callback
	 *            A callback for notifying positon changes
	 */
	void watchPosition(Callback<Position> callback);
}
