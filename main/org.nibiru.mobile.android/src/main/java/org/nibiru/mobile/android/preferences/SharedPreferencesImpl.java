package org.nibiru.mobile.android.preferences;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import org.nibiru.mobile.core.api.preferences.Preferences;
import org.nibiru.mobile.core.impl.preferences.AbstractPreferences;

import android.content.Context;
import android.content.SharedPreferences;

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
		return objectFromString(sharedPreferences.getString(key, null));
	}

	@Override
	public Preferences addParameter(String key, Object value) {
		checkNotNull(key);
		checkNotNull(value);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(key, stringFromObject(value));
		editor.commit();
		return this;
	}
}
