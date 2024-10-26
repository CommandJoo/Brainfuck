package de.johannes.util;

import java.io.*;

public class FileUtil {

    public static String readFile(String location) throws Exception{
        File file = new File(location);
        if(!file.exists()) throw new IOException("File does not exist");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        StringBuilder str = new StringBuilder();
        String line = "";
        while((line = reader.readLine()) != null) {
            str.append(line);
        }
        reader.close();
        return str.toString();
    }



}
