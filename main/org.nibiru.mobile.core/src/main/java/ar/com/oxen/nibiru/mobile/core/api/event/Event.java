package ar.com.oxen.nibiru.mobile.core.api.event;

import ar.com.oxen.nibiru.mobile.core.api.common.Configurable;
import ar.com.oxen.nibiru.mobile.core.api.common.Identifiable;

/**
 * An event.
 */
public interface Event extends Identifiable<String>, Configurable<Event> {
	/**
	 * Fires the event.
	 */
	void fire();
}
