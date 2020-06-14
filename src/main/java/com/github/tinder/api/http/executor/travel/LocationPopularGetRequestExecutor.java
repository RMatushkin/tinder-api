package com.github.tinder.api.http.executor.travel;

import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.AbstractRequestExecutor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.request.travel.LocationPopularGetRequest;
import com.github.tinder.api.http.response.travel.LocationPopularGetResponse;
import com.github.tinder.api.mapping.JsonMapper;

public class LocationPopularGetRequestExecutor
        extends AbstractRequestExecutor<LocationPopularGetRequest, LocationPopularGetResponse> {
    private final JsonMapper jsonMapper;

    public LocationPopularGetRequestExecutor(LocationPopularGetRequest request,
                                             RequestExecutor executor,
                                             Context context,
                                             JsonMapper jsonMapper) {
        super(request, executor, context);
        this.jsonMapper = jsonMapper;
    }

    @Override
    protected LocationPopularGetResponse convert(String content) {
        return jsonMapper.fromString(content, LocationPopularGetResponse.class);
    }
}
