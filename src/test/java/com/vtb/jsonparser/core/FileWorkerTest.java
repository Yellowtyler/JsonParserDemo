package com.vtb.jsonparser.core;

import com.vtb.jsonparser.core.util.FileWorker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileWorkerTest {
    final String TYPE_CONVERT = "xml-json";
    final String FILENAME_POM = "pom.xml";
    final String FILENAME_NOT_EXISTED = "notexisted.xml";
    @Test
    public void findFilesFoundPomXMLTest() {
        List<String> result = FileWorker.findFiles(TYPE_CONVERT, ".", new String[]{FILENAME_POM});
        List<String> expected = new ArrayList<>();
        expected.add(".\\pom.xml");
        assertEquals(expected.get(0), result.get(0));
    }

    @Test
    public void findFilesNotFoundTest() {
        List<String> result = FileWorker.findFiles(TYPE_CONVERT, ".", new String[]{FILENAME_NOT_EXISTED});
        List<String> expected = new ArrayList<>();
        assertEquals(result.isEmpty(), expected.isEmpty());
    }
}
