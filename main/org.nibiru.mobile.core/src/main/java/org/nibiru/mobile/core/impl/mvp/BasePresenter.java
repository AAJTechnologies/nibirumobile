package org.nibiru.mobile.core.impl.mvp;

import static com.google.common.base.Preconditions.checkNotNull;

import org.nibiru.mobile.core.api.async.Callback;
import org.nibiru.mobile.core.api.ui.AlertManager;
import org.nibiru.mobile.core.api.ui.mvp.Presenter;
import org.nibiru.mobile.core.api.ui.mvp.View;
import org.nibiru.mobile.core.impl.async.BaseCallback;

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

	/**
	 * Utility method for creating internal callbacks.
	 * It calls creates a {@link BaseCallback} instance, which uses {@link AlertManager} to show errors.
	 * 
	 * @param <T>
	 *            The callback return type
	 */
	protected <T> Callback<T> callback(Consumer<T> func) {
		return new BaseCallback<T>(alertManager) {
			@Override
			public void onSuccess(T result) {
				func.accept(result);
			}
		};
	}

	public interface Consumer<T> {
		void accept(T result);
	}
}
