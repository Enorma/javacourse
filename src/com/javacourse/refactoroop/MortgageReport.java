package com.javacourse.refactoroop;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MortgageReport {

    //----------------------------------------------------------------------------------
    //fields

    //we make this final since it's never going to change
    private static final NumberFormat CURRENCY_FORMATTER = NumberFormat.getCurrencyInstance();

    //----------------------------------------------------------------------------------
    //methods

    public static void printMortgage(MortgageCalculator calc) {

        //prepare to pretty print results
        String mort_str = CURRENCY_FORMATTER.format( calc.getTotalMortgage() ); //this has the total mortgage precomputed

        //pretty print mortgage
        System.out.println("\nMORTGAGE");
        System.out.println("----------");
        System.out.println("You will pay "+mort_str+" per month.");
    }
    //printMortgage

    public static void printPaymentSchedule(MortgageCalculator calc) {

        //prepare to pretty print results
        String bal_str = "";
        ArrayList<Double> rem_balances = calc.getRemainingBalances(); //this has the whole payment schedule precomputed

        //pretty print payment schedule
        System.out.println("\nMORTGAGE PAYMENTS SCHEDULE");
        System.out.println("----------------------------");

        //loop through every monthly payment
        for(short months=0; months<rem_balances.size(); months++) {

            //read the remaining balance for the ith month and format it
            bal_str = CURRENCY_FORMATTER.format( rem_balances.get(months) );

            //can't use foreach because we need the months counter here
            System.out.println( bal_str + " remains after " + months + " payments" );
        }
        //for
    }
    //printPaymentSchedule
}
//MortgageReport

//eof
