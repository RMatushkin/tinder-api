package com.github.tinder.api.context;

import lombok.Data;

@Data
public class Context {
    private String apiToken;
    private Proxy proxy;

    public static Context of(String apiToken) {
        Context context = new Context();
        context.apiToken = apiToken;
        return context;
    }

    public static Context of(String apiToken, Proxy proxy) {
        Context context = new Context();
        context.apiToken = apiToken;
        context.proxy = proxy;
        return context;
    }
}
