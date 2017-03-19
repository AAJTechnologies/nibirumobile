package org.nibiru.mobile.core.api.async;

import com.google.common.base.Function;

import org.nibiru.mobile.core.api.common.Consumer;

class MappedErrorPromise<D extends Exception, V, E extends Exception> implements Promise<V, E> {
    private final Promise<V, D> decorated;
    private final Function<D,E> converter;

    MappedErrorPromise(Promise<V,D> decorated,
                  Function<D,E> converter) {
        this.decorated = decorated;
        this.converter = converter;
    }

    @Override
    public Promise<V, E> then(Consumer<V> successCallback) {
        decorated.then(successCallback);
        return this;
    }

    @Override
    public Promise<V, E> capture(Consumer<E> errorCallback) {
        decorated.capture(result -> errorCallback.accept(converter.apply(result)));
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
