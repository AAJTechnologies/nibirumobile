package org.nibiru.mobile.gwt.http;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import org.nibiru.mobile.core.api.async.Callback;
import org.nibiru.mobile.core.api.config.BaseUrl;
import org.nibiru.mobile.core.api.http.HttpCallback;
import org.nibiru.mobile.core.api.http.HttpManager;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;

public class RequestBuilderHttpManager implements HttpManager {
	private final String baseUrl;

	@Inject
	public RequestBuilderHttpManager(@BaseUrl String baseUrl) {
		this.baseUrl = checkNotNull(baseUrl);
	}

	@Override
	public <T> void send(String url, final Callback<T> callback,
			final HttpCallback<T> httpCallback) {

		try {
			// TODO: Method and headers are hardcode. PArameterization might be needed in the future. 
			RequestBuilder requestBuilder = new RequestBuilder(
					RequestBuilder.POST, baseUrl + url);
			requestBuilder.setHeader(HttpManager.CONTENT_TYPE_HEADER, HttpManager.APPLICATION_JSON_MIME);
			requestBuilder.setHeader(HttpManager.ACCEPT_HEADER, HttpManager.APPLICATION_JSON_MIME);
			
			requestBuilder.sendRequest(httpCallback.buildRequest(),
					new RequestCallback() {

						@Override
						public void onResponseReceived(Request request,
								Response response) {
							try {
								String text = response.getText();

								if (text != null && text.length() == 0) {
									text = null;
								}

								callback.onSuccess(httpCallback
										.parseResponse(text));
							} catch (Exception e) {
								callback.onFailure(e);
							}
						}

						@Override
						public void onError(Request request, Throwable exception) {
							callback.onFailure((Exception) exception);
						}
					});
		} catch (RequestException e) {
			callback.onFailure(e);
		}
	}
}
