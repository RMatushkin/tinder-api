package com.github.tinder.api.http.executor.common;

import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.AbstractRequestExecutor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.request.common.SettingsPostRequest;
import com.github.tinder.api.http.response.common.SettingsPostResponse;
import com.github.tinder.api.mapping.JsonMapper;

public class SettingsPostRequestExecutor extends AbstractRequestExecutor<SettingsPostRequest, SettingsPostResponse> {
    private final JsonMapper jsonMapper;

    public SettingsPostRequestExecutor(SettingsPostRequest request,
                                       RequestExecutor executor,
                                       Context context,
                                       JsonMapper jsonMapper) {
        super(request, executor, context);
        this.jsonMapper = jsonMapper;
    }

    @Override
    protected SettingsPostResponse convert(String content) {
        return jsonMapper.fromString(content, SettingsPostResponse.class);
    }
}
