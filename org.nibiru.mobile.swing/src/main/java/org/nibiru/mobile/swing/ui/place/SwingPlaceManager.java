package org.nibiru.mobile.swing.ui.place;

import com.google.common.collect.Queues;

import org.nibiru.mobile.core.api.ui.mvp.Presenter;
import org.nibiru.mobile.core.api.ui.mvp.PresenterMapper;
import org.nibiru.mobile.core.api.ui.place.Place;
import org.nibiru.mobile.core.api.ui.place.PlaceManager;

import java.awt.Container;
import java.util.Deque;

import javax.inject.Inject;
import javax.swing.JFrame;

import static com.google.common.base.Preconditions.checkNotNull;

public class SwingPlaceManager implements PlaceManager {
    private final JFrame jFrame;
    private final PresenterMapper presenterMapper;
    private final Deque<Presenter<?>> presenterStack;

    @Inject
    public SwingPlaceManager(JFrame jFrame,
                             PresenterMapper presenterMapper) {
        this.jFrame = checkNotNull(jFrame);
        this.presenterMapper = checkNotNull(presenterMapper);
        presenterStack = Queues.newArrayDeque();
    }

    @Override
    public Place createPlace(String id) {
        return new SwingPlace(jFrame,
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
        if (presenterStack.isEmpty()) {
            jFrame.dispose();
        } else {
            Presenter<?> presenter = presenterStack.peek();
            jFrame.setContentPane((Container) presenter.getView().asNative());
            jFrame.validate();
            presenter.onActivate();
        }
    }
}
