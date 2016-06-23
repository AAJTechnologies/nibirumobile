package ar.com.oxen.nibiru.mobile.gwt.ui.place;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import ar.com.oxen.nibiru.mobile.core.api.ui.place.Place;
import ar.com.oxen.nibiru.mobile.core.api.ui.place.PlaceManager;

import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.History;

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
