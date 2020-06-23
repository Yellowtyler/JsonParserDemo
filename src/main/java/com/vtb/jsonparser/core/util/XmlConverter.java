package com.vtb.jsonparser.core.util;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import java.io.File;
import java.io.FileNotFoundException;

public class XmlConverter {


    public static <T> void toXML(String fileName, T object, Class<T> c) throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(c);
        context.createJAXBIntrospector();
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(object, new File(fileName));
    }

    public static <T> T toJavaObject(String fileName, Class<T> c) throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(c);
        Unmarshaller un = context.createUnmarshaller();
        Binder ji = context.createBinder();
        return (T) un.unmarshal(new File(fileName));

    }

}
