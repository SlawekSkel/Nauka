package com.nauka.service;

import com.nauka.model.Coder;
import com.nauka.model.Dictionary;

/**
 * Created by LENOVO on 2016-12-14.
 */
public class App {

    public static void main(String[] args) {

        //TODO zapis do jsona i odczyt
        String numberToRemember = "8079798767686767688089077743023882357";
        String result;

        //TODO: doprowadzenie żeby był tylko 1 fileservice
        FileService fileServiceA = new FileService();
        FileService fileServiceB = new FileService();
        FileService fileServiceC = new FileService();
        //slowniki A B C
//        fileServiceA.createFile("test1.txt");
//        fileServiceB.createFile("test2.txt");
//        fileServiceA.createFile("TestowySlownik");
        fileServiceA.createFile("A");
        fileServiceB.createFile("B");
        fileServiceC.createFile("C");

        Dictionary dictionary = new Dictionary();
        dictionary.addWordSet(fileServiceA.getDictionarySet());
        dictionary.addWordSet(fileServiceB.getDictionarySet());
        dictionary.addWordSet(fileServiceC.getDictionarySet());

        Coder coder = new Coder();
        result = coder.getCodedText(numberToRemember, dictionary);
//        result = dictionary.getDictionaryMap().get(numberToRemember);

        fileServiceA.saveFile("Result", result, false);
        fileServiceA.saveFile("Result", coder.getUnCodedText(), true);

    }
}
