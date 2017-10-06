package org.nibiru.mobile.core.impl.common;

import org.nibiru.mobile.core.api.common.Configurable;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class BaseConfigurable<C> implements Configurable<C> {
	@Override
	public <T> T getParameter(Enum<?> key) {
		checkNotNull(key);
		return getParameter(key.toString());
	}

	@Override
	public C addParameter(Enum<?> key, @Nullable Object value) {
		checkNotNull(key);
		return addParameter(key.toString(), value);
	}
}
