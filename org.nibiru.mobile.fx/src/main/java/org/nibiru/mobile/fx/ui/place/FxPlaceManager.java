package org.nibiru.mobile.fx.ui.place;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.nibiru.mobile.core.api.ui.mvp.Presenter;
import org.nibiru.mobile.core.api.ui.mvp.PresenterMapper;
import org.nibiru.mobile.core.api.ui.place.BaseStackPlaceManager;
import org.nibiru.mobile.fx.ui.mvp.FxView;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import static com.google.common.base.Preconditions.checkNotNull;

@Singleton
public class FxPlaceManager
        extends BaseStackPlaceManager<Scene> {
    private final Stage primaryStage;

    @Inject
    public FxPlaceManager(@Nonnull Stage primaryStage,
                          @Nonnull PresenterMapper presenterMapper) {
        super(presenterMapper);
        this.primaryStage = checkNotNull(primaryStage);
    }

    @Override
    protected void close() {
        primaryStage.close();
    }

    @Override
    protected void activateView(Scene view) {
        primaryStage.setScene(view);
    }

    @Override
    protected Scene getView(Presenter<?> presenter) {
        FxView view = (FxView) presenter.getView();
        return new Scene(view.asNative());
    }
}
