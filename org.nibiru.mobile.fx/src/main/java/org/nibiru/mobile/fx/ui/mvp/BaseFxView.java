package org.nibiru.mobile.fx.ui.mvp;

import javafx.scene.Parent;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class BaseFxView implements FxView {
    private Parent root;

    @Override
    public Parent asNative() {
        return root;
    }

    @Override
    public void setRoot(@Nonnull Parent root) {
        this.root = checkNotNull(root);
    }
}
