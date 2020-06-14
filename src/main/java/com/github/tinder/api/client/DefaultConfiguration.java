package com.github.tinder.api.client;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class DefaultConfiguration {
    public static final String DEFAULT_CLIENT_VERSION = "10.4.2";
    public static final String DEFAULT_USER_AGENT = "Tinder Android Version " + DEFAULT_CLIENT_VERSION;
    public static final String DEFAULT_OS_VERSION = "26";
    public static final String DEFAULT_APP_VERSION = "3129";
    public static final String DEFAULT_PLATFORM = "android";
    public static final String DEFAULT_X_SUPPORTED_IMAGE_FORMATS = "webp";
    public static final String DEFAULT_ACCEPT_LANGUAGE = "en";
    public static final String DEFAULT_ACCEPT_ENCODING = "gzip, deflate";
    public static final String DEFAULT_CONTENT_TYPE = "application/json; charset=UTF-8";
}
