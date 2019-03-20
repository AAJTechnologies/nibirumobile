package org.nibiru.mobile.swing.ui;

import org.nibiru.mobile.core.api.ui.DisplayInfo;

import javax.inject.Inject;

public class SwingDisplayInfo implements DisplayInfo {
	@Inject
	public SwingDisplayInfo() {
	}

	@Override
	public boolean isSmall() {
		// TODO Implement this method
		return true;
	}
}
