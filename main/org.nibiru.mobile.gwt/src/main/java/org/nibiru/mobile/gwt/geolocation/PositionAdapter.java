package org.nibiru.mobile.gwt.geolocation;

import static com.google.common.base.Preconditions.checkNotNull;

import org.nibiru.mobile.core.api.geolocation.Coordinates;
import org.nibiru.mobile.core.api.geolocation.Position;

public class PositionAdapter implements Position {
	private final com.googlecode.gwtphonegap.client.geolocation.Position position;

	public PositionAdapter(
			com.googlecode.gwtphonegap.client.geolocation.Position position) {
		this.position = checkNotNull(position);
	}

	@Override
	public Coordinates getCoordinates() {
		return new CoordinatesAdapter(position.getCoordinates());
	}

	@Override
	public long getTimeStamp() {
		return position.getTimeStamp();
	}
}
