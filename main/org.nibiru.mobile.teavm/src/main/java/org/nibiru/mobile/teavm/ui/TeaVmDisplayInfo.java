package org.nibiru.mobile.teavm.ui;

import javax.inject.Inject;

import org.nibiru.mobile.core.api.ui.DisplayInfo;
import org.teavm.jso.JSBody;

public class TeaVmDisplayInfo implements DisplayInfo {
    @Inject
    public TeaVmDisplayInfo() {
    }

    @Override
    @JSBody(params = {}, script = "return /Android|webOS|iPhone|iPod|BlackBerry|Windows Phone|bada|Bada/i.test(navigator.userAgent)")
    public native boolean isSmall();
}
