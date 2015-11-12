package ar.com.oxen.nibiru.mobile.ios.ui.place;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Deque;
import java.util.Map;

import javax.inject.Provider;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.uikit.UINavigationController;
import org.robovm.apple.uikit.UIView;
import org.robovm.apple.uikit.UIViewController;
import org.robovm.apple.uikit.UIWindow;

import ar.com.oxen.nibiru.mobile.core.api.common.Identifiable;
import ar.com.oxen.nibiru.mobile.core.api.ui.mvp.Presenter;
import ar.com.oxen.nibiru.mobile.core.api.ui.mvp.PresenterMapper;
import ar.com.oxen.nibiru.mobile.core.api.ui.mvp.View;
import ar.com.oxen.nibiru.mobile.core.api.ui.place.Place;
import ar.com.oxen.nibiru.mobile.core.impl.common.BaseConfigurable;

import com.google.common.collect.Maps;

public class UINavigationControllerPlace extends BaseConfigurable<Place>
		implements Place, Identifiable<String> {
	private final UIWindow mainWindow;
	private final Provider<UINavigationController> navigationControllerProvider;
	private final PresenterMapper presenterMapper;
	private final Deque<Presenter<?>> presenterStack;
	private final String id;
	private final Map<String, Object> parameters;

	UINavigationControllerPlace(
			UIWindow mainWindow,
			Provider<UINavigationController> navigationControllerProvider,
			PresenterMapper presenterMapper,
			Deque<Presenter<?>> presenterStack,
			String id) {
		this.mainWindow = checkNotNull(mainWindow);
		this.navigationControllerProvider = checkNotNull(navigationControllerProvider);
		this.presenterMapper = checkNotNull(presenterMapper);
		this.presenterStack = checkNotNull(presenterStack);
		this.id = checkNotNull(id);
		parameters = Maps.newHashMap();
	}

	@Override
	public String getId() {
		return id;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getParameter(String key) {
		checkNotNull(key);
		return (T) parameters.get(key);
	}

	@Override
	public Place addParameter(String key, Object value) {
		checkNotNull(key);
		checkNotNull(value);
		parameters.put(key, value);
		return this;
	}

	@Override
	public void go() {
		this.go(false);
	}

	@Override
	public void go(boolean push) {
		Presenter<? extends View> presenter = presenterMapper.getPresenter(id);
		UIView view = (UIView) presenter.getView().asNative();

		if (!presenterStack.isEmpty()) {
			presenterStack.peek().onDeactivate();
		}

		UINavigationController navigationController = (UINavigationController) mainWindow
				.getRootViewController();
		UIViewController viewController = new UIViewController();
		
		double barHeight = navigationController.getNavigationBar().getFrame().getHeight() * 1.5; // TODO: Find the way to remove the navigation bar!
		UIView container = new UIView(new CGRect(0, 0, view.getFrame().getWidth(), view.getFrame().getHeight() + barHeight)); 
		view.setFrame(new CGRect(0, barHeight, view.getFrame().getWidth(), view.getFrame().getHeight()));
		container.addSubview(view);
		viewController.setView(container);
		if (!push) {
			navigationController.getView().removeFromSuperview();
			navigationController = navigationControllerProvider.get();
			mainWindow
					.setRootViewController(navigationController);
			mainWindow.addSubview(navigationController.getView());
			
			presenterStack.clear();
		}
		
		navigationController.pushViewController(viewController, true);
		presenterStack.push(presenter);
		presenter.go(this);
		presenter.onActivate();
	}
}
