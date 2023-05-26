package com.javacourse.concurrency;

public abstract class AbstractDownloader implements Runnable {

    //downloader class #0

    //this class mocks an actual file download,
    //because all we try to demo is the behavior of threads, not downloads.

    //the run() method is intended to be overridden in the subclasses,
    //which call different methods of this class,
    //since different functionalities are needed in the different lessons.

    //---------------------------------------------------------------------------------
    //fields

    private String name = "main";
    private final DownloadStatus status;

    //---------------------------------------------------------------------------------
    //constructors

    public AbstractDownloader() {

        this.status = new DownloadStatus();
    }
    //AbstractDownloader

    public AbstractDownloader(DownloadStatus _status) {

        this.status = _status;
    }
    //AbstractDownloader (for lesson 8 onwards)

    //---------------------------------------------------------------------------------
    //methods

    public void wrappedSleep(long ms) {
        try {
            Thread.sleep(ms);
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
        //try-catch
    }
    //wrappedSleep

    public void downloadedBytesMessage(int current_total, int final_total) {

        System.out.println("bytes downloaded on " + this.name + ": " + String.format("%,d", current_total) + " of " + String.format("%,d", final_total));
    }
    //downloadedBytesMessage

    public void downloadedDoneMessage() {

        System.out.println("download completed by " + this.name + ".");
    }
    //downloadedDoneMessage

    //---------------------------------------------------------------------------------
    //download mock methods

    public void printSimple() {

        System.out.println( "download on " + this.name + "... done!" );
    }
    //printSimple (for lesson 3)

    public void printWithSleep() {
        System.out.println( this.name + " says: downloading..." );
        this.wrappedSleep(500);
        System.out.println( this.name + " says: download finished!" );
    }
    //printWithSleep (for lessons 4 and 5)

    public void printAndCatchInterrupt() {

        int big_number  = 20_000_000;
        int print_every =  2_500_000;

        for(int i=0; i<big_number; i++) {

            //print only some values to avoid flooding the output
            if(i % print_every == 0) downloadedBytesMessage(i, big_number);

            //SOMETHING TO CONSIDER:
            //if a thread catches an interrupt signal
            //while it is in the sleeping or waiting state
            //it will crash at runtime (throwing InterruptedException).
            //therefore:

            //if we know a thread expects an interrupt signal
            //and we want to call sleep() (or another built-in waiting mechanism) inside it,
            //we should wrap it inside a try-catch block.
            //so calling sleep() by itself is in fact a checked exception and will crash at compile-time.

            //SOMETHING ELSE TO CONSIDER:
            //if an interrupt signal is caught while sleeping (crashing at runtime),
            //the interrupt flag will be lowered immediately afterwards.
            //therefore:

            //inside the catch block
            //we should have the thread send itself the interrupt signal again
            //so that it's still raised whenever the sleep/wait period ends
            //and then our actual interruption-handling code can handle it.

            //like this:
            try {
                Thread.sleep(0); //we sleep just a bit, to demo this try-catch block
            }catch(InterruptedException e) {
                Thread.currentThread().interrupt(); //re-raise the interrupt flag
            }
            //try-catch

            //if the interrupt flag was raised while sleeping,
            //thanks to the try block, we won't crash!
            //and thanks to re-raising the interrupt flag,
            //it will still be raised at this point!

            //here we check if we've received an interrupt signal
            if( Thread.currentThread().isInterrupted() ) {
                System.out.println("download was interrupted.");
                return;
            }
            //if
        }
        //for
    }
    //printAndCatchInterrupt (for lesson 6)

    public void printBytes() {

        int big_number  = 10_000;
        int print_every =  5_000;

        downloadedBytesMessage(0, big_number);

        for(int i=1; i<=big_number; i++) {

            //print only some values to avoid flooding the output
            if(i % print_every == 0) downloadedBytesMessage(i, big_number);

            this.status.incrementTotalBytes(); //this incrementer method is NOT protected against race conditions!
        }
        //for
    }
    //printBytes (for lessons 8 and 10)

    public void printBytesLock() {

        int big_number  = 10_000;
        int print_every =  5_000;

        downloadedBytesMessage(0, big_number);

        for(int i=1; i<=big_number; i++) {

            //print only some values to avoid flooding the output
            if(i % print_every == 0) downloadedBytesMessage(i, big_number);

            this.status.incrementTotalBytesLock(); //this incrementer method is protected by a lock
        }
        //for
    }
    //printBytesLock (for lesson 11)

    public void printBytesSync() {

        int big_number  = 10_000;
        int print_every =  5_000;

        downloadedBytesMessage(0, big_number);

        for(int i=1; i<=big_number; i++) {

            //print only some values to avoid flooding the output
            if(i % print_every == 0) downloadedBytesMessage(i, big_number);

            this.status.incrementTotalBytesSync(); //this incrementer method is protected by a synchronized block
        }
        //for
    }
    //printBytesSync (for lesson 12)

    public void printBytesDone() {

        int big_number  = 1_000_000;
        int print_every =   250_000;

        downloadedBytesMessage(0, big_number);

        for(int i=1; i<=big_number; i++) {

            //print only some values to avoid flooding the output
            if(i % print_every == 0) downloadedBytesMessage(i, big_number);

            //this incrementer method is NOT protected against race conditions!
            //however, lesson 13 uses a single thread so there are no race conditions anyway.
            this.status.incrementTotalBytes();
        }
        //for

        downloadedDoneMessage();

        //set flag to true, indicating the download has finished.
        this.status.setDoneVanilla(); //not protected against the visibility problem
    }
    //printBytesDone (for lesson 13)

    public void printBytesVolatile() {

        int big_number  = 1_000_000;
        int print_every =   250_000;

        downloadedBytesMessage(0, big_number);

        for(int i=1; i<=big_number; i++) {

            //print only some values to avoid flooding the output
            if(i % print_every == 0) downloadedBytesMessage(i, big_number);

            //this incrementer method is NOT protected against race conditions!
            //however, lesson 13 uses a single thread so there are no race conditions anyway.
            this.status.incrementTotalBytes();
        }
        //for

        downloadedDoneMessage();

        //set flag to true, indicating the download has finished.
        this.status.setDoneVolatile(); //protected against the visibility problem
    }
    //printBytesVolatile (for lesson 13)

    public void printBytesSignalled() {

        int big_number  = 1_000_000;
        int print_every =   250_000;

        downloadedBytesMessage(0, big_number);

        for(int i=1; i<=big_number; i++) {

            //print only some values to avoid flooding the output
            if(i % print_every == 0) downloadedBytesMessage(i, big_number);

            //this incrementer method is NOT protected against race conditions!
            //however, lesson 14 uses a single thread so there are no race conditions anyway.
            this.status.incrementTotalBytes();
        }
        //for

        downloadedDoneMessage();

        //notify a thread that is sleeping on the status object that it should wake up.
        //by calling notify() inside synchronized, this thread gains control of the status object as monitor.
        //otherwise we'd have a runtime crash!
        synchronized(this.status) {
            this.status.notify();
        }
        //synchronized
    }
    //printBytesSignalled (for lesson 14)

    public void printAtomic() {

        int big_number  = 10_000;
        int print_every =  5_000;

        downloadedBytesMessage(0, big_number);

        for(int i=1; i<=big_number; i++) {

            //print only some values to avoid flooding the output
            if(i % print_every == 0) downloadedBytesMessage(i, big_number);

            this.status.incrementTotalBytesAtomic(); //this incrementer method is protected against race conditions!
        }
        //for
    }
    //printAtomic (for lesson 15)

    public void printAdder() {

        int big_number  = 10_000;
        int print_every =  5_000;

        downloadedBytesMessage(0, big_number);

        for(int i=1; i<=big_number; i++) {

            //print only some values to avoid flooding the output
            if(i % print_every == 0) downloadedBytesMessage(i, big_number);

            this.status.incrementTotalBytesAdder(); //this incrementer method is protected against race conditions!
        }
        //for
    }
    //printAdder (for lesson 16)

    //---------------------------------------------------------------------------------
    //getters

    public String getName() {

        return this.name;
    }
    //getName

    public DownloadStatus getStatus() {

        return this.status;
    }
    //getStatus

    //---------------------------------------------------------------------------------
    //setters

    public void setName(String _name) {

        this.name = _name;
    }
    //setName
}
//AbstractDownloader

//eof
