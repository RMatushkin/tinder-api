package com.github.tinder.api.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TinderClientException extends RuntimeException {
    private final boolean isConnectTimeout;
    private final boolean isRequestTimeout;

    public TinderClientException(String message) {
        super(message);
        isConnectTimeout = false;
        isRequestTimeout = false;
    }

    public TinderClientException(Throwable cause) {
        super(cause);
        isConnectTimeout = false;
        isRequestTimeout = false;
    }

    private TinderClientException(Throwable cause, boolean isConnectTimeout, boolean isRequestTimeout) {
        super(cause);
        this.isConnectTimeout = isConnectTimeout;
        this.isRequestTimeout = isRequestTimeout;
    }

    public static TinderClientException connectTimeout(Throwable cause) {
        return new TinderClientException(cause, false, false);
    }

    public static TinderClientException requestTimeout(Throwable cause) {
        return new TinderClientException(cause, true, false);
    }
}
