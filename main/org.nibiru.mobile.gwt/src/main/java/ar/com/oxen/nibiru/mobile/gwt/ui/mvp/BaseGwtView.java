package ar.com.oxen.nibiru.mobile.gwt.ui.mvp;

import ar.com.oxen.nibiru.mobile.core.api.ui.mvp.View;

import com.google.gwt.user.client.ui.Composite;

/**
 * Base class for GWT views.
 */
public abstract class BaseGwtView extends Composite implements View {
	@Override
	public Composite asNative() {
		return this;
	}
}
