package org.nibiru.mobile.dotnet.ui;

import javax.inject.Inject;

import org.nibiru.mobile.core.api.ui.DisplayInfo;

public class DotNetDisplayInfo implements DisplayInfo {
	@Inject
	public DotNetDisplayInfo() {
	}

	@Override
	public boolean isSmall() {
		// TODO Implement this method
		return true;
	}
}
