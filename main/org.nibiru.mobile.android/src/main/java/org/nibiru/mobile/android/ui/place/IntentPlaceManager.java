package org.nibiru.mobile.android.ui.place;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import org.nibiru.mobile.android.common.BaseIntentAdapter;
import org.nibiru.mobile.core.api.config.AppName;
import org.nibiru.mobile.core.api.ui.place.Place;
import org.nibiru.mobile.core.api.ui.place.PlaceManager;

import android.app.Activity;
import android.content.Intent;

public class IntentPlaceManager implements PlaceManager {
    private final Activity context;
    private final String appName;

    @Inject
    public IntentPlaceManager(Activity context,
                              @AppName String appName) {
        this.context = checkNotNull(context);
        this.appName = checkNotNull(appName);
    }

    @Override
    public Place createPlace(String id) {
        return new IntentPlace(id,
                context,
                appName);
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
