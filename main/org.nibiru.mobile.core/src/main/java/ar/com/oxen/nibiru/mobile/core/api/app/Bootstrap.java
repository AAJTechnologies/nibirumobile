package ar.com.oxen.nibiru.mobile.core.api.app;

/**
 * Component for performing platform-specific startup.
 */
public interface Bootstrap {
	/**
	 * Callback for performing startup.
	 */
	void onBootstrap();
}
