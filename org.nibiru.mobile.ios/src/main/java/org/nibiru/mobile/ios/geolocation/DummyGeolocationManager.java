package org.nibiru.mobile.ios.geolocation;

import org.nibiru.async.core.api.promise.Deferred;
import org.nibiru.async.core.api.promise.Promise;
import org.nibiru.mobile.core.api.geolocation.GeolocationManager;
import org.nibiru.mobile.core.api.geolocation.Position;

public class DummyGeolocationManager implements GeolocationManager {
    @Override
    public Promise<Position, Exception> watchPosition() {
        // TODO Implement Location manager
        Deferred<Position, Exception> deferred = Deferred.defer();
        return deferred.promise();
    }
}
