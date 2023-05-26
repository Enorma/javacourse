package com.javacourse.concurrency.downloaders;

import com.javacourse.concurrency.AbstractDownloader;
import com.javacourse.concurrency.DownloadStatus;

public class DownloadFileBytesSync extends AbstractDownloader {

    //downloader class #6

    //this class is intended to demo:
    //prevention of race conditions via the synchronized keyword.

    //---------------------------------------------------------------------------------
    //constructors

    public DownloadFileBytesSync(DownloadStatus _status) {

        super(_status);
    }
    //DownloadFileBytesSync (for lesson 12 onwards)

    //---------------------------------------------------------------------------------
    //methods

    @Override
    public void run() {

        //the thread name should be set here
        //since only the run() method is actually run in its own thread
        this.setName( Thread.currentThread().getName() );

        //call the method that works for the specific lesson here
        this.printBytesSync();
    }
    //run
}
//DownloadFileBytesSync

//eof
