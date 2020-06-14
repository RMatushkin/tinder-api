package com.github.tinder.api.http.executor.authorization;

import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.AbstractRequestExecutor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.request.authorization.OnboardingCompletePostRequest;
import com.github.tinder.api.http.response.authorization.OnboardingCompletePostResponse;
import com.github.tinder.api.mapping.JsonMapper;

public class OnboardingCompletePostRequestExecutor
        extends AbstractRequestExecutor<OnboardingCompletePostRequest, OnboardingCompletePostResponse> {
    private final JsonMapper jsonMapper;

    public OnboardingCompletePostRequestExecutor(OnboardingCompletePostRequest request,
                                                 RequestExecutor executor,
                                                 Context context,
                                                 JsonMapper jsonMapper) {
        super(request, executor, context);
        this.jsonMapper = jsonMapper;
    }

    @Override
    protected OnboardingCompletePostResponse convert(String content) {
        return jsonMapper.fromString(content, OnboardingCompletePostResponse.class);
    }
}
