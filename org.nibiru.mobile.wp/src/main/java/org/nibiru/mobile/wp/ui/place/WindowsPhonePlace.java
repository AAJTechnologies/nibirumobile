package org.nibiru.mobile.wp.ui.place;

import com.google.common.collect.Maps;

import org.nibiru.mobile.core.api.common.Identifiable;
import org.nibiru.mobile.core.api.ui.mvp.Presenter;
import org.nibiru.mobile.core.api.ui.mvp.PresenterMapper;
import org.nibiru.mobile.core.api.ui.place.Place;
import org.nibiru.mobile.core.impl.common.BaseConfigurable;

import java.util.Deque;
import java.util.Map;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

public class WindowsPhonePlace extends BaseConfigurable<Place> implements
        Place, Identifiable<String> {
    private final String id;
    private final Map<String, Object> parameters;
    private final int order;
    private final WindowsPhonePlaceManager placeManager;
    private final PresenterMapper presenterMapper;
    private final Deque<Presenter<?>> presenterStack;

    WindowsPhonePlace(String id,
                      int order,
                      WindowsPhonePlaceManager placeManager,
                      PresenterMapper presenterMapper,
                      Deque<Presenter<?>> presenterStack) {
        this.id = checkNotNull(id);
        this.parameters = Maps.newHashMap();
        this.order = order;
        this.placeManager = checkNotNull(placeManager);
        this.presenterMapper = checkNotNull(presenterMapper);
        this.presenterStack = checkNotNull(presenterStack);
    }

    @Override
    public String getId() {
        return id;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getParameter(String key) {
        checkNotNull(key);
        return (T) parameters.get(key);
    }

    @Override
    public <T> T getParameter(Enum<?> key) {
        checkNotNull(key);
        return getParameter(key.toString());
    }

    @Override
    public Place addParameter(String key, @Nullable Object value) {
        checkNotNull(key);
        parameters.put(key, value);
        return this;
    }

    @Override
    public Place addParameter(Enum<?> key, @Nullable Object value) {
        checkNotNull(key);
        return addParameter(key.toString(), value);
    }

    @Override
    public void go(boolean push, boolean animated) {
        if (!presenterStack.isEmpty()) {
            presenterStack.peek().onDeactivate();
        }
        if (!push) {
            // TODO: Clear history
            presenterStack.clear();
        }
        navigate(id.toLowerCase());
    }

    public boolean forwardFrom(WindowsPhonePlace other) {
        checkNotNull(other);
        return order > other.order;
    }

    public void activatePresenter() {
        Presenter<?> presenter = presenterMapper.getPresenter(id);
        presenterStack.push(presenter);
        presenter.go(this);
        presenter.onActivate();
    }

    private native void navigate(String page) /*-{
        var place = this;
		$wnd.WinJS.Navigation.navigate("/pages/" + page + "/" + page + ".html").done(function () {
			place.@org.nibiru.mobile.wp.ui.place.WindowsPhonePlace::activatePresenter()();
		});
	}-*/;
}
