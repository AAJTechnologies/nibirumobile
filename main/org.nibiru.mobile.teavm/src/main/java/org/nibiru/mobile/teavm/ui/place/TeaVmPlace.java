package org.nibiru.mobile.teavm.ui.place;

import com.google.common.collect.Maps;

import org.nibiru.mobile.core.api.common.Identifiable;
import org.nibiru.mobile.core.api.ui.mvp.Presenter;
import org.nibiru.mobile.core.api.ui.mvp.PresenterMapper;
import org.nibiru.mobile.core.api.ui.mvp.View;
import org.nibiru.mobile.core.api.ui.place.Place;
import org.nibiru.mobile.core.impl.common.BaseConfigurable;
import org.teavm.jso.dom.html.HTMLDocument;
import org.teavm.jso.dom.xml.Node;

import java.util.Deque;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public class TeaVmPlace extends BaseConfigurable<Place>
		implements Place, Identifiable<String> {
	private final HTMLDocument document;
	private final PresenterMapper presenterMapper;
	private final Deque<Presenter<?>> presenterStack;
	private final String id;
	private final Map<String, Object> parameters;

	TeaVmPlace(HTMLDocument document,
			PresenterMapper presenterMapper,
			Deque<Presenter<?>> presenterStack,
			String id) {
		this.document = checkNotNull(document);
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
		document.getBody().clear();
		Node view = (Node) presenter.getView().asNative();

		if (!presenterStack.isEmpty()) {
			presenterStack.peek().onDeactivate();
		}

		document.getBody().appendChild(view);
		if (!push) {
			presenterStack.clear();
		}
		presenterStack.push(presenter);
		presenter.go(this);
		presenter.onActivate();
	}
}
