package com.vtb.jsonparser.core;

import com.vtb.jsonparser.core.entities.Student;
import com.vtb.jsonparser.core.entities.StudentCopy;
import com.vtb.jsonparser.core.util.XmlConverter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MainApp {

    public static List<String> findFiles(String[] args) {
        FileFilter fileFilter;
        File[] files;
        List<String> listFiles = new ArrayList<>();
        File dir = new File(".");
        for (String arg : args) {
            fileFilter = new WildcardFileFilter(arg);
            //fileFilter = new RegexFileFilter(arg);
            files = dir.listFiles(fileFilter);
            for (File file : files) {
                System.out.println(file);
                listFiles.add(file.toString());
            }
        }
        return listFiles;
    }

    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        Student student = new Student();
        student.setId(2L);
        student.setPhone("765865");
        student.setEmail("gjkg");
        List<String> listFiles = findFiles(new String[]{"*.xml", "team.json"});

        //XmlConverter.toXML("student.xml", student);

        //Object f = XmlConverter.toJavaObject("student.xml", StudentCopy.class);
    }
}
