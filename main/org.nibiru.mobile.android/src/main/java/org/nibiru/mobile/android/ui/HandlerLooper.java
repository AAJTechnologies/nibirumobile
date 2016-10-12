package org.nibiru.mobile.android.ui;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import org.nibiru.mobile.core.api.ui.Looper;

import android.content.Context;
import android.os.Handler;

public class HandlerLooper implements Looper {
	private final Handler handler;

	@Inject
	public HandlerLooper(Context context) {
		checkNotNull(context);
		handler = new Handler(context.getMainLooper());
	}

	@Override
	public void post(Runnable runnable) {
		checkNotNull(runnable);
		handler.post(runnable);
	}
}
