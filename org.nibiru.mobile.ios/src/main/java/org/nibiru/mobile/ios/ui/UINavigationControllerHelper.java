package org.nibiru.mobile.ios.ui;

import javax.inject.Inject;

import apple.uikit.UINavigationController;
import apple.uikit.UIViewController;
import apple.uikit.UIWindow;

import static com.google.common.base.Preconditions.checkNotNull;

public class UINavigationControllerHelper {
    private final UIWindow mainWindow;

    @Inject
    public UINavigationControllerHelper(UIWindow mainWindow) {
        this.mainWindow = checkNotNull(mainWindow);
    }

    public void reset() {
        UIViewController current = mainWindow
                .rootViewController();
        if (current != null && current.view() != null) {
            current.view().removeFromSuperview();
        }

        UINavigationController navigationController = UINavigationController.alloc()
                .init();
        navigationController.setNavigationBarHidden(true);
        mainWindow.setRootViewController(navigationController);
        mainWindow.addSubview(navigationController.view());
    }

    public UINavigationController current() {
        return (UINavigationController) mainWindow
                .rootViewController();
    }
}
