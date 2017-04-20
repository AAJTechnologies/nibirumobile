package org.nibiru.mobile.gwt.app;

import org.nibiru.async.core.api.promise.Promise;

/**
 * Interface representing database creation process.
 */
public interface DatabaseBootstrap {
    /**
     * Creates the database.
     */
    Promise<Void, Exception> createDatabase();
}
