package com.javacourse.concurrency.waiters;

import com.javacourse.concurrency.AbstractWaiter;
import com.javacourse.concurrency.DownloadStatus;

public class DownloadDoneWaiter extends AbstractWaiter {

    //download done waiter class #1

    //all this class does is waiting until a download finishes
    //and when it does, prints a message.

    //this is intended to demo:
    //concurrent access to a non-volatile variable (prone to the visibility problem).

    //---------------------------------------------------------------------------------
    //constructors

    public DownloadDoneWaiter(DownloadStatus _dl_status) {

        super(_dl_status);
    }
    //DownloadDoneWaiter

    //---------------------------------------------------------------------------------
    //methods

    @Override
    public void run() {

        //WAITER METHOD:
        //wait (doing nothing) until the download has finished.
        //this uses an empty-body, potentially infinite loop:
        this.waitWithEmptyLoopVanilla();

        //DOWNLOAD FINISHED MESSAGE:
        //if this finished message is printed, it means
        //this waiter thread actually noticed that vanilla_done became true:
        this.printFinishedMessage();

        //but if it never notices it... the waiter method will keep running forever!
        //and the finished message will never be printed!
    }
    //run
}
//DownloadDoneWaiter

//eof
