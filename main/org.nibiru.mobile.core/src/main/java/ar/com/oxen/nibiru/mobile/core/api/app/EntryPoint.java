package ar.com.oxen.nibiru.mobile.core.api.app;

/**
 * Component for performing application-specific startup.
 */
public interface EntryPoint {
	/**
	 * Callback for performing startup.
	 */
	void onApplicationStart();
}
