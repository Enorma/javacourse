package com.javacourse.lambdas;

public class ConsolePrinter implements Printable {

    //this class is intended to implement Printable, a functional interface.
    //that is why it provides no other methods,
    //only print(), which is overridden from Printable.

    //for the purposes of this lesson, this will be the whole functionality of this class.
    //however, just like we've done many times, it could have more methods if we wanted.

    //we also don't care about the reusability of this class.
    //that's why there's no constructor or fields.
    //we want this class for the print() method, not to create objects.

    //---------------------------------------------------------------------------------
    //methods

    @Override
    public void print(String message) {

        System.out.println( "ConsolePrinter says: " + message );
    }
    //print
}
//ConsolePrinter

//eof
