package com.limengxiang.everlogic.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    public static JsonNode parse(String json) {
        try {
            return objectMapper.readValue(json, JsonNode.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("parse json string error:"+e.getMessage(), e);
        }
    }

    public static String stringify(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("json to string error:"+e.getMessage(), e);
        }
    }
}
