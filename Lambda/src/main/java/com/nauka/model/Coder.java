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

    private String codedText;

    private HashMap<String, String> objectCodeBook;
    private HashMap<String, String> consonantCodeBook;

    public Coder() {
        setBooks();
    }

    public String coding(String textToCode, CoderType type) {

        List<String> textToCodeList = new ArrayList<>(Arrays.asList(textToCode.split("")));
        List<String> codedList = new ArrayList<>();
        String before, next;
        next = "";

        if (type.equals(CoderType.OBJECT)) {
            codedList = textToCodeList.stream()
                    .map(x -> codeObject(x))
                    .collect(Collectors.toList());

        } else if (type.equals(CoderType.CONSONANT)) {
            for (int i = 0; i < textToCodeList.size(); i++) {

                if (i + 1 < textToCodeList.size()) next = textToCodeList.get(i + 1);
                if (i == 0) {
                    before = "";
                } else {
                    before = textToCodeList.get(i - 1);
                }
                codedList.add(codeConsonant(textToCodeList.get(i), before, next));
            }


        }

        StringBuilder stringBuilder = new StringBuilder();
        codedList.forEach(item -> {
            stringBuilder.append(item);
        });

        codedText = stringBuilder.toString();
        return codedText;
    }

    public String getCodedText(String textToCode, Dictionary dictionary) {

        List<String> resultTextList = new ArrayList<>();
        StringBuilder fragmentBuilder = new StringBuilder();
        StringBuilder workingTexOn = new StringBuilder(textToCode);

        String tempWord;
        int max = 10;
        boolean searchingCode = true;

        while (searchingCode) {

            if (workingTexOn.length() < max) {
                max = workingTexOn.length();

            } else if (workingTexOn.length() == 0) {
                searchingCode = false;
                continue;
            }

            fragmentBuilder.append(workingTexOn.substring(0, max));

            for (int i = max; i > 0; i--) {
                tempWord = dictionary.getDictionaryMap().get(fragmentBuilder.toString());
                if (tempWord == null) {
                    fragmentBuilder.deleteCharAt(i - 1);
                } else {
                    resultTextList.add(tempWord);
                    fragmentBuilder.delete(0, fragmentBuilder.length());
                    workingTexOn.delete(0, i);
                    i = -1;
                }
            }
        }

        return resultTextList.toString();
    }


    private String codeObject(String sign) {

        if (!sign.equals(" ") && objectCodeBook.containsKey(sign.toLowerCase())) {
            sign = objectCodeBook.get(sign);
        } else {
            return "";
        }
        return sign;
    }

    private String codeConsonant(String sign, String before, String next) {

        sign = sign.toLowerCase();
        before = before.toLowerCase();
        next = next.toLowerCase();

        if (!sign.equals(" ") && consonantCodeBook.containsKey(sign)) {

            if (sign.equals("z")) {
                if (before.equals("s") || before.equals("c") || before.equals("r") || before.equals("d")) return "";
            } else if (sign.equals("s")) {
                if (next.equals("z")) return "";
            } else if (sign.equals("c")) {
                if (next.equals("z")) return "";
            } else if (sign.equals("r")) {
                if (next.equals("z")) return "";
            } else if (sign.equals("d")) {
                if (next.equals("z") || next.equals("ż") || next.equals("ź")) return "";
            } else if (sign.equals("d")) {
                if (next.equals("z")) return "";
            }
            sign = consonantCodeBook.get(sign);
        } else {
            return "";
        }
        return sign;
    }

    private void setBooks() {

        objectCodeBook = new HashMap<>();
        objectCodeBook.put("0", "Dziura");
        objectCodeBook.put("1", "Miecz");
        objectCodeBook.put("2", "Łabędź");
        objectCodeBook.put("3", "Dupa");
        objectCodeBook.put("4", "Krzesło");
        objectCodeBook.put("5", "Hak");
        objectCodeBook.put("6", "Słoń");
        objectCodeBook.put("7", "Urwisko");
        objectCodeBook.put("8", "Bałwan");
        objectCodeBook.put("9", "Balon");

        objectCodeBook.put("Dziura", "0");
        objectCodeBook.put("Miecz", "1");
        objectCodeBook.put("Łabędź", "2");
        objectCodeBook.put("Dupa", "3");
        objectCodeBook.put("Krzesło", "4");
        objectCodeBook.put("Hak", "5");
        objectCodeBook.put("Słoń", "6");
        objectCodeBook.put("Urwisko", "7");
        objectCodeBook.put("Bałwan", "8");
        objectCodeBook.put("Balon", "9");

        consonantCodeBook = new HashMap<>();
        consonantCodeBook.put("0", "s");
        consonantCodeBook.put("1", "t");
        consonantCodeBook.put("2", "n");
        consonantCodeBook.put("3", "m");
        consonantCodeBook.put("4", "r");
        consonantCodeBook.put("5", "l");
        consonantCodeBook.put("6", "j");
        consonantCodeBook.put("7", "k");
        consonantCodeBook.put("8", "w");
        consonantCodeBook.put("9", "p");

        consonantCodeBook.put("s", "0");
        consonantCodeBook.put("z", "0");
        consonantCodeBook.put("t", "1");
        consonantCodeBook.put("d", "1");
        consonantCodeBook.put("n", "2");
        consonantCodeBook.put("m", "3");
        consonantCodeBook.put("r", "4");
        consonantCodeBook.put("l", "5");
        consonantCodeBook.put("t", "5");
        consonantCodeBook.put("j", "6");
        consonantCodeBook.put("k", "7");
        consonantCodeBook.put("g", "7");
        consonantCodeBook.put("w", "8");
        consonantCodeBook.put("p", "9");
        consonantCodeBook.put("b", "9");

    }

}
