package com.javacourse.lambdas;

public class Dummy {

    //this class is only for an example
    //of a constructor method reference.

    //as we can see, this constructor
    //is a method that takes a String
    //and returns a Dummy object.

    //plus, it does some extra work: printing the string.

    //this matches the printMsg() method in the Constructable interface.
    //so it can be used as a valid implementation of it
    //even though Dummy doesn't implement Constructable explicitly.

    public Dummy(String _msg) {

        System.out.println(_msg);
    }
    //Dummy
}
//Dummy

//eof
