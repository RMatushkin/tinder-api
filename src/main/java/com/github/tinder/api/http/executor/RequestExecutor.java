package com.github.tinder.api.http.executor;

import com.github.tinder.api.client.ProxySelectorBasedOnMarker;
import com.github.tinder.api.context.Context;
import com.github.tinder.api.exception.TinderClientException;
import com.github.tinder.api.http.request.ImageMultipartFormData;
import com.github.tinder.api.http.request.Request;
import com.github.tinder.api.mapping.JsonMapper;
import io.mikael.urlbuilder.UrlBuilder;
import io.mikael.urlbuilder.util.UrlParameterMultimap;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpTimeoutException;
import java.time.Duration;
import java.util.Base64;
import java.util.Map;
import java.util.Map.Entry;

import static com.github.tinder.api.client.ProxySelectorBasedOnMarker.MARKER_PARAMETER;
import static com.github.tinder.api.exception.TinderClientException.connectTimeout;
import static com.github.tinder.api.exception.TinderClientException.requestTimeout;
import static com.github.tinder.api.util.HttpUtil.ofMultipartFormData;
import static io.mikael.urlbuilder.util.UrlParameterMultimap.newMultimap;
import static java.net.Proxy.Type.HTTP;
import static java.net.http.HttpRequest.BodyPublishers.noBody;
import static java.net.http.HttpRequest.BodyPublishers.ofString;
import static lombok.Lombok.checkNotNull;
import static org.apache.commons.collections4.MapUtils.isNotEmpty;

@RequiredArgsConstructor
public class RequestExecutor {
    private static final String API_HOST = "https://api.gotinder.com";
    private static final String PROXY_AUTHORIZATION_HEADER = "Proxy-Authorization";
    private final HttpClient httpClient;
    private final ProxySelectorBasedOnMarker proxySelector;
    private final Duration requestTimeout;
    private final JsonMapper jsonMapper;

    public HttpResponse<InputStream> execute(Request request, Context context) {
        if (context.getProxy() != null) {
            proxySelector.add(request.marker(context), buildProxy(context));
        }
        try {
            HttpRequest httpRequest = buildHttpRequest(request, context);
            return httpClient.send(httpRequest, BodyHandlers.ofInputStream());
        } catch (ConnectException e) {
            throw connectTimeout(e);
        } catch (HttpTimeoutException e) {
            throw requestTimeout(e);
        } catch (Exception e) {
            throw new TinderClientException(e);
        } finally {
            if (context.getProxy() != null) {
                proxySelector.remove(request.marker(context));
            }
        }
    }

    private Proxy buildProxy(Context context) {
        var proxy = context.getProxy();
        checkNotNull(proxy.getHost(), "'host' cannot be null");
        checkNotNull(proxy.getPort(), "'port' cannot be null");
        checkNotNull(proxy.getUsername(), "'username' cannot be null");
        checkNotNull(proxy.getPassword(), "'password' cannot be null");
        return new Proxy(HTTP, new InetSocketAddress(proxy.getHost(), proxy.getPort()));
    }

    private HttpRequest buildHttpRequest(Request request, Context context) {
        HttpRequest.Builder builder = HttpRequest.newBuilder(buildURI(request, context))
                .method(request.method(), buildBody(request))
                .timeout(requestTimeout);
        if (context.getProxy() != null) {
            builder.header(PROXY_AUTHORIZATION_HEADER, basicAuth(context));
        }
        request.headers(context).forEach(builder::header);
        return builder.build();
    }

    private BodyPublisher buildBody(Request request) {
        ImageMultipartFormData imageMultipartFormData = request.imageMultipartFormData();
        if (imageMultipartFormData != null) {
            return ofMultipartFormData(
                    imageMultipartFormData.getKey(),
                    imageMultipartFormData.getBoundary(),
                    imageMultipartFormData.getContentType(),
                    imageMultipartFormData.getFilename(),
                    imageMultipartFormData.getBytes()
            );
        }
        Map<String, Object> body = request.body();
        return (body == null) ? noBody() : ofString(jsonMapper.dataToString(body));
    }

    private URI buildURI(Request request, Context context) {
        String url = request.url();
        if (url == null) url = API_HOST + request.path();
        UrlBuilder builder = UrlBuilder.fromString(url);
        if (isNotEmpty(request.selfTitledParams())) {
            UrlParameterMultimap urlParameterMultimap = newMultimap();
            urlParameterMultimap.putAll(request.selfTitledParams());
            builder = builder.withQuery(urlParameterMultimap);
        }
        for (Entry<String, String> entry : request.params().entrySet()) {
            builder = builder.addParameter(entry.getKey(), entry.getValue());
        }
        builder = builder.addParameter(MARKER_PARAMETER, request.marker(context));
        return builder.toUri();
    }

    private String basicAuth(Context context) {
        String credentials = context.getProxy().getUsername() + ":" + context.getProxy().getPassword();
        return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
    }
}
