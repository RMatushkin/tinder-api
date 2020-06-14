package com.github.tinder.api.client;

import com.github.tinder.api.client.requests.Requests;
import com.github.tinder.api.context.Context;
import com.github.tinder.api.exception.TinderClientException;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.mapping.JsonMapper;
import com.github.tinder.api.mapping.TinderJsonMapper;

import java.io.Serializable;
import java.net.http.HttpClient;
import java.time.Duration;

import static lombok.Lombok.checkNotNull;

public class TinderClient implements Serializable {
    private static volatile TinderClient INSTANCE;
    private final RequestExecutor requestExecutor;
    private final JsonMapper jsonMapper;

    private TinderClient(HttpClient httpClient, ProxySelectorBasedOnMarker proxySelector, Duration requestTimeout) {
        if (INSTANCE != null) {
            throw new TinderClientException("Use 'instance()' method to get the single instance of this class");
        }
        checkNotNull(httpClient, "'httpClient' cannot be null");
        checkNotNull(proxySelector, "'proxySelector' cannot be null");
        httpClient.proxy()
                .filter(_proxySelector -> _proxySelector.equals(proxySelector))
                .orElseThrow(() ->
                        new TinderClientException("'proxySelector' is different from the one used in 'httpClient'")
                );
        this.jsonMapper = new TinderJsonMapper();
        this.requestExecutor = new RequestExecutor(httpClient, proxySelector, requestTimeout, jsonMapper);
    }

    /**
     * Thread-safe implementation of a singleton pattern, it's used to get an instance of Tinder client.
     */
    public static TinderClient instance(HttpClient httpClient,
                                        ProxySelectorBasedOnMarker proxySelector,
                                        Duration requestTimeout) {
        if (INSTANCE == null) {
            synchronized (TinderClient.class) {
                if (INSTANCE == null) INSTANCE = new TinderClient(httpClient, proxySelector, requestTimeout);
            }
        }
        return INSTANCE;
    }

    /**
     * The main method to start to communicate with Tinder client and sending any requests to Tinder API.
     *
     * @param context contains authorization and auxiliary data such as 'X-Auth-Token' or proxy.
     * @return An object-wrapper that provides a dictionary of request builders,
     * by other words it contains all available requests of Tinder API endpoints.
     */
    public final Requests onBased(Context context) {
        checkNotNull(context, "'context' cannot be null");
        return new Requests(requestExecutor, jsonMapper, context);
    }
}
