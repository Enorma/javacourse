package com.javacourse.concurrency.downloaders;

import com.javacourse.concurrency.AbstractDownloader;
import com.javacourse.concurrency.DownloadStatus;

public class DownloadFileBytesAtomic extends AbstractDownloader {

    //downloader class #10

    //this class is intended to demo:
    //preventing race conditions via atomic objects.

    //---------------------------------------------------------------------------------
    //constructors

    public DownloadFileBytesAtomic(DownloadStatus _status) {

        super(_status);
    }
    //DownloadFileBytesAtomic (for lesson 15)

    //---------------------------------------------------------------------------------
    //methods

    @Override
    public void run() {

        //the thread name should be set here
        //since only the run() method is actually run in its own thread
        this.setName( Thread.currentThread().getName() );

        //call the method that works for the specific lesson here
        this.printAtomic();
    }
    //run
}
//DownloadFileBytesAtomic

//eof
