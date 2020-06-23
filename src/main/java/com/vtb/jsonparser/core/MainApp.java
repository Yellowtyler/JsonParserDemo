package com.vtb.jsonparser.core;

import com.vtb.jsonparser.core.util.Converter;
import com.vtb.jsonparser.core.util.FileWorker;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;

public class MainApp {


    public static void main(String[] args) throws JAXBException, FileNotFoundException {

        List<String> input = FileWorker.findFiles("xml-json", ".", new String[]{"student.xml"});
        List<String> output = FileWorker.convertFiles("json", input);
        Converter.convertXmlJson(input, output);

    }
}
