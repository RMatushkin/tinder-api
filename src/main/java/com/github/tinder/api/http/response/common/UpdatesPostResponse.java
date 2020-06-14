package com.github.tinder.api.http.response.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UpdatesPostResponse {
    private List<Match> matches;
    private List<String> blocks;
    private List<Inbox> inbox;
    private List<LikedMessage> likedMessages;
    private List<HarassingMessage> harassingMessages;
    private List<_List> lists;
    private List<Goingout> goingout;
    private List<DeletedList> deletedLists;
    private List<Squad> squads;
    private String lastActivityDate;
    private PollInterval pollInterval;

    @Data
    public static class Match {
        private Seen seen;
        @JsonProperty("_id")
        private String id;
        private Boolean closed;
        private Integer commonFriendCount;
        private Integer commonLikeCount;
        private String createdDate;
        private Boolean dead;
        private String lastActivityDate;
        private Integer messageCount;
        private List<Message> messages;
        private Boolean muted;
        private List<String> participants;
        private Boolean pending;
        private Boolean isSuperLike;
        private Boolean isBoostMatch;
        private Boolean isSuperBoostMatch;
        private Boolean isExperiencesMatch;
        private Boolean isFastMatch;
        private Boolean isOpener;
        private Person person;
        private Boolean following;
        private Boolean followingMoments;
        private Boolean isNewMessage;
        private Readreceipt readreceipt;

        @Data
        public static class Seen {
            private Boolean matchSeen;
        }

        @Data
        public static class Message {
            @JsonProperty("_id")
            private String id;
            private String matchId;
            private String sentDate;
            private String message;
            private String to;
            private String from;
            private String createdDate;
            private Long timestamp;
        }

        @Data
        public static class Person {
            @JsonProperty("_id")
            private String id;
            private String bio;
            private String birthDate;
            private Integer gender;
            private String name;
            private String pingTime;
            private List<Photo> photos;
            private Boolean hideDistance;
            private Boolean hideAge;

            @Data
            public static class Photo {
                private String id;
                private CropInfo cropInfo;
                private String url;
                private List<ProcessedFile> processedFiles;
                private String lastUpdateTime;
                private String fileName;
                private String extension;
                private List<Integer> webpQf;

                @Data
                public static class CropInfo {
                    private User user;
                    private Algo algo;
                    private Boolean processedByBullseye;
                    private Boolean userCustomized;

                    @Data
                    public static class User {
                        private Double widthPct;
                        @JsonProperty("x_offset_pct")
                        private Double xOffsetPct;
                        private Double heightPct;
                        @JsonProperty("y_offset_pct")
                        private Double yOffsetPct;
                    }

                    @Data
                    public static class Algo {
                        private Double widthPct;
                        @JsonProperty("x_offset_pct")
                        private Double xOffsetPct;
                        private Double heightPct;
                        @JsonProperty("y_offset_pct")
                        private Double yOffsetPct;
                    }
                }

                @Data
                public static class ProcessedFile {
                    private String url;
                    private Integer height;
                    private Integer width;
                }
            }
        }

        @Data
        public static class Readreceipt {
            private Boolean enabled;
        }
    }

    @Data
    public static class Inbox {
        //TODO: Add necessary fields here.
    }

    @Data
    public static class LikedMessage {
        //TODO: Add necessary fields here.
    }

    @Data
    public static class HarassingMessage {
        //TODO: Add necessary fields here.
    }

    @Data
    public static class _List {
        //TODO: Add necessary fields here.
    }

    @Data
    public static class Goingout {
        //TODO: Add necessary fields here.
    }

    @Data
    public static class DeletedList {
        //TODO: Add necessary fields here.
    }

    @Data
    public static class Squad {
        //TODO: Add necessary fields here.
    }

    @Data
    public static class PollInterval {
        private Integer standard;
        private Integer persistent;
    }
}
