package com.github.tinder.api.http.executor.authorization;

import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.AbstractRequestExecutor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.request.authorization.AuthSmsValidatePostRequest;
import com.github.tinder.api.http.response.authorization.AuthSmsValidatePostResponse;
import com.github.tinder.api.mapping.JsonMapper;

public class AuthSmsValidatePostRequestExecutor
        extends AbstractRequestExecutor<AuthSmsValidatePostRequest, AuthSmsValidatePostResponse> {
    private final JsonMapper jsonMapper;

    public AuthSmsValidatePostRequestExecutor(AuthSmsValidatePostRequest request,
                                              RequestExecutor executor,
                                              Context context,
                                              JsonMapper jsonMapper) {
        super(request, executor, context);
        this.jsonMapper = jsonMapper;
    }

    @Override
    protected AuthSmsValidatePostResponse convert(String content) {
        return jsonMapper.fromString(content, AuthSmsValidatePostResponse.class);
    }
}
