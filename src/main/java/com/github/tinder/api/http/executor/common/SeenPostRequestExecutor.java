package com.github.tinder.api.http.executor.common;

import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.AbstractRequestExecutor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.request.common.SeenPostRequest;
import com.github.tinder.api.http.response.matching.SeenPostResponse;
import com.github.tinder.api.mapping.JsonMapper;

public class SeenPostRequestExecutor extends AbstractRequestExecutor<SeenPostRequest, SeenPostResponse> {
    private final JsonMapper jsonMapper;

    public SeenPostRequestExecutor(SeenPostRequest request,
                                   RequestExecutor executor,
                                   Context context,
                                   JsonMapper jsonMapper) {
        super(request, executor, context);
        this.jsonMapper = jsonMapper;
    }

    @Override
    protected SeenPostResponse convert(String content) {
        return jsonMapper.fromString(content, SeenPostResponse.class);
    }
}
