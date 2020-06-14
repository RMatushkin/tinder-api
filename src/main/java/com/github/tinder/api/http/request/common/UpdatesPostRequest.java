package com.github.tinder.api.http.request.common;

import com.github.tinder.api.client.TinderResponse;
import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.Executor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.executor.common.UpdatesPostRequestExecutor;
import com.github.tinder.api.http.request.AbstractRequest;
import com.github.tinder.api.http.response.common.UpdatesPostResponse;
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
import static java.util.Collections.emptyMap;
import static lombok.Lombok.checkNotNull;

public class UpdatesPostRequest extends AbstractRequest {
    private final Data data;

    private UpdatesPostRequest(Data data) {
        checkNotNull(data, "'data' cannot be null");
        if (data.nudge != null && data.lastActivityDate == null) {
            throw new IllegalArgumentException("'lastActivityDate' cannot be null");
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
        return "/updates";
    }

    @Override
    public Map<String, String> params() {
        return Map.of(
                "is_boosting", "false",
                "boost_cursor", "0"
        );
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
        if ((data.nudge == null) && (data.lastActivityDate == null)) {
            return emptyMap();
        }
        if (data.nudge == null) {
            return Map.of("last_activity_date", data.lastActivityDate);
        }
        return Map.of(
                "nudge", data.nudge,
                "last_activity_date", data.lastActivityDate
        );
    }

    @Override
    public String marker(Context context) {
        checkNotNull(context.getApiToken(), "'apiToken' cannot be null");
        return context.getApiToken();
    }

    public static class Data {
        private Boolean nudge;
        private String lastActivityDate;
    }

    @Setter
    @RequiredArgsConstructor
    @Accessors(fluent = true)
    @EqualsAndHashCode(callSuper = true)
    public static class Builder extends Data implements Executor<UpdatesPostResponse> {
        private final RequestExecutor executor;
        private final JsonMapper jsonMapper;
        private final Context context;
        private Boolean nudge;
        private String lastActivityDate;

        @Override
        public TinderResponse<UpdatesPostResponse> execute() {
            Data data = new Data();
            data.nudge = nudge;
            data.lastActivityDate = lastActivityDate;
            UpdatesPostRequestExecutor executor = new UpdatesPostRequestExecutor(
                    new UpdatesPostRequest(data), this.executor, context, jsonMapper
            );
            return executor.execute();
        }
    }
}
