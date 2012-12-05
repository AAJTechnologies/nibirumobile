package ar.com.oxen.nibiru.mobile.core.impl.ui.security;

import javax.inject.Inject;

import ar.com.oxen.nibiru.mobile.core.api.business.security.AuthenticationManager;
import ar.com.oxen.nibiru.mobile.core.api.ui.AlertManager;
import ar.com.oxen.nibiru.mobile.core.api.ui.mvp.ClickHandler;
import ar.com.oxen.nibiru.mobile.core.api.ui.mvp.HasClickHandler;
import ar.com.oxen.nibiru.mobile.core.api.ui.mvp.View;
import ar.com.oxen.nibiru.mobile.core.api.ui.place.DefaultPlaces;
import ar.com.oxen.nibiru.mobile.core.api.ui.place.Place;
import ar.com.oxen.nibiru.mobile.core.api.ui.place.PlaceManager;
import ar.com.oxen.nibiru.mobile.core.impl.mvp.BasePresenter;
import ar.com.oxen.nibiru.mobile.core.impl.ui.security.LoginPresenter.Display;

/**
 * Presenter for login screen.
 */
public class LoginPresenter extends BasePresenter<Display> {
	public interface Display extends View {
		String getUsername();

		String getPassword();

		HasClickHandler getLogin();

		void showLoginError();
	}

	private AuthenticationManager authenticationManager;
	private PlaceManager placeManager;

	@Inject
	public LoginPresenter(Display display, AlertManager alertManager,
			AuthenticationManager authenticationManager,
			PlaceManager placeManager) {
		super(display, alertManager);
		this.authenticationManager = authenticationManager;
		this.placeManager = placeManager;
	}

	@Override
	public void go(Place place) {
		getView().getLogin().setClickHandler(new ClickHandler() {

			@Override
			public void onClick() {
				authenticationManager.login(getView().getUsername(), getView()
						.getPassword(), new Cbk<Boolean>() {
					@Override
					public void onSuccess(Boolean result) {
						if (result) {
							placeManager.createPlace(DefaultPlaces.HOME).go(false);
						} else {
							getView().showLoginError();
						}
					}
				});
			}
		});
	}
}
