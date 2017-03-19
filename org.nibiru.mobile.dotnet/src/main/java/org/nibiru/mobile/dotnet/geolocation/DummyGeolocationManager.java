package org.nibiru.mobile.dotnet.geolocation;

import org.nibiru.mobile.core.api.async.Deferred;
import org.nibiru.mobile.core.api.async.Promise;
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
