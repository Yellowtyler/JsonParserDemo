package com.vtb.jsonparser.core;

import com.vtb.jsonparser.core.entities.*;
import com.vtb.jsonparser.core.util.XmlConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XmlConverterTest {
    final String FILENAME = ".\\src\\main\\resources\\TeamsDIR.xml";
    Teams expectedTeams = new Teams();
    StringBuilder stringBuilder = new StringBuilder();
    XmlConverter xmlConverter;

    @BeforeEach
    public void init() {
        xmlConverter = new XmlConverter();

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

        stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<teams>\n" +
                "    <team>\n" +
                "        <id>1</id>\n" +
                "        <name>team1</name>\n" +
                "        <students>\n" +
                "            <student>\n" +
                "                <id>1</id>\n" +
                "                <firstName>Ivan</firstName>\n" +
                "                <secondName>Ivanov</secondName>\n" +
                "                <phone>+6225525</phone>\n" +
                "                <email>safsf@afas.ru</email>\n" +
                "                <tasks>\n" +
                "                    <task>\n" +
                "                        <id>1</id>\n" +
                "                        <name>task 1</name>\n" +
                "                        <status>DONE</status>\n" +
                "                        <labels>\n" +
                "                            <label>\n" +
                "<name>Java programming</name>\n" +
                "                            </label>\n" +
                "                            <label>\n" +
                "<name>Version control</name>\n" +
                "                            </label>\n" +
                "                        </labels>\n" +
                "                        <description>description example1</description>\n" +
                "                    </task>\n" +
                "                    <task>\n" +
                "                        <id>2</id>\n" +
                "                        <name>task 2</name>\n" +
                "                        <status>OPEN</status>\n" +
                "                        <labels>\n" +
                "                            <label>\n" +
                "<name>Java programming</name>\n" +
                "                            </label>\n" +
                "                            <label>\n" +
                "<name>Version control</name>\n" +
                "                            </label>\n" +
                "                        </labels>\n" +
                "                        <description>description example2</description>\n" +
                "                    </task>\n" +
                "                </tasks>\n" +
                "            </student>\n" +
                "            <student>\n" +
                "                <id>2</id>\n" +
                "                <firstName>Igor</firstName>\n" +
                "                <secondName>Igorov</secondName>\n" +
                "                <phone>+722245525</phone>\n" +
                "                <email>igor@mail.ru</email>\n" +
                "                <tasks>\n" +
                "                    <task>\n" +
                "                        <id>1</id>\n" +
                "                        <name>task 1</name>\n" +
                "                        <status>DONE</status>\n" +
                "                        <labels>\n" +
                "                            <label>\n" +
                "<name>Java programming</name>\n" +
                "                            </label>\n" +
                "                            <label>\n" +
                "<name>Version control</name>\n" +
                "                            </label>\n" +
                "                        </labels>\n" +
                "                        <description>description example1</description>\n" +
                "                    </task>\n" +
                "                    <task>\n" +
                "                        <id>2</id>\n" +
                "                        <name>task 2</name>\n" +
                "                        <status>OPEN</status>\n" +
                "                        <labels>\n" +
                "                            <label>\n" +
                "<name>Java programming</name>\n" +
                "                            </label>\n" +
                "                            <label>\n" +
                "<name>Version control</name>\n" +
                "                            </label>\n" +
                "                        </labels>\n" +
                "                        <description>description example2</description>\n" +
                "                    </task>\n" +
                "                </tasks>\n" +
                "            </student>\n" +
                "        </students>\n" +
                "        <tasks>\n" +
                "            <task>\n" +
                "                <id>1</id>\n" +
                "                <name>task 1</name>\n" +
                "                <status>DONE</status>\n" +
                "                <labels>\n" +
                "                    <label>\n" +
                "                        <name>Java programming</name>\n" +
                "                    </label>\n" +
                "                    <label>\n" +
                "                        <name>Version control</name>\n" +
                "                    </label>\n" +
                "                </labels>\n" +
                "                <description>description example1</description>\n" +
                "            </task>\n" +
                "            <task>\n" +
                "                <id>2</id>\n" +
                "                <name>task 2</name>\n" +
                "                <status>OPEN</status>\n" +
                "                <labels>\n" +
                "                    <label>\n" +
                "                        <name>Java programming</name>\n" +
                "                    </label>\n" +
                "                    <label>\n" +
                "                        <name>Version control</name>\n" +
                "                    </label>\n" +
                "                </labels>\n" +
                "                <description>description example2</description>\n" +
                "            </task>\n" +
                "        </tasks>\n" +
                "    </team>\n" +
                "</teams>\n");
    }

    @Test
    public void FromObjectToFileTest() throws IOException {
        xmlConverter.serialize(FILENAME, expectedTeams);

        StringBuilder resultReader1 = new StringBuilder();
        FileInputStream fstream = new FileInputStream(FILENAME);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        while ((strLine = br.readLine()) != null) {
            resultReader1.append(strLine).append("\n");
        }

        assertEquals(stringBuilder.toString(), resultReader1.toString());
    }

    @Test
    public void FromFileToObjectTest() throws JAXBException{
        Entity newTeams = xmlConverter.deserialize(FILENAME, Teams.class);
        assertEquals(expectedTeams, newTeams);
    }
}
