package org.utility.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ConfigReader {

    public static HashMap<String,String> readConfig(String path) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line,key,value,subdata[];

        while(reader.ready()) {
            line = reader.readLine();
            if(!line.equals("") && line.charAt(0) == '#') {
                line = line.replaceAll("\\\s+", "");
                subdata = line.split(":");
                key = subdata[0].trim();
                value = subdata[1].trim();
                map.put(key,value);
            }
        }
        return map;
    }

}
