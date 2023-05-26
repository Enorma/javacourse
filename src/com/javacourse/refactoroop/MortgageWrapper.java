package com.javacourse.refactoroop;

/*
    //wrapper class for Course 2 Section 3: Refactoring towards an OOP design

    //import to Main class with:
    import com.javacourse.refactoroop.MortgageWrapper; //or...
    import com.javacourse.refactoroop.*;

    //call from main() method with:
    com.javacourse.refactoroop.MortgageWrapper.MortgageOOP(); //or...
    MortgageWrapper.MortgageOOP();
*/

public class MortgageWrapper {

    public static void MortgageOOP() {

        //prepare to read values from user input
        String prompt = "";
        var numreader = new Console();

        //ask user for input

        prompt = "gimme principal";
        int principal = (int)numreader.readNumber(prompt, 1_000, 1_000_000);

        prompt = "gimme annual interest rate"; //in percent form
        float annual_interest_rate = (float)numreader.readNumber(prompt, 0.1, 30);

        prompt = "gimme number of years"; //number of yearly payments
        byte number_of_yearly_payments = (byte)numreader.readNumber(prompt, 1, 30);

        //create a mortgage calculator object
        var mortcalc = new MortgageCalculator(principal, annual_interest_rate, number_of_yearly_payments);

        //at this point, every relevant value for the mortgage was already computed
        //see the constructor of the MortgageCalculator class

        //call methods of the MortgageReport class to pretty print the output
        MortgageReport.printMortgage(mortcalc);
        MortgageReport.printPaymentSchedule(mortcalc);
    }
    //MortgageOOP
}
//MortgageWrapper

//eof
