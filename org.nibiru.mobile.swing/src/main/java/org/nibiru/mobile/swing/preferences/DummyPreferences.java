package org.nibiru.mobile.swing.preferences;

import com.google.common.collect.Maps;

import org.nibiru.mobile.core.api.preferences.Preferences;
import org.nibiru.mobile.core.impl.preferences.AbstractPreferences;

import java.util.Map;

import javax.annotation.Nullable;
import javax.inject.Inject;

public class DummyPreferences extends AbstractPreferences {
    private final Map<String, String> prefs;

    @Inject
    public DummyPreferences() {
        prefs = Maps.newHashMap();
    }

    @Override
    public <T> T getParameter(String key) {
        return stringToObject(prefs.get(key));
    }

    @Override
    public Preferences addParameter(String key, @Nullable Object value) {
        prefs.put(key, objectToString(value));
        return this;
    }
}
