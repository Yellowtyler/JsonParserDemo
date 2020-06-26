package com.vtb.jsonparser.core;

import com.vtb.jsonparser.core.entities.*;
import com.vtb.jsonparser.core.util.JsonConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonConverterTest {
    final String FILENAME = ".\\src\\main\\resources\\TeamsDIR.json";
    String expectedResult1 = "{\"teams\":[{\"id\":1,\"name\":\"team1\",\"students\":[{\"id\":1,\"firstName\":\"Ivan\",\"secondName\":\"Ivanov\",\"phone\":\"+6225525\",\"email\":\"safsf@afas.ru\",\"tasks\":[{\"id\":1,\"name\":\"task 1\",\"status\":\"done\",\"labels\":[{\"name\":\"Java programming\"},{\"name\":\"Version control\"}],\"description\":\"description example1\"},{\"id\":2,\"name\":\"task 2\",\"status\":\"open\",\"labels\":[{\"name\":\"Java programming\"},{\"name\":\"Version control\"}],\"description\":\"description example2\"}]},{\"id\":2,\"firstName\":\"Igor\",\"secondName\":\"Igorov\",\"phone\":\"+722245525\",\"email\":\"igor@mail.ru\",\"tasks\":[{\"id\":1,\"name\":\"task 1\",\"status\":\"done\",\"labels\":[{\"name\":\"Java programming\"},{\"name\":\"Version control\"}],\"description\":\"description example1\"},{\"id\":2,\"name\":\"task 2\",\"status\":\"open\",\"labels\":[{\"name\":\"Java programming\"},{\"name\":\"Version control\"}],\"description\":\"description example2\"}]}],\"tasks\":[{\"id\":1,\"name\":\"task 1\",\"status\":\"done\",\"labels\":[{\"name\":\"Java programming\"},{\"name\":\"Version control\"}],\"description\":\"description example1\"},{\"id\":2,\"name\":\"task 2\",\"status\":\"open\",\"labels\":[{\"name\":\"Java programming\"},{\"name\":\"Version control\"}],\"description\":\"description example2\"}]}]}";
    Teams expectedTeams = new Teams();
    JsonConverter jsonConverter;

    @BeforeEach
    public void init() {
        jsonConverter = new JsonConverter();

        List<Label> labelList = List.of(
                new Label("Java programming"),
                new Label("Version control"));

        List<Task> tasks = List.of(
                new Task(1L, "task 1", "description example1", Status.DONE, labelList),
                new Task(2L, "task 2", "description example2", Status.OPEN, labelList));

        List<Student> students = List.of(
                new Student(1L, "Ivan", "Ivanov", "+6225525", "safsf@afas.ru", tasks),
                new Student(2L, "Igor", "Igorov", "+722245525", "igor@mail.ru", tasks)
        );

        Team team = new Team(1L, "team1", students, tasks);
        expectedTeams.addTeam(team);
    }

    @Test
    public void FromObjectToFileTest() throws IOException {
        Entity entity = expectedTeams;
        jsonConverter.serialize(FILENAME, entity);
        String result = Files.lines(Paths.get(FILENAME)).reduce("", String::concat);
        assertEquals(expectedResult1, result);
    }

    @Test
    public void FromFileToObject() throws IOException {
        Entity entity = jsonConverter.deserialize(FILENAME, Teams.class);
        assertEquals(expectedTeams, entity);
    }

}
