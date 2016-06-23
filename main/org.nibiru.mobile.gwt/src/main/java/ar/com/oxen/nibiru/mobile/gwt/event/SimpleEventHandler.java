package ar.com.oxen.nibiru.mobile.gwt.event;

import com.google.gwt.event.shared.EventHandler;

public interface SimpleEventHandler extends EventHandler {
	void onEvent(SimpleEvent event);
}
