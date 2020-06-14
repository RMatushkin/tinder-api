package com.github.tinder.api.http.response.authorization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthLoginSmsPostResponse {
    private Meta meta;
    private Data data;

    @lombok.Data
    public static class Meta {
        private Integer status;
    }

    @lombok.Data
    public static class Data {
        @JsonProperty("_id")
        private String id;
        private String apiToken;
        private String refreshToken;
        private String onboardingToken;
        private Boolean isNewUser;
    }
}
