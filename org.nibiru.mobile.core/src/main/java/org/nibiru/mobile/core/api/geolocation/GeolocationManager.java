package org.nibiru.mobile.core.api.geolocation;


import org.nibiru.async.core.api.promise.Promise;

/**
 * Manager for accessing geolocation information.
 */
public interface GeolocationManager {
    /**
     * Watches position changes.
     */
    Promise<Position, Exception> watchPosition();
}
