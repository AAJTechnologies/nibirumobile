package org.nibiru.mobile.ios.ui;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import org.nibiru.mobile.core.api.ui.Looper;

import com.intel.moe.natj.objc.SEL;

import ios.NSObject;

public class NSThreadLooper extends NSObject implements Looper {
	private static final SEL run = new SEL("run:");

	@Inject
	public NSThreadLooper() {
		super(NSObject.alloc().init().getPeer());
	}

	@Override
	public void post(Runnable runnable) {
		checkNotNull(runnable);
		performSelectorOnMainThreadWithObjectWaitUntilDone(run, new NSRunnableDecorator(runnable),
				false);
	}

	public void run(NSRunnableDecorator runnable) {
		runnable.run();
	}

	private static class NSRunnableDecorator extends NSObject implements
			Runnable {
		private final Runnable decorated;

		private NSRunnableDecorator(Runnable decorated) {
			super(NSObject.alloc().init().getPeer());
			this.decorated = decorated;
		}

		@Override
		public void run() {
			decorated.run();
		}
	}
}
