package org.nibiru.mobile.ios.ui.place;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Deque;

import javax.inject.Inject;
import javax.inject.Provider;

import org.nibiru.mobile.core.api.ui.mvp.Presenter;
import org.nibiru.mobile.core.api.ui.mvp.PresenterMapper;
import org.nibiru.mobile.core.api.ui.place.Place;
import org.nibiru.mobile.core.api.ui.place.PlaceManager;

import com.google.common.collect.Queues;

import ios.uikit.UINavigationController;
import ios.uikit.UIWindow;

public class UINavigationControllerPlaceManager implements PlaceManager {
	private final UIWindow mainWindow;
	private final Provider<UINavigationController> navigationControllerProvider;
	private final PresenterMapper presenterMapper;
	private final Deque<Presenter<?>> presenterStack;

	@Inject
	public UINavigationControllerPlaceManager(UIWindow mainWindow,
			Provider<UINavigationController> navigationControllerProvider,
			PresenterMapper presenterMapper) {
		this.mainWindow = checkNotNull(mainWindow);
		this.navigationControllerProvider = checkNotNull(navigationControllerProvider);
		this.presenterMapper = checkNotNull(presenterMapper);
		presenterStack = Queues.newArrayDeque();
	}

	@Override
	public Place createPlace(String id) {
		return new UINavigationControllerPlace(mainWindow,
				navigationControllerProvider, presenterMapper, presenterStack,
				id);
	}

	@Override
	public Place createPlace(Enum<?> id) {
		return createPlace(id.toString());
	}

	@Override
	public void back() {
		presenterStack.pop().onDeactivate();
		UINavigationController navigationController = (UINavigationController) mainWindow
				.rootViewController();
		navigationController.popViewControllerAnimated(true);
		if (!presenterStack.isEmpty()) {
			presenterStack.peek().onActivate();
		}
	}
}
