package ar.com.oxen.nibiru.mobile.android.geolocation;

import static com.google.common.base.Preconditions.checkNotNull;
import android.location.Location;
import ar.com.oxen.nibiru.mobile.core.api.geolocation.Coordinates;
import ar.com.oxen.nibiru.mobile.core.api.geolocation.Position;

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
