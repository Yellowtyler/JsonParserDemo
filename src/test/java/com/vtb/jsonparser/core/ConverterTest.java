package com.vtb.jsonparser.core;

import com.vtb.jsonparser.core.entities.*;
import com.vtb.jsonparser.core.util.Converter;
import com.vtb.jsonparser.core.util.JsonConverter;
import com.vtb.jsonparser.core.util.XmlConverter;
import org.apache.logging.log4j.core.config.plugins.convert.TypeConverterRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterTest {
    final String FILENAME_JSON = "team.json";
    final String FILENAME_XML = "team.xml";
    String expectedResultJson = "{\"id\":1,\"name\":\"team1\",\"students\":[{\"id\":1,\"firstName\":\"Ivan\",\"secondName\":\"Ivanov\",\"phone\":\"+6225525\",\"email\":\"safsf@afas.ru\",\"tasks\":[{\"id\":1,\"name\":\"task 1\",\"status\":\"done\",\"labels\":[{\"name\":\"Java programming\"},{\"name\":\"Version control\"}],\"description\":\"sfafafa\"},{\"id\":2,\"name\":\"task 2\",\"status\":\"done\",\"labels\":[{\"name\":\"Java programming\"},{\"name\":\"Version control\"}],\"description\":\"sfasfasfafs\"}]},{\"id\":2,\"firstName\":\"Igor\",\"secondName\":\"Igorov\",\"phone\":\"+722245525\",\"email\":\"igor@mail.ru\",\"tasks\":[{\"id\":1,\"name\":\"task 1\",\"status\":\"done\",\"labels\":[{\"name\":\"Java programming\"},{\"name\":\"Version control\"}],\"description\":\"sfafafa\"},{\"id\":2,\"name\":\"task 2\",\"status\":\"done\",\"labels\":[{\"name\":\"Java programming\"},{\"name\":\"Version control\"}],\"description\":\"sfasfasfafs\"}]}],\"tasks\":[{\"id\":1,\"name\":\"task 1\",\"status\":\"done\",\"labels\":[{\"name\":\"Java programming\"},{\"name\":\"Version control\"}],\"description\":\"sfafafa\"},{\"id\":2,\"name\":\"task 2\",\"status\":\"done\",\"labels\":[{\"name\":\"Java programming\"},{\"name\":\"Version control\"}],\"description\":\"sfasfasfafs\"}]}";
    StringBuilder expectedResultXML = new StringBuilder();
    List<Team> teams = new ArrayList<>();
    XmlConverter xmlConverter;
    JsonConverter jsonConverter;
    Converter converter;

    @BeforeEach
    public void init() {
        xmlConverter = new XmlConverter();
        jsonConverter = new JsonConverter();
        converter = new Converter();
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

        expectedResultXML.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
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
    public void fromXmltoJsonTest() throws IOException, JAXBException {
        File fileJson = new File(FILENAME_JSON);
        fileJson.createNewFile();

        File fileXml = new File(FILENAME_XML);
        fileXml.createNewFile();

        xmlConverter.serialize(fileXml.getName(), teams.get(0));

        /*
        // В будущем для проверки списка файлов
        List<String> output = new ArrayList<>();
        output.add(FILENAME_JSON);

        List<String> input = new ArrayList<>();
        input.add(FILENAME_XML);

        converter.convertXmlJson(input, output);*/
        converter.convertXmlJson(FILENAME_XML, FILENAME_JSON);

        String result = Files.lines(Paths.get(FILENAME_JSON)).reduce("", String::concat);
        fileJson.delete();
        fileXml.delete();
        assertEquals(expectedResultJson, result);
    }

    @Test
    public void fromJsonToXmlTest() throws IOException {
        File fileJson = new File(FILENAME_JSON);
        fileJson.createNewFile();

        File fileXml = new File(FILENAME_XML);
        fileXml.createNewFile();

        jsonConverter.serialize(fileJson.getName(), teams);

        /*
        // В будущем для проверки списка файлов
        List<String> input = new ArrayList<>();
        input.add(FILENAME_JSON);

        List<String> output = new ArrayList<>();
        output.add(FILENAME_XML);

        converter.convertJsonXml(input, output);*/
        converter.convertJsonXml(FILENAME_JSON, FILENAME_XML);

        StringBuilder resultReader = new StringBuilder();
        FileInputStream fstream = new FileInputStream(FILENAME_XML);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        while ((strLine = br.readLine()) != null){
            resultReader.append(strLine).append("\n");
        }

        fileJson.delete();
        fileXml.delete();
        assertEquals(expectedResultXML.toString(), resultReader.toString());
    }

}
