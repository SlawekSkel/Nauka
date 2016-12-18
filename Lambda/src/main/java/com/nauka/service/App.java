package com.nauka.service;

import com.nauka.model.Coder;
import com.nauka.model.Dictionary;
import com.nauka.model.SpaceType;
import net.minidev.json.JSONObject;

import java.util.Iterator;
import java.util.Map;


/**
 * Created by LENOVO on 2016-12-14.
 */
public class App {

    public static void main(String[] args) {

        //TODO zapis do jsona i odczyt
        String numberToRemember = "807";
        String result;

        //TODO: doprowadzenie żeby był tylko 1 fileservice
        FileService fileServiceA = new FileService();
        FileService fileServiceB = new FileService();
        FileService fileServiceC = new FileService();

        fileServiceA.createFile("A");
        fileServiceB.createFile("B");
        fileServiceC.createFile("C");

        Dictionary dictionary = new Dictionary();
        dictionary.addWordSet(fileServiceA.getDictionarySet());
        dictionary.addWordSet(fileServiceB.getDictionarySet());
        dictionary.addWordSet(fileServiceC.getDictionarySet());

        Coder coder = new Coder();
        coder.setSpaceType(SpaceType._UNDER);
        result = coder.getCodedText(numberToRemember, dictionary);
//        result = dictionary.getDictionaryMap().get(numberToRemember);

        fileServiceA.saveFile("Result", result, false);
        fileServiceA.saveFile("Result", coder.getUnCodedText(), true);

        JSONObject json = new JSONObject();
        json.putAll(dictionary.getDictionaryMap());


        org.json.JSONObject json1 = new org.json.JSONObject();

        Iterator iterator = dictionary.getDictionaryMap().entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,String> pair = (Map.Entry)iterator.next();
            json1.append( pair.getKey(), pair.getValue());
        }
        result = JsonFormatter.format(json1);
//        result = json.toJSONString();
        fileServiceA.saveFile("Result",result, true);

    }
}
