package com.github.tinder.api.http.executor.common;

import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.AbstractRequestExecutor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.request.common.RecsCoreGetRequest;
import com.github.tinder.api.http.response.common.RecsCoreGetResponse;
import com.github.tinder.api.mapping.JsonMapper;

public class RecsCoreGetRequestExecutor extends AbstractRequestExecutor<RecsCoreGetRequest, RecsCoreGetResponse> {
    private final JsonMapper jsonMapper;

    public RecsCoreGetRequestExecutor(RecsCoreGetRequest request,
                                      RequestExecutor executor,
                                      Context context,
                                      JsonMapper jsonMapper) {
        super(request, executor, context);
        this.jsonMapper = jsonMapper;
    }

    @Override
    protected RecsCoreGetResponse convert(String content) {
        return jsonMapper.fromString(content, RecsCoreGetResponse.class);
    }
}
