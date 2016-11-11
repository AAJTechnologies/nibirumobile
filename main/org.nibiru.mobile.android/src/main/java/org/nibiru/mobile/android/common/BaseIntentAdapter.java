package org.nibiru.mobile.android.common;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;

import org.nibiru.mobile.core.api.common.Identifiable;
import org.nibiru.mobile.core.impl.common.BaseConfigurable;

import android.content.Intent;

public abstract class BaseIntentAdapter<T> extends BaseConfigurable<T>
		implements Identifiable<String> {
	public final static String PREFIX = "org.nibiru.mobile.";
	private final String ID_KEY = "nibiruId";
	private final Intent intent;

	public BaseIntentAdapter(Intent intent) {
		this.intent = checkNotNull(intent);
	}

	public BaseIntentAdapter(String id, Intent intent) {
		this(intent);
		this.intent.putExtra(ID_KEY, checkNotNull(id));
	}

	@Override
	public String getId() {
		return intent.getExtras().getString(ID_KEY);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K> K getParameter(String key) {
		checkNotNull(key);
		return (K) intent.getExtras().get(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T addParameter(String key, Object value) {
		checkNotNull(key);
		checkNotNull(value);
		intent.putExtra(key, (Serializable) value);
		return (T) this;
	}

	protected Intent getIntent() {
		return intent;
	}
}
