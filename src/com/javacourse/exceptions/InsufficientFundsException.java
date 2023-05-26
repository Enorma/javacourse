package com.javacourse.exceptions;

//here we're building a custom exception
//before anything else, we need to decide if this will be...
//checked (compile-time) (should extend Exception)
//or..
//unchecked (runtime) (should extend RuntimeException).

//any situation that's OUTSIDE our program
//but is a possible problem that might happen
//should be a checked exception
//like filesystem malfunction, network disconnection, user error, etc.

//this exception represents a user error
//when the user attempts to withdraw more money than he has
//therefore, this should be a checked exception.

public class InsufficientFundsException extends Exception {

    public InsufficientFundsException() { //default constructor

        //super is Exception()
        //which takes a String message
        super("Insufficient funds in your account");
    }
    //InsufficientFundsException

    public InsufficientFundsException(String message) { //overloaded constructor

        //super is Exception()
        //which takes a String message
        super(message);
    }
    //InsufficientFundsException
}
//InsufficientFundsException

//eof
