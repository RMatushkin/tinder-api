package com.github.tinder.api.util;

import lombok.NoArgsConstructor;

import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class HttpUtil {
    private static final String $KEY = "${key}";
    private static final String $BOUNDARY = "${boundary}";
    private static final String $FILENAME = "${filename}";
    private static final String $CONTENT_TYPE = "${contentType}";

    public static BodyPublisher ofMultipartFormData(String key,
                                                    String boundary,
                                                    String contentType,
                                                    String filename,
                                                    byte[] bytes) {
        String head = ("--" + $BOUNDARY + "\r\n" +
                "Content-Disposition: form-data; name=\"" + $KEY + "\"; filename=\"" + $FILENAME + "\"\r\n" +
                "Content-Type: " + $CONTENT_TYPE + "\r\n\r\n")
                .replace($BOUNDARY, boundary)
                .replace($KEY, key)
                .replace($FILENAME, filename)
                .replace($CONTENT_TYPE, contentType);
        List<byte[]> _bytes = new ArrayList<>();
        _bytes.add((head).getBytes(UTF_8));
        _bytes.add(bytes);
        _bytes.add(("\r\n--" + boundary + "--").getBytes(UTF_8));
        return BodyPublishers.ofByteArrays(_bytes);
    }
}