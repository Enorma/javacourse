package com.javacourse.concurrency.downloaders;

import com.javacourse.concurrency.AbstractDownloader;
import com.javacourse.concurrency.DownloadStatus;

public class DownloadFileBytes extends AbstractDownloader {

    //downloader class #4

    //this class is intended to demo:
    //race conditions.

    //---------------------------------------------------------------------------------
    //constructors

    public DownloadFileBytes(DownloadStatus _status) {

        super(_status);
    }
    //DownloadFileBytes (for lesson 8 onwards)

    //---------------------------------------------------------------------------------
    //methods

    @Override
    public void run() {

        //the thread name should be set here
        //since only the run() method is actually run in its own thread
        this.setName( Thread.currentThread().getName() );

        //call the method that works for the specific lesson here
        this.printBytes();
    }
    //run
}
//DownloadFileBytes

//eof
