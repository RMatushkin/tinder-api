package com.github.tinder.api.http.request.common;

import com.github.tinder.api.client.TinderResponse;
import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.Executor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.executor.common.SettingsPostRequestExecutor;
import com.github.tinder.api.http.request.AbstractRequest;
import com.github.tinder.api.http.response.common.SettingsPostResponse;
import com.github.tinder.api.mapping.JsonMapper;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.LinkedHashMap;
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

public class SettingsPostRequest extends AbstractRequest {
    private final Data data;

    private SettingsPostRequest(Data data) {
        checkNotNull(data, "'data' cannot be null");
        if (data.ageFilterMin != null && data.ageFilterMin < 18) {
            throw new IllegalArgumentException("'ageFilterMin' has invalid value");
        }
        if (data.ageFilterMax != null && data.ageFilterMax > 100) {
            throw new IllegalArgumentException("'ageFilterMax' has invalid value");
        }
        if (data.genderFilter != null && (data.genderFilter < -1 || data.genderFilter > 1)) {
            throw new IllegalArgumentException("'genderFilter' has invalid value");
        }
        if (data.distanceFilter != null && (data.distanceFilter < 1 || data.distanceFilter > 100)) {
            throw new IllegalArgumentException("'distanceFilter' has invalid value");
        }
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
        return "/v2/profile/user";
    }

    @Override
    public Map<String, String> headers(Context context) {
        checkNotNull(context.getApiToken(), "'apiToken' cannot be null");
        return Map.of(
                "User-Agent", DEFAULT_USER_AGENT,
                "os-version", DEFAULT_OS_VERSION,
                "app-version", DEFAULT_APP_VERSION,
                "platform", DEFAULT_PLATFORM,
                "x-supported-image-formats", DEFAULT_X_SUPPORTED_IMAGE_FORMATS,
                "Accept-Language", DEFAULT_ACCEPT_LANGUAGE,
                "X-Auth-Token", context.getApiToken(),
                "Accept-Encoding", DEFAULT_ACCEPT_ENCODING,
                "Content-Type", DEFAULT_CONTENT_TYPE
        );
    }

    @Override
    public Map<String, Object> body() {
        Map<String, Object> payload = new LinkedHashMap<>();
        if (data.ageFilterMin != null) payload.put("age_filter_min", data.ageFilterMin);
        if (data.ageFilterMax != null) payload.put("age_filter_max", data.ageFilterMax);
        if (data.genderFilter != null) payload.put("gender_filter", data.genderFilter);
        if (data.discoverable != null) payload.put("discoverable", data.discoverable);
        if (data.distanceFilter != null) payload.put("distance_filter", data.distanceFilter);
        return payload;
    }

    @Override
    public String marker(Context context) {
        checkNotNull(context.getApiToken(), "'apiToken' cannot be null");
        return context.getApiToken();
    }

    public static class Data {
        private Integer ageFilterMin;
        private Integer ageFilterMax;
        private Integer genderFilter;
        private Boolean discoverable;
        private Integer distanceFilter;
    }

    @Setter
    @RequiredArgsConstructor
    @Accessors(fluent = true)
    public static class Builder implements Executor<SettingsPostResponse> {
        private final RequestExecutor executor;
        private final JsonMapper jsonMapper;
        private final Context context;
        private Integer ageFilterMin;
        private Integer ageFilterMax;
        private Integer genderFilter;
        private Boolean discoverable;
        private Integer distanceFilter;

        @Override
        public TinderResponse<SettingsPostResponse> execute() {
            Data data = new Data();
            data.ageFilterMin = ageFilterMin;
            data.ageFilterMax = ageFilterMax;
            data.genderFilter = genderFilter;
            data.discoverable = discoverable;
            data.distanceFilter = distanceFilter;
            SettingsPostRequestExecutor executor = new SettingsPostRequestExecutor(
                    new SettingsPostRequest(data), this.executor, context, jsonMapper
            );
            return executor.execute();
        }
    }
}
