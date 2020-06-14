package com.github.tinder.api.http.executor.common;

import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.AbstractRequestExecutor;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.request.common.DownloadPhotoRequest;

import java.io.InputStream;

public class DownloadPhotoRequestExecutor extends AbstractRequestExecutor<DownloadPhotoRequest, InputStream> {

    public DownloadPhotoRequestExecutor(DownloadPhotoRequest request,
                                        RequestExecutor executor,
                                        Context context) {
        super(request, executor, context);
    }

    @Override
    protected boolean enabledUnGzip() {
        return false;
    }

    @Override
    protected InputStream convert(String content) {
        throw new UnsupportedOperationException();
    }
}
