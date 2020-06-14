package com.github.tinder.api.http.executor.authorization;

import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.AbstractRequestExecutor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.request.authorization.OnboardingFieldsGetRequest;
import com.github.tinder.api.http.response.authorization.OnboardingFieldsGetResponse;
import com.github.tinder.api.mapping.JsonMapper;

public class OnboardingFieldsGetRequestExecutor
        extends AbstractRequestExecutor<OnboardingFieldsGetRequest, OnboardingFieldsGetResponse> {
    private final JsonMapper jsonMapper;

    public OnboardingFieldsGetRequestExecutor(OnboardingFieldsGetRequest request,
                                              RequestExecutor executor,
                                              Context context,
                                              JsonMapper jsonMapper) {
        super(request, executor, context);
        this.jsonMapper = jsonMapper;
    }

    @Override
    protected OnboardingFieldsGetResponse convert(String content) {
        return jsonMapper.fromString(content, OnboardingFieldsGetResponse.class);
    }
}
