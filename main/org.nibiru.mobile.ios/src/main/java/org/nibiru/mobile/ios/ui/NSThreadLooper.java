package org.nibiru.mobile.ios.ui;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import org.moe.natj.general.Pointer;
import org.moe.natj.objc.ann.Selector;
import org.nibiru.mobile.core.api.ui.Looper;

import apple.NSObject;
import apple.foundation.c.Foundation;

public class NSThreadLooper implements Looper {
	@Inject
	public NSThreadLooper() {
	}

	@Override
	public void post(Runnable runnable) {
		checkNotNull(runnable);
		NSRunnableDecorator.alloc().initWithRunnable(runnable).performSelectorOnMainThreadWithObjectWaitUntilDone(
				Foundation.NSSelectorFromString(NSRunnableDecorator.RUN_SELECTOR), null, false);
	}

	private static class NSRunnableDecorator extends NSObject implements Runnable {
		private static final String RUN_SELECTOR = "run";

		@Selector("alloc")
		public static native NSRunnableDecorator alloc();

		private Runnable decorated;

		protected NSRunnableDecorator(Pointer peer) {
			super(peer);
		}

		public NSRunnableDecorator initWithRunnable(Runnable decorated) {
			init();
			this.decorated = decorated;
			return this;
		}

		@Override
		@Selector(RUN_SELECTOR)
		public void run() {
			decorated.run();
		}
	}
}
