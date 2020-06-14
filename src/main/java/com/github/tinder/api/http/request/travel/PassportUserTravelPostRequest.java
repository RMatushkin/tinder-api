package com.github.tinder.api.http.request.travel;

import com.github.tinder.api.client.TinderResponse;
import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.Executor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.executor.travel.PassportUserTravelPostRequestExecutor;
import com.github.tinder.api.http.request.AbstractRequest;
import com.github.tinder.api.http.response.travel.PassportUserTravelPostResponse;
import com.github.tinder.api.mapping.JsonMapper;
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

public class PassportUserTravelPostRequest extends AbstractRequest {
    private final Data data;

    private PassportUserTravelPostRequest(Data data) {
        checkNotNull(data, "'data' cannot be null");
        checkNotNull(data.latitude, "'latitude' cannot be null");
        checkNotNull(data.longitude, "'longitude' cannot be null");
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
        return "/passport/user/travel";
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
        return Map.of(
                "lat", Double.valueOf(data.latitude),
                "lon", Double.valueOf(data.longitude)
        );
    }

    @Override
    public String marker(Context context) {
        checkNotNull(context.getApiToken(), "'apiToken' cannot be null");
        return context.getApiToken();
    }

    public static class Data {
        private String latitude;
        private String longitude;
    }

    @Setter
    @RequiredArgsConstructor
    @Accessors(fluent = true)
    public static class Builder implements Executor<PassportUserTravelPostResponse> {
        private final RequestExecutor executor;
        private final JsonMapper jsonMapper;
        private final Context context;
        private String latitude;
        private String longitude;

        @Override
        public TinderResponse<PassportUserTravelPostResponse> execute() {
            Data data = new Data();
            data.latitude = latitude;
            data.longitude = longitude;
            PassportUserTravelPostRequestExecutor executor = new PassportUserTravelPostRequestExecutor(
                    new PassportUserTravelPostRequest(data), this.executor, context, jsonMapper
            );
            return executor.execute();
        }
    }
}
