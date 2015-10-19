package ar.com.oxen.nibiru.mobile.core.api.event;

import com.aajtech.model.core.api.Registration;

/**
 * An event bus, for implementing a publish-subscribe model.
 * 
 */
public interface EventBus {
	/**
	 * Creates an event.
	 * 
	 * @param id
	 *            The event id
	 * @return The event
	 */
	Event createEvent(String id);

	/**
	 * Creates an event.
	 * 
	 * @param id
	 *            The event id
	 * @return The event
	 */
	Event createEvent(Enum<?> id);

	/**
	 * Adds a handler for listening on an specific event.
	 * 
	 * @param eventId
	 *            The event id to be listen
	 * @param handler
	 *            The handler
	 * @return A registration to the event
	 */
	Registration addHandler(String eventId, EventHandler handler);

	/**
	 * Adds a handler for listening on an specific event.
	 * 
	 * @param eventId
	 *            The event id to be listen
	 * @param handler
	 *            The handler
	 * @return A registration to the event
	 */
	Registration addHandler(Enum<?> eventId, EventHandler handler);
}
