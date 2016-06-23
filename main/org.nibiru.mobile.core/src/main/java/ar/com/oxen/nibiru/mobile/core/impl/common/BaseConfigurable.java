package ar.com.oxen.nibiru.mobile.core.impl.common;

import static com.google.common.base.Preconditions.checkNotNull;
import ar.com.oxen.nibiru.mobile.core.api.common.Configurable;

public abstract class BaseConfigurable<C> implements Configurable<C> {
	@Override
	public <T> T getParameter(Enum<?> key) {
		checkNotNull(key);
		return getParameter(key.toString());
	}

	@Override
	public C addParameter(Enum<?> key, Object value) {
		checkNotNull(key);
		checkNotNull(value);
		return addParameter(key.toString(), value);
	}
}
