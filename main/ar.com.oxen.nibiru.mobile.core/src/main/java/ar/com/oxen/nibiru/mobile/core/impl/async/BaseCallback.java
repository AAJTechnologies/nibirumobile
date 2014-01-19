package ar.com.oxen.nibiru.mobile.core.impl.async;

import static com.google.common.base.Preconditions.checkNotNull;
import ar.com.oxen.nibiru.mobile.core.api.async.Callback;
import ar.com.oxen.nibiru.mobile.core.api.ui.AlertManager;

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
		alertManager.showException(error);
	}
}
