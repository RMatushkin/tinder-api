package com.github.tinder.api.http.request;

import com.github.tinder.api.context.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractRequest implements Request {
    protected static final String GET_METHOD = "GET";
    protected static final String POST_METHOD = "POST";

    @Override
    public String url() {
        return null;
    }

    @Override
    public Map<String, String> params() {
        return new HashMap<>();
    }

    @Override
    public Map<String, List<String>> selfTitledParams() {
        return new HashMap<>();
    }

    @Override
    public Map<String, String> headers(Context context) {
        return new HashMap<>();
    }

    @Override
    public Map<String, Object> body() {
        return null;
    }

    @Override
    public ImageMultipartFormData imageMultipartFormData() {
        return null;
    }
}
