package org.nibiru.mobile.vaadin.app;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import org.nibiru.mobile.core.api.app.Bootstrap;
import org.nibiru.mobile.core.api.app.EntryPoint;

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
