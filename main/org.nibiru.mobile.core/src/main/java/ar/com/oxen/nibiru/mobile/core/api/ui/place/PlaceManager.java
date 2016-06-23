package ar.com.oxen.nibiru.mobile.core.api.ui.place;

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
}
