package com.github.tinder.api.client.requests;

import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.mapping.JsonMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Requests {
    private final RequestExecutor requestExecutor;
    private final JsonMapper jsonMapper;
    private final Context context;

    public AuthorizationRequests authorization() {
        return new AuthorizationRequests(requestExecutor, jsonMapper, context);
    }

    public MatchingRequests matching() {
        return new MatchingRequests(requestExecutor, jsonMapper, context);
    }

    /**
     * The requests to use travel feature which allows changing your current location to any you want.
     * !!! An account must have at least 'Gold' tariff to use these requests. !!!
     */
    public TravelRequests travel() {
        return new TravelRequests(requestExecutor, jsonMapper, context);
    }

    public CommonRequests common() {
        return new CommonRequests(requestExecutor, jsonMapper, context);
    }
}
