package com.github.tinder.api.client.requests;

import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.request.common.SeenPostRequest;
import com.github.tinder.api.http.request.matching.UserMatchesPostRequest;
import com.github.tinder.api.mapping.JsonMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MatchingRequests {
    private final RequestExecutor requestExecutor;
    private final JsonMapper jsonMapper;
    private final Context context;

    /**
     * A builder that creates a request to mark a new matching as seen.
     * The official application sends it when a user clicks on a new matching (a new interlocutor in messages).
     */
    public SeenPostRequest.Builder seenPostRequest() {
        return SeenPostRequest.builder(requestExecutor, jsonMapper, context);
    }

    /**
     * A builder that creates a request to send an action to a matching (an interlocutor in messages).
     */
    public UserMatchesPostRequest.Builder userMatchesPostRequest() {
        return UserMatchesPostRequest.builder(requestExecutor, jsonMapper, context);
    }
}
