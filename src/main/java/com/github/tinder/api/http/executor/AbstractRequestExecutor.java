package com.github.tinder.api.http.executor;

import com.github.tinder.api.client.TinderResponse;
import com.github.tinder.api.context.Context;
import com.github.tinder.api.exception.TinderClientException;
import com.github.tinder.api.http.request.Request;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.net.http.HttpResponse;

import static com.github.tinder.api.client.ErrorMessage.INVALID_PROXY_CREDENTIALS;
import static com.github.tinder.api.client.ErrorMessage.PROXY_DOES_NOT_RESPOND;
import static com.github.tinder.api.client.ErrorMessage.TINDER_DOES_NOT_RESPOND;
import static com.github.tinder.api.util.InputStreamUtil.readInputStream;
import static com.github.tinder.api.util.InputStreamUtil.unGzip;
import static javax.servlet.http.HttpServletResponse.SC_PROXY_AUTHENTICATION_REQUIRED;

@RequiredArgsConstructor
public abstract class AbstractRequestExecutor<R extends Request, T> implements Executor<T> {
    private final R request;
    private final RequestExecutor executor;
    private final Context context;

    protected abstract T convert(String content);

    protected boolean enabledUnGzip() {
        return true;
    }

    @Override
    public final TinderResponse<T> execute() {
        HttpResponse<InputStream> httpResponse;
        try {
            httpResponse = executor.execute(request, context);
        } catch (TinderClientException e) {
            if (e.isConnectTimeout()) {
                return new TinderResponse<>(PROXY_DOES_NOT_RESPOND);
            }
            if (e.isRequestTimeout()) {
                return new TinderResponse<>(TINDER_DOES_NOT_RESPOND);
            }
            throw e;
        }
        return (SC_PROXY_AUTHENTICATION_REQUIRED == httpResponse.statusCode())
                ? new TinderResponse<T>(INVALID_PROXY_CREDENTIALS)
                : readHttpResponse(httpResponse);
    }

    private TinderResponse<T> readHttpResponse(HttpResponse<InputStream> httpResponse) {
        InputStream inputStream = httpResponse.body();
        T body = null;
        String content = null;
        if (enabledUnGzip()) {
            content = hasGzip(httpResponse) ? unGzip(inputStream) : readInputStream(inputStream);
            try {
                body = convert(content);
            } catch (Exception ignored) {
            }
        } else {
            //noinspection unchecked
            body = (T) inputStream;
        }
        return new TinderResponse<>(httpResponse, body, content);
    }

    private boolean hasGzip(HttpResponse<InputStream> httpResponse) {
        String contentEncoding = httpResponse.headers().firstValue("Content-Encoding").orElse("");
        return "gzip".equals(contentEncoding);
    }
}
