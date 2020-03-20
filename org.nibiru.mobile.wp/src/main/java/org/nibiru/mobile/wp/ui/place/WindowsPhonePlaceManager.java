package org.nibiru.mobile.wp.ui.place;

import com.google.common.collect.Lists;
import com.google.gwt.user.client.History;
import org.nibiru.mobile.core.api.ui.mvp.Presenter;
import org.nibiru.mobile.core.api.ui.mvp.PresenterMapper;
import org.nibiru.mobile.core.api.ui.place.Place;
import org.nibiru.mobile.core.api.ui.place.PlaceManager;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.Serializable;
import java.util.Deque;

import static com.google.common.base.Preconditions.checkNotNull;

@Singleton
public class WindowsPhonePlaceManager
        implements PlaceManager {
    private int creationCount;
    private final PresenterMapper presenterMapper;
    private final Deque<Presenter<?>> presenterStack;
    private Serializable result;

    @Inject
    public WindowsPhonePlaceManager(PresenterMapper presenterMapper) {
        this.presenterMapper = checkNotNull(presenterMapper);
        presenterStack = Lists.newLinkedList();
    }

    @Override
    public Place createPlace(String id) {
        checkNotNull(id);
        return new WindowsPhonePlace(id, creationCount++, this, presenterMapper, presenterStack);
    }

    @Override
    public Place createPlace(Enum<?> id) {
        checkNotNull(id);
        return createPlace(id.toString());
    }

    @Override
    public void back() {
        presenterStack.pop().onDeactivate();
        History.back();
        Serializable result = this.result;
        this.result = null;
        if (!presenterStack.isEmpty()) {
            Presenter<?> parent = presenterStack.peek();
            if (result != null) {
                parent.onResult(result);
            }
            parent.onActivate();
        }
    }

    @Override
    public void back(@Nonnull Serializable result) {
        this.result = checkNotNull(result);
        back();
    }
}
