package com.vtb.jsonparser.core.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class XmlConverter {
    private static final Logger logger = LogManager.getLogger(JsonConverter.class);

    public <T> void serialize(String fileName, T object) {
        logger.info("Сериализация в файл " + fileName);
        try {
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            context.createJAXBIntrospector();
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(object, new File(fileName));
        } catch (JAXBException ignored){
            logger.warn("Ошибка сериализации файла " + fileName);
            logger.warn(ignored.getMessage());
        }
    }

    public <T> T deserialize(String fileName, Class<T> c) throws JAXBException{
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
        //unmarshaller.setEventHandler(new );
        return (T) unmarshaller.unmarshal(new File(fileName));
    }
}
