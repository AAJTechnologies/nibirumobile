package ar.com.oxen.nibiru.mobile.core.impl.service;

import ar.com.oxen.nibiru.mobile.core.api.async.Callback;
import ar.com.oxen.nibiru.mobile.core.api.http.HttpCallback;
import ar.com.oxen.nibiru.mobile.core.api.http.HttpManager;
import ar.com.oxen.nibiru.mobile.core.api.serializer.Serializer;

public class RestService extends BaseService {
	public RestService(String serviceName, HttpManager httpManager,
			Serializer serializer) {
		super(serviceName, httpManager, serializer);
	}

	@Override
	public <T> void invoke(String method, final Object requestDto,
			final Class<T> responseClass, Callback<T> callback) {
		this.getHttpManager().send(this.getServiceName() + "/" + method,
				callback, new HttpCallback<T>() {

					@Override
					public String buildRequest() {
						return getSerializer().serialize(requestDto);
					}

					@Override
					public T parseResponse(String responseMessage) {
						return getSerializer().deserialize(responseMessage,
								responseClass);
					}
				});

	}
}
