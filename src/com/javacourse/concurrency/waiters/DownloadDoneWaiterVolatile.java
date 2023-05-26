package com.javacourse.concurrency.waiters;

import com.javacourse.concurrency.AbstractWaiter;
import com.javacourse.concurrency.DownloadStatus;

public class DownloadDoneWaiterVolatile extends AbstractWaiter {

    //download done waiter class #2

    //all this class does is waiting until a download finishes
    //and when it does, prints a message.

    //this is intended to demo:
    //concurrent access to a volatile variable (protected against the visibility problem).

    //---------------------------------------------------------------------------------
    //constructors

    public DownloadDoneWaiterVolatile(DownloadStatus _dl_status) {

        super(_dl_status);
    }
    //DownloadDoneWaiterVolatile

    //---------------------------------------------------------------------------------
    //methods

    @Override
    public void run() {

        //WAITER METHOD:
        //wait (doing nothing) until the download has finished.
        //this uses an empty-body, potentially infinite loop:
        this.waitWithEmptyLoopVolatile();

        //DOWNLOAD FINISHED MESSAGE:
        //if this finished message is printed, it means
        //this waiter thread actually noticed that volatile_done became true:
        this.printFinishedMessage();

        //but if it never notices it... the waiter method will keep running forever!
        //and the finished message will never be printed!
    }
    //run
}
//DownloadDoneWaiterVolatile

//eof
