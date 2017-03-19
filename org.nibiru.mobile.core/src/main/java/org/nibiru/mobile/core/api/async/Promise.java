package org.nibiru.mobile.core.api.async;

import com.google.common.base.Function;

import org.nibiru.mobile.core.api.common.Consumer;

public interface Promise<V, E extends Exception> {
    Promise<V, E> then(Consumer<V> successCallback);

    Promise<V, E> capture(Consumer<E> errorCallback);

    Promise<V, E> last(Runnable finallyCallback);

    <V1> Promise<V1, E> map(Function<V, V1> converter);

    <E1 extends Exception> Promise<V, E1> mapError(Function<E, E1> converter);
}
