package com.javacourse.concurrency.downloaders;

import com.javacourse.concurrency.AbstractDownloader;

public class DownloadFileSimple extends AbstractDownloader {

    //downloader class #1

    //this class is intended to demo:
    //simple execution of different threads.

    //---------------------------------------------------------------------------------
    //constructors

    public DownloadFileSimple() {

        super();
    } //for lesson 3

    //---------------------------------------------------------------------------------
    //methods

    @Override
    public void run() {

        //the thread name should be set here
        //since only the run() method is actually run in its own thread
        this.setName( Thread.currentThread().getName() );

        //call the method that works for the specific lesson here
        this.printSimple();
    }
    //run
}
//DownloadFileSimple

//eof
