package org.nibiru.mobile.android.geolocation;

import android.location.Location;

import org.nibiru.mobile.core.api.geolocation.Coordinates;
import org.nibiru.mobile.core.api.geolocation.Position;

import static com.google.common.base.Preconditions.checkNotNull;

public class LocationAdapter implements Position, Coordinates {
	private final Location location;

	public LocationAdapter(Location location) {
		this.location = checkNotNull(location);
	}

	@Override
	public Coordinates getCoordinates() {
		return this;
	}

	@Override
	public long getTimeStamp() {
		return location.getTime();
	}

	@Override
	public double getLatitude() {
		return location.getLatitude();
	}

	@Override
	public double getLongitude() {
		return location.getLongitude();
	}

	@Override
	public double getAltitude() {
		return location.getAltitude();
	}

	@Override
	public double getAccuracy() {
		return location.getAccuracy();
	}

	@Override
	public double getSpeed() {
		return location.getSpeed();
	}
}
