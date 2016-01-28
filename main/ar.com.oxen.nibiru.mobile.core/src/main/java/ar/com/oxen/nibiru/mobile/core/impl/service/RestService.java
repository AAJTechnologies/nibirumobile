package ar.com.oxen.nibiru.mobile.core.impl.service;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nullable;

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
	public <T> void invoke(String method, final @Nullable Object requestDto,
			final Class<T> responseClass, Callback<T> callback) {
		checkNotNull(method);
		checkNotNull(responseClass);
		checkNotNull(callback);
		getHttpManager().send(getServiceName() + "/" + method,
				callback, new HttpCallback<T>() {

					@Override
					public String buildRequest() {
						return requestDto != null ? getSerializer().serialize(requestDto) : "";
					}

					@Override
					public T parseResponse(String responseMessage) {
						return responseMessage != null ?getSerializer().deserialize(responseMessage,
								responseClass) : null;
					}
				});

	}
}
