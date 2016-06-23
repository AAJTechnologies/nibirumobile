package org.nibiru.mobile.gwt.data;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import org.nibiru.mobile.core.api.async.Callback;
import org.nibiru.mobile.gwt.app.DatabaseBootstrap;

public class DummyDatabaseBootstrap implements DatabaseBootstrap {
	@Inject
	public DummyDatabaseBootstrap() {
	}

	@Override
	public void createDatabase(Callback<Void> callback) {
		checkNotNull(callback);
		callback.onSuccess(null);
	}
}
