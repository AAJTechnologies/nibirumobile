package org.nibiru.mobile.ios.ui.place;

import com.google.common.collect.Queues;

import org.nibiru.mobile.core.api.ui.mvp.Presenter;
import org.nibiru.mobile.core.api.ui.mvp.PresenterMapper;
import org.nibiru.mobile.core.api.ui.place.Place;
import org.nibiru.mobile.core.api.ui.place.PlaceManager;
import org.nibiru.mobile.ios.ui.UINavigationControllerHelper;

import java.io.Serializable;
import java.util.Deque;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

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
    public Place createPlace(String id) {
        return new UINavigationControllerPlace(navigationControllerHelper,
                presenterMapper,
                presenterStack,
                id);
    }

    @Override
    public Place createPlace(Enum<?> id) {
        return createPlace(id.toString());
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
