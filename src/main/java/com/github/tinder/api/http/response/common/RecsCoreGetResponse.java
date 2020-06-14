package com.github.tinder.api.http.response.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.tinder.api.http.response.common.RecsCoreGetResponse.User.Photo;
import lombok.Data;

import java.util.List;

import static java.lang.Boolean.TRUE;

@Data
public class RecsCoreGetResponse {
    private Meta meta;
    private Data data;

    @lombok.Data
    public static class Meta {
        private Integer status;
    }

    @lombok.Data
    public static class Data {
        private List<Result> results;
        private Long timeout;
    }

    @lombok.Data
    public static class Result {
        private String type;
        private User user;
        private Instagram instagram;
        private Facebook facebook;
        private Spotify spotify;
        private Snap snap;
        private Integer distanceMi;
        private String contentHash;
        @JsonProperty("s_number")
        private Integer sNumber;
        private Teaser teaser;
        private List<Teaser> teasers;

        @JsonIgnore
        public RecsCoreGetResponse.User.Photo getMainPhoto() {
            for (Photo photo : user.photos) {
                if (TRUE.equals(photo.getMain())) {
                    return photo;
                }
            }
            return user.photos.get(0);
        }
    }

    @lombok.Data
    public static class User {
        @JsonProperty("_id")
        private String id;
        private String bio;
        private String birthDate;
        private String name;
        private List<Photo> photos;
        private Integer gender;
        private List<Job> jobs;
        private List<School> schools;
        private City city;
        private Boolean showGenderOnProfile;
        private Boolean isTraveling;
        private Boolean hideAge;
        private Boolean hideDistance;
        private String customGender;

        @lombok.Data
        public static class Photo {
            private String id;
            private Boolean main;
            private CropInfo cropInfo;
            private String url;
            @JsonProperty("processedFiles")
            private List<ProcessedFile> processedFiles;
            private String lastUpdateTime;
            @JsonProperty("fileName")
            private String fileName;
            private String extension;
            private List<Integer> webpQf;

            @lombok.Data
            public static class CropInfo {
                private CropInfo_User user;
                private Algo algo;
                private Boolean processedByBullseye;
                private Boolean userCustomized;

                @lombok.Data
                public static class CropInfo_User {
                    private Double widthPct;
                    @JsonProperty("x_offset_pct")
                    private Double xOffsetPct;
                    private Double heightPct;
                    @JsonProperty("y_offset_pct")
                    private Double yOffsetPct;
                }

                @lombok.Data
                public static class Algo {
                    private Double widthPct;
                    @JsonProperty("x_offset_pct")
                    private Double xOffsetPct;
                    private Double heightPct;
                    @JsonProperty("y_offset_pct")
                    private Double yOffsetPct;
                }
            }

            @lombok.Data
            public static class ProcessedFile {
                private String url;
                private Integer height;
                private Integer width;
            }
        }

        @lombok.Data
        public static class Job {
            private Title title;

            @lombok.Data
            public static class Company {
                private String name;
            }

            @lombok.Data
            public static class Title {
                private String name;
            }
        }

        @lombok.Data
        public static class School {
            private String name;
        }

        @lombok.Data
        public static class City {
            private String name;
        }
    }

    @lombok.Data
    public static class Instagram {
        //TODO: Add necessary fields.
    }

    @lombok.Data
    public static class Facebook {
        private List<CommonConnection> commonConnections;
        private Long connectionCount;
        private List<CommonInterest> commonInterests;

        @lombok.Data
        public static class CommonConnection {
            //TODO: Add necessary fields.
        }

        @lombok.Data
        public static class CommonInterest {
            //TODO: Add necessary fields.
        }
    }

    @lombok.Data
    public static class Spotify {
        private Boolean spotifyConnected;
    }

    @lombok.Data
    public static class Snap {
        private List<_Snap> snaps;

        @lombok.Data
        public static class _Snap {
            //TODO: Add necessary fields.
        }
    }

    @lombok.Data
    public static class Teaser {
        private String type;
        private String string;
    }
}
