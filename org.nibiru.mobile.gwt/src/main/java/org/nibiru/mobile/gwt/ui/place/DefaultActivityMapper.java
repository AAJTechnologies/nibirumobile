package org.nibiru.mobile.gwt.ui.place;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import org.nibiru.mobile.core.api.ui.mvp.PresenterMapper;
import org.nibiru.mobile.gwt.ui.mvp.PresenterActivity;

import javax.annotation.Nullable;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class DefaultActivityMapper implements ActivityMapper {
	private final PresenterMapper presenterMapper;

	@Inject
	public DefaultActivityMapper(PresenterMapper presenterMapper) {
		this.presenterMapper = checkNotNull(presenterMapper);
	}

	@Override
	@Nullable
	public Activity getActivity(Place place) {
		checkNotNull(place);
		if (place instanceof SimplePlace) {
			SimplePlace gwtPlace = (SimplePlace) place;
			if (gwtPlace.getId() != null) {
				return new PresenterActivity(
						presenterMapper.getPresenter(gwtPlace.getId()),
						gwtPlace);
			}
		}
		return null;
	}
}
