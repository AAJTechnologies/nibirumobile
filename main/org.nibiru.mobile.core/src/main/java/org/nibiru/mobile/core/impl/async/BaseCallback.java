package org.nibiru.mobile.core.impl.async;

import static com.google.common.base.Preconditions.checkNotNull;

import org.nibiru.mobile.core.api.async.Callback;
import org.nibiru.mobile.core.api.ui.AlertManager;

/**
 * Abstract base class for implementing callbacks.
 *
 * @param <T>
 *            The type returned on success
 */
public abstract class BaseCallback<T> implements Callback<T> {
	private final AlertManager alertManager;

	public BaseCallback(AlertManager alertManager) {
		this.alertManager = checkNotNull(alertManager);
	}

	@Override
	public void onFailure(Exception error) {
		checkNotNull(error);
		alertManager.showException(error);
	}
}
