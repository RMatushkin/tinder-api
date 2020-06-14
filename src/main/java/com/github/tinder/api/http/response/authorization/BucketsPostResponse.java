package com.github.tinder.api.http.response.authorization;

import lombok.Data;

@Data
public class BucketsPostResponse {
    private Meta meta;
    private Data data;

    @lombok.Data
    public static class Meta {
        private Integer status;
    }

    @lombok.Data
    public static class Data {
        private Buckets buckets;
        private Levers levers;
    }

    @lombok.Data
    public static class Buckets {
        private String defaultLoginToken;
        private String smsAuthV2;
    }

    @lombok.Data
    public static class Levers {
    }
}
