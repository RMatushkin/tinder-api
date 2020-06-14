package com.github.tinder.api.http.response.travel;

import lombok.Data;

import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

@Data
public class PassportUserTravelPostResponse {
    private Integer status;
    private String error;

    public boolean isInvalidLatLon() {
        return (status == SC_INTERNAL_SERVER_ERROR && error != null);
    }
}
