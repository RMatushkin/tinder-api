package com.github.tinder.api.http.request.travel;

import com.github.tinder.api.client.TinderResponse;
import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.Executor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.executor.travel.LocationPopularGetRequestExecutor;
import com.github.tinder.api.http.request.AbstractRequest;
import com.github.tinder.api.http.response.travel.LocationPopularGetResponse;
import com.github.tinder.api.mapping.JsonMapper;
import lombok.NoArgsConstructor;
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
import static lombok.AccessLevel.PRIVATE;
import static lombok.Lombok.checkNotNull;

@NoArgsConstructor(access = PRIVATE)
public class LocationPopularGetRequest extends AbstractRequest {

    public static Builder builder(RequestExecutor executor, JsonMapper jsonMapper, Context context) {
        return new Builder(executor, jsonMapper, context);
    }

    @Override
    public String method() {
        return GET_METHOD;
    }

    @Override
    public String path() {
        return "/location/popular";
    }

    @Override
    public Map<String, String> params() {
        return Map.of("locale", "en");
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
    public String marker(Context context) {
        checkNotNull(context.getApiToken(), "'apiToken' cannot be null");
        return context.getApiToken();
    }

    @Setter
    @RequiredArgsConstructor
    @Accessors(fluent = true)
    public static class Builder implements Executor<LocationPopularGetResponse> {
        private final RequestExecutor executor;
        private final JsonMapper jsonMapper;
        private final Context context;

        @Override
        public TinderResponse<LocationPopularGetResponse> execute() {
            LocationPopularGetRequestExecutor executor = new LocationPopularGetRequestExecutor(
                    new LocationPopularGetRequest(), this.executor, context, jsonMapper
            );
            return executor.execute();
        }
    }
}
