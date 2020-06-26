package com.vtb.jsonparser.core;

import com.vtb.jsonparser.core.exceptions.NameFileException;
import com.vtb.jsonparser.core.util.Converter;
import com.vtb.jsonparser.core.util.FileWorker;
import com.vtb.jsonparser.core.util.XsdGenerator;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class MainApp {

    public int findIndex(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equalsIgnoreCase("xml-json") ||
                    args[i].equalsIgnoreCase("json-xml"))
                return i;
        }
        return -1;
    }

    /*public static void run(int index, String[] args) {
        List<String> input;
        List<String> output;
        String typeConvert = args[index];
        String directory = args[index + 1];
        String[] files = new String[args.length - index];
        System.arraycopy(args, index, files, 0, args.length - index);
        if (typeConvert.equalsIgnoreCase("xml-json")) {
            input = FileWorker.findFiles(typeConvert, directory, files);
            output = FileWorker.convertFiles("json", input);
            Converter.convertXmlJson(input, output);
        }
        if (typeConvert.equalsIgnoreCase("json-xml")) {
            input = FileWorker.findFiles(typeConvert, directory, files);
            output = FileWorker.convertFiles("xml", input);
            Converter.convertJsonXml(input, output);
        }
    }*/

    public void run(int index, String[] args) throws NameFileException {
        String typeConvert = args[index];
        String filename = args[index + 1];
        FileWorker fileWorker = new FileWorker();
        Converter converter = new Converter();
        if (typeConvert.equalsIgnoreCase("xml-json")) {
            String output = fileWorker.convertFile("json", filename);
            converter.convertXmlJson(filename, output);
        }
        if (typeConvert.equalsIgnoreCase("json-xml")) {
            String output = fileWorker.convertFile("xml", filename);
            converter.convertJsonXml(filename, output);
        }
    }

    public static void main(String[] args) throws JAXBException, IOException {
        XsdGenerator generateXsd = new XsdGenerator();
        generateXsd.generateXsd();

        MainApp mainApp = new MainApp();
        int index = mainApp.findIndex(args);
        try {
            if (index >= 0 && args.length >= 2) mainApp.run(index, args);
        } catch (NameFileException e) {
            System.out.println("Неверно введены параметры");
            e.printStackTrace();
        }
    }
}
