package org.nibiru.mobile.java.http.okhttp;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;

import javax.inject.Inject;

import org.nibiru.mobile.core.api.async.Callback;
import org.nibiru.mobile.core.api.config.BaseUrl;
import org.nibiru.mobile.core.api.http.HttpCallback;
import org.nibiru.mobile.core.api.http.HttpManager;
import org.nibiru.mobile.java.async.AsyncManager;

import com.google.common.base.Supplier;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpHttpManager implements HttpManager {
	private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	
	private final String baseUrl;
	private final OkHttpClient okHttpClient;
	private final AsyncManager asyncManager;

	@Inject
	public OkHttpHttpManager(@BaseUrl String baseUrl,
			OkHttpClient okHttpClient, AsyncManager asyncManager) {
		this.baseUrl = checkNotNull(baseUrl);
		this.okHttpClient = checkNotNull(okHttpClient);
		this.asyncManager = checkNotNull(asyncManager);
	}

	@Override
	public <T> void send(final String url, final Callback<T> callback,
			final HttpCallback<T> httpCallback) {
		asyncManager.runAsync(new Supplier<T>() {
			@Override
			public T get() {
				return runAsync(url, httpCallback);
			}
		}, callback);
	}

	private <T> T runAsync(String url, HttpCallback<T> httpCallback) {
		try {
			RequestBody body = RequestBody.create(JSON, httpCallback.buildRequest());
			Request request = new Request.Builder().url(baseUrl + url).post(body).build();
			Response response = okHttpClient.newCall(request).execute();
			return httpCallback.parseResponse(response.body().string());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
