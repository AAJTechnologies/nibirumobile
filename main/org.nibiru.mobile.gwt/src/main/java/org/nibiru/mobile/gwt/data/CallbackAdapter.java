package org.nibiru.mobile.gwt.data;

import static com.google.common.base.Preconditions.checkNotNull;

import org.nibiru.mobile.core.api.async.Callback;

public class CallbackAdapter implements
		com.gwtmobile.persistence.client.Callback {
	private final Callback<Void> callback;

	public CallbackAdapter(Callback<Void> callback) {
		this.callback = checkNotNull(callback);
	}

	@Override
	public void onSuccess() {
		callback.onSuccess(null);
	}
}
