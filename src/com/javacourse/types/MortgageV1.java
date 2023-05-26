package com.javacourse.types;

import java.text.NumberFormat;
import java.util.Scanner;

/*
    //project class for Course 1 Section 2: Types

    //import to Main class with:
    import com.javacourse.types.MortgageV1; //or...
    import com.javacourse.types.*;

    //call from main() method with:
    com.javacourse.types.MortgageV1.mortgage(); //or...
    MortgageV1.mortgage();
*/

public class MortgageV1 {

    public static void mortgage() {

        //define constants
        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;

        //declare scanner for user input
        Scanner numscanner = new Scanner(System.in);

        //get values from user input

        System.out.print("gimme principal: ");
        int principal = numscanner.nextInt();

        System.out.print("gimme annual interest rate: ");
        float annual_interest_rate = numscanner.nextFloat();

        System.out.print("gimme number of years: ");
        byte number_of_yearly_payments = numscanner.nextByte();

        //calculate necessary values
        float monthly_interest_rate = annual_interest_rate / PERCENT / MONTHS_IN_YEAR;
        short number_of_monthly_payments = (short)(number_of_yearly_payments * MONTHS_IN_YEAR);

        //intermediate calculations for mortgage
        double powered_rate = Math.pow( (1 + monthly_interest_rate), number_of_monthly_payments );
        double numerator    = monthly_interest_rate * powered_rate;
        double denominator  = powered_rate - 1;

        //get total mortgage
        double total_mortgage = principal * numerator / denominator;

        //pretty print mortgage
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String mort_str = currency.format(total_mortgage);
        System.out.println("You will pay "+mort_str+" per month.");
    }
    //mortgage
}
//MortgageV1

//eof
