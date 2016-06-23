package ar.com.oxen.nibiru.mobile.android.ui.mvp;

import static com.google.common.base.Preconditions.checkNotNull;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Base class for Android based views.
 */
public class BaseAndroidView<V extends View> implements AndroidView {
	private V view;

	protected BaseAndroidView(V view) {
		this.view = checkNotNull(view);
	}

	@Override
	public V asNative() {
		return view;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return false;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		return false;
	}

	@Override
	public void onCreate() {
	}

	@Override
	public void onStart() {
	}

	@Override
	public void onStop() {
	}

	@Override
	public void onResume() {
	}

	@Override
	public void onPause() {
	}

	@Override
	public void onDestroy() {
	}

	@Override
	public void onRestart() {
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return false;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		return false;
	}

	@SuppressWarnings("unchecked")
	protected <T extends View> T findViewById(int id) {
		return (T) view.findViewById(id);
	}

	protected <T extends View> T findViewById(int id, Class<T> clazz) {
		return findViewById(id);
	}
}
