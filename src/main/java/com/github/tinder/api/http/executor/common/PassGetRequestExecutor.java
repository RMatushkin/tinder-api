package com.github.tinder.api.http.executor.common;

import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.AbstractRequestExecutor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.request.common.PassGetRequest;
import com.github.tinder.api.http.response.common.PassGetResponse;
import com.github.tinder.api.mapping.JsonMapper;

public class PassGetRequestExecutor extends AbstractRequestExecutor<PassGetRequest, PassGetResponse> {
    private final JsonMapper jsonMapper;

    public PassGetRequestExecutor(PassGetRequest request,
                                  RequestExecutor executor,
                                  Context context,
                                  JsonMapper jsonMapper) {
        super(request, executor, context);
        this.jsonMapper = jsonMapper;
    }

    @Override
    protected PassGetResponse convert(String content) {
        return jsonMapper.fromString(content, PassGetResponse.class);
    }
}
