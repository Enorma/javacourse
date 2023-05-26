package com.javacourse.interfaces.taxcalculator;

public class TaxReport {

    //---------------------------------------------------------------------------------
    //fields

    //this is tight coupling (calculator is of a class datatype, not an interface datatype)
    //we keep it here commented, because it's used before lesson 6
    //private TaxCalculator2018 calculator;

    //this is loose coupling (calculator is of an interface datatype, not a class datatype)
    private CanCalculateTax calculator;

    //---------------------------------------------------------------------------------
    //constructors

    public TaxReport(CanCalculateTax _calculator) {

        //these next comments are from before lesson 6
        //we didn't use interfaces yet at that point
        //(to run those examples, remove the argument from the constructor)

        //this calls a one-argument constructor of the TaxCalculator2018 class
        //if that class were to change and not have a one-argument constructor anymore
        //this method (and hence this class) will break

        //this means the two classes are tightly coupled
        //that's bad

        //this is tight coupling (calculator is instantiated here, not injected as an argument)
        //we keep it here commented, because it's used before lesson 6
        //this.calculator = new TaxCalculator2018(100_000);

        //this is loose coupling (calculator comes from the arguments, it's not instantiated here)
        this.calculator = _calculator;

        //the datatype of the calculator is internally treated as its actual class type, not the interface type
        System.out.println( "Created a TaxReport object with stored calculator of type: " + this.calculator.getClass().getSimpleName() );
    }
    //TaxReport

    //---------------------------------------------------------------------------------
    //methods

    public void show(CanCalculateTax _calculator) {

        //this is the overloaded show() method created in lesson 8

        //this is loose coupling (calculator comes from the arguments, it's not instantiated here)
        var tax = _calculator.calculateTax();

        //the datatype of the calculator is internally treated as its actual class type, not the interface type
        System.out.println( "Calculated tax " + tax + " with provided calculator of type: " + _calculator.getClass().getSimpleName() );
    }
    //show

    public void show() {

        //this is the original show() method from before lesson 8
        //after lesson 8 all the following code could be replaced with:
        //show(this.calculator);

        //however we keep the following code as-is
        //so the examples from before lesson 8 can run

        //this next line has no problem with loose or tight coupling
        var tax = this.calculator.calculateTax();

        //the datatype of the calculator is internally treated as its actual class type, not the interface type
        System.out.println( "Calculated tax " + tax + " with stored calculator of type: " + this.calculator.getClass().getSimpleName() );
    }
    //show

    //---------------------------------------------------------------------------------
    //methods

    public void setCalculator(CanCalculateTax _calculator) {

        //this is loose coupling (calculator comes from the arguments, it's not instantiated here)
        this.calculator = _calculator;

        //the datatype of the calculator is internally treated as its actual class type, not the interface type
        System.out.println( "set a stored calculator of type: " + this.calculator.getClass().getSimpleName() );
    }
    //setCalculator
}
//TaxReport

//eof
