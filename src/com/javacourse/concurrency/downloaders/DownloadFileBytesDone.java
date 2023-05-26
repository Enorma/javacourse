package com.javacourse.concurrency.downloaders;

import com.javacourse.concurrency.AbstractDownloader;
import com.javacourse.concurrency.DownloadStatus;

public class DownloadFileBytesDone extends AbstractDownloader {

    //downloader class #7

    //this class is intended to demo:
    //the visibility problem.

    //---------------------------------------------------------------------------------
    //constructors

    public DownloadFileBytesDone(DownloadStatus _status) {

        super(_status);
    }
    //DownloadFileBytesDone (for lesson 13 onwards)

    //---------------------------------------------------------------------------------
    //methods

    @Override
    public void run() {

        //the thread name should be set here
        //since only the run() method is actually run in its own thread
        this.setName( Thread.currentThread().getName() );

        //call the method that works for the specific lesson here
        this.printBytesDone();
    }
    //run
}
//DownloadFileBytesDone

//eof
