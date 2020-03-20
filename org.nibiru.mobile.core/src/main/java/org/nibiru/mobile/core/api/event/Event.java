package org.nibiru.mobile.core.api.event;

import org.nibiru.mobile.core.api.common.Configurable;
import org.nibiru.mobile.core.api.common.Identifiable;

import java.io.Serializable;

/**
 * An event.
 */
public interface Event
        extends Identifiable<String>,
        Configurable<Event, Serializable> {
    /**
     * Fires the event.
     */
    void fire();
}
