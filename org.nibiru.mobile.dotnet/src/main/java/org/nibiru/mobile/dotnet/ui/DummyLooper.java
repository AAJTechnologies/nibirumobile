package org.nibiru.mobile.dotnet.ui;

import org.nibiru.mobile.core.api.ui.Looper;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class DummyLooper implements Looper {
	@Inject
	public DummyLooper() {
	}

	@Override
	public void post(Runnable runnable) {
		checkNotNull(runnable);
		runnable.run();
	}
}
