package org;

import org.utility.config.ConfigReader;
import org.utility.security.Security;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        HashMap<String, String> data = ConfigReader.readConfig("system/Master.config");
        System.out.println(data.toString());
    }

}
