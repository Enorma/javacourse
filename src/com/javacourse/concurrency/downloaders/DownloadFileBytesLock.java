package com.javacourse.concurrency.downloaders;

import com.javacourse.concurrency.AbstractDownloader;
import com.javacourse.concurrency.DownloadStatus;

public class DownloadFileBytesLock extends AbstractDownloader {

    //downloader class #5

    //this class is intended to demo:
    //prevention of race conditions via locks.

    //---------------------------------------------------------------------------------
    //constructors

    public DownloadFileBytesLock(DownloadStatus _status) {

        super(_status);
    }
    //DownloadFileBytesLock (for lesson 11 onwards)

    //---------------------------------------------------------------------------------
    //methods

    @Override
    public void run() {

        //the thread name should be set here
        //since only the run() method is actually run in its own thread
        this.setName( Thread.currentThread().getName() );

        //call the method that works for the specific lesson here
        this.printBytesLock();
    }
    //run
}
//DownloadFileBytesLock

//eof
