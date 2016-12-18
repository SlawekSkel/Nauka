package com.nauka.service;

import com.nauka.model.Coder;
import com.nauka.model.Dictionary;

/**
 * Created by LENOVO on 2016-12-14.
 */
public class App {

    public static void main(String[] args) {

        String numberToRemember = "2302749372926809711515325253676573543432";
        String result;

        FileService fileServiceA = new FileService();
        FileService fileServiceB = new FileService();
        FileService fileServiceC = new FileService();
        //slowniki A B C
//        fileServiceA.createFile("test1.txt");
//        fileServiceB.createFile("test2.txt");
        fileServiceA.createFile("A");
        fileServiceB.createFile("B");
        fileServiceC.createFile("C");

        Dictionary dictionary = new Dictionary();
        dictionary.addWordSet(fileServiceA.getDictionarySet());

        Coder coder = new Coder();


//        result = dictionary.getDictionaryMap().get(numberToRemember);

        dictionary.addWordSet(fileServiceB.getDictionarySet());


//        result = dictionary.getDictionaryMap().get(numberToRemember);

        dictionary.addWordSet(fileServiceC.getDictionarySet());

        result = coder.getCodedText(numberToRemember, dictionary);
//        result = dictionary.getDictionaryMap().get(numberToRemember);


    }
}
