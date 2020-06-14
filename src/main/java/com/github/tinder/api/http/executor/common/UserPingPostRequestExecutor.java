package com.github.tinder.api.http.executor.common;

import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.AbstractRequestExecutor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.request.common.UserPingPostRequest;
import com.github.tinder.api.http.response.common.UserPingPostResponse;
import com.github.tinder.api.mapping.JsonMapper;

public class UserPingPostRequestExecutor extends AbstractRequestExecutor<UserPingPostRequest, UserPingPostResponse> {
    private final JsonMapper jsonMapper;

    public UserPingPostRequestExecutor(UserPingPostRequest request,
                                       RequestExecutor executor,
                                       Context context,
                                       JsonMapper jsonMapper) {
        super(request, executor, context);
        this.jsonMapper = jsonMapper;
    }

    @Override
    protected UserPingPostResponse convert(String content) {
        return jsonMapper.fromString(content, UserPingPostResponse.class);
    }
}
