package com.javacourse.concurrency;

public class UnsafeKey {

    //---------------------------------------------------------------------------------
    //fields

    private final int value;

    //---------------------------------------------------------------------------------
    //constructors

    public UnsafeKey(int _value) {

        this.value = _value;
    }
    //UnsafeKey

    //---------------------------------------------------------------------------------
    //methods

    @Override
    public String toString() {

        return "UnsafeKey(" + this.value + ")";
    }
    //toString

    @Override
    public int hashCode() {

        //all possible keys will hash to the same hash value (42)
        //this makes all keys collide in a hashmap

        return 42;
    }
    //hashCode

    @Override
    public boolean equals(Object other) {

        if( other == this ) return true;
        if( other == null ) return false;
        if( other.getClass() != this.getClass() ) return false;

        UnsafeKey other_key = (UnsafeKey)other;

        return other_key.value == this.value;
    }
    //equals
}
//UnsafeKey

//eof
