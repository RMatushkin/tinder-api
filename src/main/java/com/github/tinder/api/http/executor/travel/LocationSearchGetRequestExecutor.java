package com.github.tinder.api.http.executor.travel;

import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.AbstractRequestExecutor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.request.travel.LocationSearchGetRequest;
import com.github.tinder.api.http.response.travel.LocationSearchGetResponse;
import com.github.tinder.api.mapping.JsonMapper;

public class LocationSearchGetRequestExecutor
        extends AbstractRequestExecutor<LocationSearchGetRequest, LocationSearchGetResponse> {
    private final JsonMapper jsonMapper;

    public LocationSearchGetRequestExecutor(LocationSearchGetRequest request,
                                            RequestExecutor executor,
                                            Context context,
                                            JsonMapper jsonMapper) {
        super(request, executor, context);
        this.jsonMapper = jsonMapper;
    }

    @Override
    protected LocationSearchGetResponse convert(String content) {
        return jsonMapper.fromString(content, LocationSearchGetResponse.class);
    }
}
