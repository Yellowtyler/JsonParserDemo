package com.vtb.jsonparser.core.util;

import com.vtb.jsonparser.core.entities.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    private static final Logger logger = LogManager.getLogger(JsonConverter.class);

    private static final List<Class> classes = new ArrayList<>();
    static {
        classes.add(Label.class);
        classes.add(Status.class);
        classes.add(Student.class);
        classes.add(Task.class);
        classes.add(Team.class);
    }

    public static void convertXmlJson(List<String> input, List<String> output) {
        Object object = null;
        for(int i = 0; i < input.size(); i++) {
            logger.info("Конвертирование файла " + input.get(i) + " в " + output.get(i));
            for(Class cl : classes) {
                try {
                    object = XmlConverter.toJavaObject(input.get(i), cl);
                    JsonConverter.toJSON(output.get(i), object);
                    break;
                }
                catch (Exception ignored) {
                    logger.warn("Ошибка конвертирования");
                    logger.warn(ignored.getMessage());
                }
            }
        }
    }

    public static void convertJsonXml(List<String> input, List<String> output) {
        Object object = null;
        for(int i = 0; i < input.size(); i++) {
            logger.info("Конвертирование файла " + input.get(i) + " в " + output.get(i));
            for(Class cl : classes) {
                try {
                    object = JsonConverter.toJavaObject(input.get(i), cl);
                    XmlConverter.toXML(output.get(i), object, cl);
                    break;
                }
                catch (Exception ignored) {
                    logger.warn("Ошибка конвертирования");
                    logger.warn(ignored.getMessage());
                }
            }
        }
    }
}
