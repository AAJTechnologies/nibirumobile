package org.nibiru.mobile.gwt.data;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import org.nibiru.mobile.core.api.async.Deferred;
import org.nibiru.mobile.core.api.async.Promise;
import org.nibiru.mobile.core.api.config.AppName;
import org.nibiru.mobile.gwt.app.DatabaseBootstrap;

import com.gwtmobile.persistence.client.Callback;
import com.gwtmobile.persistence.client.Persistence;

public class GwtMobileDatabaseBootstrap implements DatabaseBootstrap {
	private final String appName;

	@Inject
	public GwtMobileDatabaseBootstrap(@AppName String appName) {
		this.appName = checkNotNull(appName);
	}

	@Override
	public Promise<Void, Exception> createDatabase() {
		Deferred<Void, Exception> deferred = Deferred.defer();

		Persistence.connect(appName, appName + " database", 5 * 1024 * 1024);

		Persistence.schemaSync(() -> {
            deferred.resolve(null);
        });
		return deferred.promise();
	}
}
