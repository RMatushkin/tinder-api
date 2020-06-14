package com.github.tinder.api.client;

import com.github.tinder.api.exception.TinderClientException;

import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public class ProxySelectorBasedOnMarker extends ProxySelector {
    public static final String MARKER_PARAMETER = "marker";
    private final Map<String, List<Proxy>> proxies = new HashMap<>();

    public synchronized void add(String marker, Proxy proxy) {
        proxies.put(marker, singletonList(proxy));
    }

    public synchronized void remove(String marker) {
        proxies.remove(marker);
    }

    @Override
    public synchronized List<Proxy> select(URI uri) {
        String marker = parseMarker(uri);
        return (marker == null) ? emptyList() : proxies.get(marker);
    }

    @Override
    public void connectFailed(URI uri, SocketAddress sa, IOException e) {
        throw new TinderClientException(e);
    }

    private String parseMarker(URI uri) {
        if (!proxies.isEmpty()) {
            for (String param : uri.getQuery().split("&")) {
                String[] keyAndValue = param.split("=");
                String key = URLDecoder.decode(keyAndValue[0], UTF_8);
                if (MARKER_PARAMETER.equals(key)) {
                    return URLDecoder.decode(keyAndValue[1], UTF_8);
                }
            }
        }
        return null;
    }
}
