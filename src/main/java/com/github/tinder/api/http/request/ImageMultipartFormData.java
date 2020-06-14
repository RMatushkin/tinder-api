package com.github.tinder.api.http.request;

import lombok.Data;

@Data
public class ImageMultipartFormData {
    private final String key;
    private final String boundary;
    private final String contentType;
    private final String filename;
    private final byte[] bytes;
}
