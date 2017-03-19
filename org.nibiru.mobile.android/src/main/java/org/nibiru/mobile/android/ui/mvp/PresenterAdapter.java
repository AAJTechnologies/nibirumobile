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
import android.view.View;
import org.nibiru.mobile.core.api.ui.place.Place;

import javax.annotation.Nullable;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * An adapter for delegating methdos from activities.
 * It allows composition whlie inheriting from
 * different activiy classes (app compat, for example)
 */
public class PresenterAdapter {
    private final PresenterMapper presenterMapper;
    private Presenter<?> presenter;
    private AndroidView androidView;

    @Inject
    public PresenterAdapter(PresenterMapper presenterMapper) {
        this.presenterMapper = checkNotNull(presenterMapper);
    }

    public View onCreate(Place place) {
        presenter = presenterMapper.getPresenter(place.getId());

        org.nibiru.mobile.core.api.ui.mvp.View view = presenter.getView();
        if (view instanceof AndroidView) {
            androidView = (AndroidView) view;
        } else {
            androidView = new BaseAndroidView<>((View) view.asNative());
        }
        androidView.onCreate();
        presenter.go(place);
        return androidView.asNative();
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

    public void onCreateContextMenu(ContextMenu menu,
                                    View v,
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

    public void onResume() {
        androidView.onResume();
        presenter.onActivate();
    }

    public void onStart() {
        androidView.onStart();
    }

    public void onActivityResult(int requestCode,
                                 int resultCode,
                                 Intent data) {
        androidView.onActivityResult(requestCode, resultCode, data);
    }
}
