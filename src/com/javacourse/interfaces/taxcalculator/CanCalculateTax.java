package com.javacourse.interfaces.taxcalculator;

//Originally named TaxCalculator
//but renamed to follow java's convention for naming interfaces:
//an interface name should convey that
//the classes that implement it are able to do something
//so CanCalculateTax is a good name
//(TaxCalculateable would be ok as well)

public interface CanCalculateTax {

    //any class that implements this interface
    //is forced to implement these methods

    //kinda like how a subclass that inherits an abstract class
    //is forced to override its abstract methods

    //the only necessary access modifier in the whole interface
    //is the (public) one right before the interface name at the top
    //because every method declared here should be implemented by some class B
    //and those need to be public in class B so some class A can use them
    //so, this enables the loose coupling between A and B

    //private interfaces can only exist nested inside another interface
    //but those are a very advanced topic not in the scope of the course
    //so for now, let's just assume all interfaces are public

    double calculateTax();
}
//CanCalculateTax

//eof
