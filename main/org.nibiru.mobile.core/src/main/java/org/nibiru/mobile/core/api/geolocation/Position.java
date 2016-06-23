package org.nibiru.mobile.core.api.geolocation;

/**
 * A location read at some moment.
 */
public interface Position {
	/**
	 * @return The coordinates
	 */
	public Coordinates getCoordinates();

	/**
	 * @return The timestamp
	 */
	public long getTimeStamp();
}
