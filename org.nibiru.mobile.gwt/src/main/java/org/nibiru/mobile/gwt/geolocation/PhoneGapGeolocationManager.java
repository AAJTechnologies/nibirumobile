package org.nibiru.mobile.gwt.geolocation;

import com.googlecode.gwtphonegap.client.PhoneGap;
import com.googlecode.gwtphonegap.client.geolocation.Geolocation;
import com.googlecode.gwtphonegap.client.geolocation.GeolocationCallback;
import com.googlecode.gwtphonegap.client.geolocation.GeolocationOptions;
import com.googlecode.gwtphonegap.client.geolocation.PositionError;

import org.nibiru.mobile.core.api.async.Deferred;
import org.nibiru.mobile.core.api.async.Promise;
import org.nibiru.mobile.core.api.geolocation.GeolocationManager;
import org.nibiru.mobile.core.api.geolocation.Position;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class PhoneGapGeolocationManager implements GeolocationManager {
    private final Geolocation geolocation;

    @Inject
    public PhoneGapGeolocationManager(PhoneGap phoneGap) {
        checkNotNull(phoneGap);
        this.geolocation = phoneGap.getGeolocation();
    }

    @Override
    public Promise<Position, Exception> watchPosition() {
        Deferred<Position, Exception> deferred = Deferred.defer();

        GeolocationOptions options = new GeolocationOptions();
        options.setTimeout(1000);

        geolocation.watchPosition(options, new GeolocationCallback() {
            @Override
            public void onSuccess(
                    com.googlecode.gwtphonegap.client.geolocation.Position position) {
                deferred.resolve(new PositionAdapter(position));
            }

            @Override
            public void onFailure(PositionError error) {
                deferred.reject(new PositionException(error));
            }
        });
        return deferred.promise();
    }
}
