package org.nibiru.mobile.java.event.guava;

import com.google.common.collect.Maps;
import com.google.common.eventbus.EventBus;
import org.nibiru.mobile.core.api.event.Event;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

class GuavaEvent implements Event {
    private final String id;
    private final EventBus eventBus;
    private final Map<String, Object> parameters;

    GuavaEvent(String id, EventBus eventBus) {
        this.id = checkNotNull(id);
        this.eventBus = checkNotNull(eventBus);
        parameters = Maps.newHashMap();
    }

    @Override
    public String getId() {
        return id;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Serializable> T getParameter(String key) {
        checkNotNull(key);
        return (T) parameters.get(key);
    }

    @Override
    public Event addParameter(String key,
                              @Nullable Serializable value) {
        checkNotNull(key);
        parameters.put(key, value);
        return this;
    }

    @Override
    public void fire() {
        eventBus.post(this);
    }
}
