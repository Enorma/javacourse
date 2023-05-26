package com.javacourse.controlflow;

import java.text.NumberFormat;
import java.util.Scanner;

/*
    //project class for Course 1 Section 3: Control Flow

    //import to Main class with:
    import com.javacourse.controlflow.MortgageV2; //or...
    import com.javacourse.controlflow.*;

    //call from main() method with:
    com.javacourse.controlflow.MortgageV2.mortgageBetter(); //or...
    MortgageV2.mortgageBetter();
*/

public class MortgageV2 {

    public static void mortgageBetter() {

        //define constants
        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;

        //declare scanner for user input
        Scanner numscanner = new Scanner(System.in);

        //get values from user input

        double value = 0;

        while(true) {
            System.out.print("gimme principal ($1K to $1M): ");
            value = numscanner.nextInt();
            if(value >= 1_000 && value <= 1_000_000) break;
        }
        //while
        int principal = (int)value;

        while(true) {
            System.out.print("gimme annual interest rate (0.1 to 30): ");
            value = numscanner.nextFloat();
            if(value >= 0.1 && value <= 30) break;
        }
        //while
        float annual_interest_rate = (float)value;

        while(true) {
            System.out.print("gimme number of years (1 to 30): ");
            value = numscanner.nextByte();
            if(value >= 1 && value <= 30) break;
        }
        //while
        byte number_of_yearly_payments = (byte)value;

        //calculate necessary values
        float monthly_interest_rate = annual_interest_rate / PERCENT / MONTHS_IN_YEAR;
        short number_of_monthly_payments = (short)(number_of_yearly_payments * MONTHS_IN_YEAR);

        //make the big mortgage calculation formula actually readable
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
    //mortgageBetter
}
//MortgageV2

//eof
