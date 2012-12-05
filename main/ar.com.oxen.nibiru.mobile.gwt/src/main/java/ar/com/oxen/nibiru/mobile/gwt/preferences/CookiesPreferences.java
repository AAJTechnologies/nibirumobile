package ar.com.oxen.nibiru.mobile.gwt.preferences;

import java.util.Date;

import com.google.gwt.user.client.Cookies;

import ar.com.oxen.nibiru.mobile.core.api.preferences.Preferences;
import ar.com.oxen.nibiru.mobile.core.impl.preferences.AbstractPreferences;

public class CookiesPreferences extends AbstractPreferences {

	@Override
	public <T> T getParameter(String key) {
		return this.objectFromString(Cookies.getCookie(key));
	}

	@Override
	public Preferences addParameter(String key, Object value) {
		long expirationTime = new Date().getTime() + 10l * 365l * 24l * 60l * 60l * 1000l;
		Cookies.setCookie(key, this.stringFromObject(value), new Date(expirationTime));
		return this;
	}

}
