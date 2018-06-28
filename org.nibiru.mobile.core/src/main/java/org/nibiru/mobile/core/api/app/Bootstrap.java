package org.nibiru.mobile.core.api.app;

import org.nibiru.async.core.api.promise.Promise;

/**
 * Component for performing platform-specific startup.
 */
public interface Bootstrap {
	/**
	 * Callback for performing startup.
	 */
	Promise<Void, Exception> onBootstrap();
}
