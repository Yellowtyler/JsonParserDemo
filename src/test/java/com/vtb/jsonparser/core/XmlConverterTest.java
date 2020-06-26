package com.vtb.jsonparser.core;

import com.vtb.jsonparser.core.entities.*;
import com.vtb.jsonparser.core.util.XmlConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XmlConverterTest {
    final String FILENAME = "data.xml";
    List<Team> teams = new ArrayList<>();
    StringBuilder stringBuilder = new StringBuilder();
//    String expectedResult = "";

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

        stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<team>\n" +
                "    <id>1</id>\n" +
                "    <name>team1</name>\n" +
                "    <students>\n" +
                "        <student>\n" +
                "            <id>1</id>\n" +
                "            <firstName>Ivan</firstName>\n" +
                "            <secondName>Ivanov</secondName>\n" +
                "            <phone>+6225525</phone>\n" +
                "            <email>safsf@afas.ru</email>\n" +
                "            <tasks>\n" +
                "                <task>\n" +
                "                    <id>1</id>\n" +
                "                    <name>task 1</name>\n" +
                "                    <status>DONE</status>\n" +
                "                    <labels>\n" +
                "                        <label>\n" +
                "                            <name>Java programming</name>\n" +
                "                        </label>\n" +
                "                        <label>\n" +
                "                            <name>Version control</name>\n" +
                "                        </label>\n" +
                "                    </labels>\n" +
                "                    <description>sfafafa</description>\n" +
                "                </task>\n" +
                "                <task>\n" +
                "                    <id>2</id>\n" +
                "                    <name>task 2</name>\n" +
                "                    <status>DONE</status>\n" +
                "                    <labels>\n" +
                "                        <label>\n" +
                "                            <name>Java programming</name>\n" +
                "                        </label>\n" +
                "                        <label>\n" +
                "                            <name>Version control</name>\n" +
                "                        </label>\n" +
                "                    </labels>\n" +
                "                    <description>sfasfasfafs</description>\n" +
                "                </task>\n" +
                "            </tasks>\n" +
                "        </student>\n" +
                "        <student>\n" +
                "            <id>2</id>\n" +
                "            <firstName>Igor</firstName>\n" +
                "            <secondName>Igorov</secondName>\n" +
                "            <phone>+722245525</phone>\n" +
                "            <email>igor@mail.ru</email>\n" +
                "            <tasks>\n" +
                "                <task>\n" +
                "                    <id>1</id>\n" +
                "                    <name>task 1</name>\n" +
                "                    <status>DONE</status>\n" +
                "                    <labels>\n" +
                "                        <label>\n" +
                "                            <name>Java programming</name>\n" +
                "                        </label>\n" +
                "                        <label>\n" +
                "                            <name>Version control</name>\n" +
                "                        </label>\n" +
                "                    </labels>\n" +
                "                    <description>sfafafa</description>\n" +
                "                </task>\n" +
                "                <task>\n" +
                "                    <id>2</id>\n" +
                "                    <name>task 2</name>\n" +
                "                    <status>DONE</status>\n" +
                "                    <labels>\n" +
                "                        <label>\n" +
                "                            <name>Java programming</name>\n" +
                "                        </label>\n" +
                "                        <label>\n" +
                "                            <name>Version control</name>\n" +
                "                        </label>\n" +
                "                    </labels>\n" +
                "                    <description>sfasfasfafs</description>\n" +
                "                </task>\n" +
                "            </tasks>\n" +
                "        </student>\n" +
                "    </students>\n" +
                "    <tasks>\n" +
                "        <task>\n" +
                "            <id>1</id>\n" +
                "            <name>task 1</name>\n" +
                "            <status>DONE</status>\n" +
                "            <labels>\n" +
                "                <label>\n" +
                "                    <name>Java programming</name>\n" +
                "                </label>\n" +
                "                <label>\n" +
                "                    <name>Version control</name>\n" +
                "                </label>\n" +
                "            </labels>\n" +
                "            <description>sfafafa</description>\n" +
                "        </task>\n" +
                "        <task>\n" +
                "            <id>2</id>\n" +
                "            <name>task 2</name>\n" +
                "            <status>DONE</status>\n" +
                "            <labels>\n" +
                "                <label>\n" +
                "                    <name>Java programming</name>\n" +
                "                </label>\n" +
                "                <label>\n" +
                "                    <name>Version control</name>\n" +
                "                </label>\n" +
                "            </labels>\n" +
                "            <description>sfasfasfafs</description>\n" +
                "        </task>\n" +
                "    </tasks>\n" +
                "</team>\n");
    }

    @Test
    public void fromObjectToFileTest() throws JAXBException, IOException {
        XmlConverter.toXML(FILENAME, teams.get(0));
//        String result = Files.lines(Paths.get(FILENAME)).reduce("", String::concat);
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
    public void fromFileToObjectTest() throws JAXBException, FileNotFoundException {
        Team newTeam = XmlConverter.toJavaObject(FILENAME, Team.class);
        assertEquals(teams.get(0), newTeam);
    }
}
