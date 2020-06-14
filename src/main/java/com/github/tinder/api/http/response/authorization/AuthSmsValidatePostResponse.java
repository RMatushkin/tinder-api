package com.github.tinder.api.http.response.authorization;

import lombok.Data;

@Data
public class AuthSmsValidatePostResponse {
    private Meta meta;
    private Data data;

    @lombok.Data
    public static class Meta {
        private Integer status;
    }

    @lombok.Data
    public static class Data {
        private String refreshToken;
        private Boolean validated;
    }
}
