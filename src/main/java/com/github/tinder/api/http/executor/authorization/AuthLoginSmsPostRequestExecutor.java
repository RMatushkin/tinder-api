package com.github.tinder.api.http.executor.authorization;

import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.AbstractRequestExecutor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.request.authorization.AuthLoginSmsPostRequest;
import com.github.tinder.api.http.response.authorization.AuthLoginSmsPostResponse;
import com.github.tinder.api.mapping.JsonMapper;

public class AuthLoginSmsPostRequestExecutor
        extends AbstractRequestExecutor<AuthLoginSmsPostRequest, AuthLoginSmsPostResponse> {
    private final JsonMapper jsonMapper;

    public AuthLoginSmsPostRequestExecutor(AuthLoginSmsPostRequest request,
                                           RequestExecutor executor,
                                           Context context,
                                           JsonMapper jsonMapper) {
        super(request, executor, context);
        this.jsonMapper = jsonMapper;
    }

    @Override
    protected AuthLoginSmsPostResponse convert(String content) {
        return jsonMapper.fromString(content, AuthLoginSmsPostResponse.class);
    }
}
