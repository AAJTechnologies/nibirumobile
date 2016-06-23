package org.nibiru.mobile.core.api.ui;

/**
 * UI looper. Used to post callbacks to UI loop.
 */
public interface Looper {
	void post(Runnable runnable);
}
