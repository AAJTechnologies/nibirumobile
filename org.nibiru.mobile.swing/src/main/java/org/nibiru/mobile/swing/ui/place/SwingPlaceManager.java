package org.nibiru.mobile.swing.ui.place;

import org.nibiru.mobile.core.api.ui.mvp.Presenter;
import org.nibiru.mobile.core.api.ui.mvp.PresenterMapper;
import org.nibiru.mobile.core.api.ui.place.BaseStackPlaceManager;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.*;
import java.awt.*;

import static com.google.common.base.Preconditions.checkNotNull;

@Singleton
public class SwingPlaceManager
        extends BaseStackPlaceManager<Container> {
    private final JFrame jFrame;

    @Inject
    public SwingPlaceManager(@Nonnull PresenterMapper presenterMapper,
                             @Nonnull JFrame jFrame) {
        super(presenterMapper);
        this.jFrame = checkNotNull(jFrame);
    }

    @Override
    protected void close() {
        jFrame.dispose();
    }

    @Override
    protected void activateView(Container view) {
        jFrame.setContentPane(view);
        jFrame.validate();
    }

    @Override
    protected Container getView(Presenter<?> presenter) {
        return (Container) presenter.getView().asNative();
    }
}
