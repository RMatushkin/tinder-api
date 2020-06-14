package com.github.tinder.api.client;

import lombok.Data;

import java.io.InputStream;
import java.net.http.HttpResponse;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@Data
public class TinderResponse<T> {
    private final HttpResponse<InputStream> httpResponse;
    private final ErrorMessage errorMessage;
    private final T data;
    private final String content;

    public TinderResponse(HttpResponse<InputStream> httpResponse, T data, String content) {
        this.httpResponse = httpResponse;
        this.errorMessage = null;
        this.data = data;
        this.content = content;
    }

    public TinderResponse(ErrorMessage errorMessage) {
        this.httpResponse = null;
        this.errorMessage = errorMessage;
        this.data = null;
        this.content = null;
    }

    public boolean isOk() {
        return (hasNotErrorMessage() && hasStatusCode(SC_OK));
    }

    public boolean isUnauthorized() {
        return (hasNotErrorMessage() && hasStatusCode(SC_UNAUTHORIZED));
    }

    public boolean isTooManyRequests() {
        return (hasNotErrorMessage() && hasStatusCode(429));
    }

    public boolean isServerError() {
        if (hasNotErrorMessage() && (nonNull(httpResponse))) {
            int statusCode = getStatusCode();
            return ((statusCode >= 500) && (statusCode <= 599));
        }
        return false;
    }

    public boolean hasErrorMessage() {
        return nonNull(errorMessage);
    }

    public boolean hasNotErrorMessage() {
        return isNull(errorMessage);
    }

    public boolean hasStatusCode(int statusCode) {
        return (nonNull(httpResponse) && (statusCode == httpResponse.statusCode()));
    }

    public int getStatusCode() {
        if (httpResponse == null) {
            throw new UnsupportedOperationException();
        }
        return httpResponse.statusCode();
    }
}
