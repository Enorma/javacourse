package com.javacourse.generics;

import java.lang.Comparable;

//in lesson 2 this class was created without a body
//and it didn't implement any interface

//in lesson 8 the Comparable interface was added
//and so was the compareTo method

public class User implements Comparable<User> {

    //the Comparable interface is generic
    //so we should give it a generic datatype
    //(in this case <User> because User is this class itself
    //and we want User objects to be capable
    //of comparing themselves against each other)

    //if we set the constraint without a generic type, like:
    //public class User implements Comparable {...}
    //the generic type of Comparable will be Object by default
    //which would lead to the same problems
    //of the naive generic implementation from lesson 3

    //---------------------------------------------------------------------------------
    //fields

    private int points;

    //---------------------------------------------------------------------------------
    //constructor

    public User(int _points) {

        this.points = _points;
    }
    //User

    //---------------------------------------------------------------------------------
    //methods

    @Override
    public int compareTo(User other) {

        //THESE COMMENTS ARE FROM THE POINT OF VIEW
        //OF THE COMPARABLE INTERFACE:

        //the type of the object that calls this method and the arg object
        //and the place from where this method is called
        //are unknown at this point

        //so, to make this work despite those unknowns
        //the caller and arg types, the return type,
        //and the way to interpret the return value
        //must be consistent, so:

        //any implementation of this method should...
        //always take an arg of the same class as the caller object,
        //always return int, and
        //return a value that follows this logic:
        //if this >  other, return a positive int
        //if this == other, return zero
        //if this <  other, return a negative int

        //THESE COMMENTS ARE FROM THE POINT OF VIEW
        //OF THIS IMPLEMENTATION:

        //considering we're comparing numeric values,
        //in this case the easiest implementation
        //is using substraction, like:
        return this.points - other.points;
    }
    //compareTo

    @Override
    public String toString() {

        //this is overridden because
        //the points are much more relevant info
        //than the default "class@hashcode"
        return("User with points: " + this.points);
    }
    //toString

    //---------------------------------------------------------------------------------
    //getters

    public int getPoints() {

        return this.points;
    }
    //getPoints
}
//User

//eof
