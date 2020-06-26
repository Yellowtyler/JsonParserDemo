package com.vtb.jsonparser.core.util;

import com.vtb.jsonparser.core.entities.Teams;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class XsdGenerator {
    private static class MySchemaOutputResolver extends SchemaOutputResolver {

        public Result createOutput(String namespaceURI, String suggestedFileName) throws IOException {
            File file = new File("./TeamsDIR.xsd");
            StreamResult result = new StreamResult(file);
            result.setSystemId(file.toURI().toURL().toString());
            return result;
        }

    }

    public void generateXsd() throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Teams.class);
        SchemaOutputResolver sor = new MySchemaOutputResolver();
        jaxbContext.generateSchema(sor);
    }
}
