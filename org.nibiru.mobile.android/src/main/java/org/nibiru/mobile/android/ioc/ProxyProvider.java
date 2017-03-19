package org.nibiru.mobile.android.ioc;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import javax.inject.Inject;
import javax.inject.Provider;

public class ProxyProvider<T, I extends InvocationHandler> implements
		Provider<T> {
	private final Class<T> proxyInterface;
	private final I handler;

	@Inject
	public ProxyProvider(Class<T> proxyInterface, I handler) {
		this.proxyInterface = checkNotNull(proxyInterface);
		this.handler = checkNotNull(handler);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get() {
		return (T) Proxy.newProxyInstance(getClass().getClassLoader(),
				new Class<?>[] { proxyInterface }, handler);
	}
}
