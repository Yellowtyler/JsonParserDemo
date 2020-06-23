package com.vtb.jsonparser.core.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vtb.jsonparser.core.entities.Team;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Converter {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static void toJSON(String fileName, List<Team> teams) throws IOException {
        objectMapper.writeValue(new File(fileName), teams);
    }

    public static List<Team> toJavaObject(String fileName) throws IOException {
        Team[] readValue =  objectMapper.readValue(new File(fileName), Team[].class);
        return Arrays.asList(readValue);
    }
}
