package ar.com.oxen.nibiru.mobile.vaadin.app;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import ar.com.oxen.nibiru.mobile.core.api.app.Bootstrap;
import ar.com.oxen.nibiru.mobile.core.api.app.EntryPoint;

public class VaadinBootstrap implements Bootstrap {
	private final EntryPoint entryPoint;

	@Inject
	public VaadinBootstrap(EntryPoint entryPoint) {
		this.entryPoint = checkNotNull(entryPoint);
	}

	@Override
	public void onBootstrap() {
		entryPoint.onApplicationStart();
	}
}
