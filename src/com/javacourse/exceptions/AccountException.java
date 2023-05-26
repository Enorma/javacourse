package com.javacourse.exceptions;

public class AccountException extends Exception {

    public AccountException(String message) {

        super(message);
    }
    //AccountException

    public AccountException(Exception cause) {

        //the Exception class has a constructor
        //which takes another exception object as its cause

        //since this doesn't take a String message argument,
        //the message will default to the name and message of the exception cause
        super(cause);
    }
    //AccountException
}
//AccountException

//eof
