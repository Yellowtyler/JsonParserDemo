package com.vtb.jsonparser.core;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;

public class MainApp {

    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        Student student = new Student();
        student.setFirstName("Ivan");
        student.setId(2L);
        student.setPhone("765865");
        student.setEmail("gjkg");
        Task task = new Task();
        task.setLabels(List.of(new Label("java programming"), new Label("Version control")));
        task.setId(1L);
        task.setStatus(Status.IN_PROCESS);
        task.setName("dfsd");
        student.setTasks(List.of(task));

        List<String> listFiles = FileWorker.findFiles("xml-json", ".", new String[]{"*.xml", "team.json", "student.xml"});
        for(String list : listFiles)
            System.out.println(list);
        List<String> listFil = FileWorker.convertFiles("xml", listFiles);
        for(String list : listFil)
            System.out.println(list);

        Student student1 = new Student();
        student1.setFirstName("Leha");
        student1.setId(2L);
        student1.setPhone("765865");
        student1.setEmail("gjkg");
        Task task1 = new Task();
        task1.setLabels(List.of(new Label("Virtual"), new Label("Version control")));
        task1.setId(1L);
        task1.setStatus(Status.DONE);
        task1.setName("dfsd");
        student1.setTasks(List.of(task1));

        List<Student> students = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        tasks.add(task1);
        students.add(student);
        students.add(student1);

        Team team = new Team();
        team.setId(1L);
        team.setName("team1");
        team.setStudents(students);
        team.setTasks(tasks);

        List<String> listFiles = findFiles(".",new String[]{"team.xml", "student.xml"});

        XmlConverter.toXML("team.xml", team, Team.class);

        Team f = (Team)XmlConverter.toJavaObject("team.xml", Team.class);
        System.out.println(f.getName());
        System.out.println(f.getTasks().get(0).getStatus());
    }
}
