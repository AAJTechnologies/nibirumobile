package ar.com.oxen.nibiru.mobile.gwt.ui;

import ar.com.oxen.nibiru.mobile.core.api.ui.DisplayInfo;

public class GwtDisplayInfo implements DisplayInfo {
	@Override
	public native boolean isSmall() /*-{
		return /Android|webOS|iPhone|iPod|BlackBerry|Windows Phone|bada|Bada/i.test(navigator.userAgent)
	}-*/; 
}
