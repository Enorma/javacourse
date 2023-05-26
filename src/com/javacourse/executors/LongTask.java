package com.javacourse.executors;

public class LongTask {

    //---------------------------------------------------------------------------------
    //methods

    public static void wrappedSleep(long ms) {
        try {
            Thread.sleep(ms);
        }catch(InterruptedException e) {
            Thread.currentThread().interrupt(); //re-raise interrupt exception
        }
        //try-catch
    }
    //wrappedSleep (a wrapper for sleep())

    public static void simulate(int seconds) {

        int i = 1;

        System.out.println();
        do {

            if( Thread.currentThread().isInterrupted() ) {
                System.out.println("Thread was interrupted.");
                return;
            }
            //if

            wrappedSleep(1000);
            System.out.println( "we've waited " + i + " seconds and counting..." );
            i++;
        }while(i<=seconds);
        System.out.println();
    }
    //simulate

    public static void simulate(int millis, String message) {

        int i = 1;

        //System.out.println();
        do {

            if( Thread.currentThread().isInterrupted() ) {
                System.out.println("Thread was interrupted while working on " + message + ".");
                return;
            }
            //if

            wrappedSleep(1);
            //System.out.println( "we've waited " + i + " ms for " + message + ", and counting..." );
            i++;
        }while(i<=millis);
        //System.out.println();
    }
    //simulate
}
//LongTask

//eof
