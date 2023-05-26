package com.javacourse.controlflow;

import java.util.Scanner;

/*
    //wrapper class for Course 1 Section 3: Control Flow

    //import to Main class with:
    import com.javacourse.controlflow.ControlFlow; //or...
    import com.javacourse.controlflow.*;

    //call from main() method with:
    com.javacourse.controlflow.ControlFlow.controlFlowExamples(); //or...
    ControlFlow.controlFlowExamples();
*/

public class ControlFlow {

    public static void fizzBuzz(int num) {
        if(num%5==0 && num%3==0) {
            System.out.print("FizzBuzz");
        }else if(num%5==0) {
            System.out.print("Fizz");
        }else if(num%3==0) {
            System.out.print("Buzz");
        }else {
            System.out.print(num);
        }
    }
    //fizzBuzz

    public static void fizzBuzzSeries(int num) {
        if(num < 1) return;
        int i = 1;
        while(true) {
            fizzBuzz(i++);
            if(i <= num) System.out.print(", ");
            else break;
        }
        //while
    }
    //fizzBuzzSeries

    public static void controlFlowExamples() {

        System.out.println("\n----------------------------------------");
        System.out.println("Comparison Operators\n");

        int x = 1;
        int y = 1;
        System.out.println(x == y);
        System.out.println(x != y);
        System.out.println(x > y);
        System.out.println(x >= y);
        System.out.println(x < y);
        System.out.println(x <= y);

        System.out.println("\n----------------------------------------");
        System.out.println("Logic Operators\n");

        //java does short-circuit evaluation

        int temperature = 22;
        boolean isWarm = temperature > 20 && temperature < 30; //AND

        boolean hasHighIncome = true;
        boolean hasGoodCredit = true;
        boolean isEligible = hasHighIncome || hasGoodCredit; //OR

        boolean hasCriminalRecord = false;
        boolean isInnocent = !hasCriminalRecord; //NOT

        System.out.println(isWarm);
        System.out.println(hasHighIncome);
        System.out.println(isInnocent);

        System.out.println("\n----------------------------------------");
        System.out.println("If statements\n");

        int temp = 32;
        if (temp > 30) {
            System.out.println("It's a hot day");
        } else if (temp > 20) {
            System.out.println("Beautiful day");
        } else {
            System.out.println("It's a cold day");
        }

        //it's possible to write empty-body if statements like:
        if(true);

        //or like:
        if(true);
        else;

        //or even:
        if(true);
        else if(false);
        else;

        System.out.println("\n----------------------------------------");
        System.out.println("Simplified If statements\n");

        //simplified if statement (example 1):
        //this assigns literally true/false to a variable
        //depending on a condition

        int income = 120_000;

        //naive form:
        if (income > 100_000) {
            hasHighIncome = true;
        } else {
            hasHighIncome = false;
        }

        //a little bit more elegant
        hasHighIncome = false;
        if (income > 100_000) {
            hasHighIncome = true;
        }

        //most elegant
        //(best way in this case, since the values being assigned are literally true/false)
        hasHighIncome = (income > 100_000);

        //simplified if statement (example 2):
        //this assigns something to a variable
        //depending on a condition

        String passenger_class = "";

        //naive form:
        if (income > 100_000) {
            passenger_class = "luxury";
        } else {
            passenger_class = "economy";
        }

        //a little bit more elegant
        passenger_class = "economy";
        if (income > 100_000) {
            passenger_class = "luxury";
        }

        //most elegant (ternary)
        //(best way in this case, since the values being assigned are NOT literally true/false)
        passenger_class = (income > 100_000) ? "luxury" : "economy";

        System.out.println(income);
        System.out.println(hasHighIncome);
        System.out.println(passenger_class);

        System.out.println("\n----------------------------------------");
        System.out.println("Switch/case statements\n");

        String role = "admin";

        switch(role) {

            case "admin": {
                System.out.println("You're an admin");
                break;
            }
            case "moderator": {
                System.out.println("You're a moderator");
                break;
            }
            default: {
                System.out.println("You're a guest");
                break;
            }
            //default
        }
        //switch

        System.out.println("\n----------------------------------------");
        System.out.println("Exercise: Fizzbuzz\n");

        Scanner scanner = new Scanner(System.in);
        System.out.print("gimme number: ");
        //int num = scanner.nextInt(); //use this for testing fizzbuzz
        int num = 20; //use this when not testing fizzbuzz
        fizzBuzzSeries(num);

        System.out.println("\n----------------------------------------");
        System.out.println("For loops\n");

        for(int i=0; i<5; i++) {
            System.out.println("Hello World " + i);
        }
        //for

        System.out.println("\n----------------------------------------");
        System.out.println("While loops\n");

        int c = 0;
        while (c < 5) {
            System.out.println("Hello World " + c);
            c++;
        }
        //while

        System.out.println();

        String input = "";
        input = "quit"; //comment this if testing this loop
        while (!input.equalsIgnoreCase("quit")) {
            System.out.print("gimme text (W): ");
            input = scanner.nextLine().trim();
            System.out.println("you wrote: " + input);
        }
        //while

        //it's possible to write empty-body while statements like:
        while(!input.equalsIgnoreCase("quit"));

        System.out.println("\n----------------------------------------");
        System.out.println("Do-While loops\n");

        input = "";
        do {
            System.out.print("gimme text (DW): ");
            //input = scanner.nextLine().trim(); //use this if testing this loop
            input = "quit"; //use this if not testing this loop
            System.out.println("you wrote: " + input);
        } while (!input.equalsIgnoreCase("quit"));

        System.out.println("\n----------------------------------------");
        System.out.println("Break & Continue\n");

        Boolean runthis = true;
        runthis = false; //use this if NOT testing this loop
        input = "";
        while (runthis) {
            System.out.print("gimme text (BC): ");
            input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("quit")) break;
            if (input.equalsIgnoreCase("pass")) continue;
            System.out.println("you wrote: " + input);
        }
        //while

        System.out.println("\n----------------------------------------");
        System.out.println("For each\n");

        String[] fruits = {"apple", "mango", "orange"};

        //traverse array with indexes
        for(int i = 0; i < fruits.length; i++) {
            System.out.println(fruits[i]);
        }
        //for

        //traverse array with foreach
        for(String fruit : fruits) {
            System.out.println(fruit);
        }
        //for

        //foreach can traverse forward-only.
    }
    //controlFlowExamples
}
//ControlFlow

//eof
