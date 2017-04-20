package org.nibiru.mobile.gwt.ui.place;

import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.History;

import org.nibiru.mobile.core.api.ui.place.Place;
import org.nibiru.mobile.core.api.ui.place.PlaceManager;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class GwtPlaceManager implements PlaceManager {
	private final PlaceController placeController;
	private static int creationCount;

	@Inject
	public GwtPlaceManager(PlaceController placeController) {
		this.placeController = checkNotNull(placeController);
	}

	@Override
	public Place createPlace(String id) {
		checkNotNull(id);
		return new SimplePlace(id, creationCount++, placeController);
	}

	@Override
	public Place createPlace(Enum<?> id) {
		checkNotNull(id);
		return createPlace(id.toString());
	}

	@Override
	public void back() {
		History.back();
	}
}
