package com.vtb.jsonparser.core.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;


public class JsonConverter {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> void toJSON(String fileName, T teams) throws IOException {
        objectMapper.writeValue(new File(fileName), teams);
    }

    public static <T> T toJavaObject(String fileName, Class<T> c) throws IOException {
        return (T) objectMapper.readValue(new File(fileName), c);
    }
}
