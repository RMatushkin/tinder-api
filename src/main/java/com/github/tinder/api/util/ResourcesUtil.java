package com.github.tinder.api.util;

import lombok.NoArgsConstructor;

import java.io.InputStream;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class ResourcesUtil {

    public static InputStream readFileAsInputStream(String path) {
        return ResourcesUtil.class.getClassLoader().getResourceAsStream(path);
    }
}
