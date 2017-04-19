package org.nibiru.mobile.gwt.ui.mvp;

import com.google.gwt.user.client.ui.Composite;

import org.nibiru.mobile.core.api.ui.mvp.View;

/**
 * Base class for GWT views.
 */
public abstract class BaseGwtView extends Composite implements View {
	@Override
	public Composite asNative() {
		return this;
	}
}
