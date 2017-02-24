package org.nibiru.mobile.java.http.okhttp;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;

import javax.inject.Inject;

import org.nibiru.mobile.core.api.async.Promise;
import org.nibiru.mobile.core.api.config.BaseUrl;
import org.nibiru.mobile.core.api.http.HttpException;
import org.nibiru.mobile.core.api.http.HttpManager;
import org.nibiru.mobile.core.api.http.HttpStatus;
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
	public Promise<String, HttpException> send(String url, String request) {
		return asyncManager.runAsync(() -> runAsync(url, request));
	}

	private String runAsync(String url, String requestBody ) {
		try {
			RequestBody body = RequestBody.create(JSON, requestBody);
			Request request = new Request.Builder().url(baseUrl + url).post(body).build();
			Response response = okHttpClient.newCall(request).execute();
			if (!response.isSuccessful()) {
				throw new HttpException(HttpStatus.valueOf(response.code()), response.message());
			}
			return response.body().string();
		} catch (IOException e) {
			throw new HttpException(e);
		}
	}
}
