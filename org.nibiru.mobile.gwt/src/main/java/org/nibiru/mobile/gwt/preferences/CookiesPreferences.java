package org.nibiru.mobile.gwt.preferences;

import com.google.gwt.user.client.Cookies;

import org.nibiru.mobile.core.api.preferences.Preferences;
import org.nibiru.mobile.core.impl.preferences.AbstractPreferences;

import java.util.Date;

import javax.annotation.Nullable;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class CookiesPreferences extends AbstractPreferences {
    @Inject
    public CookiesPreferences() {
    }

    @Override
    public <T> T getParameter(String key) {
        return objectFromString(Cookies.getCookie(key));
    }

    @Override
    public Preferences addParameter(String key, @Nullable Object value) {
        checkNotNull(key);
        if (value != null) {
            long expirationTime = new Date().getTime() + 10l * 365l * 24l * 60l * 60l * 1000l;
            Cookies.setCookie(key, stringFromObject(value), new Date(expirationTime));
        } else {
            Cookies.removeCookie(key);
        }
        return this;
    }

}
