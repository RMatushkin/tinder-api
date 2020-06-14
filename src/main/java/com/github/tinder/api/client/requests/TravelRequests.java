package com.github.tinder.api.client.requests;

import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.request.travel.LocationPopularGetRequest;
import com.github.tinder.api.http.request.travel.LocationSearchGetRequest;
import com.github.tinder.api.http.request.travel.PassportUserTravelPostRequest;
import com.github.tinder.api.mapping.JsonMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TravelRequests {
    private final RequestExecutor requestExecutor;
    private final JsonMapper jsonMapper;
    private final Context context;

    /**
     * A builder that creates a request to see popular locations.
     * The official application sends it when a user clicks on changing the current location.
     */
    public LocationPopularGetRequest.Builder locationPopularGetRequest() {
        return LocationPopularGetRequest.builder(requestExecutor, jsonMapper, context);
    }

    /**
     * A builder that creates a request to search location info by latitude and longitude.
     * The official application sends it when a user clicks on the map (sets a point on the map).
     */
    public LocationSearchGetRequest.Builder locationSearchGetRequest() {
        return LocationSearchGetRequest.builder(requestExecutor, jsonMapper, context);
    }

    /**
     * A builder that creates a request to change the current location by latitude and longitude.
     * The official application sends it when a user followed the point on the map.
     */
    public PassportUserTravelPostRequest.Builder passportUserTravelPostRequest() {
        return PassportUserTravelPostRequest.builder(requestExecutor, jsonMapper, context);
    }
}
