package org.nibiru.mobile.ios.ui.place;

import apple.coregraphics.struct.CGPoint;
import apple.coregraphics.struct.CGRect;
import apple.coregraphics.struct.CGSize;
import apple.uikit.UIView;
import apple.uikit.UIViewController;
import com.google.common.collect.Queues;
import org.nibiru.mobile.core.api.ui.mvp.Presenter;
import org.nibiru.mobile.core.api.ui.mvp.PresenterMapper;
import org.nibiru.mobile.core.api.ui.mvp.View;
import org.nibiru.mobile.core.api.ui.place.Place;
import org.nibiru.mobile.core.api.ui.place.PlaceManager;
import org.nibiru.mobile.ios.ui.UINavigationControllerHelper;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.Serializable;
import java.util.Deque;

import static com.google.common.base.Preconditions.checkNotNull;

@Singleton
public class UINavigationControllerPlaceManager
        implements PlaceManager {
    private final UINavigationControllerHelper navigationControllerHelper;
    private final PresenterMapper presenterMapper;
    private final Deque<Presenter<?>> presenterStack;
    private Serializable result;

    @Inject
    public UINavigationControllerPlaceManager(UINavigationControllerHelper navigationControllerHelper,
                                              PresenterMapper presenterMapper) {
        this.navigationControllerHelper = checkNotNull(navigationControllerHelper);
        this.presenterMapper = checkNotNull(presenterMapper);
        presenterStack = Queues.newArrayDeque();
    }

    @Override
    public void go(@Nonnull Place place,
                   boolean push,
                   boolean animated) {
        checkNotNull(place);
        Presenter<? extends View> presenter = presenterMapper.getPresenter(place.getId());
        UIView view = (UIView) presenter.getView().asNative();

        if (!presenterStack.isEmpty()) {
            presenterStack.peek().onDeactivate();
        }

        UIViewController viewController = UIViewController.alloc().init();

        view.setFrame(new CGRect(new CGPoint(0, 0),
                new CGSize(view.frame().size().width(),
                        view.frame().size().height())));
        viewController.setView(view);
        if (!push) {
            navigationControllerHelper.reset();
            presenterStack.clear();
        }

        navigationControllerHelper.current()
                .pushViewControllerAnimated(viewController, true);
        presenterStack.push(presenter);
        presenter.go(place);
        presenter.onActivate();
    }

    @Override
    public void back() {
        presenterStack.pop().onDeactivate();
        navigationControllerHelper.current()
                .popViewControllerAnimated(true);
        if (!presenterStack.isEmpty()) {
            Presenter<?> parent = presenterStack.peek();
            if (result != null) {
                parent.onResult(result);
            }
            parent.onActivate();
        }
    }

    @Override
    public void back(@Nonnull Serializable result) {
        this.result = checkNotNull(result);
        back();
    }
}
