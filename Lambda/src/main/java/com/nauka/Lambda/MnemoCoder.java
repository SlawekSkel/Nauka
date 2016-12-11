package com.nauka.Lambda;

import com.nauka.model.Coder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by LENOVO on 2016-12-11.
 */
public class MnemoCoder {

    public static void main(String[] args) {


        Coder coder = new Coder();
//        coder.setUnCodedText("1789 5118 8548");

        System.out.println(coder.getCodedText("1789 5118 8548"));


    }

}
