package com.javacourse.lambdas;

//the @FunctionalInterface annotation
//is not strictly required, but
//it provides the advantage of checking, at compile-time
//that our interface does follow the structure of a functional interface
//and will crash at compile-time if it doesn't.

@FunctionalInterface
public interface Printable {

    //this is a functional interface
    //as such, it needs to have exactly one abstract method
    //no more, no less.

    void print(String message);

    //the print method is abstract
    //because it has no implementation.
    //it's only declared!

    //we could introduce
    //default and/or private methods here
    //as long as they're not abstract
    //and this interface would still be functional.

    //however, as we learned before,
    //adding implementation inside an interface
    //is a BAD practice.

    //so for practical purposes
    //a functional interface is like a wrapper for exactly one method,
    //and NOTHING else.
    //that one method is the interface's whole purpose.

    //when we use a functional interface,
    //we always know which wrapped method we want to call, because there's only one.
    //there's no ambiguity!

    //so, just by calling a functional interface,
    //we can implicitly assume we're calling the wrapped method.
    //no need to be explicit or verbose!

    //no verbosity means less code!
    //no ambiguity means no bugs!
    //that's the beauty of functional interfaces!
}
//Printable

//eof
