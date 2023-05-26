package com.javacourse.executors;

import java.util.concurrent.CompletableFuture;

public class MailService {

    //---------------------------------------------------------------------------------
    //fields

    //---------------------------------------------------------------------------------
    //constructors

    //---------------------------------------------------------------------------------
    //methods

    public void sendSync() {
        LongTask.simulate(3);
        System.out.println("Mail was sent.");
    }
    //send

    public CompletableFuture<Void> sendAsync() {
        System.out.println("\nSending mail asynchronously...");
        return CompletableFuture.runAsync(this::sendSync);
    }
    //sendAsync

    //---------------------------------------------------------------------------------
    //getters

    //---------------------------------------------------------------------------------
    //setters
}
//MailService

//eof
