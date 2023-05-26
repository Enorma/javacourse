package com.javacourse.exceptions;

import java.io.IOException;

public class Account {

    private float balance;

    //this throws an IllegalArgumentException (an unchecked exception)
    //so we can throw it directly
    public void uncheckedDeposit(float value) {

        //in case of a wrong value,
        //we instantiate an exception object
        //initialize it, and throw it

        //this is defensive programming
        //since we protect our program from running
        //with wrong or potentially dangerous values

        //it's possible to add the "throws" clause (see method below)
        //to this method too
        //but that's a bad practice, since
        //unchecked exceptions are programming errors
        //so you'd want to actually fix the offending code
        //not just declare "hey, this method might crash because I coded it wrong"

        if(value <= 0) throw new IllegalArgumentException("can't handle non-positive values");
        System.out.println("deposited " + value);
    }
    //uncheckedDeposit

    //this throws an IOException (a checked exception)
    //so we need to add "throws IOException"
    //after the method signature
    public void checkedDeposit(float value) throws IOException {

        //here we apply defensive programming
        //by throwing a checked exception
        //so we need to handle it with a try-catch block
        //or we get a compile-time error

        //however, it makes no sense to throw an exception
        //just to immediately handle it right then and there

        //instead, we should expect the caller of this method
        //to handle the exception

        //in those kinds of methods
        //it is required to add "throws <exception class>"
        //after the method signature

        //this way, the method caller knows which exception to handle

        //whenever a checked exception appears in the code
        //the java compiler gives us an error
        //but we can fix that either with a try-catch block
        //or with a "throws..." statement

        if(value <= 0) throw new IOException("can't handle non-positive values");
        System.out.println("deposited " + value);
    }
    //checkedDeposit

    public void withdraw_old(float value) throws InsufficientFundsException {

        //this error is triggered when the user asks for more money than he can
        //therefore it's user error, it's expected and recoverable
        //therefore it's a checked exception
        //therefore we write the "throws..." statement at the method signature

        if(value > balance) {
            System.out.println("insufficient funds!");
            throw new InsufficientFundsException("not enough money in your account");
        }
        //if
    }
    //withdraw_old

    public void withdraw(float value) throws AccountException {

        //this wraps the InsufficientFundsException
        //as the cause of an AccountException,
        //many more exceptions can be wrapped as causes of AccountException like this
        //so we now have AccountException as a general exception with many possible causes

        if(value > balance) {
            System.out.println("insufficient funds!");
            throw new AccountException(new InsufficientFundsException("you lack money!"));
        }
        //if
    }
    //withdraw
}
//Account

//eof
