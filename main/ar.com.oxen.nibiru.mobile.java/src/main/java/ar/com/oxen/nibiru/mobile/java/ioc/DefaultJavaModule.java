package ar.com.oxen.nibiru.mobile.java.ioc;

import ar.com.oxen.nibiru.mobile.core.api.http.HttpManager;
import ar.com.oxen.nibiru.mobile.core.api.serializer.Serializer;
import ar.com.oxen.nibiru.mobile.java.http.okhttp.OkHttpHttpManager;
import ar.com.oxen.nibiru.mobile.java.serializer.jackson.JacksonSerializer;
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
