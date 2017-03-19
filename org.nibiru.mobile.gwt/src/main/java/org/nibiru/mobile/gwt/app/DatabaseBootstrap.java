package org.nibiru.mobile.gwt.app;

import org.nibiru.mobile.core.api.async.Promise;

/**
 * Interface representing database creation process.
 */
public interface DatabaseBootstrap {
    /**
     * Creates the database.
     */
    Promise<Void, Exception> createDatabase();
}
