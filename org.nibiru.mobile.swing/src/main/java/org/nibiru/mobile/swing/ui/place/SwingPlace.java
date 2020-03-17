package org.nibiru.mobile.swing.ui.place;

import com.google.common.collect.Maps;

import org.nibiru.mobile.core.api.common.Configurable;
import org.nibiru.mobile.core.api.common.Identifiable;
import org.nibiru.mobile.core.api.ui.mvp.Presenter;
import org.nibiru.mobile.core.api.ui.mvp.PresenterMapper;
import org.nibiru.mobile.core.api.ui.mvp.View;
import org.nibiru.mobile.core.api.ui.place.Place;

import java.util.Deque;
import java.util.Map;

import javax.annotation.Nullable;
import javax.swing.JComponent;
import javax.swing.JFrame;

import static com.google.common.base.Preconditions.checkNotNull;

public class SwingPlace
        implements Configurable<Place>, Place, Identifiable<String> {
    private final JFrame jFrame;
    private final PresenterMapper presenterMapper;
    private final Deque<Presenter<?>> presenterStack;
    private final String id;
    private final Map<String, Object> parameters;

    SwingPlace(JFrame jFrame,
               PresenterMapper presenterMapper,
               Deque<Presenter<?>> presenterStack,
               String id) {
        this.jFrame = checkNotNull(jFrame);
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
    public void go(boolean push, boolean animated) {
        Presenter<? extends View> presenter = presenterMapper.getPresenter(id);
        JComponent view = (JComponent) presenter.getView().asNative();

        if (!presenterStack.isEmpty()) {
            presenterStack.peek().onDeactivate();
        }

        if (!push) {
            presenterStack.clear();
        }

        jFrame.setContentPane(view);
        jFrame.validate();
        presenterStack.push(presenter);
        presenter.go(this);
        presenter.onActivate();
    }
}
