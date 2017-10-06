package org.nibiru.mobile.ios.ui.place;

import com.google.common.collect.Maps;

import org.nibiru.mobile.core.api.common.Identifiable;
import org.nibiru.mobile.core.api.ui.mvp.Presenter;
import org.nibiru.mobile.core.api.ui.mvp.PresenterMapper;
import org.nibiru.mobile.core.api.ui.mvp.View;
import org.nibiru.mobile.core.api.ui.place.Place;
import org.nibiru.mobile.core.impl.common.BaseConfigurable;

import java.util.Deque;
import java.util.Map;

import javax.annotation.Nullable;
import javax.inject.Provider;

import apple.coregraphics.struct.CGPoint;
import apple.coregraphics.struct.CGRect;
import apple.coregraphics.struct.CGSize;
import apple.uikit.UINavigationController;
import apple.uikit.UIView;
import apple.uikit.UIViewController;
import apple.uikit.UIWindow;

import static com.google.common.base.Preconditions.checkNotNull;

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
    public Place addParameter(String key, @Nullable Object value) {
        checkNotNull(key);
        parameters.put(key, value);
        return this;
    }

    @Override
    public void go() {
        this.go(false, true);
    }

    @Override
    public void go(boolean push, boolean animated) {
        Presenter<? extends View> presenter = presenterMapper.getPresenter(id);
        UIView view = (UIView) presenter.getView().asNative();

        if (!presenterStack.isEmpty()) {
            presenterStack.peek().onDeactivate();
        }

        UINavigationController navigationController = (UINavigationController) mainWindow
                .rootViewController();
        UIViewController viewController = UIViewController.alloc().init();

        double barHeight = navigationController.navigationBar().frame().size().height() * 1.5; // TODO: Find the way to remove the navigation bar!
        UIView container = UIView.alloc().init();
        container.setFrame(new CGRect(new CGPoint(0, 0), new CGSize(view.frame().size().width(), view.frame().size().height() + barHeight)));
        view.setFrame(new CGRect(new CGPoint(0, barHeight), new CGSize(view.frame().size().width(), view.frame().size().height())));
        container.addSubview(view);
        viewController.setView(container);
        if (!push) {
            navigationController.view().removeFromSuperview();
            navigationController = navigationControllerProvider.get();
            mainWindow
                    .setRootViewController(navigationController);
            mainWindow.addSubview(navigationController.view());

            presenterStack.clear();
        }

        navigationController.pushViewControllerAnimated(viewController, true);
        presenterStack.push(presenter);
        presenter.go(this);
        presenter.onActivate();
    }
}
