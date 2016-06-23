package ar.com.oxen.nibiru.mobile.gwt.app;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * Annotation for application root widget. The provider must return the app
 * widget with its corresponding Activity Manager configured.
 * 
 */
public interface AppWidgetBootstrap {
	 IsWidget createAppWidget();
}
