package org.nibiru.mobile.dotnet.ui.place;

import com.google.common.collect.Queues;

import org.nibiru.mobile.core.api.ui.mvp.Presenter;
import org.nibiru.mobile.core.api.ui.mvp.PresenterMapper;
import org.nibiru.mobile.core.api.ui.place.Place;
import org.nibiru.mobile.core.api.ui.place.PlaceManager;

import java.util.Deque;

import javax.inject.Inject;

import cli.System.Windows.Window;

import static com.google.common.base.Preconditions.checkNotNull;

public class DotNetPlaceManager implements PlaceManager {
	private final Window mainWindow;
	private final PresenterMapper presenterMapper;
	private final Deque<Presenter<?>> presenterStack;

	@Inject
	public DotNetPlaceManager(Window mainWindow,
                              PresenterMapper presenterMapper) {
		this.mainWindow = checkNotNull(mainWindow);
		this.presenterMapper = checkNotNull(presenterMapper);
		presenterStack = Queues.newArrayDeque();
	}

	@Override
	public Place createPlace(String id) {
		return new DotNetPlace(mainWindow,
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
		if (!presenterStack.isEmpty()) {
			Presenter<?> presenter = presenterStack.peek();
			mainWindow.set_Content(presenter.getView().asNative());
			presenter.onActivate();
		}
	}
}
