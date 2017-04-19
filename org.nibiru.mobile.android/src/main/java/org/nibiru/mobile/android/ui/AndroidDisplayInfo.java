package org.nibiru.mobile.android.ui;

import android.content.res.Configuration;
import android.content.res.Resources;

import org.nibiru.mobile.core.api.ui.DisplayInfo;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

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
