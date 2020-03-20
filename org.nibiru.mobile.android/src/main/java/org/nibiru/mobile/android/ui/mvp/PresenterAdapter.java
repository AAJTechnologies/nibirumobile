package org.nibiru.mobile.android.ui.mvp;

import android.content.Intent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.nibiru.mobile.android.ui.place.IntentPlaceManager;
import org.nibiru.mobile.core.api.ui.mvp.Presenter;
import org.nibiru.mobile.core.api.ui.mvp.PresenterMapper;
import org.nibiru.mobile.core.api.ui.place.Place;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * An adapter for delegating methods from activities.
 * It allows composition while inheriting from
 * different activity classes (app compat, for example)
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
        if (requestCode == IntentPlaceManager.NIBIRU_REQUEST_CODE
                && resultCode == IntentPlaceManager.NIBIRU_RESULT_CODE) {
            presenter.onResult(data.getSerializableExtra(IntentPlaceManager.NIBIRU_RESULT_KEY));
        }
    }
}
