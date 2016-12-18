package com.nauka.service;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by LENOVO on 2016-12-13.
 */
public class FileService {

    final static Logger LOG = Logger.getLogger(FileService.class);

    private String fileName = "test";
    private String defaultFileLocation = "D:\\Programowanie\\Git\\Nauka\\Lambda\\src\\main\\resources\\";

    public void setDefaultFileLocation(String defaultFileLocation) {
        this.defaultFileLocation = defaultFileLocation;
    }

    public boolean createFile(String fileName) {

        // "D:\\Programowanie\\Git\\Nauka\\Lambda\\src\\main\\resources\\slownik"
        this.fileName = defaultFileLocation + fileName;
        File file = new File(this.fileName);

        boolean fileExists = file.exists();
        if (!fileExists) {
            try {
                LOG.info("Creating a file: ");
                fileExists = file.createNewFile();
            } catch (IOException e) {
                System.out.println("File could not be opened");
            }
        }
        if (fileExists) LOG.info("Plik " + fileName + " istnieje");

        return fileExists;
    }

    public Set<String> getDictionarySet() {

        List<String> rowDictionaryList = new ArrayList<>();
        Set<String> DictionarySet = new TreeSet<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "ISO-8859-2"));
//            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "Windows-1250"));
            String tmp = null;
            while ((tmp = reader.readLine()) != null) {
                rowDictionaryList.add(tmp);
//                System.out.println(tmp);//
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        rowDictionaryList = cleanInputLine(rowDictionaryList);
        DictionarySet.addAll(rowDictionaryList);

        return DictionarySet;
    }

    private List<String> cleanInputLine(List<String> rowDictionary) {

        StringBuilder stringBuilder = new StringBuilder();
        char tempChar;

        for (int i = 0; i < rowDictionary.size(); i++) {
            for (int j = 0; j < rowDictionary.get(i).length(); j++) {

                tempChar = rowDictionary.get(i).charAt(j);
                if (tempChar == '/' || tempChar == ' ' || tempChar == '\t') {

                    j = rowDictionary.get(i).length();
                    rowDictionary.set(i, stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.length());
                    continue;
                }
                stringBuilder.append(tempChar);

            }
        }

        return rowDictionary;
    }


}
