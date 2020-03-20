package org.nibiru.mobile.android.ui.place;

import android.app.Activity;

import android.content.Intent;
import org.nibiru.mobile.core.api.config.AppName;
import org.nibiru.mobile.core.api.ui.place.Place;
import org.nibiru.mobile.core.api.ui.place.PlaceManager;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import java.io.Serializable;

import static com.google.common.base.Preconditions.checkNotNull;

public class IntentPlaceManager
        implements PlaceManager {
    public static final int NIBIRU_REQUEST_CODE = 10261985;
    public static final int NIBIRU_RESULT_CODE = 10212015;
    public static final String NIBIRU_RESULT_KEY = "NIBIRU_RESULT_KEY";
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

    @Override
    public void back(@Nonnull Serializable result) {
        checkNotNull(result);
        Intent data = new Intent();
        data.putExtra(NIBIRU_RESULT_KEY, result);
        context.setResult(NIBIRU_RESULT_CODE, data);
        back();
    }
}
