package org.nibiru.mobile.android.ui.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;

import com.google.common.base.Preconditions;

import org.nibiru.mobile.android.ui.place.IntentPlace;
import org.nibiru.mobile.core.api.ui.mvp.Presenter;
import org.nibiru.mobile.core.api.ui.mvp.PresenterMapper;
import org.nibiru.mobile.core.api.ui.mvp.View;
import org.nibiru.mobile.core.api.ui.place.Place;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * An adapter for delegating methdos from activities.
 * It allows composition whlie inheriting from
 * different activiy classes (app compat, for example)
 */
public class PresenterAdapter {
    private final Activity activity;
    private final PresenterMapper presenterMapper;
    private Presenter<?> presenter;
    private AndroidView androidView;

    public PresenterAdapter(Activity activity, PresenterMapper presenterMapper) {
        this.activity = checkNotNull(activity);
        this.presenterMapper = checkNotNull(presenterMapper);
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        Place place = new IntentPlace(activity.getIntent(), activity);

        presenter = presenterMapper.getPresenter(place.getId());

        View view = presenter.getView();
        if (view instanceof AndroidView) {
            androidView = (AndroidView) view;
        } else {
            androidView = new BaseAndroidView<android.view.View>((android.view.View) view.asNative());
        }
        androidView.onCreate();
        activity.setContentView(androidView.asNative());
        presenter.go(place);
    }

    public void onStop() {
        androidView.onStop();
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        return androidView.onPrepareOptionsMenu(menu);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return androidView.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return androidView.onOptionsItemSelected(item);
    }

    public void onCreateContextMenu(ContextMenu menu, android.view.View v,
                             ContextMenu.ContextMenuInfo menuInfo) {
        androidView.onCreateContextMenu(menu, v, menuInfo);
    }

    public boolean onContextItemSelected(MenuItem item) {
        return androidView.onContextItemSelected(item);
    }

    public void onDestroy() {
        androidView.onDestroy();
    }

    public void onPause() {
        androidView.onPause();
        presenter.onDeactivate();
    }

    public void onRestart() {
        androidView.onRestart();
    }

    public void onResume() {
        androidView.onResume();
        presenter.onActivate();
    }

    public void onStart() {
        androidView.onStart();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        androidView.onActivityResult(requestCode, resultCode, data);
    }
}
