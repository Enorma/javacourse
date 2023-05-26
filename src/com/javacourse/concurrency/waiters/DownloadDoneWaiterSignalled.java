package com.javacourse.concurrency.waiters;

import com.javacourse.concurrency.AbstractWaiter;
import com.javacourse.concurrency.DownloadStatus;

public class DownloadDoneWaiterSignalled extends AbstractWaiter {

    //download done waiter class #3

    //all this class does is waiting until a download finishes
    //and when it does, prints a message.

    //this is intended to demo:
    //waiting via the wait() method called on the synchronized monitor
    //and waking up by catching an external call to notify() or notifyAll().

    //---------------------------------------------------------------------------------
    //constructors

    public DownloadDoneWaiterSignalled(DownloadStatus _dl_status) {

        super(_dl_status);
    }
    //DownloadDoneWaiterSignalled

    //---------------------------------------------------------------------------------
    //methods

    @Override
    public void run() {

        //WAITER METHOD:
        //wait (doing nothing) until the download has finished.
        //this uses the wait() method and expects an external notify() call:
        this.waitWithSignals();

        //DOWNLOAD FINISHED MESSAGE:
        //if this finished message is printed, it means
        //this waiter thread actually noticed that another thread has awakened it.
        this.printFinishedMessage();

        //but if it never notices it... the waiter method will keep running forever!
        //and the finished message will never be printed!
    }
    //run
}
//DownloadDoneWaiterSignalled

//eof
