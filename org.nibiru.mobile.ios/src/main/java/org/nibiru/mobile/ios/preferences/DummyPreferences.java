package org.nibiru.mobile.ios.preferences;

import org.nibiru.mobile.core.api.preferences.Preferences;

import javax.inject.Inject;

public class DummyPreferences implements Preferences {
    @Inject
    public DummyPreferences() {
    }

	@Override
	public <T> T getParameter(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getParameter(Enum<?> key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Preferences addParameter(String key, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Preferences addParameter(Enum<?> key, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

}
