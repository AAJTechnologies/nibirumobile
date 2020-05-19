package org.nibiru.mobile.core.api.ui.place;

import javax.annotation.Nonnull;
import java.io.Serializable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A manager for handling places.
 */
public interface PlaceManager {
    /**
     * Creates a place.
     *
     * @param id The place id
     * @return The place
     */
    default Place createPlace(@Nonnull String id) {
        checkNotNull(id);
        return new GenericPlace(this, id);
    }

    /**
     * Creates a place.
     *
     * @param id The place id
     * @return The place
     */
    default Place createPlace(@Nonnull Enum<?> id) {
        checkNotNull(id);
        return createPlace(id.toString());
    }

    /**
     * Navigates to a place.
     */
    void go(@Nonnull Place place,
            boolean push,
            boolean animated);

    /**
     * Backs to previous place.
     */
    void back();

    /**
     * Backs to previous place, returning a result.
     */
    void back(@Nonnull Serializable result);
}
