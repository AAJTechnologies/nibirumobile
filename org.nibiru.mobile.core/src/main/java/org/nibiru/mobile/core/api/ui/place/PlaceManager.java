package org.nibiru.mobile.core.api.ui.place;

import javax.annotation.Nonnull;
import java.io.Serializable;

/**
 * A manager for handling places.
 */
public interface PlaceManager {
	/**
	 * Creates a place.
	 * 
	 * @param id
	 *            The place id
	 * @return The place
	 */
	Place createPlace(String id);

	/**
	 * Creates a place.
	 * 
	 * @param id
	 *            The place id
	 * @return The place
	 */
	Place createPlace(Enum<?> id);

	/**
	 * Backs to previous place.
	 */
	void back();

	/**
	 * Backs to previous place, returning a result.
	 */
	void back(@Nonnull Serializable result);
}
