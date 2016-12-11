package com.nauka.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by LENOVO on 2016-12-11.
 */
public class Coder {

    private String unCodedText;
    private String codedText;

    private HashMap<Integer,String> codeBook;

    public Coder() {
        codeBook = new HashMap<>();
        codeBook.put(1,"Miecz");
        codeBook.put(2,"Łabędź");
        codeBook.put(3,"Dupa");
        codeBook.put(4,"Krzesło");
        codeBook.put(5,"Hak");
        codeBook.put(6,"Słoń");
        codeBook.put(7,"Urwisko");
        codeBook.put(8,"Bałwan");
        codeBook.put(9,"Balon");
        codeBook.put(0,"Dziura");
    }
    public String getValue(Integer key) {
        return codeBook.get(key);
    }
    public void setValue(Integer key,String value) {
        this.codeBook.put(key, value);
    }


    public String getUnCodedText() {
        return unCodedText;
    }

    private void setUnCodedText(String unCodedText) {
        this.unCodedText = unCodedText;
    }

    public String getCodedText(String textToCode) {

        List<String> textToCodeList = new ArrayList<>(Arrays.asList(textToCode.split("")));

        List<String> codedList = textToCodeList.stream()
                .map(x -> code(x))
//                .peek(System.out::println)
//                .filter(x-> !x.equals(" "))
                .collect(Collectors.toList());

        StringBuilder stringBuilder = new StringBuilder();
        codedList.forEach(item -> {
            stringBuilder.append(item);
            stringBuilder.append(" ");
        });

        codedText = stringBuilder.toString();
        return codedText;
    }

    public void setCodedText(String codedText) {
        this.codedText = codedText;
    }

    private String code(String sign){

        if(!sign.equals(" ") && codeBook.containsKey(Integer.parseInt(sign))){
          sign = codeBook.get(Integer.parseInt(sign));
        }
        return sign;
    }

}
