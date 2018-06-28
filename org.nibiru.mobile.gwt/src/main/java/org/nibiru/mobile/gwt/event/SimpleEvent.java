package org.nibiru.mobile.gwt.event;

import com.google.common.collect.Maps;
import com.google.gwt.event.shared.GwtEvent;
import com.google.web.bindery.event.shared.EventBus;

import org.nibiru.mobile.core.api.event.Event;

import java.util.Map;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

public class SimpleEvent extends GwtEvent<SimpleEventHandler> implements Event {
    private final String id;
    private final GwtEvent.Type<SimpleEventHandler> associatedType;
    private final EventBus eventBus;
    private final Map<String, Object> parameters;

    public SimpleEvent(String id, Type<SimpleEventHandler> associatedType,
                       EventBus eventBus) {
        this.id = checkNotNull(id);
        this.associatedType = checkNotNull(associatedType);
        this.eventBus = checkNotNull(eventBus);
        this.parameters = Maps.newHashMap();
    }

    @Override
    public GwtEvent.Type<SimpleEventHandler> getAssociatedType() {
        return associatedType;
    }

    @Override
    protected void dispatch(SimpleEventHandler handler) {
        checkNotNull(handler);
        handler.onEvent(this);
    }

    @Override
    public String getId() {
        return id;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getParameter(String key) {
        checkNotNull(key);
        return (T) parameters.get(key);
    }

    @Override
    public <T> T getParameter(Enum<?> key) {
        checkNotNull(key);
        return getParameter(key.toString());
    }

    @Override
    public Event addParameter(String key, @Nullable Object value) {
        checkNotNull(key);
        parameters.put(key, value);
        return this;
    }

    @Override
    public Event addParameter(Enum<?> key, @Nullable Object value) {
        checkNotNull(key);
        return addParameter(key.toString(), value);
    }

    @Override
    public void fire() {
        eventBus.fireEvent(this);
    }
}
