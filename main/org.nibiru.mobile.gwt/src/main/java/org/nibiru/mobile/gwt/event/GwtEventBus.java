package org.nibiru.mobile.gwt.event;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;

import javax.inject.Inject;

import org.nibiru.mobile.core.api.event.Event;
import org.nibiru.mobile.core.api.event.EventBus;
import org.nibiru.mobile.core.api.event.EventHandler;
import org.nibiru.model.core.api.Registration;

import com.google.common.collect.Maps;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class GwtEventBus implements EventBus {
	private final com.google.web.bindery.event.shared.EventBus eventBus;
	private final Map<String, GwtEvent.Type<SimpleEventHandler>> eventTypes;

	@Inject
	public GwtEventBus(com.google.web.bindery.event.shared.EventBus eventBus) {
		this.eventBus = checkNotNull(eventBus);
		this.eventTypes = Maps.newHashMap();
	}

	@Override
	public Event createEvent(String id) {
		checkNotNull(id);
		return new SimpleEvent(id, getType(id), eventBus);
	}

	@Override
	public Event createEvent(Enum<?> id) {
		checkNotNull(id);
		return createEvent(id.toString());
	}

	@Override
	public Registration addHandler(String eventId, final EventHandler handler) {
		checkNotNull(eventId);
		checkNotNull(handler);
		final HandlerRegistration handlerRegistration = eventBus.addHandler(getType(eventId), new SimpleEventHandler() {
			@Override
			public void onEvent(SimpleEvent event) {
				handler.onEvent(event);
			}
		});
		return new Registration() {
			@Override
			public void remove() {
				handlerRegistration.removeHandler();
			}
		};
	}

	@Override
	public Registration addHandler(Enum<?> eventId, EventHandler handler) {
		checkNotNull(eventId);
		checkNotNull(handler);
		return addHandler(eventId.toString(), handler);
	}

	private GwtEvent.Type<SimpleEventHandler> getType(String eventId) {
		GwtEvent.Type<SimpleEventHandler> type = eventTypes.get(eventId);
		if (type == null) {
			type = new Type<SimpleEventHandler>();
			eventTypes.put(eventId, type);
		}
		return type;
	}
}
