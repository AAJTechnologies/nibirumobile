package ar.com.oxen.nibiru.mobile.android.ui;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import android.content.res.Configuration;
import android.content.res.Resources;
import ar.com.oxen.nibiru.mobile.core.api.ui.DisplayInfo;

public class AndroidDisplayInfo implements DisplayInfo {
	private final Resources resources;

	@Inject
	public AndroidDisplayInfo(Resources resources) {
		this.resources = checkNotNull(resources);
	}

	@Override
	public boolean isSmall() {
		return (resources.getConfiguration().screenLayout
				& Configuration.SCREENLAYOUT_SIZE_MASK) < Configuration.SCREENLAYOUT_SIZE_LARGE;
	}
}
