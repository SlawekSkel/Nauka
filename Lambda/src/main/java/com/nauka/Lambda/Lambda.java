package com.nauka.Lambda;


import java.io.File;
import java.io.FilenameFilter;

public class Lambda {


    static String location = "D:\\Programowanie\\Git\\Nauka\\Lambda\\src\\main\\resources";

    static File dir = new File(location);

//    static String[] filelist = dir.list( new FilenameFilter() {
//        @Override
//        public boolean accept(File dir, String name) {
//            return name.endsWith(".txt");
//        }
//    });

    static  String [] filelist = dir.list( (f,s) -> { return s.endsWith(".txt"); } );

    public static void main(String[] args) {

        for (String str : filelist) {
            System.out.println(str);
        }


    }


}
