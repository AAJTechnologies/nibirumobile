package ar.com.oxen.nibiru.mobile.core.api.ui.mvp;

/**
 * A component for getting the presenter associated to a given place.
 */
public interface PresenterMapper {
	/**
	 * Gets a presenter.
	 * 
	 * @param place
	 *            The associated place
	 * @return The presenter
	 * @throws NoPresenterFoundException
	 *             If no presenter is mapped for such place
	 */
	Presenter<?> getPresenter(String place) throws NoPresenterFoundException;
}
