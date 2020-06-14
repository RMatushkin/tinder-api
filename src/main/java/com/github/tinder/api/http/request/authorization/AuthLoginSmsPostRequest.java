package com.github.tinder.api.http.request.authorization;

import com.github.tinder.api.client.TinderResponse;
import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.Executor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.executor.authorization.AuthLoginSmsPostRequestExecutor;
import com.github.tinder.api.http.request.AbstractRequest;
import com.github.tinder.api.http.response.authorization.AuthLoginSmsPostResponse;
import com.github.tinder.api.mapping.JsonMapper;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;

import static com.github.tinder.api.client.DefaultConfiguration.DEFAULT_ACCEPT_ENCODING;
import static com.github.tinder.api.client.DefaultConfiguration.DEFAULT_ACCEPT_LANGUAGE;
import static com.github.tinder.api.client.DefaultConfiguration.DEFAULT_APP_VERSION;
import static com.github.tinder.api.client.DefaultConfiguration.DEFAULT_CLIENT_VERSION;
import static com.github.tinder.api.client.DefaultConfiguration.DEFAULT_CONTENT_TYPE;
import static com.github.tinder.api.client.DefaultConfiguration.DEFAULT_OS_VERSION;
import static com.github.tinder.api.client.DefaultConfiguration.DEFAULT_PLATFORM;
import static com.github.tinder.api.client.DefaultConfiguration.DEFAULT_USER_AGENT;
import static com.github.tinder.api.client.DefaultConfiguration.DEFAULT_X_SUPPORTED_IMAGE_FORMATS;
import static lombok.Lombok.checkNotNull;

public class AuthLoginSmsPostRequest extends AbstractRequest {
    private final Data data;

    private AuthLoginSmsPostRequest(Data data) {
        checkNotNull(data, "'data' cannot be null");
        checkNotNull(data.installId, "'installId' cannot be null");
        checkNotNull(data.appsflyerId, "'appsflyerId' cannot be null");
        checkNotNull(data.refreshToken, "'refreshToken' cannot be null");
        this.data = data;
    }

    public static Builder builder(RequestExecutor executor, JsonMapper jsonMapper, Context context) {
        return new Builder(executor, jsonMapper, context);
    }

    @Override
    public String method() {
        return POST_METHOD;
    }

    @Override
    public String path() {
        return "/v2/auth/login/sms";
    }

    @Override
    public Map<String, String> headers(Context context) {
        return Map.of(
                "platform", DEFAULT_PLATFORM,
                "User-Agent", DEFAULT_USER_AGENT,
                "os-version", DEFAULT_OS_VERSION,
                "app-version", DEFAULT_APP_VERSION,
                "x-supported-image-formats", DEFAULT_X_SUPPORTED_IMAGE_FORMATS,
                "Accept-Language", DEFAULT_ACCEPT_LANGUAGE,
                "install-id", data.installId,
                "appsflyer-id", data.appsflyerId,
                "Content-Type", DEFAULT_CONTENT_TYPE,
                "Accept-Encoding", DEFAULT_ACCEPT_ENCODING
        );
    }

    @Override
    public Map<String, Object> body() {
        return Map.of(
                "client_version", DEFAULT_CLIENT_VERSION,
                "refresh_token", data.refreshToken
        );
    }

    @Override
    public String marker(Context context) {
        return data.installId;
    }

    public static class Data {
        private String installId;
        private String appsflyerId;
        private String refreshToken;
    }

    @Setter
    @RequiredArgsConstructor
    @Accessors(fluent = true)
    @EqualsAndHashCode(callSuper = true)
    public static class Builder extends Data implements Executor<AuthLoginSmsPostResponse> {
        private final RequestExecutor executor;
        private final JsonMapper jsonMapper;
        private final Context context;
        private String installId;
        private String appsflyerId;
        private String refreshToken;

        @Override
        public TinderResponse<AuthLoginSmsPostResponse> execute() {
            Data data = new Data();
            data.installId = installId;
            data.appsflyerId = appsflyerId;
            data.refreshToken = refreshToken;
            AuthLoginSmsPostRequestExecutor executor = new AuthLoginSmsPostRequestExecutor(
                    new AuthLoginSmsPostRequest(data), this.executor, context, jsonMapper
            );
            return executor.execute();
        }
    }
}
