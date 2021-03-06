package org.nibiru.mobile.android.ui.place;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;

import org.nibiru.mobile.android.common.BaseIntentAdapter;
import org.nibiru.mobile.core.api.ui.place.Place;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

public class IntentPlace extends BaseIntentAdapter<Place> implements Place {
    private final Activity activity;
    private final String category;
    private final String defaultCategory;

    public IntentPlace(Intent intent) {
        super(intent);
        this.activity = null;
        category = null;
        defaultCategory = null;
    }

    IntentPlace(String id,
                Activity activity,
                String appName) {
        super(id, new Intent(BaseIntentAdapter.PREFIX + appName));
        this.activity = checkNotNull(activity);
        checkNotNull(appName);
        this.category = BaseIntentAdapter.PREFIX + appName + "." + id;
        this.defaultCategory = BaseIntentAdapter.PREFIX + appName + ".DEFAULT";
    }

    @Override
    public void go(boolean push, boolean animated) {
        checkState(activity != null, "This place was not instantied form place manager and it can't navigate.");
        Intent intent = getIntent();
        if (!animated) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        }
        try {
            // Try with specific inten category
            intent.addCategory(category);
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            try {
                // Try with default Nibiru category
                intent.removeCategory(category);
                intent.addCategory(defaultCategory);
                activity.startActivity(intent);
            } catch (ActivityNotFoundException e1) {
                // Try with no catecory
                intent.removeCategory(defaultCategory);
                activity.startActivity(intent);
            }
        }
        if (!push) {
            activity.finish();
        }
    }

    @Override
    public void go() {
        go(false, true);
    }
}
