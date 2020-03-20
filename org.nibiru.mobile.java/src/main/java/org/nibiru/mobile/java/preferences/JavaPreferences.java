package org.nibiru.mobile.java.preferences;

import org.nibiru.mobile.core.api.preferences.Preferences;
import org.nibiru.mobile.core.impl.preferences.AbstractPreferences;

import javax.annotation.Nullable;
import javax.inject.Inject;

import java.util.prefs.BackingStoreException;

import static com.google.common.base.Preconditions.checkNotNull;

public class JavaPreferences
        extends AbstractPreferences {
    private final java.util.prefs.Preferences javaPreferences;

    @Inject
    public JavaPreferences() {
        javaPreferences = java.util.prefs.Preferences
                .userNodeForPackage(JavaPreferences.class);
    }

    @Override
    public <T> T getParameter(String key) {
        checkNotNull(key);
        return stringToObject(javaPreferences.get(key, null));
    }

    @Override
    public Preferences addParameter(String key,
                                    @Nullable Object value) {
        checkNotNull(key);
        try {
            javaPreferences.put(key, objectToString(value));
            javaPreferences.flush();
            return this;
        } catch (BackingStoreException e) {
            throw new RuntimeException(e);
        }
    }
}
