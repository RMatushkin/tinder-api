package com.github.tinder.api.http.request;

import com.github.tinder.api.context.Context;

import java.util.List;
import java.util.Map;

public interface Request {

    String method();

    String url();

    String path();

    Map<String, String> params();

    Map<String, List<String>> selfTitledParams();

    Map<String, String> headers(Context context);

    Map<String, Object> body();

    ImageMultipartFormData imageMultipartFormData();

    String marker(Context context);
}
