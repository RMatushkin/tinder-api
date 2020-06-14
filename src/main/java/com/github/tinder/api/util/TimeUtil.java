package com.github.tinder.api.util;

import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.time.ZoneOffset.UTC;
import static java.util.Locale.US;
import static java.util.TimeZone.getTimeZone;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class TimeUtil {
    private static final SimpleDateFormat ZULU_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", US);

    static {
        ZULU_TIME_FORMAT.setTimeZone(getTimeZone(UTC));
    }

    public static String toZuluTime(long timestamp) {
        return ZULU_TIME_FORMAT.format(new Date(timestamp));
    }
}
