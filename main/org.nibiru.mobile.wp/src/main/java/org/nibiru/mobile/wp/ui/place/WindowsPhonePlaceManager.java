package org.nibiru.mobile.wp.ui.place;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Deque;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.nibiru.mobile.core.api.ui.mvp.Presenter;
import org.nibiru.mobile.core.api.ui.mvp.PresenterMapper;
import org.nibiru.mobile.core.api.ui.place.Place;
import org.nibiru.mobile.core.api.ui.place.PlaceManager;

import com.google.common.collect.Lists;
import com.google.gwt.user.client.History;

@Singleton
public class WindowsPhonePlaceManager implements PlaceManager {
	private int creationCount;
	private final PresenterMapper presenterMapper;
	private final Deque<Presenter<?>> presenterStack;

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
		if (!presenterStack.isEmpty()) {
			presenterStack.peek().onActivate();
		}
	}
}
