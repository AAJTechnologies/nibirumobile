package org.nibiru.mobile.core.api.http;

import com.google.common.base.Preconditions;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * An exception resulting from an HTTP response..
 */
public class HttpException extends RuntimeException {
    private final HttpStatus status;

    public HttpException(HttpStatus status, String message) {
        super(message);
        this.status = checkNotNull(status);
    }

    public HttpException(Throwable cause) {
        super(cause);
        this.status = null;
    }

    @Nullable
    public HttpStatus getStatus() {
        return status;
    }
}
