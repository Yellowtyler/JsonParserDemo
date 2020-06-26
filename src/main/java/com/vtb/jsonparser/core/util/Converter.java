package com.vtb.jsonparser.core.util;

import com.vtb.jsonparser.core.entities.Teams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public class Converter {
    private static final Logger logger = LogManager.getLogger(JsonConverter.class);

    public void convertXmlJson(String input, String output) {
        Object object = null;
        JsonConverter jsonConverter = new JsonConverter();
        XmlConverter xmlConverter = new XmlConverter();
        try {
            object = xmlConverter.deserialize(input, Teams.class);
            jsonConverter.serialize(output, object);
        } catch (JAXBException exception) {
            logger.warn("Ошибка конвертирования");
            logger.warn(exception.getMessage());
        }

    }

    public void convertJsonXml(String input, String output) {
        Object object = null;
        JsonConverter jsonConverter = new JsonConverter();
        XmlConverter xmlConverter = new XmlConverter();
        try {
            object = jsonConverter.deserialize(input, Teams.class);
            xmlConverter.serialize(output, object);
        } catch (IOException exception) {
            logger.warn("Ошибка конвертирования");
            logger.warn(exception.getMessage());
        }
    }

    /*private static final List<Class> classes = new ArrayList<>();
    static {
        classes.add(Label.class);
        classes.add(Status.class);
        classes.add(Student.class);
        classes.add(Task.class);
        classes.add(Team.class);
    }

    public void convertXmlJson(List<String> input, List<String> output) {
        Object object = null;
        JsonConverter jsonConverter = new JsonConverter();
        XmlConverter xmlConverter = new XmlConverter();
        for(int i = 0; i < input.size(); i++) {
            logger.info("Конвертирование файла " + input.get(i) + " в " + output.get(i));
            for(Class cl : classes) {
                try {
                    object = xmlConverter.deserialize(input.get(i), cl);
                    jsonConverter.serialize(output.get(i), object);
                    break;
                } catch (JAXBException exception) {
                    logger.warn("Ошибка конвертирования");
                    logger.warn(exception.getMessage());
                }
            }
        }
    }

    public void convertJsonXml(List<String> input, List<String> output) {
        Object object = null;
        JsonConverter jsonConverter = new JsonConverter();
        XmlConverter xmlConverter = new XmlConverter();
        for(int i = 0; i < input.size(); i++) {
            logger.info("Конвертирование файла " + input.get(i) + " в " + output.get(i));
            for(Class cl : classes) {
                try {
                    object = jsonConverter.deserialize(input.get(i), cl);
                    xmlConverter.serialize(output.get(i), object);
                    break;
                } catch (IOException exception) {
                    logger.warn("Ошибка конвертирования");
                    logger.warn(exception.getMessage());
                }
            }
        }
    }*/
}
