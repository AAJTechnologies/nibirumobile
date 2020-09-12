package org.nibiru.mobile.java.preferences;

import org.nibiru.mobile.core.api.preferences.Preferences;
import org.nibiru.mobile.core.impl.preferences.AbstractPreferences;

import javax.annotation.Nonnull;
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
    public <T> T getParameter(@Nonnull String key) {
        checkNotNull(key);
        return stringToObject(javaPreferences.get(key, null));
    }

    @Override
    public Preferences addParameter(@Nonnull String key,
                                    @Nullable Object value) {
        checkNotNull(key);
        try {
            if (value != null) {
                javaPreferences.put(key, objectToString(value));
            } else {
                javaPreferences.remove(key);
            }
            javaPreferences.flush();
            return this;
        } catch (BackingStoreException e) {
            throw new RuntimeException(e);
        }
    }
}
