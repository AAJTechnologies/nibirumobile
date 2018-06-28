package org.nibiru.mobile.java.ioc;

import org.nibiru.mobile.core.api.http.HttpManager;
import org.nibiru.mobile.core.api.serializer.Serializer;
import org.nibiru.mobile.core.api.service.PushServiceFactory;
import org.nibiru.mobile.java.http.okhttp.OkHttpHttpManager;
import org.nibiru.mobile.java.serializer.jackson.JacksonSerializer;
import org.nibiru.mobile.java.service.WebSocketPushServiceFactory;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class DefaultJavaModule {
    @Provides
    public OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    public HttpManager getHttpManager(OkHttpHttpManager manager) {
        return manager;
    }

    @Provides
    public Serializer getSerializer(JacksonSerializer serializer) {
        return serializer;
    }

    @Provides
    public PushServiceFactory getPushServiceFactory(WebSocketPushServiceFactory webSocketPushServiceFactory) {
        return webSocketPushServiceFactory;
    }
}
