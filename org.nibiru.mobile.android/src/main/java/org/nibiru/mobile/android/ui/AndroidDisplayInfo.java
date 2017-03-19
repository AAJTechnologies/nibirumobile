package org.nibiru.mobile.android.ui;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import org.nibiru.mobile.core.api.ui.DisplayInfo;

import android.content.res.Configuration;
import android.content.res.Resources;

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
