package org.nibiru.mobile.android.ui.mvp;

import android.content.Intent;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import org.nibiru.mobile.core.api.ui.mvp.View;

/**
 * {@link View} specialization that adds Android events.
 */
public interface AndroidView extends View {
	android.view.View asNative();

	boolean onPrepareOptionsMenu(Menu menu);

	boolean onCreateOptionsMenu(Menu menu);

	boolean onOptionsItemSelected(MenuItem item);

	void onCreateContextMenu(ContextMenu menu, android.view.View v,
			ContextMenuInfo menuInfo);

	boolean onContextItemSelected(MenuItem item);

	// Activity LifeCycle
	void onCreate();

	void onStart();

	void onStop();

	void onResume();

	void onPause();

	void onDestroy();

	void onActivityResult(int requestCode, int resultCode, Intent data);

	boolean onKeyDown(int keyCode, KeyEvent event);

	boolean onMenuItemSelected(int featureId, MenuItem item);
}
