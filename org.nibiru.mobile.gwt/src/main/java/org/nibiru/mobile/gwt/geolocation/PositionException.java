package org.nibiru.mobile.gwt.geolocation;

import static com.google.common.base.Preconditions.checkNotNull;

import com.googlecode.gwtphonegap.client.geolocation.PositionError;

public class PositionException extends RuntimeException {
	private static final long serialVersionUID = -6777629815610377078L;
	private final PositionError positionError;

	public PositionException(PositionError positionError) {
		this.positionError = checkNotNull(positionError);
	}

	public PositionError getPositionError() {
		return positionError;
	}
}
