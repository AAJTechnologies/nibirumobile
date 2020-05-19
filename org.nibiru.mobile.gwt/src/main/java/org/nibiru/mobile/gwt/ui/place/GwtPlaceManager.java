package org.nibiru.mobile.gwt.ui.place;

import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.History;
import org.nibiru.mobile.core.api.ui.place.Place;
import org.nibiru.mobile.core.api.ui.place.PlaceManager;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.Serializable;

import static com.google.common.base.Preconditions.checkNotNull;

@Singleton
public class GwtPlaceManager
        implements PlaceManager {
    private final PlaceController placeController;
    private int creationCount;
    private Serializable result;

    @Inject
    public GwtPlaceManager(PlaceController placeController) {
        this.placeController = checkNotNull(placeController);
    }

    @Override
    public Place createPlace(String id) {
        checkNotNull(id);
        return SimplePlace.create(id,
                creationCount++,
                placeController);
    }

    @Override
    public void go(@Nonnull Place place,
                   boolean push,
                   boolean animated) {
        checkNotNull(place);
        placeController.goTo((com.google.gwt.place.shared.Place) place);
        // TODO: Result will not work
    }

    @Override
    public void back() {
        History.back();
    }

    @Override
    public void back(@Nonnull Serializable result) {
        this.result = checkNotNull(result);
        back();
    }
}
