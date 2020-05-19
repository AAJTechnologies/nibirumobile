package org.nibiru.mobile.core.api.ui.place;

import com.google.common.collect.Maps;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

class GenericPlace implements Place {
    private final PlaceManager placeManager;
    private final String id;
    private final Map<String, Object> parameters;

    GenericPlace(@Nonnull PlaceManager placeManager,
                 @Nonnull String id) {
        this.placeManager = checkNotNull(placeManager);
        this.id = checkNotNull(id);
        this.parameters = Maps.newHashMap();
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
    public Place addParameter(String key,
                              @Nullable Serializable value) {
        checkNotNull(key);
        parameters.put(key, value);
        return this;
    }

    @Override
    public void go(boolean push,
                   boolean animated) {
        placeManager.go(this, push, animated);
    }
}
