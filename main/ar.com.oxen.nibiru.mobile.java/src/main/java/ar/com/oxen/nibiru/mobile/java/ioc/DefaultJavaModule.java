package ar.com.oxen.nibiru.mobile.java.ioc;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.inject.AbstractModule;

import ar.com.oxen.nibiru.mobile.core.api.http.HttpManager;
import ar.com.oxen.nibiru.mobile.core.api.serializer.Serializer;
import ar.com.oxen.nibiru.mobile.java.http.httpclient.HttpClientHttpManager;
import ar.com.oxen.nibiru.mobile.java.serializer.jackson.JacksonSerializer;

public class DefaultJavaModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(HttpClient.class).to(DefaultHttpClient.class);
		bind(HttpManager.class).to(HttpClientHttpManager.class);
		bind(Serializer.class).to(JacksonSerializer.class);
	}
}
