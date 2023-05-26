package com.javacourse.generics;

public class Instructor extends User {

    //---------------------------------------------------------------------------------
    //constructor

    public Instructor(int _points) {

        super(_points);
    }
    //Instructor

    //---------------------------------------------------------------------------------
    //methods

    @Override
    public String toString() {

        //this is overridden because
        //the points are much more relevant info
        //than the default "class@hashcode"
        return("Instructor with points: " + this.getPoints());
    }
    //toString
}
//Instructor

//eof
