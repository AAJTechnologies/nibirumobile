package ar.com.oxen.nibiru.mobile.vaadin.ui.mvp;

import com.vaadin.ui.Component;

import ar.com.oxen.nibiru.mobile.core.api.ui.mvp.View;

public interface ComponentView extends View {
	Component asNative();
}
