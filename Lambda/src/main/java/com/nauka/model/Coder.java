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

    private String unCodedText;

    private HashMap<String, String> objectCodeBook;

    private HashMap<String, String> consonantCodeBook;

    public Coder() {
        setBooks();
    }

    public String getUnCodedText() {
        return unCodedText;
    }

    public String coding(String textToCode, CoderType type, SpaceType space) {

        unCodedText = textToCode;
        List<String> textToCodeList = new ArrayList<>(Arrays.asList(unCodedText.split("")));
        List<String> codedList = new ArrayList<>();
        String before, next;
        next = "";

        if (type.equals(CoderType.OBJECT)) {
            codedList = textToCodeList.stream()
                    .map(x -> codeObject(x, space))
                    .collect(Collectors.toList());

        } else if (type.equals(CoderType.CONSONANT)) {
            for (int i = 0; i < textToCodeList.size(); i++) {

                if (i + 1 < textToCodeList.size()) next = textToCodeList.get(i + 1);
                if (i == 0) {
                    before = "";
                } else {
                    before = textToCodeList.get(i - 1);
                }
                codedList.add(codeConsonant(textToCodeList.get(i), before, next, space));
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

        unCodedText = textToCode;
        List<String> resultTextList = new ArrayList<>();
        List<String> comparingTextList = new ArrayList<>();
        StringBuilder fragmentBuilder = new StringBuilder();
        StringBuilder workingTexOn = new StringBuilder(unCodedText);

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
                    //to do comparing list
                    resultTextList.add(tempWord);
                    comparingTextList.add(coding(tempWord, CoderType.CONSONANT, SpaceType._UNDER));

                    fragmentBuilder.delete(0, fragmentBuilder.length());
                    workingTexOn.delete(0, i);
                    i = -1;
                }
            }
        }

        unCodedText = comparingTextList.toString();
        codedText = resultTextList.toString();
        return codedText;
    }


    private String codeObject(String sign, SpaceType space) {



        if (!sign.equals(" ") && objectCodeBook.containsKey(sign.toLowerCase())) {
            sign = objectCodeBook.get(sign);
        } else {
            return space.getSpace();
        }
        return sign;
    }

    private String codeConsonant(String inputSign, String before, String next, SpaceType space) {

        inputSign = inputSign.toLowerCase();
        before = before.toLowerCase();
        next = next.toLowerCase();
        String outputSign;

        if (!inputSign.equals(" ") && consonantCodeBook.containsKey(inputSign)) {

            if (inputSign.equals("z")) {
                if (before.equals("s") || before.equals("c") || before.equals("r") || before.equals("d")) return space.getSpace();
            } else if (inputSign.equals("s")) {
                if (next.equals("z")) return space.getSpace();
            } else if (inputSign.equals("c")) {
                if (next.equals("z")) return space.getSpace();
            } else if (inputSign.equals("r")) {
                if (next.equals("z")) return space.getSpace();
            } else if (inputSign.equals("d")) {
                if (next.equals("z") || next.equals("ż") || next.equals("ź")) return space.getSpace();
            } else if (inputSign.equals("d")) {
                if (next.equals("z")) return space.getSpace();
            }
            outputSign = consonantCodeBook.get(inputSign);
        } else {
            return space.getSpace();
        }
        return outputSign;
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
