package com.vtb.jsonparser.core.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vtb.jsonparser.core.entities.Team;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JsonConverter {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> void toJSON(String fileName, T object, Class<T> c) throws IOException {
        objectMapper.writeValue(new File(fileName), object);
    }

    public static <T> T toJavaObject(String fileName, Class<T> c) throws IOException {
        return (T) objectMapper.readValue(new File(fileName), c);
    }
}
