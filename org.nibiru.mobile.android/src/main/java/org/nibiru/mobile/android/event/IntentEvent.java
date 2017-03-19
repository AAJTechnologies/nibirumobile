package org.nibiru.mobile.android.event;

import static com.google.common.base.Preconditions.checkNotNull;

import org.nibiru.mobile.android.common.BaseIntentAdapter;
import org.nibiru.mobile.core.api.event.Event;

import android.content.Context;
import android.content.Intent;

class IntentEvent extends BaseIntentAdapter<Event> implements Event {
	private final Context context;

	IntentEvent(Intent intent, Context context) {
		super(intent);
		this.context = checkNotNull(context);
	}

	public IntentEvent(String id, Intent intent, Context context) {
		super(id, intent);
		this.context = checkNotNull(context);
	}

	@Override
	public void fire() {
		context.sendBroadcast(getIntent());
	}
}
