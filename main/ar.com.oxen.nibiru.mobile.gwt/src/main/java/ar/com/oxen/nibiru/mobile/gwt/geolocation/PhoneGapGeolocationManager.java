package ar.com.oxen.nibiru.mobile.gwt.geolocation;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import ar.com.oxen.nibiru.mobile.core.api.async.Callback;
import ar.com.oxen.nibiru.mobile.core.api.geolocation.GeolocationManager;
import ar.com.oxen.nibiru.mobile.core.api.geolocation.Position;

import com.googlecode.gwtphonegap.client.PhoneGap;
import com.googlecode.gwtphonegap.client.geolocation.Geolocation;
import com.googlecode.gwtphonegap.client.geolocation.GeolocationCallback;
import com.googlecode.gwtphonegap.client.geolocation.GeolocationOptions;
import com.googlecode.gwtphonegap.client.geolocation.PositionError;

public class PhoneGapGeolocationManager implements GeolocationManager {
	private final Geolocation geolocation;

	@Inject
	public PhoneGapGeolocationManager(PhoneGap phoneGap) {
		checkNotNull(phoneGap);
		this.geolocation = phoneGap.getGeolocation();
	}

	@Override
	public void watchPosition(final Callback<Position> callback) {
		checkNotNull(callback);
		GeolocationOptions options = new GeolocationOptions();
		options.setFrequency(1000);

		geolocation.watchPosition(options, new GeolocationCallback() {
			@Override
			public void onSuccess(
					com.googlecode.gwtphonegap.client.geolocation.Position position) {
				callback.onSuccess(new PositionAdapter(position));
			}

			@Override
			public void onFailure(PositionError error) {
				callback.onFailure(new PositionException(error));
			}
		});
	}
}
