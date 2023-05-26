package com.javacourse.concurrency.downloaders;

import com.javacourse.concurrency.AbstractDownloader;
import com.javacourse.concurrency.DownloadStatus;

public class DownloadFileBytesAdder extends AbstractDownloader {

    //downloader class #11

    //this class is intended to demo:
    //preventing race conditions via adder objects.

    //---------------------------------------------------------------------------------
    //constructors

    public DownloadFileBytesAdder(DownloadStatus _status) {

        super(_status);
    }
    //DownloadFileBytesAdder (for lesson 16)

    //---------------------------------------------------------------------------------
    //methods

    @Override
    public void run() {

        //the thread name should be set here
        //since only the run() method is actually run in its own thread
        this.setName( Thread.currentThread().getName() );

        //call the method that works for the specific lesson here
        this.printAdder();
    }
    //run
}
//DownloadFileBytesAdder

//eof
