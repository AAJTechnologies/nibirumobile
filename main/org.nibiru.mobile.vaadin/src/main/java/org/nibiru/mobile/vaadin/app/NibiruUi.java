package org.nibiru.mobile.vaadin.app;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;
import javax.inject.Provider;

import org.nibiru.mobile.core.api.app.Bootstrap;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

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
