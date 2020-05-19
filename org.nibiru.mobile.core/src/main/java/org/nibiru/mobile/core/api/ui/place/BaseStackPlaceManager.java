package org.nibiru.mobile.core.api.ui.place;

import com.google.common.collect.Queues;
import org.nibiru.mobile.core.api.ui.mvp.Presenter;
import org.nibiru.mobile.core.api.ui.mvp.PresenterMapper;
import org.nibiru.mobile.core.api.ui.mvp.View;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Deque;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class BaseStackPlaceManager<V>
        implements PlaceManager {
    private final PresenterMapper presenterMapper;
    private final Deque<PresenterAndView<V>> stack;
    private Serializable result;

    protected BaseStackPlaceManager(PresenterMapper presenterMapper) {
        this.presenterMapper = checkNotNull(presenterMapper);
        stack = Queues.newArrayDeque();
    }

    @Override
    public void back() {
        stack.pop().presenter.onDeactivate();
        Serializable result = this.result;
        this.result = null;
        nativeBack();
        if (stack.isEmpty()) {
            close();
        } else {
            PresenterAndView<V> parent = stack.peek();
            activateView(parent.view);
            if (result != null) {
                parent.presenter.onResult(result);
            }
            parent.presenter.onActivate();
        }
    }


    @Override
    public void back(@Nonnull Serializable result) {
        this.result = checkNotNull(result);
        back();
    }

    @Override
    public void go(Place place, boolean push, boolean animated) {
        Presenter<? extends View> presenter = presenterMapper.getPresenter(place.getId());

        if (!stack.isEmpty()) {
            stack.peek()
                    .presenter
                    .onDeactivate();
        }

        if (!push) {
            stack.clear();
        }

        V view = getView(presenter);
        stack.push(new PresenterAndView(presenter, view));
        activateView(view);
        presenter.go(place);
        presenter.onActivate();
    }

    protected abstract void close();

    protected void nativeBack(){

    }

    protected abstract void activateView(V view);

    protected abstract V getView(Presenter<?> presenter);

    private static class PresenterAndView<V> {
        private final Presenter<?> presenter;
        private final V view;

        private PresenterAndView(Presenter<?> presenter,
                                 V view) {
            this.presenter = presenter;
            this.view = view;
        }
    }
}