package org.nibiru.mobile.fx.ui.place;

import com.google.common.collect.Maps;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.nibiru.mobile.core.api.ui.mvp.Presenter;
import org.nibiru.mobile.core.api.ui.mvp.PresenterMapper;
import org.nibiru.mobile.core.api.ui.mvp.View;
import org.nibiru.mobile.core.api.ui.place.Place;
import org.nibiru.mobile.fx.ui.mvp.FxView;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.Deque;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public class FxPlace
        implements Place {
    private final Stage primaryStage;
    private final PresenterMapper presenterMapper;
    private final Deque<Presenter<?>> presenterStack;
    private final String id;
    private final Map<String, Object> parameters;

    FxPlace(Stage primaryStage,
            PresenterMapper presenterMapper,
            Deque<Presenter<?>> presenterStack,
            String id) {
        this.primaryStage = checkNotNull(primaryStage);
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
    public <T extends Serializable> T getParameter(String key) {
        checkNotNull(key);
        return (T) parameters.get(key);
    }

    @Override
    public Place addParameter(String key,
                              @Nullable Serializable value) {
        checkNotNull(key);
        parameters.put(key, value);
        return this;
    }

    @Override
    public void go(boolean push,
                   boolean animated) {
        Presenter<? extends View> presenter = presenterMapper.getPresenter(id);

        if (!presenterStack.isEmpty()) {
            presenterStack.peek()
                    .onDeactivate();
        }

        if (!push) {
            presenterStack.clear();
        }

        FxView view = (FxView) presenter.getView();

        primaryStage.setScene(new Scene(view.asNative()));
        presenterStack.push(presenter);
        presenter.go(this);
        presenter.onActivate();
    }
}
