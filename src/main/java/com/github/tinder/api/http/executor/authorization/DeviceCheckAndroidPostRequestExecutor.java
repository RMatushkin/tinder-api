package com.github.tinder.api.http.executor.authorization;

import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.AbstractRequestExecutor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.request.authorization.DeviceCheckAndroidPostRequest;
import com.github.tinder.api.http.response.authorization.DeviceCheckAndroidPostResponse;
import com.github.tinder.api.mapping.JsonMapper;

public class DeviceCheckAndroidPostRequestExecutor
        extends AbstractRequestExecutor<DeviceCheckAndroidPostRequest, DeviceCheckAndroidPostResponse> {
    private final JsonMapper jsonMapper;

    public DeviceCheckAndroidPostRequestExecutor(DeviceCheckAndroidPostRequest request,
                                                 RequestExecutor executor,
                                                 Context context,
                                                 JsonMapper jsonMapper) {
        super(request, executor, context);
        this.jsonMapper = jsonMapper;
    }

    @Override
    protected DeviceCheckAndroidPostResponse convert(String content) {
        return jsonMapper.fromString(content, DeviceCheckAndroidPostResponse.class);
    }
}
