package com.github.tinder.api.http.executor.common;

import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.AbstractRequestExecutor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.request.common.ProfileGetRequest;
import com.github.tinder.api.http.response.common.ProfileGetResponse;
import com.github.tinder.api.mapping.JsonMapper;

public class ProfileGetRequestExecutor extends AbstractRequestExecutor<ProfileGetRequest, ProfileGetResponse> {
    private final JsonMapper jsonMapper;

    public ProfileGetRequestExecutor(ProfileGetRequest request,
                                     RequestExecutor executor,
                                     Context context,
                                     JsonMapper jsonMapper) {
        super(request, executor, context);
        this.jsonMapper = jsonMapper;
    }

    @Override
    protected ProfileGetResponse convert(String content) {
        return jsonMapper.fromString(content, ProfileGetResponse.class);
    }
}
