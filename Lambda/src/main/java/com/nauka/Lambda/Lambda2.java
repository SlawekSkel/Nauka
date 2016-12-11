package com.nauka.Lambda;

import com.nauka.model.Person;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by LENOVO on 2016-12-11.
 */
public class Lambda2 {

    public static void main(String[] args) {

        Function<String,String> function = (String str) -> str.toLowerCase().trim();
        Consumer<String> consumer = (String str) -> System.out.println(str);
        Predicate<Integer> predicate = (Integer input) -> input > 8;
        Supplier<Person> supplier = () ->  new Person("Jacek","Nowak",67);

        String orginal = "  WIELKI NAPIS";

        orginal = function.apply(orginal);
        consumer.accept(orginal);
        boolean IsBiggerThenTen  = predicate.test(7);

        consumer.accept(String.valueOf(IsBiggerThenTen) );

        Person somePerson = supplier.get();
        consumer.accept(somePerson.toString());



    }

}
