package org.nibiru.mobile.core.impl.preferences;

import javax.annotation.Nullable;

import org.nibiru.mobile.core.api.preferences.Preferences;
import org.nibiru.mobile.core.impl.common.BaseConfigurable;


public abstract class AbstractPreferences extends
		BaseConfigurable<Preferences> implements Preferences {

	private static final char STRING_PREFIX = 's';

	@SuppressWarnings("unchecked")
	protected <T> T objectFromString(@Nullable String data) {
		if (data != null && data.length() > 0) {
			String body = data.substring(1);
			switch (data.charAt(0)) {
			case STRING_PREFIX:
				return (T) body;

			default:
				throw new IllegalArgumentException("Invalid data: " + data);
			}
		} else {
			return null;
		}
	}

	protected String stringFromObject(@Nullable Object object) {
		if (object != null) {
			if (object instanceof String) {
				return "" + STRING_PREFIX + object;
			} else {
				throw new IllegalArgumentException("Invalid object type: "
						+ object.getClass());
			}
		} else {
			return null;
		}
	}

}
