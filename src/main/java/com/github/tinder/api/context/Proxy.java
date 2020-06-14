package com.github.tinder.api.context;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Proxy {
    private String host;
    private int port;
    private String username;
    private String password;
}
