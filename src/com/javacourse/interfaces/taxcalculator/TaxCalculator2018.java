package com.javacourse.interfaces.taxcalculator;

//FIRST TaxCalculator created (lesson 3)
//originally named TaxCalculator
//then renamed TaxCalculator2018 in lesson 4

//it is possible for a class to extend zero or one superclass
//and also to implement zero or more interfaces
//multiple interfaces are comma-separated, so the syntax is like:
//public class ClassA extends ClassB implements InterfaceC, InterfaceD, ...

public class TaxCalculator2018 implements CanCalculateTax {

    //---------------------------------------------------------------------------------
    //fields

    private double taxableIncome;

    //---------------------------------------------------------------------------------
    //constructors

    public TaxCalculator2018(double _taxable_income) {

        //this comments make sense in lesson 3
        //before we used interfaces

        //the class TaxReport calls this constructor
        //which means it depends on this class
        //if we added a new argument to this constructor
        //(and didn't have another compatible constructor)
        //then the TaxReport class will break

        //this means the two classes are tightly coupled
        //that's bad

        //go ahead and add another argument like "int insurance" to this constructor
        //see what happens to the TaxReport class

        this.taxableIncome = _taxable_income;
        System.out.println("Created a TaxCalculator2018 object!");
    }
    //TaxCalculator2018

    //---------------------------------------------------------------------------------
    //methods

    //implementations of methods taken from interfaces
    //should have the @Override annotation

    @Override
    public double calculateTax() {

        //this comments make sense in lesson 3
        //before we used interfaces

        //same as in the constructor
        //if we modify the arguments or the return type
        //of this method, the TaxReport is gonna break
        //go ahead and try it

        //also, there can be non-breaking changes
        //for example, if we change the calculation below
        //like changing 0.3 to 0.4
        //the TaxReport class won't break
        //but now we would need to recompile it

        //therefore ANY changes to a dependency
        //breaking or not
        //are bad because they force us to do work
        //on the dependent classes

        //that's the definition of tight coupling
        //that's bad

        return this.taxableIncome * 0.3;
    }
    //calculateTax
}
//TaxCalculator2018

//eof
