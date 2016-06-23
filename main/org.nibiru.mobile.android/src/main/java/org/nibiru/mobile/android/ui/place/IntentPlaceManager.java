package org.nibiru.mobile.android.ui.place;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import org.nibiru.mobile.core.api.ui.place.Place;
import org.nibiru.mobile.core.api.ui.place.PlaceManager;

import android.app.Activity;
import android.content.Intent;

public class IntentPlaceManager implements PlaceManager {
	private final Activity context;

	@Inject
	public IntentPlaceManager(Activity context) {
		this.context = checkNotNull(context);
	}

	@Override
	public Place createPlace(String id) {
		return new IntentPlace(id,
				new Intent("org.nibiru.mobile.android.ui.mvp.PresenterActivity"), context);
	}

	@Override
	public Place createPlace(Enum<?> id) {
		return createPlace(id.toString());
	}

	@Override
	public void back() {
		context.finish();
	}
}
