package com.javacourse.collections;

import java.util.Comparator;

public class EmailComparator implements Comparator<Customer> {

    //this class is intended to have only one method: compare()
    //we created it because we want to have a method that defines
    //a comparison between two objects of the same class (in this case Customer)
    //by some criteria (in this case their email).

    //the sort() method has one overload which expects an object
    //that implements the compare() method, like here:

    @Override
    public int compare(Customer c1, Customer c2) {

        return c1.getEmail().compareTo( c2.getEmail() );
    }
    //compare
}
//EmailComparator

//eof
