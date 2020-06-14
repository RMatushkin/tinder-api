package com.github.tinder.api.http.request.authorization;

import com.github.tinder.api.client.TinderResponse;
import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.Executor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.executor.authorization.OnboardingCompletePostRequestExecutor;
import com.github.tinder.api.http.request.AbstractRequest;
import com.github.tinder.api.http.response.authorization.OnboardingCompletePostResponse;
import com.github.tinder.api.mapping.JsonMapper;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashMap;
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

public class OnboardingCompletePostRequest extends AbstractRequest {
    private final Data data;

    private OnboardingCompletePostRequest(Data data) {
        checkNotNull(data, "'data' cannot be null");
        checkNotNull(data.onboardingToken, "'onboardingToken' cannot be null");
        checkNotNull(data.installId, "'installId' cannot be null");
        checkNotNull(data.appsflyerId, "'appsflyerId' cannot be null");
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
        return "/v2/onboarding/complete";
    }

    @Override
    public Map<String, String> headers(Context context) {
        return new HashMap<>() {{
            put("token", data.onboardingToken);
            put("install-id", data.installId);
            put("appsflyer-id", data.appsflyerId);
            put("platform", DEFAULT_PLATFORM);
            put("User-Agent", DEFAULT_USER_AGENT);
            put("os-version", DEFAULT_OS_VERSION);
            put("app-version", DEFAULT_APP_VERSION);
            put("x-supported-image-formats", DEFAULT_X_SUPPORTED_IMAGE_FORMATS);
            put("Accept-Language", DEFAULT_ACCEPT_LANGUAGE);
            put("Accept-Encoding", DEFAULT_ACCEPT_ENCODING);
            put("Content-Type", DEFAULT_CONTENT_TYPE);
        }};
    }

    @Override
    public String marker(Context context) {
        return data.onboardingToken;
    }

    public static class Data {
        private String onboardingToken;
        private String installId;
        private String appsflyerId;
    }

    @Setter
    @RequiredArgsConstructor
    @Accessors(fluent = true)
    @EqualsAndHashCode(callSuper = true)
    public static class Builder extends Data implements Executor<OnboardingCompletePostResponse> {
        private final RequestExecutor executor;
        private final JsonMapper jsonMapper;
        private final Context context;
        private String onboardingToken;
        private String installId;
        private String appsflyerId;

        @Override
        public TinderResponse<OnboardingCompletePostResponse> execute() {
            Data data = new Data();
            data.onboardingToken = onboardingToken;
            data.installId = installId;
            data.appsflyerId = appsflyerId;
            OnboardingCompletePostRequestExecutor executor = new OnboardingCompletePostRequestExecutor(
                    new OnboardingCompletePostRequest(data), this.executor, context, jsonMapper
            );
            return executor.execute();
        }
    }
}
