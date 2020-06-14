package com.github.tinder.api.http.executor.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.AbstractRequestExecutor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.request.common.LikePostRequest;
import com.github.tinder.api.http.response.common.LikePostResponse;
import com.github.tinder.api.mapping.JsonMapper;

import java.util.Map;

public class LikePostRequestExecutor extends AbstractRequestExecutor<LikePostRequest, LikePostResponse> {
    private final JsonMapper jsonMapper;

    public LikePostRequestExecutor(LikePostRequest request,
                                   RequestExecutor executor,
                                   Context context,
                                   JsonMapper jsonMapper) {
        super(request, executor, context);
        this.jsonMapper = jsonMapper;
    }

    @Override
    protected LikePostResponse convert(String content) {
        Map<String, Object> rawBody = jsonMapper.fromString(content, new TypeReference<>() {
        });
        if (rawBody.get("match") instanceof Boolean) {
            rawBody.replace("match", null);
        }
        return jsonMapper.convertValue(rawBody, LikePostResponse.class);
    }
}
