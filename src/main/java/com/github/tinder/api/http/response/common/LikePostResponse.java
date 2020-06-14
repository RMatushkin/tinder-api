package com.github.tinder.api.http.response.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class LikePostResponse {
    private Match match;
    private Integer likesRemaining;
    private Long rateLimitedUntil;
    @JsonProperty("X-Padding")
    private String xPadding;

    @Data
    public static class Match {
        @JsonProperty("_id")
        private String id;
        private Boolean closed;
        private Integer commonFriendCount;
        private Integer commonLikeCount;
        private String createdDate;
        private Boolean dead;
        private String lastActivityDate;
        private Integer messageCount;
        private List<Object> messages; //TODO: Replace by correct type
        private Object participants;
        private Boolean pending;
        private Boolean isSuperLike;
        private Boolean isBoostMatch;
        private Boolean isSuperBoostMatch;
        private Boolean isFastMatch;
        private Boolean isTopPick;
        private Boolean isExperiencesMatch;
        private Boolean following;
        private Boolean followingMoments;
    }
}
