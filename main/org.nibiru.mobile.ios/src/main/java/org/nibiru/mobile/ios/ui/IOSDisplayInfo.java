package org.nibiru.mobile.ios.ui;

import javax.inject.Inject;

import org.nibiru.mobile.core.api.ui.DisplayInfo;

public class IOSDisplayInfo implements DisplayInfo {
	@Inject
	public IOSDisplayInfo() {
	}

	@Override
	public boolean isSmall() {
		// TODO Implement this method
		return true;
	}
}
