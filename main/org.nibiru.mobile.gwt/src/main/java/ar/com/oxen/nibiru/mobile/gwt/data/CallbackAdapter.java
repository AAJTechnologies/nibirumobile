package ar.com.oxen.nibiru.mobile.gwt.data;

import static com.google.common.base.Preconditions.checkNotNull;
import ar.com.oxen.nibiru.mobile.core.api.async.Callback;

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
