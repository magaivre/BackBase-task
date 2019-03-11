package com.backbase.datareaders;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author Maged.Mamdouh
 * Contains parser for json file and to get the test data
 */
public class JsonClass {

    JSONObject jsonObj;

    JSONParser parser;

    HashMap<String, String> objectData;

    public JsonClass() {
        try {
            parser = new JSONParser();
            jsonObj = (JSONObject) parser.parse(new FileReader(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
                    + File.separator + "resources" + File.separator + "Data.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public HashMap<String, String> getData(String object) {
        objectData = new HashMap<String, String>();
        objectData = ((HashMap<String, String>) jsonObj.get(object));
        return objectData;
    }

}
