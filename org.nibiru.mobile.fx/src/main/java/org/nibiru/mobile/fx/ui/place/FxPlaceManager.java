package org.nibiru.mobile.fx.ui.place;

import javafx.scene.Parent;
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
        extends BaseStackPlaceManager<Parent> {
    private final Stage primaryStage;
    private final StyleSheetProvider styleSheetProvider;
    private boolean first = true;

    @Inject
    public FxPlaceManager(@Nonnull Stage primaryStage,
                          @Nonnull PresenterMapper presenterMapper,
                          @Nonnull StyleSheetProvider styleSheetProvider) {
        super(presenterMapper);
        this.primaryStage = checkNotNull(primaryStage);
        this.styleSheetProvider = checkNotNull(styleSheetProvider);
    }

    @Override
    protected void close() {
        primaryStage.close();
    }

    @Override
    protected void activateView(Parent view) {
        view.getStylesheets()
                .addAll(styleSheetProvider.get());
        if (first) {
            first = false;
            primaryStage.setScene(new Scene(view));
        } else {
            primaryStage.getScene().setRoot(view);
        }
    }

    @Override
    protected Parent getView(Presenter<?> presenter) {
        FxView view = (FxView) presenter.getView();
        return view.asNative();
    }
}
