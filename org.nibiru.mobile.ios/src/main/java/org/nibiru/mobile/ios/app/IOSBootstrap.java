package org.nibiru.mobile.ios.app;

import org.nibiru.async.core.api.promise.Deferred;
import org.nibiru.async.core.api.promise.Promise;
import org.nibiru.mobile.core.api.app.Bootstrap;
import org.nibiru.mobile.core.api.app.EntryPoint;
import org.nibiru.mobile.ios.ui.UINavigationControllerHelper;

import javax.inject.Inject;

import apple.uikit.UIColor;
import apple.uikit.UIWindow;

import static com.google.common.base.Preconditions.checkNotNull;

public class IOSBootstrap implements Bootstrap {
    private final EntryPoint entryPoint;
    private final UIWindow mainWindow;
    private final UINavigationControllerHelper navigationControllerHelper;

    @Inject
    public IOSBootstrap(EntryPoint entryPoint,
                        UIWindow mainWindow,
                        UINavigationControllerHelper navigationControllerHelper) {
        this.entryPoint = checkNotNull(entryPoint);
        this.mainWindow = checkNotNull(mainWindow);
        this.navigationControllerHelper = checkNotNull(navigationControllerHelper);
    }

    @Override
    public Promise<Void, Exception> onBootstrap() {
        // TODO: Background color should be application-specific?
        mainWindow.setBackgroundColor(UIColor.lightGrayColor());
        mainWindow.makeKeyAndVisible();
        navigationControllerHelper.reset();
        entryPoint.onApplicationStart();
        return Deferred.<Void, Exception>defer().promise();
    }
}
