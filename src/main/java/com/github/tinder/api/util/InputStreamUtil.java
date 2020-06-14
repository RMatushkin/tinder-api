package com.github.tinder.api.util;

import com.github.tinder.api.exception.TinderClientException;
import lombok.NoArgsConstructor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class InputStreamUtil {

    public static String unGzip(InputStream inputStream) {
        try {
            return readInputStream(new GZIPInputStream(inputStream));
        } catch (IOException e) {
            throw new TinderClientException(e);
        }
    }

    public static String readInputStream(InputStream inputStream) {
        try (inputStream; ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            inputStream.transferTo(outputStream);
            return new String(outputStream.toByteArray(), UTF_8);
        } catch (IOException e) {
            throw new TinderClientException(e);
        }
    }
}
