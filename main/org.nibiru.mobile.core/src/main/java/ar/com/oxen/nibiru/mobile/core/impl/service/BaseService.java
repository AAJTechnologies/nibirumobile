package ar.com.oxen.nibiru.mobile.core.impl.service;

import static com.google.common.base.Preconditions.checkNotNull;
import ar.com.oxen.nibiru.mobile.core.api.http.HttpManager;
import ar.com.oxen.nibiru.mobile.core.api.serializer.Serializer;
import ar.com.oxen.nibiru.mobile.core.api.service.RemoteService;

abstract class BaseService implements RemoteService {
	private final String serviceName;
	private final HttpManager httpManager;
	private final Serializer serializer;

	public BaseService(String serviceName, HttpManager httpManager,
			Serializer serializer) {
		this.serviceName = checkNotNull(serviceName);
		this.httpManager = checkNotNull(httpManager);
		this.serializer = checkNotNull(serializer);
	}

	protected String getServiceName() {
		return serviceName;
	}

	protected HttpManager getHttpManager() {
		return httpManager;
	}

	protected Serializer getSerializer() {
		return serializer;
	}
}
