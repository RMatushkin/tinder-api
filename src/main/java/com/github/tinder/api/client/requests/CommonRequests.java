package com.github.tinder.api.client.requests;

import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.request.common.DownloadPhotoRequest;
import com.github.tinder.api.http.request.common.LikePostRequest;
import com.github.tinder.api.http.request.common.MetaGetRequest;
import com.github.tinder.api.http.request.common.PassGetRequest;
import com.github.tinder.api.http.request.common.ProfileGetRequest;
import com.github.tinder.api.http.request.common.RecsCoreGetRequest;
import com.github.tinder.api.http.request.common.SettingsPostRequest;
import com.github.tinder.api.http.request.common.UpdatesPostRequest;
import com.github.tinder.api.http.request.common.UserPingPostRequest;
import com.github.tinder.api.mapping.JsonMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommonRequests {
    private final RequestExecutor requestExecutor;
    private final JsonMapper jsonMapper;
    private final Context context;

    /**
     * A builder that creates a request to update settings like age filter or gender filter and etc.
     */
    public SettingsPostRequest.Builder settingsPostRequest() {
        return SettingsPostRequest.builder(requestExecutor, jsonMapper, context);
    }

    public LikePostRequest.Builder likePostRequest() {
        return LikePostRequest.builder(requestExecutor, jsonMapper, context);
    }

    public PassGetRequest.Builder passGetRequest() {
        return PassGetRequest.builder(requestExecutor, jsonMapper, context);
    }

    public MetaGetRequest.Builder metaGetRequest() {
        return MetaGetRequest.builder(requestExecutor, jsonMapper, context);
    }

    public ProfileGetRequest.Builder profileGetRequest() {
        return ProfileGetRequest.builder(requestExecutor, jsonMapper, context);
    }

    public RecsCoreGetRequest.Builder recsCoreGetRequest() {
        return RecsCoreGetRequest.builder(requestExecutor, jsonMapper, context);
    }

    public UpdatesPostRequest.Builder updatesPostRequest() {
        return UpdatesPostRequest.builder(requestExecutor, jsonMapper, context);
    }

    public UserPingPostRequest.Builder userPingPostRequest() {
        return UserPingPostRequest.builder(requestExecutor, jsonMapper, context);
    }

    /**
     * A builder that creates a request to download a photo by its url.
     */
    public DownloadPhotoRequest.Builder downloadPhotoRequest() {
        return DownloadPhotoRequest.builder(requestExecutor, jsonMapper, context);
    }
}
