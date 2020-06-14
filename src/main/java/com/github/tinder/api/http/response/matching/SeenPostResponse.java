package com.github.tinder.api.http.response.matching;

import lombok.Data;

@Data
public class SeenPostResponse {
    private Meta meta;

    @Data
    public static class Meta {
        private Integer status;
    }
}
