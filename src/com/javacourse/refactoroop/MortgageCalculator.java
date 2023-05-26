package com.javacourse.refactoroop;

import java.util.ArrayList;

public class MortgageCalculator {

    //----------------------------------------------------------------------------------
    //fields

    //seed values
    private int principal;
    private float annualInterestRate;
    private byte numberOfYearlyPayments;

    //auto-calculated values
    private float monthlyInterestRate;
    private short numberOfMonthlyPayments;

    //total mortgage amount
    private double totalMortgage;

    //monthly balance formula values that don't change through the months
    private double inner;
    private double innerTotalPower;
    private double scheduleDenominator;

    //list of remaining monthly balances
    private ArrayList<Double> remainingBalances;

    //----------------------------------------------------------------------------------
    //constructors

    public MortgageCalculator(int _principal, float _annualInterestRate, byte _numberOfYearlyPayments) {

        this.principal              = _principal;
        this.annualInterestRate     = _annualInterestRate;
        this.numberOfYearlyPayments = _numberOfYearlyPayments;

        this.remainingBalances = new ArrayList<Double>();

        this.calcMonthlyValues();
        this.calcScheduleValues();

        this.calcMortgage();
        this.calcRemainingBalances();
    }
    //MortgageCalculator

    //----------------------------------------------------------------------------------
    //methods

    private void calcMonthlyValues() {

        //declare constants
        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;

        this.monthlyInterestRate     = this.annualInterestRate / PERCENT / MONTHS_IN_YEAR;
        this.numberOfMonthlyPayments = (short)( this.numberOfYearlyPayments * MONTHS_IN_YEAR );
    }
    //calcMonthlyValues

    private void calcMortgage() {

        double poweredRate = Math.pow( (1 + this.monthlyInterestRate), this.numberOfMonthlyPayments );

        double mortgageNumerator   = this.monthlyInterestRate * poweredRate;
        double mortgageDenominator = poweredRate - 1;

        this.totalMortgage = this.principal * mortgageNumerator / mortgageDenominator;
    }
    //calcMortgage

    private void calcScheduleValues() {

        this.inner               = 1 + this.monthlyInterestRate;
        this.innerTotalPower     = Math.pow( this.inner, this.numberOfMonthlyPayments );
        this.scheduleDenominator = this.innerTotalPower - 1;
    }
    //calcScheduleValues

    private double calcMonthBalance(short ithMonth) {

        double innerDonePower    = Math.pow(this.inner, ithMonth);
        double innerDifference   = this.innerTotalPower - innerDonePower;
        double scheduleNumerator = this.principal * innerDifference;
        double balance           = scheduleNumerator / this.scheduleDenominator;

        return balance;
    }
    //calcMonthBalance

    private void calcRemainingBalances() {

        for(short i=0; i<=this.numberOfMonthlyPayments; i++) {
            remainingBalances.add( this.calcMonthBalance(i) );
        }
        //for
    }
    //calcRemainingBalances

    //----------------------------------------------------------------------------------
    //getters

    public double getTotalMortgage() {

        return this.totalMortgage;
    }
    //getTotalMortgage

    public ArrayList<Double> getRemainingBalances() {

        return this.remainingBalances;
    }
    //getRemainingBalances
}
//MortgageCalculator

//eof
