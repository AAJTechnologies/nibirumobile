package org.nibiru.mobile.dotnet.preferences;

import org.nibiru.mobile.core.api.preferences.Preferences;

public class DummyPreferences implements Preferences {

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
