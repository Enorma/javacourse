package com.javacourse.collections;

public class Customer implements Comparable<Customer> {

    //---------------------------------------------------------------------------------
    //fields

    private String name;
    private String email;

    //---------------------------------------------------------------------------------
    //constructors

    public Customer(String _name, String _email) {

        this.name  = _name;
        this.email = _email;
    }
    //Customer

    //---------------------------------------------------------------------------------
    //methods

    @Override
    public int compareTo(Customer other) {

        //we've already done something like this before,
        //check the User class in the Generics section
        //for an explanation.

        //yes, we can call compareTo() inside of compareTo()
        //and it's not recursion, since
        //even though they both come from the Comparable interface,
        //they are different implementations,
        //so they are not the same method.

        //here we are in the compareTo() method of the Customer class
        //so, Customer.compareTo().

        //and down here we're calling the compareTo() method of the String class
        //so, String.compareTo().
        return this.name.compareTo(other.name);
    }
    //compareTo

    @Override
    public String toString() {

        return( "[name:" + this.name + ",email:" + this.email + "]" );
    }
    //toString

    //---------------------------------------------------------------------------------
    //getters

    public String getName() {

        return this.name;
    }
    //getName

    public String getEmail() {

        return this.email;
    }
    //getEmail
}
//Customer

//eof
