package com.vtb.jsonparser.core;

import com.vtb.jsonparser.core.entities.*;
import com.vtb.jsonparser.core.util.JsonConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonConverterTest {
    final String FILENAME = "data.json";
    List<Team> teams = new ArrayList<>();
    String expectedResult = "[{\"id\":1,\"name\":\"team1\",\"students\":[{\"id\":1,\"firstName\":\"Ivan\",\"secondName\":\"Ivanov\",\"phone\":\"+6225525\",\"email\":\"safsf@afas.ru\",\"tasks\":[{\"id\":1,\"name\":\"task 1\",\"status\":\"done\",\"labels\":[{\"name\":\"Java programming\"},{\"name\":\"Version control\"}],\"description\":\"sfafafa\"},{\"id\":2,\"name\":\"task 2\",\"status\":\"done\",\"labels\":[{\"name\":\"Java programming\"},{\"name\":\"Version control\"}],\"description\":\"sfasfasfafs\"}]},{\"id\":2,\"firstName\":\"Igor\",\"secondName\":\"Igorov\",\"phone\":\"+722245525\",\"email\":\"igor@mail.ru\",\"tasks\":[{\"id\":1,\"name\":\"task 1\",\"status\":\"done\",\"labels\":[{\"name\":\"Java programming\"},{\"name\":\"Version control\"}],\"description\":\"sfafafa\"},{\"id\":2,\"name\":\"task 2\",\"status\":\"done\",\"labels\":[{\"name\":\"Java programming\"},{\"name\":\"Version control\"}],\"description\":\"sfasfasfafs\"}]}],\"tasks\":[{\"id\":1,\"name\":\"task 1\",\"status\":\"done\",\"labels\":[{\"name\":\"Java programming\"},{\"name\":\"Version control\"}],\"description\":\"sfafafa\"},{\"id\":2,\"name\":\"task 2\",\"status\":\"done\",\"labels\":[{\"name\":\"Java programming\"},{\"name\":\"Version control\"}],\"description\":\"sfasfasfafs\"}]}]";

    @BeforeEach
    public void init() {
        List<Label> labelList = List.of(
                new Label("Java programming"),
                new Label("Version control"));

        List<Task> tasks = List.of(
                new Task(1L, "task 1", "sfafafa", Status.DONE, labelList),
                new Task(2L, "task 2", "sfasfasfafs", Status.DONE, labelList));

        List<Student> students = List.of(
                new Student(1L, "Ivan", "Ivanov", "+6225525", "safsf@afas.ru", tasks),
                new Student(2L, "Igor", "Igorov", "+722245525", "igor@mail.ru", tasks)
        );

        Team team = new Team(1L, "team1", students, tasks);

        teams.add(team);

    }

    @Test
    public void ConverterToJsonTest() throws IOException {
        JsonConverter.toJSON(FILENAME, teams);
        String result = Files.lines(Paths.get(FILENAME)).reduce("", String::concat);
        assertEquals(expectedResult, result);
    }

    @Test
    public void ConverterToJavaObject() throws IOException {
        List<Team> newTeams = JsonConverter.toJavaObject(FILENAME);
        assertEquals(newTeams.get(0).toString(), teams.get(0).toString());
    }
}
