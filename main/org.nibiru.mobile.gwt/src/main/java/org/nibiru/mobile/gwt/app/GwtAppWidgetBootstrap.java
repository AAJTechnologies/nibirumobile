package org.nibiru.mobile.gwt.app;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;

public class GwtAppWidgetBootstrap implements AppWidgetBootstrap {
	private final EventBus eventBus;
	private final ActivityMapper activityMapper;

	@Inject
	public GwtAppWidgetBootstrap(EventBus eventBus,
			ActivityMapper activityMapper) {
		this.eventBus = checkNotNull(eventBus);
		this.activityMapper = checkNotNull(activityMapper);
	}

	@Override
	public IsWidget createAppWidget() {
		SimplePanel appWidget = new SimplePanel();

		ActivityManager activityManager = new ActivityManager(activityMapper,
				eventBus);
		activityManager.setDisplay(appWidget);

		return appWidget;
	}
}
