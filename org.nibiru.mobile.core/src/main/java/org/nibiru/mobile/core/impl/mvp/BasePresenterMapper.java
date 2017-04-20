package org.nibiru.mobile.core.impl.mvp;

import com.google.common.collect.Maps;

import org.nibiru.mobile.core.api.ui.mvp.NoPresenterFoundException;
import org.nibiru.mobile.core.api.ui.mvp.Presenter;
import org.nibiru.mobile.core.api.ui.mvp.PresenterMapper;

import java.util.Map;

import javax.inject.Provider;

import static com.google.common.base.Preconditions.checkNotNull;

public class BasePresenterMapper implements PresenterMapper {
	private final Map<String, Provider<?>> providers = Maps.newHashMap();

	protected <T extends Presenter<?>> void map(String place,
			Provider<T> presenter) {
		checkNotNull(place);
		checkNotNull(presenter);
		providers.put(place, presenter);
	}

	protected <T extends Presenter<?>> void map(Enum<?> place,
			Provider<T> presenter) {
		checkNotNull(place);
		checkNotNull(presenter);
		map(place.toString(), presenter);
	}

	@Override
	public Presenter<?> getPresenter(String place) {
		checkNotNull(place);
		if (!providers.containsKey(place)) {
			throw new NoPresenterFoundException(place);
		}
		return (Presenter<?>) providers.get(place).get();
	}
}
