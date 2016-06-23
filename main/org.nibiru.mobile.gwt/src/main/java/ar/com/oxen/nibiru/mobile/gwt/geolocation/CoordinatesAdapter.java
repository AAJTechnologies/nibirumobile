package ar.com.oxen.nibiru.mobile.gwt.geolocation;

import static com.google.common.base.Preconditions.checkNotNull;
import ar.com.oxen.nibiru.mobile.core.api.geolocation.Coordinates;

public class CoordinatesAdapter implements Coordinates {
	private final com.googlecode.gwtphonegap.client.geolocation.Coordinates coordinates;

	public CoordinatesAdapter(
			com.googlecode.gwtphonegap.client.geolocation.Coordinates coordinates) {
		this.coordinates = checkNotNull(coordinates);
	}

	@Override
	public double getLatitude() {
		return coordinates.getLatitude();
	}

	@Override
	public double getLongitude() {
		return coordinates.getLongitude();
	}

	@Override
	public double getAltitude() {
		return coordinates.getAltitude();
	}

	@Override
	public double getAccuracy() {
		return coordinates.getAccuracy();
	}

	@Override
	public double getSpeed() {
		return coordinates.getSpeed();
	}
}
