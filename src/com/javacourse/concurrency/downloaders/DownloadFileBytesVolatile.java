package com.javacourse.concurrency.downloaders;

import com.javacourse.concurrency.AbstractDownloader;
import com.javacourse.concurrency.DownloadStatus;

public class DownloadFileBytesVolatile extends AbstractDownloader {

    //downloader class #8

    //this class is intended to demo:
    //prevention of the visibility problem via the volatile keyword.

    //---------------------------------------------------------------------------------
    //constructors

    public DownloadFileBytesVolatile(DownloadStatus _status) {

        super(_status);
    }
    //DownloadFileBytesVolatile (for lesson 13 onwards)

    //---------------------------------------------------------------------------------
    //methods

    @Override
    public void run() {

        //the thread name should be set here
        //since only the run() method is actually run in its own thread
        this.setName( Thread.currentThread().getName() );

        //call the method that works for the specific lesson here
        this.printBytesVolatile();
    }
    //run
}
//DownloadFileBytesVolatile

//eof
