package com.github.tinder.api.http.response.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProfileGetResponse {
    private Meta meta;
    private Data data;

    @lombok.Data
    public static class Meta {
        private Integer status;
    }

    @lombok.Data
    public static class Data {
        private User user;

        @lombok.Data
        public static class User {
            @JsonProperty("_id")
            private String id;
            private Integer ageFilterMax;
            private Integer ageFilterMin;
            private String birthDate;
            private String createDate;
            private String crmId;
            private Boolean discoverable;
            private Integer distanceFilter;
            private Integer gender;
            private Integer genderFilter;
            private String name;
            private List<Photo> photos;
            private Boolean photosProcessing;
            private Boolean photoOptimizerEnabled;
            private String pingTime;
            private List<Object> schools;
            private List<Object> badges;
            private String phoneId;
            private List<Object> interestedIn;
            @JsonProperty("pos")
            private Position position;
            private String autoplayVideo;
            private Boolean topPicksDiscoverable;
            private Boolean photoTaggingEnabled;
            private Boolean recommendedSortDiscoverable;

            @lombok.Data
            public static class Photo {
                private String id;
                private CropInfo cropInfo;
                private String url;
                @JsonProperty("fbId")
                private String fbId;
                private List<ProcessedFile> processedFiles;

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
            public static class Position {
                private Double lat;
                private Double lon;
            }
        }
    }
}
