package com.javacourse.concurrency.downloaders;

import com.javacourse.concurrency.AbstractDownloader;
import com.javacourse.concurrency.DownloadStatus;

public class DownloadFileBytesSignalled extends AbstractDownloader {

    //downloader class #9

    //this class is intended to demo:
    //prevention of the visibility problem via the wait and notify signals.

    //---------------------------------------------------------------------------------
    //constructors

    public DownloadFileBytesSignalled(DownloadStatus _status) {

        super(_status);
    }
    //DownloadFileBytesSignalled (for lesson 14 onwards)

    //---------------------------------------------------------------------------------
    //methods

    @Override
    public void run() {

        //the thread name should be set here
        //since only the run() method is actually run in its own thread
        this.setName( Thread.currentThread().getName() );

        //call the method that works for the specific lesson here
        this.printBytesSignalled();
    }
    //run
}
//DownloadFileBytesSignalled

//eof
