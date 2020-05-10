package org.nibiru.mobile.fx.ui;

import org.nibiru.mobile.core.api.ui.DisplayInfo;

import javax.inject.Inject;

public class FxDisplayInfo implements DisplayInfo {
	@Inject
	public FxDisplayInfo() {
	}

	@Override
	public boolean isSmall() {
		// TODO Implement this method
		return true;
	}
}
