package org.nibiru.mobile.core.api.serializer;

import com.google.common.base.Objects;

import java.util.Arrays;

import static com.google.common.base.Preconditions.checkNotNull;

public class TypeLiteral<T> {
    public static <X> TypeLiteral<X> create(Class<X> type) {
        return new TypeLiteral<>(type);
    }

    public static <Z, X extends Z> TypeLiteral<X> create(Class<Z> type,
                                            Class<?>... parameters) {
        return new TypeLiteral<>((Class<X>) type, parameters);
    }

    private final Class<T> type;
    private final Class[] parameters;

    private TypeLiteral(Class<T> type, Class<?>... parameters) {
        this.type = checkNotNull(type);
        this.parameters = checkNotNull(parameters);
    }

    public Class<T> getType() {
        return type;
    }

    public Class<?>[] getParameters() {
        return parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TypeLiteral)) return false;
        TypeLiteral<?> that = (TypeLiteral<?>) o;
        return Objects.equal(type, that.type) &&
                Arrays.equals(parameters, that.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(type, parameters.length);
    }

    @Override
    public String toString() {
        return "TypeLiteral{" +
                "type=" + type +
                ", parameters=" + Arrays.toString(parameters) +
                '}';
    }
}
