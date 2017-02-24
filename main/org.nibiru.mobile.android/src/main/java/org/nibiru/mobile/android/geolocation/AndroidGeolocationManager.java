package org.nibiru.mobile.android.geolocation;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import org.nibiru.mobile.core.api.async.Deferred;
import org.nibiru.mobile.core.api.async.Promise;
import org.nibiru.mobile.core.api.geolocation.GeolocationManager;
import org.nibiru.mobile.core.api.geolocation.Position;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class AndroidGeolocationManager implements GeolocationManager {
    private final LocationManager locationManager;

    @Inject
    public AndroidGeolocationManager(LocationManager locationManager) {
        this.locationManager = checkNotNull(locationManager);
    }

    @Override
    public Promise<Position, Exception> watchPosition() {
        Deferred<Position, Exception> deferred = Deferred.defer();
        // TODO Provider type should be configrable
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1000, 10, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        if (location != null) {
                            deferred.resolve(new LocationAdapter(location));
                        } else {
                            deferred.reject(new IllegalStateException(
                                    "No location read"));
                        }
                    }

                    @Override
                    public void onStatusChanged(String arg0, int arg1,
                                                Bundle arg2) {
                    }

                    @Override
                    public void onProviderEnabled(String arg0) {
                    }

                    @Override
                    public void onProviderDisabled(String arg0) {
                    }

                });
        return deferred.promise();
    }
}
