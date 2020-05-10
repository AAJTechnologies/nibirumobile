package org.nibiru.mobile.fx.ui.place;

import com.google.common.collect.Queues;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.nibiru.mobile.core.api.ui.mvp.Presenter;
import org.nibiru.mobile.core.api.ui.mvp.PresenterMapper;
import org.nibiru.mobile.core.api.ui.place.Place;
import org.nibiru.mobile.core.api.ui.place.PlaceManager;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.Serializable;
import java.util.Deque;

import static com.google.common.base.Preconditions.checkNotNull;

@Singleton
public class FxPlaceManager
        implements PlaceManager {
    private final Stage primaryStage;
    private final PresenterMapper presenterMapper;
    private final Deque<Presenter<?>> presenterStack;
    private Serializable result;

    @Inject
    public FxPlaceManager(Stage primaryStage,
                          PresenterMapper presenterMapper) {
        this.primaryStage = checkNotNull(primaryStage);
        this.presenterMapper = checkNotNull(presenterMapper);
        presenterStack = Queues.newArrayDeque();
    }

    @Override
    public Place createPlace(String id) {
        return new FxPlace(primaryStage,
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
        Serializable result = this.result;
        this.result = null;
        if (presenterStack.isEmpty()) {
            primaryStage.close();
        } else {
            Presenter<?> parent = presenterStack.peek();
            primaryStage.setScene(new Scene((Parent) parent.getView().asNative()));
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
