package com.javacourse.concurrency;

public abstract class AbstractWaiter implements Runnable {

    //download done waiter class #0

    //all this class does is waiting until a download finishes
    //and when it does, prints a message.

    //this is intended to demo:
    //several scenarios prone to, or protected against, the visibility problem.

    //---------------------------------------------------------------------------------
    //fields

    //all objects intended to be used as synchronized monitors should be final
    private final DownloadStatus status;

    //---------------------------------------------------------------------------------
    //constructors

    public AbstractWaiter(DownloadStatus _status) {

        this.status = _status;
    }
    //AbstractWaiter

    //---------------------------------------------------------------------------------
    //methods

    public void printFinishedMessage() {

        System.out.println( "Waiter thread finished! " + String.format("%,d", this.status.getTotalBytes()) + " bytes downloaded in total." );
    }
    //printFinishedMessage

    public void waitWithEmptyLoopVanilla() {

        //wait (doing nothing) until the download has finished by
        //constantly checking if vanilla_done is true,
        //then exit the while loop when it is:

        while( !this.status.isDoneVanilla() );

        //an empty-body loop is prone to "thread divergence".
        //see note at the end of this file.
    }
    //waitWithEmptyLoopVanilla

    public void waitWithEmptyLoopVolatile() {

        //wait (doing nothing) until the download has finished by
        //constantly checking if volatile_done is true,
        //then exit the while loop when it is:

        while( !this.status.isDoneVolatile() );
    }
    //waitWithEmptyLoopVolatile

    public void waitWithSignals() {

        //instead of using an infinite loop,
        //we'll put the thread to sleep
        //with our status object (the shared resource)
        //being the mechanism that determines if the thread should
        //keep sleeping or wake up.

        synchronized(this.status) {
            try {

                //by calling wait() inside synchronized, this thread gains control of the status object as monitor.

                this.status.wait();

                //this thread can now only be awakened by another thread
                //calling notify() or notifyAll() inside synchronized on the same status object.
            }catch(InterruptedException e) {

                e.printStackTrace();
            }
            //try-catch
        }
        //synchronized
    }
    //waitWithSignals

    //---------------------------------------------------------------------------------
    //getters

    public DownloadStatus getStatus() {

        return this.status;
    }
    //getStatus
}
//AbstractWaiter

//about thread divergence:
/*
thread divergence
is a very (VERY) advanced mechanism of Java
that basically causes unpredictable background behavior
on empty-body infinite loops inside multithreaded code.
an explanation of it is beyond this course.

but in this case, suffice to say that
it behaves just like in a theoretical visibility problem scenario:
the updated state of status is never known,
because the while loop will never attempt to read it from anywhere but the RAM.

if we introduce any read/write operation inside the while body
on any value not local to this class
(for example a println() would write to the standard output)
then the updated state of status is known perfectly.
it's like magic. try it if you want.
*/

//eof
