package com.github.tinder.api.http.request.authorization;

import com.github.tinder.api.client.TinderResponse;
import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.Executor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.executor.authorization.AuthSmsValidatePostRequestExecutor;
import com.github.tinder.api.http.request.AbstractRequest;
import com.github.tinder.api.http.response.authorization.AuthSmsValidatePostResponse;
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

public class AuthSmsValidatePostRequest extends AbstractRequest {
    private final Data data;

    private AuthSmsValidatePostRequest(Data data) {
        checkNotNull(data, "'data' cannot be null");
        checkNotNull(data.installId, "'installId' cannot be null");
        checkNotNull(data.phoneNumber, "'phoneNumber' cannot be null");
        checkNotNull(data.otpCode, "'otpCode' cannot be null");
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
        return "/v2/auth/sms/validate";
    }

    @Override
    public Map<String, String> params() {
        return Map.of("auth_type", "sms");
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
                "Content-Type", DEFAULT_CONTENT_TYPE,
                "Accept-Encoding", DEFAULT_ACCEPT_ENCODING
        );
    }

    @Override
    public Map<String, Object> body() {
        return Map.of(
                "phone_number", data.phoneNumber,
                "otp_code", data.otpCode,
                "is_update", false
        );
    }

    @Override
    public String marker(Context context) {
        return data.installId;
    }

    public static class Data {
        private String installId;
        private String phoneNumber;
        private String otpCode;
    }

    @Setter
    @RequiredArgsConstructor
    @Accessors(fluent = true)
    @EqualsAndHashCode(callSuper = true)
    public static class Builder extends Data implements Executor<AuthSmsValidatePostResponse> {
        private final RequestExecutor executor;
        private final JsonMapper jsonMapper;
        private final Context context;
        private String installId;
        private String phoneNumber;
        private String otpCode;

        @Override
        public TinderResponse<AuthSmsValidatePostResponse> execute() {
            Data data = new Data();
            data.installId = installId;
            data.phoneNumber = phoneNumber;
            data.otpCode = otpCode;
            AuthSmsValidatePostRequestExecutor executor = new AuthSmsValidatePostRequestExecutor(
                    new AuthSmsValidatePostRequest(data), this.executor, context, jsonMapper
            );
            return executor.execute();
        }
    }
}
