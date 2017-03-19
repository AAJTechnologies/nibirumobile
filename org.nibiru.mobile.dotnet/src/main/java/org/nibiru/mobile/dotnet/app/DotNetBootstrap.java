package org.nibiru.mobile.dotnet.app;

import org.nibiru.mobile.core.api.app.Bootstrap;
import org.nibiru.mobile.core.api.app.EntryPoint;

import javax.inject.Inject;

import cli.System.Windows.Window;

import static com.google.common.base.Preconditions.checkNotNull;

public class DotNetBootstrap implements Bootstrap {
    private final EntryPoint entryPoint;
    private final Window mainWindow;

    @Inject
    public DotNetBootstrap(EntryPoint entryPoint,
                           Window mainWindow) {
        this.entryPoint = checkNotNull(entryPoint);
        this.mainWindow = checkNotNull(mainWindow);
    }

    @Override
    public void onBootstrap() {
        mainWindow.ShowDialog();
        entryPoint.onApplicationStart();
    }
}
