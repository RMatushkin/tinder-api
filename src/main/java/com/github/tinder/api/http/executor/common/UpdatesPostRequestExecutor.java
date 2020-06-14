package com.github.tinder.api.http.executor.common;

import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.AbstractRequestExecutor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.request.common.UpdatesPostRequest;
import com.github.tinder.api.http.response.common.UpdatesPostResponse;
import com.github.tinder.api.mapping.JsonMapper;

public class UpdatesPostRequestExecutor extends AbstractRequestExecutor<UpdatesPostRequest, UpdatesPostResponse> {
    private final JsonMapper jsonMapper;

    public UpdatesPostRequestExecutor(UpdatesPostRequest request,
                                      RequestExecutor executor,
                                      Context context,
                                      JsonMapper jsonMapper) {
        super(request, executor, context);
        this.jsonMapper = jsonMapper;
    }

    @Override
    protected UpdatesPostResponse convert(String content) {
        return jsonMapper.fromString(content, UpdatesPostResponse.class);
    }
}
