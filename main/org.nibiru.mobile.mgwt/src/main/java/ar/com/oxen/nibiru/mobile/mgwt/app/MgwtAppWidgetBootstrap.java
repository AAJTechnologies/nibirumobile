package ar.com.oxen.nibiru.mobile.mgwt.app;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import ar.com.oxen.nibiru.mobile.gwt.app.AppWidgetBootstrap;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.mvp.client.AnimatingActivityManager;
import com.googlecode.mgwt.mvp.client.AnimationMapper;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.googlecode.mgwt.ui.client.widget.animation.AnimatableDisplay;
import com.googlecode.mgwt.ui.client.widget.animation.AnimationWidget;

public class MgwtAppWidgetBootstrap implements AppWidgetBootstrap {
	private final EventBus eventBus;
	private final ActivityMapper activityMapper;
	private final AnimationMapper animationMapper;

	@Inject
	public MgwtAppWidgetBootstrap(EventBus eventBus,
			ActivityMapper activityMapper,
			AnimationMapper animationMapper) {
		this.eventBus = checkNotNull(eventBus);
		this.activityMapper = checkNotNull(activityMapper);
		this.animationMapper = checkNotNull(animationMapper);
	}

	@Override
	public IsWidget createAppWidget() {
		MGWT.applySettings(MGWTSettings.getAppSetting());

		AnimatableDisplay appWidget = new AnimationWidget();

		AnimatingActivityManager activityManager = new AnimatingActivityManager(
				activityMapper, animationMapper, eventBus);
		activityManager.setDisplay(appWidget);

		return appWidget;
	}
}
