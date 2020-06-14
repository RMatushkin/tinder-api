package com.github.tinder.api.mapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.io.InputStream;

public interface JsonMapper {

    boolean validateJson(String json);

    byte[] toBytes(Object data);

    String toString(Object data);

    <T> String dataToString(T data);

    JsonNode toJson(Object data);

    <T> T fromBytes(byte[] content, Class<T> type);

    <T> T fromBytes(byte[] content, TypeReference<T> type);

    <T> T fromString(String content, Class<T> type);

    <T> T fromString(String content, TypeReference<T> type);

    <T> T fromString(String content, JavaType type);

    <T> T fromInputStream(InputStream content, Class<T> type);

    <T> T fromInputStream(InputStream content, TypeReference<T> type);

    <T> T fromFile(File file, JavaType type);

    <T> T fromFile(File file, Class<T> type);

    <T> T fromFile(File file, TypeReference<T> type);

    <T> T convertValue(Object data, Class<T> type);

    <T> T convertValue(Object data, TypeReference<T> valueType);

    <T> T convertValue(Object data, JavaType type);
}
