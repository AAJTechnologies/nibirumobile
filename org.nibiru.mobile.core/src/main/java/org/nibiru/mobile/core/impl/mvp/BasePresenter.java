package org.nibiru.mobile.core.impl.mvp;

import org.nibiru.mobile.core.api.ui.mvp.Presenter;
import org.nibiru.mobile.core.api.ui.mvp.View;
import org.nibiru.mobile.core.api.ui.place.PlaceManager;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Base class for presenters
 *
 * @param <V>
 *            The view type
 */
abstract public class BasePresenter<V extends View>
		implements Presenter<V> {
	private final V view;
	private final PlaceManager placeManager;

	public BasePresenter(V view, PlaceManager placeManager) {
		this.view = checkNotNull(view);
		this.placeManager = checkNotNull(placeManager);
	}

	@Override
	public V getView() {
		return view;
	}

	protected PlaceManager getPlaceManager() {
		return placeManager;
	}
}
