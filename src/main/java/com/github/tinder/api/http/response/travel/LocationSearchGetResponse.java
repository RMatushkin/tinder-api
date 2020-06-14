package com.github.tinder.api.http.response.travel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class LocationSearchGetResponse {
    private Integer status;
    private List<Result> results;

    @Data
    public static class Result {
        private String lat;
        private String lon;
        private StreetNumber streetNumber;
        private Route route;
        private Locality locality;
        @JsonProperty("administrative_area_level_1")
        private AdministrativeAreaLevel1 administrativeAreaLevel1;
        @JsonProperty("administrative_area_level_2")
        private AdministrativeAreaLevel2 administrativeAreaLevel2;
        private Country country;
    }

    @Data
    public static class StreetNumber {
        private String shortName;
        private String longName;
    }

    @Data
    public static class Route {
        private String shortName;
        private String longName;
    }

    @Data
    public static class Locality {
        private String shortName;
        private String longName;
    }

    @Data
    public static class AdministrativeAreaLevel1 {
        private String shortName;
        private String longName;
    }

    @Data
    public static class AdministrativeAreaLevel2 {
        private String shortName;
        private String longName;
    }

    @Data
    public static class Country {
        private String shortName;
        private String longName;
    }
}
