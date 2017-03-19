package org.nibiru.mobile.android.event;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import org.nibiru.mobile.core.api.event.Event;
import org.nibiru.mobile.core.api.event.EventBus;
import org.nibiru.mobile.core.api.event.EventHandler;
import org.nibiru.model.core.api.Registration;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class BroadcastEventBus implements EventBus {
	private static final String EVENT_PREFIX = "org.nibiru.mobile.android.event.";
	private final Context context;

	@Inject
	public BroadcastEventBus(Context context) {
		this.context = checkNotNull(context);
	}

	@Override
	public Event createEvent(String id) {
		checkNotNull(id);
		return new IntentEvent(id, new Intent(EVENT_PREFIX + id), context);
	}

	@Override
	public Event createEvent(Enum<?> id) {
		checkNotNull(id);
		return createEvent(id.toString());
	}

	@Override
	public Registration addHandler(String event,
			final EventHandler handler) {
		checkNotNull(event);
		checkNotNull(handler);
		IntentFilter filter = new IntentFilter(EVENT_PREFIX + event);

		final BroadcastReceiver receiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				handler.onEvent(new IntentEvent(intent, context));
			}
		};

		context.registerReceiver(receiver, filter);

		return new Registration() {
			@Override
			public void remove() {
				context.unregisterReceiver(receiver);
			}
		};
	}

	@Override
	public Registration addHandler(Enum<?> eventId, EventHandler handler) {
		checkNotNull(eventId);
		checkNotNull(handler);
		return addHandler(eventId.toString(), handler);
	}
}
