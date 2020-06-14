package com.github.tinder.api.http.executor;

import com.github.tinder.api.client.TinderResponse;

public interface Executor<T> {

    TinderResponse<T> execute();
}
