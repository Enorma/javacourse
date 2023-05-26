package com.javacourse.cleancode;

/*
    //project class for Course 1 Section 4: Clean Code

    //import to Main class with:
    import com.javacourse.cleancode.MortgageV3; //or...
    import com.javacourse.cleancode.*;

    //call from main() method with:
    com.javacourse.cleancode.MortgageV3.mortgageRefactored(); //or...
    MortgageV3.mortgageRefactored();
*/

import java.text.NumberFormat;
import java.util.Scanner;

public class MortgageV3 {

    //define constants
    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;

    public static double readNumber(String prompt, double lbound, double ubound) {

        //refactoring rationale:
        //repetitive patterns become a parameterized method with a parameterized loop inside

        //declare scanner for user input
        Scanner numscanner = new Scanner(System.in);

        double value = 0;

        while(true) {
            System.out.print(prompt + " (" + lbound + " to " + ubound + "): ");
            value = numscanner.nextDouble();
            if(value >= lbound && value <= ubound) break;
        }
        //while

        return value;
    }
    //readNumber

    public static double calculateMortgage(
    int principal,
    float annual_interest_rate,
    byte number_of_yearly_payments) {

        //refactoring rationale:
        //only lines that do the mortgage calculations are in this method

        //calculate necessary values
        float monthly_interest_rate      = annual_interest_rate / PERCENT / MONTHS_IN_YEAR;
        short number_of_monthly_payments = (short)(number_of_yearly_payments * MONTHS_IN_YEAR);

        //make the big mortgage calculation formula actually readable
        double powered_rate = Math.pow( (1 + monthly_interest_rate), number_of_monthly_payments );
        double numerator    = monthly_interest_rate * powered_rate;
        double denominator  = powered_rate - 1;

        //get total mortgage
        double total_mortgage = principal * numerator / denominator;

        return total_mortgage;
    }
    //calculateMortgage

    public static double calculateMonthBalance(
    int principal,
    float annual_interest_rate,
    byte number_of_yearly_payments,
    short ith_month) {

        //calculate necessary values
        float monthly_interest_rate      = annual_interest_rate / PERCENT / MONTHS_IN_YEAR;
        short number_of_monthly_payments = (short)(number_of_yearly_payments * MONTHS_IN_YEAR);

        //calculate values that don't change depending on the month
        double inner                = 1 + monthly_interest_rate;
        double innerTotalPower      = Math.pow(inner, number_of_monthly_payments);
        double schedule_denominator = innerTotalPower - 1;

        //calculate values that change depending on the month
        double innerDonePower     = Math.pow(inner, ith_month);
        double innerDifference    = innerTotalPower - innerDonePower;
        double schedule_numerator = principal * innerDifference;
        double balance            = schedule_numerator / schedule_denominator;

        return balance;
    }
    //calculateMonthBalance

    public static void printMortgage(double total_mortgage) {

        //prepare to pretty print results
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String mort_str = currency.format(total_mortgage);

        //pretty print mortgage
        System.out.println("\nMORTGAGE");
        System.out.println("--------");
        System.out.println("You will pay "+mort_str+" per month.");
    }
    //printMortgage

    public static void printPaymentSchedule(
    int principal,
    float annual_interest_rate,
    byte number_of_yearly_payments) {

        //prepare to pretty print results
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        short number_of_monthly_payments = (short)(number_of_yearly_payments * MONTHS_IN_YEAR);
        double month_balance = 0;

        //pretty print payment schedule
        System.out.println("\nMORTGAGE SCHEDULE");
        System.out.println("-----------------");

        //loop through every monthly payment
        for(short months=0; months<=number_of_monthly_payments; months++) {

            //calculate the remaining balance for the ith month
            month_balance = calculateMonthBalance(
                principal,
                annual_interest_rate,
                number_of_yearly_payments,
                months
            );

            //pretty print balance for the ith month
            System.out.println( currency.format(month_balance) + " remains after " + months + " payments");
        }
        //for
    }
    //printPaymentSchedule

    public static void mortgageRefactored() {

        //get values from user input

        String prompt = "";

        prompt = "gimme principal";
        int principal = (int)readNumber(prompt, 1_000, 1_000_000);

        prompt = "gimme annual interest rate";
        float annual_interest_rate = (float)readNumber(prompt, 0.1, 30);

        prompt = "gimme number of years";
        byte number_of_yearly_payments = (byte)readNumber(prompt, 1, 30);

        //calculate mortgage
        double total_mortgage = calculateMortgage(principal, annual_interest_rate, number_of_yearly_payments);

        //pretty print mortgage
        printMortgage(total_mortgage);

        //pretty print monthly schedule
        printPaymentSchedule(principal, annual_interest_rate, number_of_yearly_payments);
    }
    //mortgageRefactored
}
//MortgageV3

//eof
