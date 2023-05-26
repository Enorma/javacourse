package com.javacourse.concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DownloadStatus {

    //this class is intended to be instanced only once (except in lesson 10!)
    //as an object that will accumulate the total bytes downloaded
    //by the downloader threads.

    //it also serves as a place to provide several mechanisms like
    //locks, synchronization, monitors and volatile variables.

    //all classes starting with "DownloadFile" are downloader Runnables
    //meant to be ran on their own thread (downloader threads).
    //they take an object of this class as dependency.

    //all classes starting with "DownloadDoneWaiter" are Runnables
    //for checking if a download has finished.
    //they also run on their own thread (waiter threads)
    //and take an object of this class as dependency.

    //race condition-related examples will have
    //several concurrent downloader threads, to demo the threads racing each other.

    //visibility problem-related examples will have
    //just one downloader thread, and one waiter thread,
    //to demo the waiter thread being able/unable to read a value written
    //by the downloader thread.

    //---------------------------------------------------------------------------------
    //fields

    //COUNTERS:

    //these counters simply accumulate the total amount of bytes
    //downloaded by all downloader classes that
    //hold the same object of this class.

    //vanilla, used before lesson 15
    private int totalBytes = 0;

    //atomics do all their write operations
    //as single-CPU-instruction operations,
    //so they are immune to race conditions (used in lesson 15).
    private AtomicInteger atomicTotalBytes = new AtomicInteger(0);

    //adders are also immune to race conditions
    //but they are faster than atomics, so they're
    //better suited for when writes are frequent (used in lesson 16).
    private LongAdder adderTotalBytes = new LongAdder(); //DoubleAdder also exists

    //LOCKS:

    //a Lock is actually an interface in the Java Standard Library.
    //one implementation of it that we can use is ReentrantLock.
    //we'll make this class thread-safe by introducing a Lock:
    private Lock threadlock = new ReentrantLock(); //used in lesson 11

    //MONITORS:

    //a monitor is a vanilla Object which is only useful
    //because of its built-in internal lock mechanisms.
    //we use it in the synchronized blocks below (lesson 12).
    private final Object incrementerMonitor = new Object(); //by convention, should be a final Object.

    //these two monitors are used to demonstrate that
    //even when protected against race conditions
    //the visibility problem can still arise.

    //a volatile variable is forced to be stored/read/written
    //only in RAM, never in cache.
    //so its up-to-date state will always be accessible by all threads.
    //we make variables volatile to make them immune to the visibility problem.

    //we'll use these two monitors and two done flags
    //to demo the difference between vanilla and volatile variables.
    //(these are used in lesson 13).
    private final Object vanillaMonitor = new Object();
    private final Object volatileMonitor = new Object(); //not needed unless we had several concurrent downloader threads

    //these are true if the download thread has finished downloading, false otherwise:
    private boolean vanilla_done;
    private volatile boolean volatile_done;

    //---------------------------------------------------------------------------------
    //methods

    public void incrementTotalBytes() {
        //this is just a vanilla incrementer method.
        //used before lesson 11.
        this.totalBytes++;
    }
    //incrementTotalBytes (before lesson 11)

    public void incrementTotalBytesLock() {

        //this is an incrementer wrapped inside a lock.
        //used in lesson 11.

        this.threadlock.lock(); //this locks the critical section

        try {
            //any locked code MUST be inside a try block
            this.totalBytes++; //this is the critical section
        }finally {
            //in case the critical section crashes at runtime
            //we use this finally block to ensure the lock is unlocked.
            //otherwise it would remain locked forever,
            //causing a deadlock!
            this.threadlock.unlock(); //this unlocks the critical section
        }
    }
    //incrementTotalBytesLock (lesson 11)

    public void incrementTotalBytesSync() {

        //this is an incrementer wrapped inside a synchronized block.
        //used in lesson 12.

        //as we can see, we don't need an explicit lock object
        //nor a try-finally block,
        //so this is cleaner!

        synchronized(this.incrementerMonitor) {
            this.totalBytes++;
        }
        //synchronized
    }
    //incrementTotalBytesSync (lesson 12)

    public synchronized void incrementTotalBytesBad() {

        //this entire method is implicitly inside a synchronized block
        //because it is declared as synchronized on the method signature.

        //it's like the whole method is a critical section!

        //looks super clean and convenient...
        //HOWEVER, this is a bad practice.

        //because, the monitor object for the synchronized block
        //in this case would be the object this.

        //which means, if we have more than one synchronized method
        //like this one, they'd all have the same monitor: this.

        //it is a BAD PRACTICE to have more than one critical section
        //use the same monitor.
        //every critical section should have a separate, explicit, dedicated monitor!

        this.totalBytes++;
    }
    //incrementTotalBytesBad (lesson 12)

    public void incrementTotalBytesAtomic() {

        this.atomicTotalBytes.incrementAndGet();
    }
    //incrementTotalBytesAtomic (lesson 15)

    public void incrementTotalBytesAdder() {

        this.adderTotalBytes.increment();
    }
    //incrementTotalBytesAdder (lesson 16)

    //---------------------------------------------------------------------------------
    //getters

    public int getTotalBytes() {

        return this.totalBytes;
    }
    //getTotalBytes (before lesson 15)

    public int getTotalBytesAtomic() {
        //get() returns the int value wrapped by the AtomicInteger.
        return this.atomicTotalBytes.get();
    }
    //getTotalBytesAtomic (lesson 15)

    public int getTotalBytesAdder() {

        return this.adderTotalBytes.intValue();
    }
    //getTotalBytesAdder (lesson 16)

    public boolean isDoneVanilla() {

        return this.vanilla_done;
    }
    //isDoneVanilla (lesson 13)

    public boolean isDoneVolatile() {

        return this.volatile_done;
    }
    //isDoneVolatile (lesson 13)

    //---------------------------------------------------------------------------------
    //setters

    public void setDoneVanilla() {
        synchronized(this.vanillaMonitor) {
            this.vanilla_done = true;
        }
        //synchronized
    }
    //setDoneVanilla (lesson 13)

    public void resetDoneVanilla() {
        synchronized(this.vanillaMonitor) {
            this.vanilla_done = false;
        }
        //synchronized
    }
    //resetDoneVanilla (lesson 13)

    public void setDoneVolatile() {

        this.volatile_done = true;
    }
    //setDoneVolatile (lesson 13)

    public void resetDoneVolatile() {

        this.volatile_done = false;
    }
    //resetDoneVolatile (lesson 13)
}
//DownloadStatus

//eof
