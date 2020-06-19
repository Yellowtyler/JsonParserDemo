package com.vtb.core;

import com.vtb.core.entities.*;
import com.vtb.core.util.Converter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterTest {

    @Test
    public void JsonConverterTest1() throws IOException {
        List<Label> labelList = new ArrayList<>();
        labelList.add(Label.JAVA_PROGRAMMING);
        labelList.add(Label.VER_CONTROL);
        Task task1 = new Task(1L, "task 1", "sfafafa", Status.DONE, labelList);
        Task task2 = new Task(2L, "task 2", "sfasfasfafs", Status.DONE, labelList);
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        Student ivan = new Student(1L,
                "Ivan",
                "Ivanov",
                "+6225525",
                "safsf@afas.ru",
                (ArrayList<Task>) tasks);

        Student igor = new Student(2L,
                "Igor",
                "Igorov",
                "+722245525",
                "igor@mail.ru",
                (ArrayList<Task>) tasks);

        List<Student> students = new ArrayList<>();
        students.add(igor);
        students.add(ivan);
        Team team = new Team(1L, "team1", students, tasks);
        ArrayList<Team> teams = new ArrayList<>();
        teams.add(team);

        final String FILENAME = "data.json";
        Converter.toJSON(FILENAME, teams);

        List<Team> teams1 = Converter.toJavaObject(FILENAME);

        assertEquals(teams, teams1);
    }
}
