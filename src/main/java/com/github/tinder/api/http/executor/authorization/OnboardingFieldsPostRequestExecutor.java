package com.github.tinder.api.http.executor.authorization;

import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.AbstractRequestExecutor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.request.authorization.OnboardingFieldsPostRequest;
import com.github.tinder.api.http.response.authorization.OnboardingFieldsPostResponse;
import com.github.tinder.api.mapping.JsonMapper;

public class OnboardingFieldsPostRequestExecutor
        extends AbstractRequestExecutor<OnboardingFieldsPostRequest, OnboardingFieldsPostResponse> {
    private final JsonMapper jsonMapper;

    public OnboardingFieldsPostRequestExecutor(OnboardingFieldsPostRequest request,
                                               RequestExecutor executor,
                                               Context context,
                                               JsonMapper jsonMapper) {
        super(request, executor, context);
        this.jsonMapper = jsonMapper;
    }

    @Override
    protected OnboardingFieldsPostResponse convert(String content) {
        return jsonMapper.fromString(content, OnboardingFieldsPostResponse.class);
    }
}
