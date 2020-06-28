package com.vtb.jsonparser.core.util;

import generated.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.lang.reflect.Field;
import java.security.spec.ECField;

public class XmlConverter {
    private static final Logger logger = LogManager.getLogger(JsonConverter.class);
    private final Integrator integrator = new Integrator();

    public <T> void serialize(String fileName, T object) {
        logger.info("Сериализация в файл " + fileName);
        try {
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            context.createJAXBIntrospector();
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(object, new File(fileName));
        } catch (JAXBException ignored) {
            logger.warn("Ошибка сериализации файла " + fileName);
            logger.warn(ignored.getMessage());
        }
    }

    public <T> T deserialize(String fileName, Class<T> c) throws JAXBException {
        logger.info("Десериализация файла " + fileName);
        JAXBContext context = JAXBContext.newInstance(c);
        Unmarshaller un = context.createUnmarshaller();
        return (T) un.unmarshal(new File(fileName));
    }

    public <T> T validateXsd(String fileXsdName, String fileName, Class<T> c) throws JAXBException, SAXException {
        JAXBContext context = JAXBContext.newInstance(c);

        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File(fileXsdName));

        Unmarshaller unmarshaller = context.createUnmarshaller();

        unmarshaller.setSchema(schema);
        unmarshaller.setEventHandler(new ValidationEventHandlerTeams());

        return (T) integrator.integrate((T) unmarshaller.unmarshal(new File(fileName)));
    }

    class ValidationEventHandlerTeams implements ValidationEventHandler {
        @Override
        public boolean handleEvent(ValidationEvent event) {
            System.out.println("\nEVENT");
            System.out.println("SEVERITY:  " + event.getSeverity());
            System.out.println("MESSAGE:  " + event.getMessage());
            System.out.println("LINKED EXCEPTION:  " + event.getLinkedException());
            System.out.println("LOCATOR");
            System.out.println("    LINE NUMBER:  " + event.getLocator().getLineNumber());
            System.out.println("    COLUMN NUMBER:  " + event.getLocator().getColumnNumber());
            System.out.println("    OFFSET:  " + event.getLocator().getOffset());
            System.out.println("    OBJECT:  " + event.getLocator().getObject());
            System.out.println("    NODE:  " + event.getLocator().getNode());
            System.out.println("    URL:  " + event.getLocator().getURL());
            return true;
        }
    }
}
