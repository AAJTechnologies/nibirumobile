package ar.com.oxen.nibiru.mobile.android.ui.place;

import static com.google.common.base.Preconditions.checkNotNull;
import android.app.Activity;
import android.content.Intent;
import ar.com.oxen.nibiru.mobile.android.common.BaseIntentAdapter;
import ar.com.oxen.nibiru.mobile.core.api.ui.place.Place;

public class IntentPlace extends BaseIntentAdapter<Place> implements Place {
	private final Activity activity;

	public IntentPlace(Intent intent, Activity activity) {
		super(intent);
		this.activity = checkNotNull(activity);
	}

	public IntentPlace(String id, Intent intent, Activity activity) {
		super(id, intent);
		this.activity = checkNotNull(activity);
	}

	@Override
	public void go(boolean push, boolean animated) {
		Intent intent = getIntent();
		if (!animated) {
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		}
		activity.startActivity(intent);
		if (!push) {
			activity.finish();
		}
	}

	@Override
	public void go() {
		go(false, true);
	}
}
