package org.nibiru.mobile.core.api.async;

public class Deferred<V, E extends Exception> {
    public static <V, E extends Exception> Deferred<V, E> defer() {
        return new Deferred<>();
    }

    private final PromiseImpl<V, E> promise = new PromiseImpl<>();

    public void resolve(V value) {
        promise.resolve(value);
    }

    public void reject(E exception) {
        promise.reject(exception);
    }

    public PromiseImpl<V, E> promise() {
        return promise;
    }
}
