package org.nibiru.mobile.fx.ioc;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.util.Callback;
import org.nibiru.mobile.fx.ui.mvp.FxView;

import javax.inject.Inject;
import javax.inject.Provider;

import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;

public class FxViewProvider<V extends FxView>
        implements Provider<V> {
    private final String fxml;
    private final Callback<Class<?>, Object> controllerFactory;

    @Inject
    public FxViewProvider(String fxml,
                          Callback<Class<?>, Object> controllerFactory) {
        this.fxml = checkNotNull(fxml);
        this.controllerFactory = checkNotNull(controllerFactory);
    }

    @Override
    public V get() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(controllerFactory);
            Parent root = loader.load(getClass()
                    .getResourceAsStream(fxml));
            V view = loader.getController();
            view.setRoot(root);
            return view;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
