package org.nibiru.mobile.core.api.async;

import com.google.common.base.Function;

import org.nibiru.mobile.core.api.common.Consumer;

class MappedPromise<D, V, E extends Exception> implements Promise<V, E> {
    private final Promise<D, E> decorated;
    private final Function<D, V> converter;

    MappedPromise(Promise<D, E> decorated,
                  Function<D, V> converter) {
        this.decorated = decorated;
        this.converter = converter;
    }

    @Override
    public Promise<V, E> then(Consumer<V> successCallback) {
        decorated.then(result -> successCallback.accept(converter.apply(result)));
        return this;
    }

    @Override
    public Promise<V, E> capture(Consumer<E> errorCallback) {
        decorated.capture(errorCallback);
        return this;
    }

    @Override
    public Promise<V, E> last(Runnable finallyCallback) {
        decorated.last(finallyCallback);
        return this;
    }

    @Override
    public <V1> Promise<V1, E> map(Function<V, V1> converter) {
        return new MappedPromise<>(this, converter);
    }

    @Override
    public <E1 extends Exception> Promise<V, E1> mapError(Function<E, E1> converter) {
        return new MappedErrorPromise<>(this, converter);
    }
}
