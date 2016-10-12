package org.nibiru.mobile.java.ioc;

import org.nibiru.mobile.core.api.http.HttpManager;
import org.nibiru.mobile.core.api.serializer.Serializer;
import org.nibiru.mobile.java.http.okhttp.OkHttpHttpManager;
import org.nibiru.mobile.java.serializer.jackson.JacksonSerializer;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class DefaultJavaModule {
	@Provides
	public OkHttpClient  getOkHttpClient () {
		return new OkHttpClient();
	}

	@Provides
	public HttpManager getHttpManager(OkHttpHttpManager manager) {
		return manager;
	}

	@Provides
	public Serializer getSerializer(JacksonSerializer serializer) {
		return serializer;
	}
}
