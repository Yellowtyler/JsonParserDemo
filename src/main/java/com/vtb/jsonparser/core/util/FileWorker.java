package com.vtb.jsonparser.core.util;

import com.vtb.jsonparser.core.exceptions.NameFileException;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileWorker {

    private static boolean checkNameFile(String typeConvert, String fileName) {
        if (typeConvert.equalsIgnoreCase("xml-json"))
            return Pattern.compile(".xml").matcher(fileName).find();
        if (typeConvert.equalsIgnoreCase("json-xml"))
            return Pattern.compile(".json").matcher(fileName).find();
        return false;
    }

    public List<String> convertFiles(String typeConvert, List<String> files) {
        int index;
        String str;
        List<String> newFiles = new ArrayList<>();
        for (String file : files) {
            index = file.lastIndexOf(".");
            str = file.substring(0, index + 1);
            newFiles.add(str.concat(typeConvert));
        }
        return newFiles;
    }

    public String convertFile(String typeConvert, String file) throws NameFileException {
        int index;
        String str;
        index = file.lastIndexOf(".");
        if (index < 0) {
            throw new NameFileException("Файл должен иметь формат: имя_файла.xml, либо имя_файла.json");
        }
        str = file.substring(0, index + 1);
        str = str.concat(typeConvert);
        return str;
    }

    public static List<String> findFiles(String typeConvert, String directory, String[] arrFiles) {
        FileFilter fileFilter;
        File[] files;
        List<String> listFiles = new ArrayList<>();
        File dir = new File(directory);
        for (String arg : arrFiles) {
            fileFilter = new WildcardFileFilter(arg);
            //fileFilter = new RegexFileFilter(arg);
            files = dir.listFiles(fileFilter);
            for (File file : files) {
                if (checkNameFile(typeConvert, file.toString())) {
                    listFiles.add(file.toString());
                }
            }
        }
        return listFiles.stream().distinct().collect(Collectors.toList());
    }

}
