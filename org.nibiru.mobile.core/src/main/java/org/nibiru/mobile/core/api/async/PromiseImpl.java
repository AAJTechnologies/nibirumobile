package org.nibiru.mobile.core.api.async;

import com.google.common.base.Function;

import org.nibiru.mobile.core.api.common.Consumer;

import static com.google.common.base.Preconditions.checkNotNull;

class PromiseImpl<V, E extends Exception> implements Promise<V, E> {
    private boolean resolved;
    private V value;
    private boolean rejected;
    private E exception;
    private Consumer<V> successCallback;
    private Consumer<E> errorCallback;
    private Runnable finallyCallback;

    PromiseImpl() {
    }

    void resolve(V value) {
        resolved = true;
        this.value = value;
        if (successCallback != null) {
            successCallback.accept(value);
        }
        if (finallyCallback != null) {
            finallyCallback.run();
        }
    }

    void reject(E exception) {
        rejected = true;
        this.exception = exception;
        if (errorCallback != null) {
            errorCallback.accept(exception);
        }
        if (finallyCallback != null) {
            finallyCallback.run();
        }
    }

    @Override
    public Promise<V, E> then(Consumer<V> successCallback) {
        this.successCallback = checkNotNull(successCallback);
        if (resolved) {
            successCallback.accept(value);
        }
        return this;
    }

    @Override
    public Promise<V, E> capture(Consumer<E> errorCallback) {
        this.errorCallback = checkNotNull(errorCallback);
        if (rejected) {
            errorCallback.accept(exception);
        }
        return this;
    }

    @Override
    public Promise<V, E> last(Runnable finallyCallback) {
        this.finallyCallback = checkNotNull(finallyCallback);
        if (resolved || rejected) {
            finallyCallback.run();
        }
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
