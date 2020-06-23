package com.vtb.jsonparser.core;

import com.vtb.jsonparser.core.entities.Student;
import com.vtb.jsonparser.core.entities.Task;
import com.vtb.jsonparser.core.util.FileWorker;
import com.vtb.jsonparser.core.util.XmlConverter;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;

public class MainApp {


    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        Student student = new Student();
        student.setId(2L);
        student.setPhone("765865");
        student.setEmail("gjkg");
        Task task = new Task();
        task.setId(1L);
        task.setName("dfsd");
        student.setTasks(List.of(task));
        List<String> listFiles = FileWorker.findFiles("xml-json", ".", new String[]{"*.xml", "team.json", "student.xml"});
        for(String list : listFiles)
            System.out.println(list);
        List<String> listFil = FileWorker.convertFiles("xml", listFiles);
        for(String list : listFil)
            System.out.println(list);

        XmlConverter.toXML("student.xml", student, Student.class);

        Object f = XmlConverter.toJavaObject("student.xml", Student.class);
    }
}
