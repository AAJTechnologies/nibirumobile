package org.nibiru.mobile.gwt.preferences;

import java.util.Date;

import org.nibiru.mobile.core.api.preferences.Preferences;
import org.nibiru.mobile.core.impl.preferences.AbstractPreferences;

import com.google.gwt.user.client.Cookies;

public class CookiesPreferences extends AbstractPreferences {

	@Override
	public <T> T getParameter(String key) {
		return objectFromString(Cookies.getCookie(key));
	}

	@Override
	public Preferences addParameter(String key, Object value) {
		long expirationTime = new Date().getTime() + 10l * 365l * 24l * 60l * 60l * 1000l;
		Cookies.setCookie(key, stringFromObject(value), new Date(expirationTime));
		return this;
	}

}