package ar.com.oxen.nibiru.mobile.gwt.data;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nullable;

import ar.com.oxen.nibiru.mobile.core.api.async.Callback;

import com.gwtmobile.persistence.client.ScalarCallback;

public class ScalarCallbackAdapter<T, K extends T> implements ScalarCallback<K> {
	private final Callback<T> callback;

	public ScalarCallbackAdapter(Callback<T> callback) {
		this.callback = checkNotNull(callback);
	}

	@Override
	public void onSuccess(@Nullable K result) {
		callback.onSuccess(result);
	}
}
