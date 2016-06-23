package org.nibiru.mobile.android.ui.mvp;

import javax.annotation.Nullable;

import org.nibiru.mobile.android.ui.place.IntentPlace;
import org.nibiru.mobile.core.api.ui.mvp.Presenter;
import org.nibiru.mobile.core.api.ui.mvp.PresenterMapper;
import org.nibiru.mobile.core.api.ui.mvp.View;
import org.nibiru.mobile.core.api.ui.place.Place;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;


/**
 * An activity that delegates logic to a presenter.
 */
public abstract class BasePresenterActivity extends Activity {
	private Presenter<?> presenter;
	private AndroidView androidView;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Place place = new IntentPlace(getIntent(), this);

		presenter = getPresenterMapper().getPresenter(place.getId());

		View view = presenter.getView();
		if (view instanceof AndroidView) {
			androidView = (AndroidView) view;
		} else {
			androidView = new BaseAndroidView<android.view.View>((android.view.View)view.asNative());
		}
		androidView.onCreate();
		setContentView(androidView.asNative());
		presenter.go(place);
	}

	protected abstract PresenterMapper getPresenterMapper();

	@Override
	protected void onStop() {
		super.onStop();
		androidView.onStop();
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return androidView.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return androidView.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return androidView.onOptionsItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, android.view.View v,
			ContextMenuInfo menuInfo) {
		androidView.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		return androidView.onContextItemSelected(item);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		androidView.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
		androidView.onPause();
		presenter.onDeactivate();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		androidView.onRestart();
	}

	@Override
	protected void onResume() {
		super.onResume();
		androidView.onResume();
		presenter.onActivate();
	}

	@Override
	protected void onStart() {
		super.onStart();
		androidView.onStart();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		androidView.onActivityResult(requestCode, resultCode, data);
	}
}
