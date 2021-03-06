package org.nibiru.mobile.ios.ui.place;

import com.google.common.collect.Maps;

import org.nibiru.mobile.core.api.common.Identifiable;
import org.nibiru.mobile.core.api.ui.mvp.Presenter;
import org.nibiru.mobile.core.api.ui.mvp.PresenterMapper;
import org.nibiru.mobile.core.api.ui.mvp.View;
import org.nibiru.mobile.core.api.ui.place.Place;
import org.nibiru.mobile.core.impl.common.BaseConfigurable;
import org.nibiru.mobile.ios.ui.UINavigationControllerHelper;

import java.util.Deque;
import java.util.Map;

import javax.annotation.Nullable;

import apple.coregraphics.struct.CGPoint;
import apple.coregraphics.struct.CGRect;
import apple.coregraphics.struct.CGSize;
import apple.uikit.UIView;
import apple.uikit.UIViewController;

import static com.google.common.base.Preconditions.checkNotNull;

public class UINavigationControllerPlace extends BaseConfigurable<Place>
        implements Place, Identifiable<String> {
    private final UINavigationControllerHelper navigationControllerHelper;
    private final PresenterMapper presenterMapper;
    private final Deque<Presenter<?>> presenterStack;
    private final String id;
    private final Map<String, Object> parameters;

    UINavigationControllerPlace(
            UINavigationControllerHelper navigationControllerHelper,
            PresenterMapper presenterMapper,
            Deque<Presenter<?>> presenterStack,
            String id) {
        this.navigationControllerHelper = checkNotNull(navigationControllerHelper);
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

        UIViewController viewController = UIViewController.alloc().init();

        view.setFrame(new CGRect(new CGPoint(0, 0), new CGSize(view.frame().size().width(),
                view.frame().size().height())));
        viewController.setView(view);
        if (!push) {
            navigationControllerHelper.reset();
            presenterStack.clear();
        }

        navigationControllerHelper.current()
                .pushViewControllerAnimated(viewController, true);
        presenterStack.push(presenter);
        presenter.go(this);
        presenter.onActivate();
    }
}
