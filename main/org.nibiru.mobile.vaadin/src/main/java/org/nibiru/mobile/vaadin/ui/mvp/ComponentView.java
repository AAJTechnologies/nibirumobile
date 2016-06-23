package org.nibiru.mobile.vaadin.ui.mvp;

import org.nibiru.mobile.core.api.ui.mvp.View;

import com.vaadin.ui.Component;

public interface ComponentView extends View {
	Component asNative();
}
