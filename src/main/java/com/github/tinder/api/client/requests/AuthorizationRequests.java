package com.github.tinder.api.client.requests;

import com.github.tinder.api.context.Context;
import com.github.tinder.api.http.executor.RequestExecutor;
import com.github.tinder.api.http.request.authorization.AuthLoginSmsPostRequest;
import com.github.tinder.api.http.request.authorization.AuthSmsSendPostRequest;
import com.github.tinder.api.http.request.authorization.AuthSmsValidatePostRequest;
import com.github.tinder.api.http.request.authorization.BucketsPostRequest;
import com.github.tinder.api.http.request.authorization.DeviceCheckAndroidPostRequest;
import com.github.tinder.api.http.request.authorization.OnboardingCompletePostRequest;
import com.github.tinder.api.http.request.authorization.OnboardingFieldsGetRequest;
import com.github.tinder.api.http.request.authorization.OnboardingFieldsPostRequest;
import com.github.tinder.api.http.request.authorization.OnboardingPhotoPostRequest;
import com.github.tinder.api.mapping.JsonMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthorizationRequests {
    private final RequestExecutor requestExecutor;
    private final JsonMapper jsonMapper;
    private final Context context;

    /**
     * A builder that creates the 1st authorization request.
     * The official application sends it before registration.
     */
    public BucketsPostRequest.Builder bucketsPostRequest() {
        return BucketsPostRequest.builder(requestExecutor, jsonMapper, context);
    }

    /**
     * A builder that creates the 2nd authorization request to send SMS.
     */
    public AuthSmsSendPostRequest.Builder authSmsSendPostRequest() {
        return AuthSmsSendPostRequest.builder(requestExecutor, jsonMapper, context);
    }

    /**
     * A builder that creates the 3rd authorization request to verify a code from SMS.
     */
    public AuthSmsValidatePostRequest.Builder authSmsValidatePostRequest() {
        return AuthSmsValidatePostRequest.builder(requestExecutor, jsonMapper, context);
    }

    /**
     * A builder that creates the 4th authorization request to get 'api_token' ('X-Auth-Token') and 'refresh_token'.
     * It can be used to reauthorize to fetch a new 'api_token' ('X-Auth-Token').
     */
    public AuthLoginSmsPostRequest.Builder authLoginSmsPostRequest() {
        return AuthLoginSmsPostRequest.builder(requestExecutor, jsonMapper, context);
    }

    /**
     * A builder that creates the 5th authorization request to get required fields.
     */
    public OnboardingFieldsGetRequest.Builder onboardingFieldsGetRequest() {
        return OnboardingFieldsGetRequest.builder(requestExecutor, jsonMapper, context);
    }

    /**
     * A builder that creates the 6th authorization request to send fields' value.
     */
    public OnboardingFieldsPostRequest.Builder onboardingFieldsPostRequest() {
        return OnboardingFieldsPostRequest.builder(requestExecutor, jsonMapper, context);
    }

    /**
     * A builder that creates the 7th authorization request to send a photo.
     */
    public OnboardingPhotoPostRequest.Builder onboardingPhotoPostRequest() {
        return OnboardingPhotoPostRequest.builder(requestExecutor, jsonMapper, context);
    }

    /**
     * A builder that creates the 8th authorization request to complete registration.
     */
    public OnboardingCompletePostRequest.Builder onboardingCompletePostRequest() {
        return OnboardingCompletePostRequest.builder(requestExecutor, jsonMapper, context);
    }

    /**
     * A builder that creates the 9th (latest) authorization request.
     * The official application sends it when registration has been completed.
     */
    public DeviceCheckAndroidPostRequest.Builder deviceCheckAndroidPostRequest() {
        return DeviceCheckAndroidPostRequest.builder(requestExecutor, jsonMapper, context);
    }
}
