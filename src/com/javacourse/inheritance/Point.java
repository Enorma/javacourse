package com.javacourse.inheritance;

import java.util.Objects;

public class Point {

    //--------------------------------------------------------
    //fields

    private int x;
    private int y;

    //--------------------------------------------------------
    //constructors

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    //Point

    //--------------------------------------------------------
    //methods

    @Override
    public boolean equals(Object obj) {

        //instead of the original equals()
        //which compares by heap address
        //we want to compare two points by their coordinates

        //if the argument is the caller object itself
        //then of course they're equal
        if(this == obj) return true;

        //we can't change the argument datatype (Object)
        //because we can't change the method signature
        //because of the @Override annotation,
        //but if obj is in fact a Point, we can downcast it explicitly:

        if( !(obj instanceof Point) ) return false; //prevent a classCastException
        var other = (Point)obj; //downcasting

        //compare two points by their coordinates
        return this.x == other.x && this.y == other.y;
    }
    //equals

    @Override
    public int hashCode() {

        //whenever we override the equals() method
        //it is best practice to override hashCode() too

        //by default, hashCode hashes the object's memory address
        //here we can hash whatever we want

        return Objects.hash(this.x, this.y);
    }
}
//Point

//eof
