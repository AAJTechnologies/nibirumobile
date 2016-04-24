package ar.com.oxen.nibiru.mobile.core.api.ui;

/**
 * Interface providing display information.
 */
public interface DisplayInfo {
	/**
	 * @return <code>true</code> if the device is a phone or watch.
	 *         <code>true</code> on tablets and desktop
	 */
	boolean isSmall();
}
