package com.vtb.jsonparser.core.util;

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

    public static List<String> convertFiles(String typeConvert, List<String> files) {
        int index ;
        String str;
        List<String> newFiles = new ArrayList<>();
        for(String file : files) {
            index = file.lastIndexOf(".");
            str = file.substring(0, index + 1);
            newFiles.add(str.concat(typeConvert));
        }
        return newFiles;
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
