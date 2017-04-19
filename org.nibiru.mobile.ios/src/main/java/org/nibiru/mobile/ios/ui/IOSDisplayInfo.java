package org.nibiru.mobile.ios.ui;

import org.nibiru.mobile.core.api.ui.DisplayInfo;

import javax.inject.Inject;

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
