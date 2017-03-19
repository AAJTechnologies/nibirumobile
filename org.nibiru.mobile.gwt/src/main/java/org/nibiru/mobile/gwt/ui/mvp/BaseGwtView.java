package org.nibiru.mobile.gwt.ui.mvp;

import org.nibiru.mobile.core.api.ui.mvp.View;

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
