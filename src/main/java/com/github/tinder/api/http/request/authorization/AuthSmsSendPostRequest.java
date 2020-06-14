package com.github.tinder.api.http.request.authorization;

import com.github.tinder.api.client.TinderResponse;
import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.Executor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.executor.authorization.AuthSmsSendPostRequestExecutor;
import com.github.tinder.api.http.request.AbstractRequest;
import com.github.tinder.api.http.response.authorization.AuthSmsSendPostResponse;
import com.github.tinder.api.mapping.JsonMapper;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;

import static com.github.tinder.api.client.DefaultConfiguration.DEFAULT_ACCEPT_ENCODING;
import static com.github.tinder.api.client.DefaultConfiguration.DEFAULT_ACCEPT_LANGUAGE;
import static com.github.tinder.api.client.DefaultConfiguration.DEFAULT_APP_VERSION;
import static com.github.tinder.api.client.DefaultConfiguration.DEFAULT_CONTENT_TYPE;
import static com.github.tinder.api.client.DefaultConfiguration.DEFAULT_OS_VERSION;
import static com.github.tinder.api.client.DefaultConfiguration.DEFAULT_PLATFORM;
import static com.github.tinder.api.client.DefaultConfiguration.DEFAULT_USER_AGENT;
import static com.github.tinder.api.client.DefaultConfiguration.DEFAULT_X_SUPPORTED_IMAGE_FORMATS;
import static lombok.Lombok.checkNotNull;

public class AuthSmsSendPostRequest extends AbstractRequest {
    private final Data data;

    private AuthSmsSendPostRequest(Data data) {
        checkNotNull(data, "'data' cannot be null");
        checkNotNull(data.installId, "'installId' cannot be null");
        checkNotNull(data.phoneNumber, "'phoneNumber' cannot be null");
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
        return "/v2/auth/sms/send";
    }

    @Override
    public Map<String, String> params() {
        return Map.of("auth_type", "sms");
    }

    @Override
    public Map<String, String> headers(Context context) {
        return Map.of(
                "User-Agent", DEFAULT_USER_AGENT,
                "os-version", DEFAULT_OS_VERSION,
                "app-version", DEFAULT_APP_VERSION,
                "platform", DEFAULT_PLATFORM,
                "x-supported-image-formats", DEFAULT_X_SUPPORTED_IMAGE_FORMATS,
                "Accept-Language", DEFAULT_ACCEPT_LANGUAGE,
                "install-id", data.installId,
                "Accept-Encoding", DEFAULT_ACCEPT_ENCODING,
                "Content-Type", DEFAULT_CONTENT_TYPE
        );
    }

    @Override
    public Map<String, Object> body() {
        return Map.of("phone_number", data.phoneNumber);
    }

    @Override
    public String marker(Context context) {
        return data.installId;
    }

    public static class Data {
        private String installId;
        private String phoneNumber;
    }

    @Setter
    @RequiredArgsConstructor
    @Accessors(fluent = true)
    @EqualsAndHashCode(callSuper = true)
    public static class Builder extends Data implements Executor<AuthSmsSendPostResponse> {
        private final RequestExecutor executor;
        private final JsonMapper jsonMapper;
        private final Context context;
        private String installId;
        private String phoneNumber;

        @Override
        public TinderResponse<AuthSmsSendPostResponse> execute() {
            Data data = new Data();
            data.installId = installId;
            data.phoneNumber = phoneNumber;
            AuthSmsSendPostRequestExecutor executor = new AuthSmsSendPostRequestExecutor(
                    new AuthSmsSendPostRequest(data), this.executor, context, jsonMapper
            );
            return executor.execute();
        }
    }
}
