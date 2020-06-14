package com.github.tinder.api.http.executor.travel;

import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.AbstractRequestExecutor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.request.travel.PassportUserTravelPostRequest;
import com.github.tinder.api.http.response.travel.PassportUserTravelPostResponse;
import com.github.tinder.api.mapping.JsonMapper;

public class PassportUserTravelPostRequestExecutor
        extends AbstractRequestExecutor<PassportUserTravelPostRequest, PassportUserTravelPostResponse> {
    private final JsonMapper jsonMapper;

    public PassportUserTravelPostRequestExecutor(PassportUserTravelPostRequest request,
                                                 RequestExecutor executor,
                                                 Context context,
                                                 JsonMapper jsonMapper) {
        super(request, executor, context);
        this.jsonMapper = jsonMapper;
    }

    @Override
    protected PassportUserTravelPostResponse convert(String content) {
        return jsonMapper.fromString(content, PassportUserTravelPostResponse.class);
    }
}
