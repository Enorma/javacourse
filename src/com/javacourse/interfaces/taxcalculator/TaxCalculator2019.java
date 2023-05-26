package com.javacourse.interfaces.taxcalculator;

//SECOND TaxCalculator created (lesson 7)

public class TaxCalculator2019 implements CanCalculateTax {

    //---------------------------------------------------------------------------------
    //fields

    private double taxableIncome;

    //---------------------------------------------------------------------------------
    //constructors

    public TaxCalculator2019(double _taxable_income) {

        this.taxableIncome = _taxable_income;
        System.out.println("Created a TaxCalculator2019 object!");
    }
    //TaxCalculator2019

    //---------------------------------------------------------------------------------
    //methods

    @Override
    public double calculateTax() {

        //this class does a different calculation from TaxCalculator2018
        //simulating how taxation changes across different years
        return this.taxableIncome * 0.4; //TaxCalculator2018 used 0.3
    }
    //calculateTax
}
//TaxCalculator2019

//eof
