package com.javacourse.concurrency.downloaders;

import com.javacourse.concurrency.AbstractDownloader;

public class DownloadFileInterrupt extends AbstractDownloader {

    //downloader class #3

    //this class is intended to demo:
    //interrupt signals.

    //---------------------------------------------------------------------------------
    //constructors

    public DownloadFileInterrupt() {

        super();
    } //for lesson 6

    //---------------------------------------------------------------------------------
    //methods

    @Override
    public void run() {

        //the thread name should be set here
        //since only the run() method is actually run in its own thread
        this.setName( Thread.currentThread().getName() );

        //call the method that works for the specific lesson here
        this.printAndCatchInterrupt();
    }
    //run
}
//DownloadFileInterrupt

//eof
