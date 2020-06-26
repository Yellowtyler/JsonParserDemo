package com.vtb.jsonparser.core;

import com.vtb.jsonparser.core.entities.*;
import com.vtb.jsonparser.core.util.JsonConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonConverterTest {
    final String FILENAME = "data.json";
    final String FILENAME2 = "data2.json";
    List<Team> teams = new ArrayList<>();
    String expectedResult = "[{\"id\":1,\"name\":\"team1\",\"students\":[{\"id\":1,\"firstName\":\"Ivan\",\"secondName\":\"Ivanov\",\"phone\":\"+6225525\",\"email\":\"safsf@afas.ru\",\"tasks\":[{\"id\":1,\"name\":\"task 1\",\"status\":\"done\",\"labels\":[{\"name\":\"Java programming\"},{\"name\":\"Version control\"}],\"description\":\"sfafafa\"},{\"id\":2,\"name\":\"task 2\",\"status\":\"done\",\"labels\":[{\"name\":\"Java programming\"},{\"name\":\"Version control\"}],\"description\":\"sfasfasfafs\"}]},{\"id\":2,\"firstName\":\"Igor\",\"secondName\":\"Igorov\",\"phone\":\"+722245525\",\"email\":\"igor@mail.ru\",\"tasks\":[{\"id\":1,\"name\":\"task 1\",\"status\":\"done\",\"labels\":[{\"name\":\"Java programming\"},{\"name\":\"Version control\"}],\"description\":\"sfafafa\"},{\"id\":2,\"name\":\"task 2\",\"status\":\"done\",\"labels\":[{\"name\":\"Java programming\"},{\"name\":\"Version control\"}],\"description\":\"sfasfasfafs\"}]}],\"tasks\":[{\"id\":1,\"name\":\"task 1\",\"status\":\"done\",\"labels\":[{\"name\":\"Java programming\"},{\"name\":\"Version control\"}],\"description\":\"sfafafa\"},{\"id\":2,\"name\":\"task 2\",\"status\":\"done\",\"labels\":[{\"name\":\"Java programming\"},{\"name\":\"Version control\"}],\"description\":\"sfasfasfafs\"}]}]";
    String expectedResult1 = "{\"teams\":[{\"id\":1,\"name\":\"team1\",\"students\":[{\"id\":1,\"firstName\":\"Ivan\",\"secondName\":\"Ivanov\",\"phone\":\"+6225525\",\"email\":\"safsf@afas.ru\",\"tasks\":[{\"id\":1,\"name\":\"task 1\",\"status\":\"done\",\"labels\":[{\"name\":\"Java programming\"},{\"name\":\"Version control\"}],\"description\":\"sfafafa\"},{\"id\":2,\"name\":\"task 2\",\"status\":\"done\",\"labels\":[{\"name\":\"Java programming\"},{\"name\":\"Version control\"}],\"description\":\"sfasfasfafs\"}]},{\"id\":2,\"firstName\":\"Igor\",\"secondName\":\"Igorov\",\"phone\":\"+722245525\",\"email\":\"igor@mail.ru\",\"tasks\":[{\"id\":1,\"name\":\"task 1\",\"status\":\"done\",\"labels\":[{\"name\":\"Java programming\"},{\"name\":\"Version control\"}],\"description\":\"sfafafa\"},{\"id\":2,\"name\":\"task 2\",\"status\":\"done\",\"labels\":[{\"name\":\"Java programming\"},{\"name\":\"Version control\"}],\"description\":\"sfasfasfafs\"}]}],\"tasks\":[{\"id\":1,\"name\":\"task 1\",\"status\":\"done\",\"labels\":[{\"name\":\"Java programming\"},{\"name\":\"Version control\"}],\"description\":\"sfafafa\"},{\"id\":2,\"name\":\"task 2\",\"status\":\"done\",\"labels\":[{\"name\":\"Java programming\"},{\"name\":\"Version control\"}],\"description\":\"sfasfasfafs\"}]}]}";
    Teams teamsClass;

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
        teamsClass = new Teams(teams);
    }

    @Test
    public void FromObjectToFileTest() throws IOException {
        JsonConverter.toJSON(FILENAME, teams);
        String result = Files.lines(Paths.get(FILENAME)).reduce("", String::concat);
        assertEquals(expectedResult, result);
    }

    @Test
    public void FromFileToObject() throws IOException {
        List<Team> newTeams = Arrays.asList(JsonConverter.toJavaObject(FILENAME, Team[].class));
        assertEquals(teams.get(0).toString(), newTeams.get(0).toString());
    }

    @Test
    public void ConverterToJsonClassTeamsTest() throws IOException {
        JsonConverter.toJSON(FILENAME2, teamsClass);
        String result = Files.lines(Paths.get(FILENAME2)).reduce("", String::concat);
        assertEquals(expectedResult1, result);
    }

    @Test
    public void ConverterToJavaClassTeamsObject() throws IOException {
        Teams newTeams = JsonConverter.toJavaObject(FILENAME2, Teams.class);
        assertEquals(newTeams.getTeams().get(0), teamsClass.getTeams().get(0));
    }

    @Test
    public void FromObjectToFileTest2() throws IOException {
        Entity entity = teamsClass;
//        Entity entity = new Teams();
        JsonConverter.toJSON(FILENAME2, entity);
        String result = Files.lines(Paths.get(FILENAME2)).reduce("", String::concat);
        assertEquals(expectedResult1, result);
    }

    @Test
    public void fromFileToObj() throws IOException {
//            List<Team> newTeams = Arrays.asList(JsonConverter.toJavaObject(FILENAME, Team[].class));
        Entity entity = JsonConverter.toJavaObject(FILENAME2, Teams.class);
        assertEquals(teamsClass, entity);
//        assertEquals( teamsClass.getTeams().get(0), entity.newTeams.getTeams().get(0));
    }

}
