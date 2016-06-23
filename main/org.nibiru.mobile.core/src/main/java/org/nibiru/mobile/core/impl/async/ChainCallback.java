package org.nibiru.mobile.core.impl.async;

import static com.google.common.base.Preconditions.checkNotNull;

import org.nibiru.mobile.core.api.async.Callback;

/**
 * Class for composing two callbacks.
 * 
 * @param <T>
 *            The type returned by the callback
 * @param <C>
 *            The type returned by the chained callback
 */
public abstract class ChainCallback<T, C> implements Callback<T> {
	private final Callback<C> chained;

	public ChainCallback(Callback<C> chained) {
		this.chained = checkNotNull(chained);
	}

	protected Callback<C> getChained() {
		return chained;
	}

	@Override
	public void onFailure(Exception error) {
		checkNotNull(error);
		chained.onFailure(error);
	}
}
