package ar.com.oxen.nibiru.mobile.core.api.event;

/**
 * A handler for listening events.
 */
public interface EventHandler {
	/**
	 * Callback method called when the event is fired.
	 * 
	 * @param event
	 *            The event
	 */
	void onEvent(Event event);
}
