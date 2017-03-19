package org.nibiru.mobile.core.api.ui.mvp;

public class NoPresenterFoundException extends RuntimeException {
	private static final long serialVersionUID = -8716818452330782113L;
	private String place;

	public NoPresenterFoundException(String place) {
		super("No presenter found for place " + place);
		this.place = place;
	}

	public String getPlace() {
		return place;
	}
}
