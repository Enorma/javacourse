package com.javacourse.concurrency.downloaders;

import com.javacourse.concurrency.AbstractDownloader;

public class DownloadFileSleep extends AbstractDownloader {

    //downloader class #2

    //this class is intended to demo:
    //concurrent execution of different threads.

    //---------------------------------------------------------------------------------
    //constructors

    public DownloadFileSleep() {

        super();
    } //for lesson 4

    //---------------------------------------------------------------------------------
    //methods

    @Override
    public void run() {

        //the thread name should be set here
        //since only the run() method is actually run in its own thread
        this.setName( Thread.currentThread().getName() );

        //call the method that works for the specific lesson here
        this.printWithSleep();
    }
    //run
}
//DownloadFileSleep

//eof
