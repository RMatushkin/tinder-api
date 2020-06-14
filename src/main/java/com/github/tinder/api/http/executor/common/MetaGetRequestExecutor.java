package com.github.tinder.api.http.executor.common;

import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.AbstractRequestExecutor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.request.common.MetaGetRequest;
import com.github.tinder.api.http.response.common.MetaGetResponse;
import com.github.tinder.api.mapping.JsonMapper;

public class MetaGetRequestExecutor extends AbstractRequestExecutor<MetaGetRequest, MetaGetResponse> {
    private final JsonMapper jsonMapper;

    public MetaGetRequestExecutor(MetaGetRequest request,
                                  RequestExecutor executor,
                                  Context context,
                                  JsonMapper jsonMapper) {
        super(request, executor, context);
        this.jsonMapper = jsonMapper;
    }

    @Override
    protected MetaGetResponse convert(String content) {
        return jsonMapper.fromString(content, MetaGetResponse.class);
    }
}
