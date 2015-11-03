package ar.com.oxen.nibiru.mobile.java.http.httpclient;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Closeable;
import java.io.IOException;
import java.util.Scanner;

import javax.inject.Inject;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import ar.com.oxen.nibiru.mobile.core.api.async.Callback;
import ar.com.oxen.nibiru.mobile.core.api.config.BaseUrl;
import ar.com.oxen.nibiru.mobile.core.api.http.HttpCallback;
import ar.com.oxen.nibiru.mobile.core.api.http.HttpManager;
import ar.com.oxen.nibiru.mobile.java.thread.ThreadHelper;

import com.google.common.base.Supplier;
import com.google.common.base.Throwables;
import com.google.common.io.Closer;

public class HttpClientHttpManager implements HttpManager {
	private final String baseUrl;
	private final HttpClient httpClient;
	private final ThreadHelper threadHelper;

	@Inject
	public HttpClientHttpManager(@BaseUrl String baseUrl,
			HttpClient httpClient, ThreadHelper threadHelper) {
		this.baseUrl = checkNotNull(baseUrl);
		this.httpClient = checkNotNull(httpClient);
		this.threadHelper = checkNotNull(threadHelper);
	}

	@Override
	public <T> void send(final String url, final Callback<T> callback,
			final HttpCallback<T> httpCallback) {
		threadHelper.runOnNewThread(new Supplier<T>() {
			@Override
			public T get() {
				return runAsync(url, httpCallback);
			}
		}, callback);
	}

	private <T> T runAsync(String url, HttpCallback<T> httpCallback) {
		try {
			Closer closer = Closer.create();
			try {
				// TODO: Method and headers are hardcode. PArameterization might be needed in the future. 
				HttpPost request = new HttpPost(baseUrl + url);
				request.setEntity(new StringEntity(httpCallback.buildRequest()));
				request.addHeader(HttpManager.CONTENT_TYPE_HEADER, HttpManager.APPLICATION_JSON_MIME);
				request.addHeader(HttpManager.ACCEPT_HEADER, HttpManager.APPLICATION_JSON_MIME);
				HttpResponse response = httpClient.execute(request);
				Scanner scanner = new Scanner(response.getEntity().getContent());
				scanner.useDelimiter("\\A");
				// Scanner does not implements Closeable in Android.
				closer.register(new Closeable() {
					@Override
					public void close() throws IOException {
						scanner.close();
					}
				});
				return httpCallback.parseResponse(scanner.next());
			} catch (Throwable e) {
				throw closer.rethrow(e);
			} finally {
				closer.close();
			}
		} catch (IOException ioException) {
			throw Throwables.propagate(ioException);
		}
	}
}
