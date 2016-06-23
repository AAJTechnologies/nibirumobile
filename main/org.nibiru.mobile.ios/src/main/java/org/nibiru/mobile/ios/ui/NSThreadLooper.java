package org.nibiru.mobile.ios.ui;

import static com.google.common.base.Preconditions.checkNotNull;

import org.nibiru.mobile.core.api.ui.Looper;
import org.robovm.apple.foundation.NSObject;
import org.robovm.objc.Selector;
import org.robovm.objc.annotation.Method;

public class NSThreadLooper extends NSObject implements Looper {
	private static final Selector run = Selector.register("run:");

	@Override
	public void post(Runnable runnable) {
		checkNotNull(runnable);
		performSelectorOnMainThread(run, new NSRunnableDecorator(runnable),
				false);
	}

	@Method
	public void run(NSRunnableDecorator runnable) {
		runnable.run();
	}

	private static class NSRunnableDecorator extends NSObject implements
			Runnable {
		private final Runnable decorated;

		private NSRunnableDecorator(Runnable decorated) {
			this.decorated = decorated;
		}

		@Override
		public void run() {
			decorated.run();
		}
	}
}
