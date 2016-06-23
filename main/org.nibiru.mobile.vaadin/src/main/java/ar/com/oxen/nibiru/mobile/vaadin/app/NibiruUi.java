package ar.com.oxen.nibiru.mobile.vaadin.app;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;
import javax.inject.Provider;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

import ar.com.oxen.nibiru.mobile.core.api.app.Bootstrap;

public class NibiruUi extends UI {
	private final Provider<Bootstrap> bootstrapProvider;

	@Inject
	public NibiruUi(Provider<Bootstrap> bootstrapProvider) {
		this.bootstrapProvider = checkNotNull(bootstrapProvider);
	}

	@Override
	protected void init(VaadinRequest request) {
		bootstrapProvider.get().onBootstrap();
	}
}
