package com.javacourse.cleancode;

/*
    //wrapper class for Course 1 Section 4: Clean Code

    //import to Main class with:
    import com.javacourse.cleancode.CleanCode; //or...
    import com.javacourse.cleancode.*;

    //call from main() method with:
    com.javacourse.cleancode.CleanCode.cleanCodeExamples(); //or...
    CleanCode.cleanCodeExamples();
*/

public class CleanCode {

    //up to 10 lines of code per method is ideal
    //up to 20 is barely acceptable
    //more than 20 is BAD CODE

    //best practices for refactoring:
    //only lines of code that are tightly related should be together
    //repetitive patterns can become parameterized loops or methods

    public static void greet(String name, String last_name) {

        System.out.println( greetMessage(name, last_name) );
    }
    //greet

    public static String greetMessage(String name, String last_name) {

        return "hello " + name + " " + last_name;
    }
    //greetMessage

    public static void cleanCodeExamples() {

        System.out.println("\n----------------------------------------");
        System.out.println("Creating Methods\n");

        greet("John", "Smith");
        greet("Average", "Joe");
        greet("Jane", "Doe");
    }
    //cleanCodeExamples
}
//CleanCode

//eof
