package org.nibiru.mobile.fx.ui.mvp;

import javafx.scene.Parent;
import org.nibiru.mobile.core.api.ui.mvp.View;

import javax.annotation.Nonnull;

public interface FxView extends View {
    Parent asNative();

    void setRoot(@Nonnull Parent root);
}
