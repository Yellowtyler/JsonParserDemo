package com.vtb.jsonparser.core;

import com.vtb.jsonparser.core.util.Converter;
import com.vtb.jsonparser.core.util.FileWorker;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public class MainApp {

    public static int findIndex(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equalsIgnoreCase("xml-json") ||
                    args[i].equalsIgnoreCase("json-xml"))
                return i;
        }
        return -1;
    }

    public static void run(int index, String[] args) {
        Converter converter = new Converter();
        List<String> input;
        List<String> output;
        String typeConvert = args[index];
        String directory = args[index + 1];
        String[] files = new String[args.length - index];
        System.arraycopy(args, index, files, 0, args.length - index);
        if (typeConvert.equalsIgnoreCase("xml-json")) {
            input = FileWorker.findFiles(typeConvert, directory, files);
            output = FileWorker.convertFiles("json", input);
            converter.convertXmlJson(input, output);
        }
        if (typeConvert.equalsIgnoreCase("json-xml")) {
            input = FileWorker.findFiles(typeConvert, directory, files);
            output = FileWorker.convertFiles("xml", input);
            converter.convertJsonXml(input, output);
        }
    }

    public static void main(String[] args) throws JAXBException, IOException {
        int index = findIndex(args);
        if (index >= 0) run(index, args);
        else System.out.println("Неверно введены параметры");
    }
}
