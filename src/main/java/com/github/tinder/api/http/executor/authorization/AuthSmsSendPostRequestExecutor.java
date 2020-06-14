package com.github.tinder.api.http.executor.authorization;

import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.AbstractRequestExecutor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.request.authorization.AuthSmsSendPostRequest;
import com.github.tinder.api.http.response.authorization.AuthSmsSendPostResponse;
import com.github.tinder.api.mapping.JsonMapper;

public class AuthSmsSendPostRequestExecutor
        extends AbstractRequestExecutor<AuthSmsSendPostRequest, AuthSmsSendPostResponse> {
    private final JsonMapper jsonMapper;

    public AuthSmsSendPostRequestExecutor(AuthSmsSendPostRequest request,
                                          RequestExecutor executor,
                                          Context context,
                                          JsonMapper jsonMapper) {
        super(request, executor, context);
        this.jsonMapper = jsonMapper;
    }

    @Override
    protected AuthSmsSendPostResponse convert(String content) {
        return jsonMapper.fromString(content, AuthSmsSendPostResponse.class);
    }
}
