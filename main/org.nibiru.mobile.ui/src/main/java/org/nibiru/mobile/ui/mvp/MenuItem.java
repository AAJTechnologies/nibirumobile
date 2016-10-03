package org.nibiru.mobile.ui.mvp;

import static com.google.common.base.Preconditions.checkNotNull;

import org.nibiru.mobile.core.api.ui.place.Place;

public class MenuItem {
	private final String title;
	private final Place place;

	public MenuItem(String title, Place place) {
		this.title = checkNotNull(title);
		this.place = checkNotNull(place);
	}

	public String getTitle() {
		return title;
	}

	public Place getPlace() {
		return place;
	}
}