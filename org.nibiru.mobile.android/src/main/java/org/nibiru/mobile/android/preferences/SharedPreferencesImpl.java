package org.nibiru.mobile.android.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import org.nibiru.mobile.core.api.preferences.Preferences;
import org.nibiru.mobile.core.impl.preferences.AbstractPreferences;

import javax.annotation.Nullable;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class SharedPreferencesImpl extends AbstractPreferences {
    private final SharedPreferences sharedPreferences;

    @Inject
    public SharedPreferencesImpl(Context context) {
        checkNotNull(context);
        sharedPreferences = context.getSharedPreferences("NibiruPreferences",
                Context.MODE_PRIVATE);
    }

    @Override
    public <T> T getParameter(String key) {
        checkNotNull(key);
        return stringToObject(sharedPreferences.getString(key, null));
    }

    @Override
    public Preferences addParameter(String key, @Nullable Object value) {
        checkNotNull(key);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, objectToString(value));
        editor.commit();
        return this;
    }
}
