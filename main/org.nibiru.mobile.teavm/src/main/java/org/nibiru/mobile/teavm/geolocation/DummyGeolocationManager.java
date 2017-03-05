package org.nibiru.mobile.teavm.geolocation;

import org.nibiru.mobile.core.api.async.Deferred;
import org.nibiru.mobile.core.api.async.Promise;
import org.nibiru.mobile.core.api.geolocation.GeolocationManager;
import org.nibiru.mobile.core.api.geolocation.Position;

import javax.inject.Inject;

// TODO: Implement this
public class DummyGeolocationManager implements GeolocationManager {

    @Inject
    public DummyGeolocationManager() {
    }

    @Override
    public Promise<Position, Exception> watchPosition() {
        Deferred<Position, Exception> deferred = Deferred.defer();
        return deferred.promise();
    }
}
