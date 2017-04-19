package org.nibiru.mobile.gwt.ui.mvp;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

import org.nibiru.mobile.core.api.ui.mvp.Presenter;
import org.nibiru.mobile.core.api.ui.mvp.View;
import org.nibiru.mobile.core.api.ui.place.Place;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Presenter-based activity.
 */
public class PresenterActivity extends AbstractActivity {
	private final Presenter<? extends View> presenter;
	private final Place place;

	public PresenterActivity(Presenter<? extends View> presenter, Place place) {
		this.presenter = checkNotNull(presenter);
		this.place = checkNotNull(place);
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		checkNotNull(containerWidget);
		checkNotNull(eventBus);
		IsWidget widget = (IsWidget) presenter.getView().asNative();
		containerWidget.setWidget(widget);
		presenter.go(place);
		presenter.onActivate();
	}

	@Override
	public void onStop() {
		super.onStop();
		presenter.onDeactivate();
	}
}
