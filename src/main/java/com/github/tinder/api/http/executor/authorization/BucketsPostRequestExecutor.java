package com.github.tinder.api.http.executor.authorization;

import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.AbstractRequestExecutor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.request.authorization.BucketsPostRequest;
import com.github.tinder.api.http.response.authorization.BucketsPostResponse;
import com.github.tinder.api.mapping.JsonMapper;

public class BucketsPostRequestExecutor extends AbstractRequestExecutor<BucketsPostRequest, BucketsPostResponse> {
    private final JsonMapper jsonMapper;

    public BucketsPostRequestExecutor(BucketsPostRequest request,
                                      RequestExecutor executor,
                                      Context context,
                                      JsonMapper jsonMapper) {
        super(request, executor, context);
        this.jsonMapper = jsonMapper;
    }

    @Override
    protected BucketsPostResponse convert(String content) {
        return jsonMapper.fromString(content, BucketsPostResponse.class);
    }
}
