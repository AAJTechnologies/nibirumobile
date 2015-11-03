package ar.com.oxen.nibiru.mobile.android.ui.mvp;

import javax.annotation.Nullable;
import javax.inject.Inject;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import ar.com.oxen.nibiru.mobile.android.ui.place.IntentPlace;
import ar.com.oxen.nibiru.mobile.core.api.ui.mvp.Presenter;
import ar.com.oxen.nibiru.mobile.core.api.ui.mvp.PresenterMapper;
import ar.com.oxen.nibiru.mobile.core.api.ui.mvp.View;
import ar.com.oxen.nibiru.mobile.core.api.ui.place.Place;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContextScope;


/**
 * An activity that delegates logic to a presenter.
 */
public class PresenterActivity extends RoboActivity {
	@Inject
	private PresenterMapper presenterMapper;

	@Inject
	protected ContextScope scope;

	private Presenter<?> presenter;
	private AndroidView androidView;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Place place = new IntentPlace(getIntent(), this);

		synchronized (ContextScope.class) {
			scope.enter(this);
			try {
				presenter = presenterMapper.getPresenter(place.getId());
			} finally {
				scope.exit(this);
			}
		}

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
