package com.github.tinder.api.util;

import lombok.NoArgsConstructor;

import static java.util.UUID.randomUUID;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class UuidUtil {

    public static String randomUuid(boolean dashes) {
        String uuid = randomUUID().toString();
        return dashes ? uuid : uuid.replaceAll("-", "");
    }
}
