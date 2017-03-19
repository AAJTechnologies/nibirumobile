package org.nibiru.mobile.core.api.event;

import org.nibiru.mobile.core.api.common.Configurable;
import org.nibiru.mobile.core.api.common.Identifiable;

/**
 * An event.
 */
public interface Event extends Identifiable<String>, Configurable<Event> {
	/**
	 * Fires the event.
	 */
	void fire();
}
