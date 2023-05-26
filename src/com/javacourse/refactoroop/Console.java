package com.javacourse.refactoroop;

import java.util.Scanner;

public class Console {

    //----------------------------------------------------------------------------------
    //fields

    //we make this final since it's never going to change
    private static final Scanner NUM_SCANNER = new Scanner(System.in);

    //----------------------------------------------------------------------------------
    //methods

    public static double readNumber(String prompt) {
        System.out.print(prompt);
        return NUM_SCANNER.nextDouble();
    }
    //readNumber

    public static double readNumber(String prompt, double lbound, double ubound) {

        double value = 0;

        while(true) {
            System.out.print(prompt + " (" + lbound + " to " + ubound + "): ");
            value = NUM_SCANNER.nextDouble();
            if(value >= lbound && value <= ubound) break;
        }
        //while

        return value;
    }
    //readNumber
}
//Console

//eof
