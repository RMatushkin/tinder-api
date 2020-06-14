package com.github.tinder.api.client;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    PROXY_DOES_NOT_RESPOND("Proxy doesn't respond"),
    TINDER_DOES_NOT_RESPOND("Tinder doesn't respond"),
    INVALID_PROXY_CREDENTIALS("Invalid proxy credentials");

    private static final Set<String> TEMPORARY_PROBLEMS = Set.of(
            TINDER_DOES_NOT_RESPOND.value,
            PROXY_DOES_NOT_RESPOND.value,
            INVALID_PROXY_CREDENTIALS.value
    );
    private final String value;

    public boolean isTemporaryProblem() {
        return TEMPORARY_PROBLEMS.contains(value);
    }
}
