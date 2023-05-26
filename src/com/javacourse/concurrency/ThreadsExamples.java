package com.javacourse.concurrency;

/*
    //wrapper class for Course 3 Section 7: Concurrency

    //import to Main class with:
    import com.javacourse.concurrency.ThreadsExamples; //or...
    import com.javacourse.concurrency.*;

    //call from main() method with:
    com.javacourse.concurrency.ThreadsExamples.runStreamsExamples(); //or...
    ThreadsExamples.runStreamsExamples();
*/

import com.javacourse.concurrency.downloaders.*;
import com.javacourse.concurrency.waiters.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadsExamples {

    //----------------------------------------------------------------------------
    //in order to avoid the try-catch boilerplate,
    //we'll define some custom methods here:

    public static void wrappedSleep(long ms) {
        try {
            Thread.sleep(ms);
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
        //try-catch
    }
    //wrappedSleep (a wrapper for sleep())

    public static void wrappedJoin(Thread t) {
        try {
            t.join();
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
        //try-catch
    }
    //wrappedJoin (a wrapper for join())

    public static void downloadedTotalBytesMessage(int total) {

        System.out.println("downloaded " + String.format("%,d", total) + " bytes in total.");
    }
    //downloadedBytesMessage

    //----------------------------------------------------------------------------

    public static void runThreadsExamples() {

        System.out.println("\n----------------------------------------");
        System.out.println("Processes and Threads\n");

        //a PROCESS is a logical construct made by the OS
        //which encapsulates everything about a program.

        //it includes the program's code, the reserved RAM for its variables
        //and handles for any other resources it might need.

        //a process is created when a program starts running
        //and exists as long as it's running.

        //every program has at least one thread: its main thread.

        //a thread is an ordered sequence of instructions,
        //its basically an algorithm (our code),
        //but at the bytecode level.

        //just like a process, a thread has its instructions,
        //its reserved memory, and its resources.

        //CPUs nowadays have multiple cores,
        //and every core can run multiple threads concurrently.

        //for example, a 4-core CPU with 2 threads per core
        //will be able to provide up to 4x2=8 threads concurrently.

        //here's those stats for the current CPU running this:

        System.out.println( "this program runs on " + Thread.activeCount() + " threads." );
        System.out.println( "your CPU provides up to " + Runtime.getRuntime().availableProcessors() + " threads." );

        //at the conceptual level, any two running threads will be independent
        //of each other, and capable of running concurrently (simultaneously).
        //regardless of if they belong to the same program or not.

        //let's stick with our hypothetical 8-thread CPU.
        //given 8 single-threaded programs, our CPU can run them all concurrently.
        //there's no slowdown of any kind. It's like we had 8 processors instead of 1.

        //however, programs are usually multi-threaded,
        //for example, this program itself uses 2 threads:
        //one main thread for this code, and another one for the garbage collector.

        //any developer may freely assume their program will be the only program running,
        //so, in order to boost the performance of the program,
        //they might (and usually will) use up as many threads as the CPU can provide.

        //for any CPU with N processing threads,
        //if we attempt to run more than N program threads simultaneously,
        //we'll run into a slowdown, because at least one of those program threads
        //will need to wait while doing nothing,
        //until some other program thread to releases a processing thread on the CPU.

        //that's a concept called scheduling
        //which is more complex than can be explained in this course
        //but it is a concern of the operating system, not ours or Java's, so...

        //in this section we'll assume an ideal scenario where we can freely
        //spawn as many threads as we want.

        //the main thread of a program is created automatically
        //while additional threads are created through code
        //by the main thread.

        //the main thread manages the rest of the threads belonging to its program
        //which includes interrupting them, pausing them, destroying them
        //and spawning new ones.

        //generally speaking, it is unsafe to work with threads
        //because if more than one thread attempts to write to some resource simultaneously
        //we would get unpredictable behavior.

        //each thread has a name and an ID.
        //here's the name of the main thread:

        System.out.println( "this is running on the thread: " + Thread.currentThread().getName() ); //it's called "main", what a surprise.

        //now you know why all Java programs boot from a method called main().
        //it's because that's the method which kickstarts the main thread!

        System.out.println("\n----------------------------------------");
        System.out.println("Starting a new Thread\n");

        //in Java, just about any block of code can be run in a separate thread.

        //all that is needed, is to put that code inside some method
        //which conforms to the run() method of the Runnable functional interface.

        //run() takes no args and returns void, so it's super easy to use.

        //in short: threadable code comes in the form of
        //implementations of the run() functional method.

        //if any such implementation is short and simple, it could even be a lambda.

        //but, if it's complex and requires a lot of code, we could do an entire class
        //which implements the Runnable interface explicitly
        //and overrides the run() method.

        //then such a class will be intended to be ran on its own thread.

        //such a scenario is simulated by our own DownloadFileSimple class.
        //it's a class intended for running a method which downloads a file.
        //but, every instance (object) of that class will run on its own thread.

        //Thread, just like everything in Java, is a class.
        //and its constructor requires a Runnable.

        Thread downloader1 = new Thread( new DownloadFileSimple() );

        //so, every Thread object has an inner Runnable.

        //a Thread object's start() method
        //calls the run() method of its inner Runnable.

        //our implementation of run() prints the name of the thread running it:

        downloader1.start(); //this will be Thread-0

        //as soon as the run() method ends, the thread running it automatically dies.

        //here's the interesting part:
        //my_first_thread has already been spawned and started
        //so we might assume it would have ran completely by this point
        //but no. since it's on a thread separate from this one
        //there are no guarantees on which thread will run or finish first.

        //if we print the main thread's name here,
        //it will probably be printed BEFORE my_first_thread's!
        //even though it appears later in this code!

        System.out.println( "main thread: " + Thread.currentThread().getName() ); //this gets printed before all else!

        //an even more dramatic example is by creating 5 threads and letting them run.
        //but who knows in what order!

        for(int i=0; i<5; i++) {
            Thread downloader = new Thread( new DownloadFileSimple() );
            downloader.start();
        }
        //for

        //in order to keep this section tidy
        //and keep the console output consistent
        //we'll make the main thread sleep
        //before starting the next lesson.
        //this gives time for the additional threads to finish.

        wrappedSleep(10); //wait a bit for all threads to end so the output is consistent

        System.out.println("\n----------------------------------------");
        System.out.println("Concurrency and Sleeps\n");

        //in order to demo how concurrency can speed up a program
        //we'll make every mock download wait 0.5 seconds before finishing
        //(as if each file took 0.5 seconds to download).
        //see the printWithSleep() method of DownloadFileSleep.

        //we make our additional threads wait or pause by calling
        //Thread.sleep() inside the run() method,
        //or, we make the main thread wait
        //by calling it anywhere else.

        //if we didn't have threads
        //we'd have to wait 0.5 seconds per download
        //before even starting the next one
        //(so 5 downloads would take 2.5 seconds)

        //uncomment this block if testing this lesson:
        System.out.println("These run in serial, so we gotta wait 2.5 seconds!\n");
        //for(int i=0; i<5; i++) {
        //    var downloader_object = new DownloadFileSleep();
        //    downloader_object.run();
        //}
        //for

        System.out.println();

        //but with threads
        //all downloads run concurrently
        //so they are all done in just 0.5 seconds
        //(they all wait during the same half second!)

        //uncomment this block if testing this lesson:
        System.out.println("These run in parallel, so we only wait 0.5 seconds!\n");
        //for(int i=0; i<5; i++) {
        //    Thread downloader = new Thread( new DownloadFileSleep() );
        //    downloader.start();
        //}
        //for

        //we've just ran 5 concurrent tasks.
        //but we could have ran hundreds!
        //how could that be? given our CPU can use 8 threads at most.
        //turns out the JVM has a built-in thread scheduler.
        //it gives each thread a slice of CPU time,
        //so quickly that it's seamless.

        //if the scheduler sees a thread in a sleeping state
        //it will try to give CPU time to some other thread.

        //there are more mechanisms involved in scheduling
        //but those are the basics of parallelism at the software level.

        //previous operations take 500 ms, we'll wait until they end:
        //wrappedSleep(510); //uncomment this if testing this lesson

        System.out.println("\n----------------------------------------");
        System.out.println("Awaiting and Joining\n");

        //in normal, single-threaded code, no line of code can run before those before it.
        //which means, previous instructions make the next instructions wait for their turn.
        //previous instructions block the next ones from running.
        //this is called synchronous or blocking code.

        //in contrast, multithreaded code is asynchronous and non-blocking.
        //which means, one thread will not wait for another to finish running its instructions.

        //however, there is an exception, which is to explicitly force
        //a thread to await another thread until it finishes.

        //this is useful in the following case:

        //there's a way to know, in the main thread, if another thread has finished
        //and then, run the next code being sure that the other thread has done its job.

        //it's called joining threads.
        //we do it by calling a thread's join() method.

        //by doing this, we join the additional thread to the main one as soon as it finishes.
        //this means the main thread will actually wait for the additional thread to finish.

        Thread downloader2 = new Thread( new DownloadFileSleep() );

        //uncomment this if testing this lesson:
        //System.out.println( Thread.currentThread().getName() + " thread says: I'm gonna wait..." );
        //downloader2.start();
        //wrappedJoin(downloader2); //this calls join() on the downloader2 thread
        //System.out.println( Thread.currentThread().getName() + " thread says: I've waited and now it's done!" );

        //this seems super useful, but actually
        //what we just did is losing the advantage of running asynchronous code
        //because now, our additional thread can block the main thread.

        //blocking the main thread happens when the main thread is waiting for something
        //(just like it happened above)
        //while it waits, it's frozen, so the user will see it as a program crash.

        System.out.println("\n----------------------------------------");
        System.out.println("Interrupting a Thread\n");

        //there is no way to forcefully abort an additional thread
        //from the main thread, while the additional thread is running.

        //however, we can send an "interrupt signal" to the additional thread
        //from the main thread.

        //the additional thread can check periodically
        //if the interrupt signal has been received
        //and if it has, it may do something in response
        //(no guarantees it will actually abort,
        //or of what it will do, if anything at all).

        //WHAT IS AN INTERRUPT SIGNAL:

        //an interrupt signal is in fact just a boolean variable,
        //so just like a flag, it can be raised (true) or lowered (false).

        //SENDING AND CATCHING INTERRUPT SIGNALS:

        //to send an interrupt signal to a Thread object just means
        //to have that object raise its interrupt flag.

        //to catch an interrupt signal in a Thread object just means that,
        //inside that object, it reads the state of its own interrupt flag,
        //and it turns out to be raised.

        //THREAD METHODS FOR HANDLING INTERRUPT SIGNALS:

        //the <some_thread>.interrupt() method
        //raises the interrupt flag of the some_thread object.

        //the Thread.currentThread().isInterrupted() method, called inside a thread,
        //returns the state of that thread's own interrupt flag.

        //the Thread.currentThread().interrupted() method does the same,
        //but also lowers the interrupt flag afterwards.

        //EXAMPLE:

        //in this example we simulate a download being performed bit by bit
        //and then interrupted mid-download after 5 seconds.
        //notice all we can do from here is call interrupt()
        //to send an interrupt signal:

        //uncomment this if testing it:
        Thread downloader3 = new Thread( new DownloadFileInterrupt() );
        //downloader3.start();
        //wrappedSleep(2500); //let the downloader thread work for a bit
        //downloader3.interrupt(); //this sends the interrupt signal

        //we see the interrupt signal gets sent after 5 seconds
        //and properly caught.
        //also, it was properly handled, given the additional thread was aborted
        //in response to the interrupt signal.

        wrappedSleep(10);

        System.out.println("\n----------------------------------------");
        System.out.println("Concurrency Issues\n");

        //a shared resource is anything that
        //more than one thread might want to read/write to
        //at the same time.

        //for example: a peripheral, a file or the network.

        //in our example use case, we could have a thread for monitoring
        //the progress of the downloads,
        //so, both it and the downloader thread would access
        //the (partially) downloaded file.

        //if there is more than one thread attempting to access the same resource
        //and at least one of them writes to it,
        //the resource would change in a way that's unexpected for the other threads.
        //we will refer to this as "unpredictable behavior".

        //unpredictable behavior may cause unwanted effects or results
        //or make our program crash.

        //PROBLEM #1: RACE CONDITIONS

        //notice, the thread that got to write to the resource
        //will be the only one that understands the state of that resource after the change.
        //so all threads want to be that thread!
        //no thread would want to be one of the other (clueless) threads!

        //now, consider that the first thread to write to the resource
        //will control it. so in practice:
        //all threads race to be the first to access the resource!
        //this is known as a "race condition".

        //PROBLEM #2: VISIBILITY PROBLEMS

        //imagine one thread made changes to some shared resource
        //safely from race conditions.

        //there could still be problems though.

        //the changes to the shared resource might not be visible to other threads
        //which means only the writer thread is aware of the resource's new state.

        //for example let's say our shared resource is a connection to a database.
        //imagine one thread closed said connection.
        //but other threads still think it's open.
        //they could eventually try to query the database, but
        //would get a runtime crash instead.

        //THREAD-SAFE CODE

        //thread-safe code is any multithreaded code that is so well-designed
        //it can't run into the issues described above.

        //for example, having a bunch of threads attempting to access a shared resource
        //but only for reading it, is thread-safe.
        //because the risks only appear when writing is involved.

        //many classes and methods in the Java Standard Library are thread-safe
        //which means we could have many instances of those, running concurrently
        //in separate threads, without any risk.

        System.out.println("this lesson is theory-only.");

        System.out.println("\n----------------------------------------");
        System.out.println("Race Conditions\n");

        //to demo a race condition
        //we'll use our own DownloadStatus class
        //which keeps track of the total amount of bytes
        //downloaded by every one of the downloader threads.

        //the downloader threads are those we've used earlier
        //like DownloadFileSimple or DownloadFileInterrupt.

        //so we'll have:
        //one thread running both main and DownloadStatus
        //plus N threads, each running a downloader.

        //this is the DownloadStatus object:
        var status1 = new DownloadStatus();

        //in this example, status is the shared resource.
        //the downloader threads will race to write to it.

        //we're gonna "new" up our threads inline below,
        //so we will need to store them somewhere, or
        //we won't have access to them.
        //so we create a list of downloader threads, but won't populate it yet:
        List<Thread> downloader_list1 = new ArrayList<>();

        //we spawn 5 downloader threads and start them:
        for(int i=0; i<5; i++) {

            Thread downloader = new Thread( new DownloadFileBytes(status1) ); //spawn a new downloader thread
            downloader.start(); //start it
            downloader_list1.add(downloader); //add it to the list

            //do NOT call join() here (see below).
        }
        //for

        //at this point, if the for loop is over
        //then all downloader threads have started.

        //remember, every thread is represented by an object of the Thread class
        //and every object is nothing more than a reference to a memory address.
        //so, a list of threads is just a list of memory addresses of thread objects.

        //and also, passing our status object to each thread
        //just means making that thread aware of the memory address
        //where its status-monitoring object can be found.

        //here we need to find out if/when all of them have finished.
        //since we've stored references to all of them in our list
        //we can make them join the main thread:

        for(Thread downloader : downloader_list1) {

            //we can finally call join() here
            //after the previous for loop:
            wrappedJoin(downloader); //this calls join() on the downloader thread

            //because if join() happened in the previous for loop
            //each loop iteration would require
            //awaiting for the spawned thread to finish
            //before starting the next iteration.
        }
        //for

        //each downloader will simulate a download of 10,000 bytes.
        //we spawned 5 threads, so, we expect a total count of 50,000 bytes.
        //but... we get something else:

        downloadedTotalBytesMessage( status1.getTotalBytes() );
        System.out.println("...huh? we expected 50,000 bytes!");

        //in fact, we get something different on each run!
        //what's wrong?

        //that's a race condition.
        //multiple threads try to write to the status object at the same time,
        //causing unpredictable results.

        //under the hood, imagine at some point the status
        //counts 10 bytes downloaded.
        //if two threads try to add 1 to it
        //the end result should be 12.

        //BUT, since it happens at the same time,
        //they both have an initial value of 10, so they do 10+1
        //and store 11 instead of 12.
        //it's like one write operation didn't happen.

        //in fact, it's possible that only 1 of every N simultaneous writes is actually effective.

        System.out.println("\n----------------------------------------");
        System.out.println("Avoiding Race Conditions\n");

        //here are a few (conceptual) ways to prevent race conditions:
        //(keep in mind, none of them is "the best one".
        //they may be more or less convenient, depending on the use case.)

        //IMMUTABILITY:

        //Immutability means that the shared resource is not writable.
        //which means, as we know, that all operations are read-only
        //and therefore thread-safe.

        //CONFINEMENT:

        //Confinement simply means not having shared resources.
        //any resource that could be shared between N threads
        //should instead be replicated into N instances and
        //each instance should be given to a thread.

        //after the threads finish, the resources could be
        //accumulated or combined somehow
        //to produce the desired result.
        //but this final step should only
        //happen in the main thread.

        //PARTITIONING:

        //this strategy is applicable when the shared resource is a collection
        //(like a List or Set).

        //Java provides some partitioned collection classes which essentially grant
        //each thread access to only a portion of the collection,
        //so that no item of the collection is ever accessed by more than one
        //thread at a time.

        //we say a partition of the collection is allocated to each thread.

        //ATOMICITY:

        //Java has a built-in mechanism known as "atomic classes".
        //these are classes that are thread-safe by design and are intended for
        //performing the kinds of tasks that are usually asynchronous.

        //for example, in our previous example we created a status object
        //which worked as a downloaded bytes accumulator (an integer).
        //with its risky code being an integer increment operation.

        //we could replace that with Java's AtomicInteger class
        //which is essentially a thread-safe integer,
        //providing thread-safe implementations of an integer's common operations.

        //SYNCHRONIZATION:

        //Synchronization is a confusing name. It kinda suggests
        //to make asynchronous code synchronous.

        //this is true to an extent, but not completely.

        //the "risky" portion of the code,
        //that is, the lines of code that actually access the shared resource
        //will be called the "critical section" from now on.

        //in synced code, access to the shared resource will
        //indeed be made synchronous, so there's no concurrent access
        //to it and therefore we achieve thread-safety.

        //however, the rest of the code in the threads is executed asynchronously.

        //in other words, only the critical section is made safe
        //by making threads wait until others finish accessing a resource,
        //before accessing it themselves.

        //the internal mechanism for this consists in identifying
        //the critical section, delimiting it,
        //and then placing locking or waiting (or both!) mechanisms around it.

        //locks and waits are explained in later lessons.

        //however...
        //there are several downsides to synchronization.
        //first of all, we lose the advantage of asynchronicity.
        //second, it's pretty hard to implement, as it is very error-prone.
        //and we're talking logical errors, not even runtime errors.
        //and third, we risk running into a deadlock,
        //which is when two threads wait for each other (which of course never ends).

        System.out.println("this lesson is theory-only.");

        System.out.println("\n----------------------------------------");
        System.out.println("Confinement\n");

        //this time, we will instantiate a new status object
        //for every downloader thread:

        //we're gonna "new up" our downloaders and our threads inline below,
        //so we will need to store them somewhere, or
        //we won't have access to them.

        //so we create lists for storing them:

        //a list for our downloader objects:
        List<DownloadFileBytes> download_object_list1 = new ArrayList<>();

        //and a list for our downloader threads:
        List<Thread> downloader_list2 = new ArrayList<>();

        //we spawn 5 downloader threads and start them:
        for(int i=0; i<5; i++) {

            var download_object = new DownloadFileBytes( new DownloadStatus() ); //create a new downloader with its own status object
            download_object_list1.add(download_object); //add it to the list

            Thread downloader = new Thread(download_object); //spawn a new downloader thread
            downloader.start(); //start it
            downloader_list2.add(downloader); //add it to the list
        }
        //for

        //just like before, at this point we can be sure all downloaders are
        //running independently,
        //but also, this time their status objects are counting the bytes properly!

        //wait until all threads are finished:
        for(Thread downloader : downloader_list2) {

            wrappedJoin(downloader); //this calls join() on the downloader thread
        }
        //for

        //each downloader will simulate a download of 10,000 bytes.
        //we spawned 5 threads, so, we expect a total count of 50,000 bytes.

        //this time we counted the bytes using independent status objects, so let's accumulate them
        //by mapping each downloader to the total bytes counted by its status object
        //and then sum-reducing all those totals.
        int total_bytes = download_object_list1.stream()
            .map(d -> d.getStatus().getTotalBytes())
            .reduce(0, Integer::sum);
        //download_object_list1.stream()

        //now we see the correct amount of bytes downloaded!
        downloadedTotalBytesMessage( total_bytes );
        System.out.println( "that's the right amount of bytes!" );

        System.out.println("\n----------------------------------------");
        System.out.println("Locks\n");

        //to fix the race condition we had before
        //we can lock down the shared status object.
        //this means we're actually locking down the code that
        //accesses the status object (the critical section).

        //if a thread attempts to run code inside the critical section,
        //the lock will be checked:

        //if it's unlocked, then it will become locked,
        //then the thread will run the code.
        //eventually, when the thread exits the critical section, the lock will be unlocked.

        //if it's locked, then it'll wait until it unlocks.
        //it means some other thread is accessing the critical section.

        //moreover, regardless if the lock is locked or unlocked,
        //if a thread attempts to run the critical section,
        //but there are other threads doing the same,
        //they will be organized into some structure,
        //usually a queue, sorted by arrival order.
        //so they can wait orderly, both for the lock to unlock and
        //for their turn.

        //see our implementations of the classes
        //DownloadStatus and DownloadFileBytesLock
        //to see how a lock is declared and called.

        //our shared DownloadStatus object has several methods
        //for incrementing the bytes counter:
        //in previous lessons we used incrementTotalBytes()
        //which is straightforward and thread-unsafe.
        //in this lesson we'll use incrementTotalBytesLock()
        //which is protected by a lock and thread-safe.

        //this is the shared status object:
        var status2 = new DownloadStatus();

        //we need a list of threads to keep track of them:
        List<Thread> downloader_list3 = new ArrayList<>();

        //create and run our downloader threads:
        for(int i=0; i<5; i++) {

            Thread downloader = new Thread( new DownloadFileBytesLock(status2) ); //spawn a new downloader thread
            downloader.start(); //start it
            downloader_list3.add(downloader); //add it to the list
        }
        //for

        //wait for all downloader threads to finish
        for(Thread downloader : downloader_list3) {

            wrappedJoin(downloader); //this calls dl_thread.join()
        }
        //for

        //now we see the correct amount of bytes downloaded!
        downloadedTotalBytesMessage( status2.getTotalBytes() );
        System.out.println( "that's the right amount of bytes!" );

        System.out.println("\n----------------------------------------");
        System.out.println("Synchronized Blocks\n");

        //there's a Java reserved keyword we've not seen yet: synchronized.

        //synchronized defines a code block (just like if or while)
        //inside of which we're supposed to write the critical section
        //of a shared resource.

        //it goes like this:

        //synchronized(<monitor>) {
        //    <the critical section>
        //}

        //where monitor is any object, even a simple, vanilla object of the Object class.

        //all objects have an internal lock,
        //plus lock-related methods like wait() and notify() (explained in a later lesson)
        //which are used by Java in the background (we don't have to call them explicitly!),
        //and provide the same behavior of explicit lock objects,
        //but with cleaner code.

        //as a best practice, we should have a different monitor Object
        //for every critical section of every shared resource.

        //this is the shared status object:
        var status3 = new DownloadStatus();

        //we need a list of threads to keep track of them:
        List<Thread> downloader_list4 = new ArrayList<>();

        //create and run our downloader threads:
        for(var i=0; i<5; i++) {

            Thread downloader = new Thread( new DownloadFileBytesSync(status3) ); //spawn a new downloader thread
            downloader.start(); //start it
            downloader_list4.add(downloader); //add it to the list
        }
        //for

        //wait for all downloader threads to finish
        for(Thread downloader : downloader_list4) {

            wrappedJoin(downloader); //this calls join() on the downloader thread
        }
        //for

        //now we see the correct amount of bytes downloaded!
        downloadedTotalBytesMessage( status3.getTotalBytes() );
        System.out.println( "that's the right amount of bytes!" );

        //BUT!
        //this whole lesson aside... synchronization is bad.
        //synchronization is really only ever used in legacy (old) code
        //because there are better and lighter alternatives to it now.

        //plus, synchronization can cause all sorts of inconveniences.
        //(see above!)

        System.out.println("\n----------------------------------------");
        System.out.println("The Visibility Problem\n");

        //let's move away from race conditions for a bit.

        //earlier we mentioned another possible problem of concurrency
        //which is the Visibility Problem.

        //from a high-level perspective, this means that at the start of
        //some multithreaded code, all threads see the same state of the shared resource,
        //but then, after one thread modifies the shared resource, other threads
        //won't be aware of said modification.

        //this causes the other threads to believe the shared resource has
        //some state, which is different from its actual state.

        //therefore, the solution to the visibility problem
        //is finding a way to keep the state of the shared resource
        //always up-to-date and accessible by all threads.

        //to explain what happens under the hood,
        //we need to understand that the RAM is one big memory space
        //which is common and accessible to all threads.

        //anything written to RAM is immediately visible by all threads,
        //so storing things in RAM is a good way to keep all threads equally updated.

        //however, RAM is slow when compared to the cache
        //which is physically inside the CPU.

        //if a thread needs speedy reads/writes, it should use the cache, not the RAM.
        //and turns out, the JVM uses the cache by default in multithreaded code,
        //because the JVM values performance rather than thread-safety.

        //as opposed to RAM, the cache is many small memory spaces,
        //so we actually have many caches. one per thread.
        //a thread's cache CANNOT be read/written by other threads.

        //now to explain the visibility problem,
        //the updated state of the shared resource is of course always in RAM,
        //but the JVM, to prevent overhead, makes each thread
        //NOT be constantly reading it from the RAM,
        //but instead, threads read/write to a (potentially outdated) copy of it on their CPU cache.

        //each thread writes the (believed-to-be) updated state of the shared resource
        //on its cache, once at the beginning of the thread execution,
        //and once more every time the thread writes to the resource.
        //but not when other threads write to it.

        //let's look at an example:

        //just like before, our shared resource will be a status (DownloadStatus) object.
        //we'll have 3 threads: one downloader thread (DownloadFileBytesDone) that writes to it,
        //one waiter thread (DownloadDoneWaiter), which will read it constantly expecting some value,
        //and the main thread.

        //this status object has a boolean field called vanilla_done,
        //which will be set to true in the downloader thread
        //when the download is finished.
        //it will also be constantly read by a waiter thread.
        final var status4 = new DownloadStatus();

        //we'll demo how the waiter thread is not up-to-date
        //because it can't see the new state of the status object
        //after the downloader thread has written to it:

        //we spawn a downloader thread:
        Thread downloader4 = new Thread( new DownloadFileBytesDone(status4) );

        //we spawn a waiter thread:
        Thread waiter1 = new Thread( new DownloadDoneWaiter(status4) );

        //both threads take the status object as dependency
        //and both are prone to the visibility problem.

        //start both the downloader and the waiter.
        //uncomment these if testing this lesson (they produce an infinite loop):
        //downloader4.start();
        //waiter1.start();

        //at this point we notice that
        //the execution of the program hangs indefinitely
        //because the waiter thread is stuck inside its while loop,
        //because it can't see the up-to-date state of a variable!

        //wrappedSleep(1000); //uncomment this if testing this lesson.

        System.out.println("\nis the waiter still running?");

        if( waiter1.isAlive() ) {
            System.out.println("yeah it is...");
            System.out.println("you might wanna comment out this whole lesson...");
            //waiter_thread.interrupt(); //interruptions don't quite work in the DownloadDoneWaiter class...
        }else {
            System.out.println("nope. it's not!");
        }
        //if-else

        //notice that in the DownloadStatus class
        //the methods that write to the vanilla_done variable
        //are actually protected by synchronized blocks.
        //plus, we have a single downloader thread, so
        //we can be absolutely sure we're not running into a race condition.
        //we are running into the visibility problem, an entirely different issue.

        //plus! since said methods are synchronized
        //access to the vanilla_done variable is in fact sequential, not concurrent.
        //so our code is both broken AND slow!

        System.out.println("\n----------------------------------------");
        System.out.println("Volatile variables\n");

        //the default caching behavior by the JVM
        //seems really inconvenient in cases where
        //many threads need to be aware of the updated state of a shared resource.

        //is there a way to override the JVM's default behavior?
        //yes there is: the "volatile" keyword.

        //we declare variables as volatile like:
        //volatile int num = 0;
        //or, if they're class fields:
        //private volatile int num = 0;

        //once a variable is declared as volatile,
        //it will never be cached.
        //forcing all threads to access it from the RAM
        //which is slower, but thread-safe.

        //visibility problem solved!

        //let's look at an example:

        //we'll have the same stuff as before:
        //one DownloadStatus object,
        //one downloader thread (DownloadFileBytesVolatile) that writes to it,
        //one waiter thread (DownloadDoneWaiterVolatile), which will read it constantly expecting some value,
        //and the main thread.

        //the only difference is that this time,
        //the variable to be read/written is volatile_done,
        //which is declared as volatile!
        final var status5 = new DownloadStatus();

        //we'll demo how the waiter thread is up-to-date
        //because now it can see the new volatile state of the status object
        //after the downloader thread has written to it:

        //we spawn a downloader thread:
        Thread downloader5 = new Thread( new DownloadFileBytesVolatile(status5) );

        //we spawn a waiter thread:
        Thread waiter2 = new Thread( new DownloadDoneWaiterVolatile(status5) );

        //both threads take the status object as dependency
        //and both are protected against the visibility problem.

        //start both the downloader and the waiter.
        downloader5.start();
        waiter2.start();

        //at this point we notice that our code works!
        //the waiter thread actually sees the correct value
        //of the volatile_done variable,
        //so its while loop doesn't hang like before!

        //this example had only one downloader thread,
        //just to demo visibility problems.
        //however, in real scenarios, we'll have many threads,
        //so we'll need to protect our code from both
        //visibility problems and race conditions.

        wrappedSleep(100);

        System.out.println("\n----------------------------------------");
        System.out.println("Signals, Wait and Notify\n");

        //we already know how to make a thread wait
        //for a determinate amount of time (using sleep()).

        //we also know how to make a thread wait
        //until another one is finished (using join()).

        //and in the last lesson, we learned that
        //we can make a thread wait until some condition is true.
        //(using an empty-body, potentially infinite, loop)

        //however, this third technique is prone to unexpected behavior,
        //plus it wastes CPU cycles, since it's constantly checking the condition.

        //there's a better way to make a thread wait on some condition:
        //it's called "thread signaling".

        //in Java, there are two signal handlers built into every Object.
        //the WAIT signal is handled by the wait() method,
        //and the NOTIFY signal is handled by the notify() method.

        //earlier we mentioned the synchronized keyword uses both signals internally
        //so we don't have to call those methods explicitly.
        //and that's true, but we can still use them explicitly if we want.

        //by calling wait() on any Object, running on any thread,
        //that thread will be put to sleep.

        //the thread will continue sleeping
        //until some other thread wakes it up.

        //in order to wake it up,
        //a reference to the same object that called wait()
        //must exist in some other thread.

        //then, in that thread, we can call the notify() method on that object.

        //if we have several threads waiting on the same object,
        //we can wake them all at once by calling notifyAll() instead of notify().

        //notice that, just like when explicitly calling sleep()
        //the wait() call must be wrapped inside a try-catch block.

        //moreover, the JVM expects us to put said
        //try-catch block inside a synchronized block.

        //and the monitor of said synchronized block
        //should be the Object that wait() was called on.

        //in a similar manner, calls to notify() and notifyAll()
        //should be inside a synchronized block
        //with the caller object as monitor.

        //so, wait() goes something like:

        //synchronized(some_object) {
        //    try{
        //        some_object.wait();
        //    }catch(InterruptedException e) {
        //        e.printStackTrace();
        //    }
        //}

        //notify() goes something like:

        //synchronized(some_object) {
        //    some_object.notify();
        //}

        //and notifyAll() goes something like:

        //synchronized(some_object) {
        //    some_object.notifyAll();
        //}

        //if you think it through, both solutions for race conditions we've seen,
        //synchronized and locks, are mechanisms based on WAITING.
        //which means wait() and notify() are, too, viable solutions for race conditions.

        //in this case we'll use it to protect against visibility problems,
        //since, in the past we were prone to those because we depended on a variable
        //that should be accessed across several threads.
        //this time, there is no such variable, only signals.

        //the synchronized blocks that we'll use here
        //are not used because we want to be protected against race conditions
        //but only because it's the syntax Java expects for using signals.

        //let's look at an example:

        //we'll have the same stuff as before:
        //one DownloadStatus object,
        //one downloader thread (DownloadFileBytesVolatile) that writes to it,
        //one waiter thread (DownloadDoneWaiterVolatile),
        //which will sleep and wait to be awakened when the status object has changed,
        //and the main thread.

        //this time, there's no variable being written
        //only signals to put objects to sleep and wake them up.
        final var status6 = new DownloadStatus();

        //we'll demo how the waiter thread is up-to-date
        //because it can wake up when the status object has changed
        //after the downloader thread has written to it:

        //we spawn a downloader thread:
        Thread downloader6 = new Thread( new DownloadFileBytesSignalled(status6) );

        //we spawn a waiter thread:
        Thread waiter3 = new Thread( new DownloadDoneWaiterSignalled(status6) );

        //both threads take the status object as dependency.

        //start both the downloader and the waiter.
        downloader6.start();
        waiter3.start();

        //at this point we notice that our code works!
        //the waiter thread actually wakes up at the proper moment
        //and prints a message at the end.

        wrappedSleep(100);

        System.out.println("\n----------------------------------------");
        System.out.println("Atomic Objects\n");

        //in our race condition example, we had an integer variable
        //(our shared resource) being incremented by many threads.

        //the problem is, under the hood,
        //the vanilla integer increment operation (++)
        //is internally performed as 3 steps (3 CPU instructions):
        //step #1: get current value from RAM
        //step #2: add 1 to it
        //step #3: write the result back into RAM.

        //(operations with more than one internal CPU instruction are called "complex")

        //those 3 steps happen at different times,
        //and if not synchronized, there could be a thread
        //performing one or more of the steps
        //before another thread finished performing all 3 steps.

        //the result will be several threads accessing the variable
        //in an alternating fashion <-- (which shouldn't happen!)
        //across the 3 steps, like:

        //imagine the variable's initial value is v=0
        //(at this point: the RAM sees v=0).

        //first, thread A does step 1
        //(at this point: RAM sees v=0, A sees v=0).
        //then thread B does step 1
        //(at this point: RAM sees v=0, A sees v=0, B sees v=0).

        //(this is already wrong, A hasn't finished its 3 steps and B has already started!)

        //then, A does step 2
        //(at this point: RAM sees v=0, A sees v=1, B sees v=0).
        //then, B does step 2
        //(at this point: RAM sees v=0, A sees v=1, B sees v=1).

        //(v has been incremented TWICE already, but no thread sees v=2!!!)

        //then, A does step 3
        //(at this point: RAM sees v=1, A sees v=1, B sees v=1).
        //then, B does step 3
        //(at this point: RAM sees v=1, A sees v=1, B sees v=1).

        //see the problem?
        //the end result should be v=2 in RAM, but it ended up as v=1.

        //now, if only we could do the increment operation
        //in a single step (a single CPU instruction)...

        //because, and this is a key property of CPUs:
        //no CPU can do TWO writes to the same RAM address in the same CPU cycle.
        //not even using two different threads.
        //so write access to any RAM address is synchronous by nature, which means
        //any single CPU instruction is protected from race conditions.
        //it's multi-instruction operations that are prone to it.

        //now you see it?
        //at the single-CPU-instruction level,
        //when concerning one shared RAM address,
        //"simultaneous" access is in fact alternating!

        //single-operation instructions are called "atomic"
        //since they can't be split into intermediate steps.

        //now, if only there was an atomic way to do an operation
        //that was equivalent to the vanilla, complex way...

        //luckily for us, Java provides alternate implementations of
        //booleans, integers, longs and generic Objects
        //for which all their write operations are atomic.

        //they are called "atomic objects", and are implemented by the classes:
        //AtomicBoolean, AtomicInteger, AtomicLong and AtomicReference<T>.

        //there are also AtomicIntegerArray, AtomicLongArray and AtomicReferenceArray<E>.
        //sadly, AtomicDouble doesn't exist, but can be manually implemented.
        //see here: https://stackoverflow.com/questions/5505460/java-is-there-no-atomicfloat-or-atomicdouble

        //see documentation on atomic objects here:
        //https://docs.oracle.com/en/java/javase/20/docs/api/java.base/java/util/concurrent/atomic/package-summary.html

        //let's see an example of
        //avoiding race conditions using an atomic integer:

        //we'll do our usual example of counting
        //total downloaded bytes, but this time
        //the counter variable is an atomic integer.

        //as usual, we instance our shared status object:
        var status7 = new DownloadStatus();

        //create a list of threads, to keep a reference of them:
        List<Thread> downloader_list5 = new ArrayList<>();

        //spawn 5 threads, and run an atomic downloader on each:
        for(var i=0; i<5; i++) {

            Thread downloader = new Thread( new DownloadFileBytesAtomic(status7) );
            downloader.start();
            downloader_list5.add(downloader);
        }
        //for

        //wait until all downloader threads are done
        for(Thread downloader : downloader_list5) {

            wrappedJoin(downloader);
        }
        //for

        //print the result.
        //we got the right amount of total bytes!
        downloadedTotalBytesMessage( status7.getTotalBytesAtomic() );
        System.out.println( "that's the right amount of bytes!" );

        System.out.println("\n----------------------------------------");
        System.out.println("Adders\n");

        //adders are number-wrapping classes
        //which are also protected against race conditions.

        //they can be compared to atomic types,
        //the difference being that adders take up a lot more RAM
        //but they perform faster.

        //adders are preferable to atomics
        //when multiple threads update a common sum frequently
        //but not for fine-grained synchronization control.

        //under the hood, adders basically are key-value maps of size N
        //which map from a thread to a list,
        //and they also hold a common initial value.

        //so every one of the N entries represents a thread,
        //and is created whenever a thread attempts to write to the adder,
        //so it grows dynamically.

        //each entry maps a thread to the list of increments/decrements
        //said thread has performed on the adder.

        //all writes are performed by the methods:
        //reset() which deletes all stored increments/decrements, and
        //add(x) which sums x (or subtracts x, in case x is negative) to the adder,
        //where x is actually stored as an increment/decrement,
        //not actually summed to the initial value.

        //there's also increment() and decrement()
        //which are shorthands for add(1) and add(-1).

        //all reads are performed by the methods:
        //sum(), which returns the initial value
        //with all stored increments/decrements from all threads applied
        //and intValue(), longValue(), floatValue() and DoubleValue()
        //which call sum() and then cast it to int/long/float/double.

        //there's also the sumThenReset() method
        //which is equivalent to calling sum() and reset() in succession.

        //let's do an example:

        //as usual, we instance our shared status object:
        var status8 = new DownloadStatus();

        //create a list of threads, to keep a reference of them:
        List<Thread> downloader_list6 = new ArrayList<>();

        //spawn 5 threads, and run an atomic downloader on each:
        for(var i=0; i<5; i++) {

            Thread downloader = new Thread( new DownloadFileBytesAdder(status8) );
            downloader.start();
            downloader_list6.add(downloader);
        }
        //for

        //wait until all downloader threads are done
        for(Thread downloader : downloader_list6) {

            wrappedJoin(downloader);
        }
        //for

        //print the result.
        //we got the right amount of total bytes!
        downloadedTotalBytesMessage( status8.getTotalBytesAdder() );
        System.out.println( "that's the right amount of bytes!" );

        System.out.println("\n----------------------------------------");
        System.out.println("Race Conditions on Collections\n");

        //in this next example, we try to modify the contents of a collection
        //in two separate threads.

        //we have an empty integer list, and then
        //one thread adds 1, 2 and 3
        //and the other one adds 4, 5 and 6

        //however, it is not guaranteed to have both modifications
        //present in the end result.

        //run this a few times,
        //you may get the expected result [1,2,3,4,5,6]
        //or you may get only [4,5,6].

        //so it's like playing the lottery, there's no predictability.
        //this happens because both threads race for access to the collection
        //and if both threads see the collection as empty,
        //none of them will consider the changes made by the other one.

        //in this case, the effect can be just getting [4,5,6]
        //which becomes the final result instead of [1,2,3]
        //only because it appears later in the code.
        //it's like thread 2 overwrites the changes made by thread 1.

        //create an empty collection (an arraylist)
        Collection<Integer> int_list = new ArrayList<>();

        //spawn two list updater threads, then
        //give them a Runnable lambda
        //which will add numbers to the arraylist:

        Thread list_updater1 = new Thread(() -> {
            int_list.addAll( Arrays.asList(1,2,3) );
        });

        Thread list_updater2 = new Thread(() -> {
            int_list.addAll( Arrays.asList(4,5,6) );
        });

        //start both threads:
        list_updater1.start();
        list_updater2.start();

        //make the main thread wait for both list updater threads
        //and then have both join the main thread when they finish:
        wrappedJoin(list_updater1);
        wrappedJoin(list_updater2);

        //check the resulting list:
        System.out.println(int_list);

        //you may need to run this a few times
        //to get both possible results.

        //but the point is,
        //this way we CANNOT predict what our program will do
        //so we need a safe way to avoid race conditions on collections!

        System.out.println("\n----------------------------------------");
        System.out.println("Synchronized Collections\n");

        //inside the Collections class, there are factory methods for creating
        //synchronized collections.

        //these are almost the same as the vanilla collection classes
        //except all their write operations are, internally,
        //wrapped inside synchronization mechanisms.

        //inside a synchronized collection object
        //there's a vanilla collection object, so
        //all the methods are still called the same.

        //test how both modifications on different threads
        //now produce the expected, correct result:

        //create an empty synchronized collection (which wraps an arraylist)
        Collection<Integer> int_list_sync = Collections.synchronizedCollection(new ArrayList<>());

        //spawn two list updater threads, then
        //give them a Runnable lambda
        //which will add numbers to the arraylist:

        Thread list_updater3 = new Thread(() -> {
            int_list_sync.addAll( Arrays.asList(1,2,3) );
        });

        Thread list_updater4 = new Thread(() -> {
            int_list_sync.addAll( Arrays.asList(4,5,6) );
        });

        //start both threads:
        list_updater3.start();
        list_updater4.start();

        //make the main thread wait for both list updater threads
        //and then have both join the main thread when they finish:
        wrappedJoin(list_updater3);
        wrappedJoin(list_updater4);

        //check the resulting list:
        System.out.println(int_list_sync);

        System.out.println("\n----------------------------------------");
        System.out.println("About Immutability\n");

        //in previous lessons, we've demonstrated how to prevent
        //race conditions via confinement, synchronization and atomicity.

        //we have not seen an example of immutability,
        //however, it's as simple as having only shared object(s)
        //that aren't actually writable.

        //for example, Strings are immutable.
        //"write" operations on Strings don't in fact
        //modify it, but generate a new String instead.

        System.out.println("this lesson is theory-only.");

        System.out.println("\n----------------------------------------");
        System.out.println("Race Conditions on Vanilla HashMaps\n");

        //given Map is not a subinterface of Collection,
        //plus it has many exclusive mechanisms,
        //race conditions produce some unique effects on HashMaps.

        //let's do an example with a key-value mapping
        //that maps from an unsafe key object to an integer:

        //our map keys (called the "unsafe keys") are just objects
        //whose hashcode is always 42,
        //which means we will have collisions on all hashmap entries.

        //this forces the hashmap to perform
        //collision-handling (extra steps) on every insertion.
        //more steps means more chances for unpredictable behavior
        //if running on many threads.

        //first let's see how a vanilla Map works:

        //create a new Map
        Map<UnsafeKey,Integer> vanilla_map = new HashMap<>();

        //next we'll create two updater threads,
        //which will add 2000 new entries to the map
        //with all keys colliding.

        Thread map_updater1 = new Thread(() -> {
            for(int i=0; i<1000; ++i) {
                vanilla_map.put(new UnsafeKey(i), i); //collision-handler could crash at runtime...
            }
            //for
        });
        //map_updater1

        Thread map_updater2 = new Thread(() -> {
            for(int i=2000; i<3000; ++i) {
                vanilla_map.put(new UnsafeKey(i), i); //collision-handler could crash at runtime...
            }
            //for
        });
        //map_updater2

        //at this point we may run only one thread or both.
        //one thread works fine, two produce unpredictable behavior:

        //start the threads:
        map_updater1.start();
        map_updater2.start(); //to test normal behavior, comment this so only map_updater1 works

        //make the main thread wait for both list updater threads
        //and then have both join the main thread when they finish:
        wrappedJoin(map_updater1);
        wrappedJoin(map_updater2);

        //check the resulting list:
        System.out.println( vanilla_map.size() );
        System.out.println( vanilla_map );

        //run this a few times to see the possible effects, which could be:
        ////// only one key-value pair inserted.
        ////// less than 2000 pairs inserted.
        ////// X pairs inserted, but the size reported is different from X.
        ////// the program hangs indefinitely.
        ////// a runtime crash at the collision handling code.

        //in case the previous code crashes at runtime
        //print the stacktrace before the start of the next lesson
        wrappedSleep(10);

        System.out.println("\n----------------------------------------");
        System.out.println("Concurrent Collections\n");

        //...which leaves us with the last
        //defense against race conditions: partitioning.

        //the partitioning of collections is what Concurrent Collections do.

        //Concurrent Collections are a kind of collections,
        //similar to Synchronized Collections, which are protected
        //against race conditions.

        //the difference is that
        //synchronized collections, like their name implies,
        //are synchronous (at least at the moment they write to the shared resource)
        //which means they're slower.

        //the technique applied by Concurrent Collections
        //is to partition the collection into N segments,
        //one per every thread attempting to access it.

        //any thread writing to the collection at any given instant
        //can write only to its assigned segment.

        //let's see the same scenario from the previous example,
        //now using a ConcurrentHashMap instead:

        //create a new Map
        Map<UnsafeKey,Integer> concurrent_map = new ConcurrentHashMap<>();

        //next we'll create two updater threads,
        //which will add 2000 new entries to the map
        //with all keys colliding.

        Thread map_updater3 = new Thread(() -> {
            for(int i=0; i<1000; ++i) {
                concurrent_map.put(new UnsafeKey(i), i);
            }
            //for
        });
        //map_updater3

        Thread map_updater4 = new Thread(() -> {
            for(int i=2000; i<3000; ++i) {
                concurrent_map.put(new UnsafeKey(i), i);
            }
            //for
        });
        //map_updater4

        //start the threads:
        map_updater3.start();
        map_updater4.start(); //to test normal behavior, comment this so only map_updater1 works

        //make the main thread wait for both list updater threads
        //and then have both join the main thread when they finish:
        wrappedJoin(map_updater3);
        wrappedJoin(map_updater4);

        //check the resulting list:
        System.out.println( concurrent_map.size() );
        System.out.println( concurrent_map );

        //now we see the expected behavior, which is
        //2000 key-value pairs inserted and reported.
    }
    //runThreadsExamples
}
//ThreadsExamples

//eof
