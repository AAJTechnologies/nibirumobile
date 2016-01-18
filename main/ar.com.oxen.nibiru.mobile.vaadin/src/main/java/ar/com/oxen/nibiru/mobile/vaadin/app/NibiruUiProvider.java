package ar.com.oxen.nibiru.mobile.vaadin.app;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;
import javax.inject.Provider;

import com.vaadin.server.UIClassSelectionEvent;
import com.vaadin.server.UICreateEvent;
import com.vaadin.server.UIProvider;
import com.vaadin.ui.UI;

import ar.com.oxen.nibiru.mobile.core.api.app.Bootstrap;

public class NibiruUiProvider extends UIProvider {
	private final Provider<NibiruUi> uiProvider;

	@Inject
	public NibiruUiProvider(Provider<NibiruUi> uiProvider, Provider<Bootstrap> bootstrapProvider) {
		this.uiProvider = checkNotNull(uiProvider);
	}

	@Override
	public UI createInstance(UICreateEvent event) {
		return uiProvider.get();
	}

	@Override
	public Class<? extends UI> getUIClass(UIClassSelectionEvent event) {
		return NibiruUi.class;
	}
}
