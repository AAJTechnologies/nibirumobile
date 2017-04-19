package org.nibiru.mobile.core.impl.mvp;

import org.nibiru.mobile.core.api.ui.AlertManager;
import org.nibiru.mobile.core.api.ui.mvp.Presenter;
import org.nibiru.mobile.core.api.ui.mvp.View;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Base class for presenters
 * 
 * @param <V>
 *            The view type
 */
abstract public class BasePresenter<V extends View> implements Presenter<V> {
	private final V view;
	private final AlertManager alertManager;

	public BasePresenter(V view, AlertManager alertManager) {
		this.view = checkNotNull(view);
		this.alertManager = checkNotNull(alertManager);
	}

	@Override
	public V getView() {
		return view;
	}

	@Override
	public void onActivate() {
	}

	@Override
	public void onDeactivate() {
	}

	protected AlertManager getAlertManager() {
		return alertManager;
	}

	public void showException(Exception error) {
		alertManager.showException(error);
	}
}
