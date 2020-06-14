package com.github.tinder.api.http.request.authorization;

import com.github.tinder.api.client.TinderResponse;
import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.Executor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.executor.authorization.OnboardingFieldsGetRequestExecutor;
import com.github.tinder.api.http.request.AbstractRequest;
import com.github.tinder.api.http.response.authorization.OnboardingFieldsGetResponse;
import com.github.tinder.api.mapping.JsonMapper;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

import static com.github.tinder.api.client.DefaultConfiguration.DEFAULT_ACCEPT_ENCODING;
import static com.github.tinder.api.client.DefaultConfiguration.DEFAULT_ACCEPT_LANGUAGE;
import static com.github.tinder.api.client.DefaultConfiguration.DEFAULT_APP_VERSION;
import static com.github.tinder.api.client.DefaultConfiguration.DEFAULT_OS_VERSION;
import static com.github.tinder.api.client.DefaultConfiguration.DEFAULT_PLATFORM;
import static com.github.tinder.api.client.DefaultConfiguration.DEFAULT_USER_AGENT;
import static com.github.tinder.api.client.DefaultConfiguration.DEFAULT_X_SUPPORTED_IMAGE_FORMATS;
import static java.util.Arrays.asList;
import static lombok.Lombok.checkNotNull;

public class OnboardingFieldsGetRequest extends AbstractRequest {
    private final Data data;

    private OnboardingFieldsGetRequest(Data data) {
        checkNotNull(data, "'data' cannot be null");
        checkNotNull(data.onboardingToken, "'onboardingToken' cannot be null");
        this.data = data;
    }

    public static Builder builder(RequestExecutor executor, JsonMapper jsonMapper, Context context) {
        return new Builder(executor, jsonMapper, context);
    }

    @Override
    public String method() {
        return GET_METHOD;
    }

    @Override
    public String path() {
        return "/v2/onboarding/fields";
    }

    @Override
    public Map<String, List<String>> selfTitledParams() {
        return Map.of("requested", asList(
                "name",
                "birth_date",
                "gender",
                "custom_gender",
                "show_gender_on_profile",
                "photos",
                "email",
                "password",
                "allow_email_marketing",
                "consents",
                "schools"
        ));
    }

    @Override
    public Map<String, String> headers(Context context) {
        return Map.of(
                "token", data.onboardingToken,
                "platform", DEFAULT_PLATFORM,
                "User-Agent", DEFAULT_USER_AGENT,
                "os-version", DEFAULT_OS_VERSION,
                "app-version", DEFAULT_APP_VERSION,
                "x-supported-image-formats", DEFAULT_X_SUPPORTED_IMAGE_FORMATS,
                "Accept-Language", DEFAULT_ACCEPT_LANGUAGE,
                "Accept-Encoding", DEFAULT_ACCEPT_ENCODING
        );
    }

    @Override
    public String marker(Context context) {
        return data.onboardingToken;
    }

    public static class Data {
        private String onboardingToken;
    }

    @Setter
    @RequiredArgsConstructor
    @Accessors(fluent = true)
    @EqualsAndHashCode(callSuper = true)
    public static class Builder extends Data implements Executor<OnboardingFieldsGetResponse> {
        private final RequestExecutor executor;
        private final JsonMapper jsonMapper;
        private final Context context;
        private String onboardingToken;

        @Override
        public TinderResponse<OnboardingFieldsGetResponse> execute() {
            Data data = new Data();
            data.onboardingToken = onboardingToken;
            OnboardingFieldsGetRequestExecutor executor = new OnboardingFieldsGetRequestExecutor(
                    new OnboardingFieldsGetRequest(data), this.executor, context, jsonMapper
            );
            return executor.execute();
        }
    }
}
