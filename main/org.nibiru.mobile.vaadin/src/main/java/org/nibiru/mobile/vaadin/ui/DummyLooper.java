package org.nibiru.mobile.vaadin.ui;

import static com.google.common.base.Preconditions.checkNotNull;

import org.nibiru.mobile.core.api.ui.Looper;

public class DummyLooper implements Looper {
	@Override
	public void post(Runnable runnable) {
		checkNotNull(runnable);
		runnable.run();
	}
}
