package com.github.tinder.api.http.request.authorization;

import com.github.tinder.api.client.TinderResponse;
import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.Executor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.executor.authorization.OnboardingPhotoPostRequestExecutor;
import com.github.tinder.api.http.request.AbstractRequest;
import com.github.tinder.api.http.request.ImageMultipartFormData;
import com.github.tinder.api.http.response.authorization.OnboardingPhotoPostResponse;
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
import static com.github.tinder.api.util.UuidUtil.randomUuid;
import static java.util.Arrays.asList;
import static lombok.Lombok.checkNotNull;

public class OnboardingPhotoPostRequest extends AbstractRequest {
    private final Data data;

    private OnboardingPhotoPostRequest(Data data) {
        checkNotNull(data, "'data' cannot be null");
        checkNotNull(data.onboardingToken, "'onboardingToken' cannot be null");
        checkNotNull(data.photo, "'photo' cannot be null");
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
        return "/v2/onboarding/photo";
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
                "Content-Type", "multipart/form-data; boundary=" + data.boundary,
                "Accept-Encoding", DEFAULT_ACCEPT_ENCODING
        );
    }

    @Override
    public ImageMultipartFormData imageMultipartFormData() {
        return new ImageMultipartFormData(
                "photo",
                data.boundary,
                "image/jpeg",
                randomUuid(true) + ".jpg",
                data.photo
        );
    }

    @Override
    public String marker(Context context) {
        return data.onboardingToken;
    }

    public static class Data {
        private String onboardingToken;
        private String boundary;
        private byte[] photo;
    }

    @Setter
    @RequiredArgsConstructor
    @Accessors(fluent = true)
    @EqualsAndHashCode(callSuper = true)
    public static class Builder extends Data implements Executor<OnboardingPhotoPostResponse> {
        private final RequestExecutor executor;
        private final JsonMapper jsonMapper;
        private final Context context;
        private String onboardingToken;
        private byte[] photo;

        @Override
        public TinderResponse<OnboardingPhotoPostResponse> execute() {
            Data data = new Data();
            data.onboardingToken = onboardingToken;
            data.boundary = randomUuid(true);
            data.photo = photo;
            OnboardingPhotoPostRequestExecutor executor = new OnboardingPhotoPostRequestExecutor(
                    new OnboardingPhotoPostRequest(data), this.executor, context, jsonMapper
            );
            return executor.execute();
        }
    }
}
