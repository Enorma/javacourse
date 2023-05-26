package com.javacourse.executors;

import java.util.Random;

public class FlightSeller {

    //---------------------------------------------------------------------------------
    //fields

    private String name;
    private Quote quote;

    //---------------------------------------------------------------------------------
    //constructors

    public FlightSeller(String _name) {
        this.name  = _name;
        this.quote = new Quote(this.name, randomPrice());
    }
    //FlightSeller

    //---------------------------------------------------------------------------------
    //methods

    //just for variety across runs, make prices and delays random

    public int randomPrice() {

        return 100 + new Random().nextInt(11);
    }
    //randomPrice

    public int randomDelay() {

        return new Random().nextInt(3001);
    }
    //randomPrice

    //---------------------------------------------------------------------------------
    //getters

    //this method is synchronous by itself,
    //but we'll call it inside an async wrapper:

    public Quote getQuote() {
        System.out.println( "Getting a quote from " + this.name + "..." );
        LongTask.simulate( randomDelay(), "a quote by " + this.name );
        //System.out.println( "Got a quote from " + this.name + "!" );
        return this.quote;
    }
    //getQuote

    //---------------------------------------------------------------------------------
    //setters
}
//FlightSeller

//eof
