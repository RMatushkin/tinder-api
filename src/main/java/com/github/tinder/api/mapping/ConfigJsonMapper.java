package com.github.tinder.api.mapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;

public class ConfigJsonMapper extends AbstractJsonMapper {
    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper()
                .configure(FAIL_ON_EMPTY_BEANS, false)
                .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setSerializationInclusion(NON_NULL);
    }

    public ConfigJsonMapper() {
        super(OBJECT_MAPPER);
    }
}
