package org.nibiru.mobile.teavm.ui.place;

import com.google.common.collect.Queues;

import org.nibiru.mobile.core.api.ui.mvp.Presenter;
import org.nibiru.mobile.core.api.ui.mvp.PresenterMapper;
import org.nibiru.mobile.core.api.ui.mvp.View;
import org.nibiru.mobile.core.api.ui.place.Place;
import org.nibiru.mobile.core.api.ui.place.PlaceManager;
import org.teavm.jso.dom.html.HTMLDocument;
import org.teavm.jso.dom.xml.Node;

import java.util.Deque;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class TeaVmPlaceManager implements PlaceManager {
	private final HTMLDocument document;
	private final PresenterMapper presenterMapper;
	private final Deque<Presenter<?>> presenterStack;

	@Inject
	public TeaVmPlaceManager(HTMLDocument document,
							 PresenterMapper presenterMapper) {
		this.document = checkNotNull(document);
		this.presenterMapper = checkNotNull(presenterMapper);
		presenterStack = Queues.newArrayDeque();
	}

	@Override
	public Place createPlace(String id) {
		return new TeaVmPlace(document,
                presenterMapper,
				presenterStack,
				id);
	}

	@Override
	public Place createPlace(Enum<?> id) {
		return createPlace(id.toString());
	}

	@Override
	public void back() {
		presenterStack.pop().onDeactivate();
		document.getBody().clear();
		if (!presenterStack.isEmpty()) {
            Presenter<? extends View> presenter = presenterStack.peek();
            document.getBody().clear();
            Node view = (Node) presenter.getView().asNative();
            document.getBody().appendChild(view);
            presenter.onActivate();
		}
	}
}
