package com.javacourse.lambdas;

public interface Constructable {

    //this interface was created just to demo
    //constructor-based functional interfaces.

    //in this case, this matches the constructor
    //of the Dummy class
    //since it takes the same args (one string)
    //and returns the same thing (a Dummy object)

    //so the constructor of Dummy would be
    //a valid implementation of this functional method:

    Dummy printMsg(String _msg);
}
//Constructable

//eof
