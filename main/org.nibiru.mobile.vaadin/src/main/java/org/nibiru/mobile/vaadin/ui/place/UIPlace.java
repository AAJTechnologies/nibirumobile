package org.nibiru.mobile.vaadin.ui.place;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Deque;
import java.util.Map;

import org.nibiru.mobile.core.api.ui.mvp.Presenter;
import org.nibiru.mobile.core.api.ui.mvp.PresenterMapper;
import org.nibiru.mobile.core.api.ui.mvp.View;
import org.nibiru.mobile.core.api.ui.place.Place;
import org.nibiru.mobile.core.impl.common.BaseConfigurable;

import com.google.common.collect.Maps;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

public class UIPlace extends BaseConfigurable<Place> implements Place {
	private final PresenterMapper presenterMapper;
	private final Deque<Presenter<?>> presenterStack;
	private final String id;
	private final Map<String, Object> parameters;

	UIPlace(PresenterMapper presenterMapper, Deque<Presenter<?>> presenterStack, String id) {
		this.presenterMapper = checkNotNull(presenterMapper);
		this.presenterStack = checkNotNull(presenterStack);
		this.id = checkNotNull(id);
		parameters = Maps.newHashMap();
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
	public Place addParameter(String key, Object value) {
		checkNotNull(key);
		checkNotNull(value);
		parameters.put(key, value);
		return this;
	}

	@Override
	public void go() {
		this.go(false, true);
	}

	@Override
	public void go(boolean push, boolean animated) {
		Presenter<? extends View> presenter = presenterMapper.getPresenter(id);

		if (!presenterStack.isEmpty()) {
			presenterStack.peek().onDeactivate();
		}

		if (!push) {
			presenterStack.clear();
		}
		Component view = (Component) presenter.getView().asNative();
		UI.getCurrent().setContent(view);
		presenterStack.push(presenter);
		presenter.go(this);
		presenter.onActivate();
	}
}
