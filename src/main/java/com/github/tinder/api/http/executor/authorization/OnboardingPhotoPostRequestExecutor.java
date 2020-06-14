package com.github.tinder.api.http.executor.authorization;

import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.AbstractRequestExecutor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.request.authorization.OnboardingPhotoPostRequest;
import com.github.tinder.api.http.response.authorization.OnboardingPhotoPostResponse;
import com.github.tinder.api.mapping.JsonMapper;

public class OnboardingPhotoPostRequestExecutor
        extends AbstractRequestExecutor<OnboardingPhotoPostRequest, OnboardingPhotoPostResponse> {
    private final JsonMapper jsonMapper;

    public OnboardingPhotoPostRequestExecutor(OnboardingPhotoPostRequest request,
                                              RequestExecutor executor,
                                              Context context,
                                              JsonMapper jsonMapper) {
        super(request, executor, context);
        this.jsonMapper = jsonMapper;
    }

    @Override
    protected OnboardingPhotoPostResponse convert(String content) {
        return jsonMapper.fromString(content, OnboardingPhotoPostResponse.class);
    }
}
