package org.nibiru.mobile.gwt.data;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.nibiru.mobile.core.api.async.Callback;

import com.google.common.collect.ImmutableList;
import com.gwtmobile.persistence.client.CollectionCallback;
import com.gwtmobile.persistence.client.Persistable;

public class CollectionCallbackAdapter<T extends Persistable> implements
		CollectionCallback<T> {
	private final Callback<List<? super T>> callback;

	@SuppressWarnings("unchecked")
	public CollectionCallbackAdapter(Callback<? extends List<? super T>> callback) {
		this.callback = (Callback<List<? super T>>) checkNotNull(callback);
	}

	@Override
	public void onSuccess(T[] results) {
		callback.onSuccess(ImmutableList.copyOf(results));
	}
}
