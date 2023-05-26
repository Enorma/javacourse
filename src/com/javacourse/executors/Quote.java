package com.javacourse.executors;

public class Quote {

    //---------------------------------------------------------------------------------
    //fields

    private String seller;
    private int price;

    //---------------------------------------------------------------------------------
    //constructors

    public Quote(String _seller, int _price) {

        this.seller = _seller;
        this.price  = _price;
    }
    //Quote

    //---------------------------------------------------------------------------------
    //methods

    @Override
    public String toString() {

        return "Quote{site=" + this.seller + ", price=" + this.price + "}";
    }
    //toString
}
//Quote

//eof
