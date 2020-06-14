package com.github.tinder.api.http.response.matching;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserMatchesPostResponse {
    @JsonProperty("_id")
    private String id;
    private String from;
    private String to;
    private String matchId;
    private String sentDate;
    private String message;
    private Media media;
    private String createdDate;

    @Data
    public static class Media {
        private Object width; //TODO: Set correct type here.
        private Object height; //TODO: Set correct type here.
    }
}
