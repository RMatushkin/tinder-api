package com.github.tinder.api.mapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tinder.api.exception.TinderClientException;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.InputStream;

import static java.nio.charset.StandardCharsets.UTF_8;

@RequiredArgsConstructor
public class AbstractJsonMapper implements JsonMapper {
    private final ObjectMapper objectMapper;

    @Override
    public boolean validateJson(String json) {
        try {
            objectMapper.readTree(json);
            return true;
        } catch (JsonProcessingException e) {
            return false;
        }
    }

    @Override
    public byte[] toBytes(Object data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new TinderClientException(e);
        }
    }

    @Override
    public String toString(Object data) {
        try {
            return objectMapper.writeValueAsString(data);
        } catch (Exception e) {
            throw new TinderClientException(e);
        }
    }

    @Override
    public <T> String dataToString(T data) {
        try {
            return new String(objectMapper.writeValueAsBytes(data), UTF_8);
        } catch (Exception e) {
            throw new TinderClientException(e);
        }
    }

    @Override
    public JsonNode toJson(Object data) {
        try {
            String content = objectMapper.writeValueAsString(data);
            return objectMapper.readTree(content);
        } catch (Exception e) {
            throw new TinderClientException(e);
        }
    }

    @Override
    public <T> T fromBytes(byte[] content, Class<T> type) {
        try {
            return objectMapper.readValue(content, type);
        } catch (Exception e) {
            throw new TinderClientException(e);
        }
    }

    @Override
    public <T> T fromBytes(byte[] content, TypeReference<T> type) {
        try {
            return objectMapper.readValue(content, type);
        } catch (Exception e) {
            throw new TinderClientException(e);
        }
    }

    @Override
    public <T> T fromString(String content, Class<T> type) {
        try {
            return objectMapper.readValue(content, type);
        } catch (Exception e) {
            throw new TinderClientException(e);
        }
    }

    @Override
    public <T> T fromString(String content, TypeReference<T> type) {
        try {
            return objectMapper.readValue(content, type);
        } catch (Exception e) {
            throw new TinderClientException(e);
        }
    }

    @Override
    public <T> T fromString(String content, JavaType type) {
        try {
            return objectMapper.readValue(content, type);
        } catch (Exception e) {
            throw new TinderClientException(e);
        }
    }

    @Override
    public <T> T fromInputStream(InputStream content, Class<T> type) {
        try {
            return objectMapper.readValue(content, type);
        } catch (Exception e) {
            throw new TinderClientException(e);
        }
    }

    @Override
    public <T> T fromInputStream(InputStream content, TypeReference<T> type) {
        try {
            return objectMapper.readValue(content, type);
        } catch (Exception e) {
            throw new TinderClientException(e);
        }
    }

    @Override
    public <T> T fromFile(File file, JavaType type) {
        try {
            return objectMapper.readValue(file, type);
        } catch (Exception e) {
            throw new TinderClientException(e);
        }
    }

    @Override
    public <T> T fromFile(File file, Class<T> type) {
        try {
            return objectMapper.readValue(file, type);
        } catch (Exception e) {
            throw new TinderClientException(e);
        }
    }

    @Override
    public <T> T fromFile(File file, TypeReference<T> type) {
        try {
            return objectMapper.readValue(file, type);
        } catch (Exception e) {
            throw new TinderClientException(e);
        }
    }

    @Override
    public <T> T convertValue(Object data, Class<T> type) {
        try {
            return objectMapper.convertValue(data, type);
        } catch (Exception e) {
            throw new TinderClientException(e);
        }

    }

    @Override
    public <T> T convertValue(Object data, TypeReference<T> valueType) {
        try {
            return objectMapper.convertValue(data, valueType);
        } catch (Exception e) {
            throw new TinderClientException(e);
        }

    }

    @Override
    public <T> T convertValue(Object data, JavaType type) {
        try {
            return objectMapper.convertValue(data, type);
        } catch (Exception e) {
            throw new TinderClientException(e);
        }
    }
}
