package com.github.tinder.api.http.executor.messaging;

import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.AbstractRequestExecutor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.request.matching.UserMatchesPostRequest;
import com.github.tinder.api.http.response.matching.UserMatchesPostResponse;
import com.github.tinder.api.mapping.JsonMapper;

public class UserMatchesPostRequestExecutor extends AbstractRequestExecutor<UserMatchesPostRequest, UserMatchesPostResponse> {
    private final JsonMapper jsonMapper;

    public UserMatchesPostRequestExecutor(UserMatchesPostRequest request,
                                          RequestExecutor executor,
                                          Context context,
                                          JsonMapper jsonMapper) {
        super(request, executor, context);
        this.jsonMapper = jsonMapper;
    }

    @Override
    protected UserMatchesPostResponse convert(String content) {
        return jsonMapper.fromString(content, UserMatchesPostResponse.class);
    }
}
