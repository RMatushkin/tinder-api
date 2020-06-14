package com.github.tinder.api.http.request.common;

import com.github.tinder.api.client.TinderResponse;
import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.Executor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.executor.common.PassGetRequestExecutor;
import com.github.tinder.api.http.request.AbstractRequest;
import com.github.tinder.api.http.response.common.PassGetResponse;
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

public class PassGetRequest extends AbstractRequest {
    private final Data data;

    private PassGetRequest(Data data) {
        checkNotNull(data, "'data' cannot be null");
        checkNotNull(data.targetUserId, "'targetUserId' cannot be null");
        checkNotNull(data.firstPhotoId, "'firstPhotoId' cannot be null");
        checkNotNull(data.contentHash, "'contentHash' cannot be null");
        checkNotNull(data.sNumber, "'sNumber' cannot be null");
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
        return "/pass/" + data.targetUserId;
    }

    @Override
    public Map<String, String> params() {
        return Map.of(
                "photoId", data.firstPhotoId,
                "content_hash", data.contentHash,
                "s_number", data.sNumber.toString()
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
    public String marker(Context context) {
        checkNotNull(context.getApiToken(), "'apiToken' cannot be null");
        return context.getApiToken();
    }

    public static class Data {
        private String targetUserId;
        private String firstPhotoId;
        private String contentHash;
        private Integer sNumber;
    }

    @Setter
    @RequiredArgsConstructor
    @Accessors(fluent = true)
    @EqualsAndHashCode(callSuper = true)
    public static class Builder extends Data implements Executor<PassGetResponse> {
        private final RequestExecutor executor;
        private final JsonMapper jsonMapper;
        private final Context context;
        private String targetUserId;
        private String firstPhotoId;
        private String contentHash;
        private Integer sNumber;

        @Override
        public TinderResponse<PassGetResponse> execute() {
            Data data = new Data();
            data.targetUserId = targetUserId;
            data.firstPhotoId = firstPhotoId;
            data.contentHash = contentHash;
            data.sNumber = sNumber;
            PassGetRequestExecutor executor = new PassGetRequestExecutor(
                    new PassGetRequest(data), this.executor, context, jsonMapper
            );
            return executor.execute();
        }
    }
}
