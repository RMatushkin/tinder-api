package com.github.tinder.api.http.request.authorization;

import com.github.tinder.api.client.TinderResponse;
import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.Executor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.executor.authorization.BucketsPostRequestExecutor;
import com.github.tinder.api.http.request.AbstractRequest;
import com.github.tinder.api.http.response.authorization.BucketsPostResponse;
import com.github.tinder.api.mapping.JsonMapper;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;

import static com.github.tinder.api.client.DefaultConfiguration.DEFAULT_ACCEPT_ENCODING;
import static com.github.tinder.api.client.DefaultConfiguration.DEFAULT_CONTENT_TYPE;
import static com.github.tinder.api.client.DefaultConfiguration.DEFAULT_PLATFORM;
import static java.util.Arrays.asList;
import static lombok.Lombok.checkNotNull;

public class BucketsPostRequest extends AbstractRequest {
    private final Data data;

    private BucketsPostRequest(Data data) {
        checkNotNull(data, "'data' cannot be null");
        checkNotNull(data.installId, "'installId' cannot be null");
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
        return "/v2/buckets";
    }

    @Override
    public Map<String, String> headers(Context context) {
        return Map.of(
                "platform", DEFAULT_PLATFORM,
                "Content-Type", DEFAULT_CONTENT_TYPE,
                "Accept-Encoding", DEFAULT_ACCEPT_ENCODING,
                "User-Agent", "okhttp/3.11.0"
        );
    }

    @Override
    public Map<String, Object> body() {
        return Map.of(
                "device_id", data.installId,
                "experiments", asList("sms_auth_v2", "default_login_token")
        );
    }

    @Override
    public String marker(Context context) {
        return data.installId;
    }

    public static class Data {
        private String installId;
    }

    @Setter
    @RequiredArgsConstructor
    @Accessors(fluent = true)
    @EqualsAndHashCode(callSuper = true)
    public static class Builder extends Data implements Executor<BucketsPostResponse> {
        private final RequestExecutor executor;
        private final JsonMapper jsonMapper;
        private final Context context;
        private String installId;

        @Override
        public TinderResponse<BucketsPostResponse> execute() {
            Data data = new Data();
            data.installId = installId;
            BucketsPostRequestExecutor executor = new BucketsPostRequestExecutor(
                    new BucketsPostRequest(data), this.executor, context, jsonMapper
            );
            return executor.execute();
        }
    }
}
