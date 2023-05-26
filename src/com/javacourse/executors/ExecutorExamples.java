package com.javacourse.executors;

/*
    //wrapper class for Course 3 Section 8: Executors

    //import to Main class with:
    import com.javacourse.executors.ExecutorExamples; //or...
    import com.javacourse.executors.*;

    //call from main() method with:
    com.javacourse.executors.ExecutorExamples.runExecutorExamples(); //or...
    ExecutorExamples.runExecutorExamples();
*/

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.*;

public class ExecutorExamples {

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

    public static void wrappedRunnableExecutor(int nThreads, List<Runnable> jobs) {

        try(
            ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        ) {
            for( Runnable j : jobs ) executor.submit(j);
        }catch( Exception e ) {
            e.printStackTrace();
        }
        //try-catch
    }
    //wrappedRunnableExecutor

    public static <V> Map<String,Future<V>> wrappedCallableExecutor(int nThreads, Map<String,Callable<V>> jobs) {

        Map<String,Future<V>> futures = new HashMap<>();

        try(
            ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        ) {
            for( Entry<String,Callable<V>> j : jobs.entrySet() ) {
                futures.put(
                    j.getKey(),
                    executor.submit( j.getValue() )
                );
                //put
            }
            //for
        }catch( Exception e ) {
            e.printStackTrace();
        }
        //try-catch

        return futures;
    }
    //wrappedCallableExecutor

    //----------------------------------------------------------------------------
    //these 3 methods are used in lesson 12:

    public static <T> CompletionStage<T> getStartStage(T seed) {

        //explicitly create and return a new, ready to use CompletionStage
        //which may be used to kickstart a new chain
        //or as the return value of the thenCompose() or thenComposeAsync() methods:

        return CompletableFuture.completedStage(seed);
    }
    //getStartStage

    //create new CompletionStages since these will be used in conjunction
    //with the thenCompose() or thenComposeAsync() methods:

    public static CompletionStage<String> idToEmailAsync(String id) {

        //mock the logic that, given the id, we can obtain the email.
        //take the id, then return the email wrapped inside a CompletionStage:

        return getStartStage(id).thenApplyAsync(_id -> "xyz@xyz.xyz");
    }
    //idToEmailAsync

    public static CompletionStage<String> emailToPlaylistAsync(String email) {

        //mock the logic that, given the email, we can obtain the playlist.
        //take the email, then return the playlist wrapped inside a CompletionStage:

        return getStartStage(email).thenApplyAsync(_email -> "songX...songY...songZ");
    }
    //emailToPlaylistAsync

    //----------------------------------------------------------------------------

    public static void runExecutorExamples() {

        //uncomment this next block to run all lessons
        /*

        System.out.println("\n----------------------------------------");
        System.out.println("Thread Pools\n");

        //In terms of the amount of threads at work simultaneously,
        //there are risks when our program grows too big:
        //we may create too many threads and consume all our RAM, or
        //we may waste too many CPU cycles in spawning/despawning threads,
        //which is in fact pretty expensive in computation terms.

        //These risks appear when we're dealing with threads manually.
        //And so, Java came up with a solution called Thread Pools.

        //Thread Pools are basically auto-managed threads.

        //In a Thread Pool, there's an arbitrary amount of threads
        //called "workers". These can be idle or performing a task.

        //If one worker finishes a job, it will be put back into the pool,
        //and it will wait for some other job to be assigned to it.

        //so, Thread Pools reuse threads. This is better than
        //managing threads manually, which treats threads as disposable.

        //Thread Pools avoid the overhead of spawning/despawning threads!

        //Thread Pools also avoid hitting the RAM limit,
        //since it always has a manageable amount of threads alive,
        //and it never creates more.

        //Thread Pools also help with scheduling!
        //If there are more pending jobs than available workers,
        //the thread pool will make the idle workers line up in a queue.
        //so they will be assigned their tasks in an orderly fashion.

        //So all we have to do is
        //create a thread pool, and then
        //give it the tasks we want to perform.
        //The thread pool takes care of all the rest!

        System.out.println("\n----------------------------------------");
        System.out.println("Executors\n");

        //Thread Pools are implemented in our code
        //via the ExecutorService interface
        //and (most commonly) the ThreadPoolExecutor class.

        //these are in the package java.util.concurrent.

        //However, the proper way to instantiate those
        //is via the Executors utility class.
        //it includes several factory methods which give us
        //instances of a thread pool.

        //here we'll use the newFixedThreadPool() method
        //to instance a ThreadPoolExecutor with the amount of workers we want.

        //we could use var here, but we'll make the datatype explicit for clarity:
        ExecutorService executor1 = Executors.newFixedThreadPool(2);

        //at this point, our executor variable is of the datatype
        //of the ExecutorService interface.
        //but, at runtime, it will be an instance
        //of the ThreadPoolExecutor class.

        //now that we have an executor,
        //we can pass tasks to it, using the submit() method.
        //(at least one of the overloads of) submit() takes a Runnable as arg.

        //we'll do a simple task where the running thread prints its name:

        executor1.submit(() -> {
            System.out.println( Thread.currentThread().getName() );
        });

        //you can see the names of our threads
        //are something like: "pool-X-thread-Y".

        //give time for other threads' output to be printed
        //then add a newline to clean the output a bit.
        wrappedSleep(800);
        System.out.println();

        //see? we didn't have to explicitly create a worker thread
        //with "new Thread()".
        //our executor took care of that.
        //it even created more than one worker!

        //let's use both workers at the same time.
        //also, let's see what happens if we give our executor
        //more tasks than workers:

        //submit 10 tasks to only 2 workers:
        for(int i=0; i<10; i++) {

            final int n = i; //i will be used inside the lambda, so we need a final copy of it

            executor1.submit(() -> {
                System.out.println( "task " + n + " running on " + Thread.currentThread().getName() );
            });
            //submit
        }
        //for

        //run that a few times to see how the worker assigned
        //to every job is random and unpredictable.
        //it depends on the state of our CPU.

        //also, notice how there's no manual start()
        //for the individual workers. The executor takes care of that.
        //that means as soon as we submit a task,
        //we should expect it to run.

        wrappedSleep(800);
        System.out.println();

        //after running the previous examples
        //we noticed we have to close our program manually.
        //that's because the executor, and all its workers, will keep running.
        //(they may be idle, but they are still alive!)

        //this is because workers are supposed to be
        //always ready to run. They are always alive
        //and waiting for some next task.

        //so, to close an executor and despawn its workers
        //we need to do so manually.

        //there are two methods for that:

        //shutdown() prevents new tasks to be assigned,
        //then waits for each worker to become idle
        //then despawns each worker when it does.

        //shutdownNow() forcefully aborts all jobs
        //and despawns every worker.

        executor1.shutdown();

        //however...
        //what would happen if we got a runtime crash
        //before shutdown() gets called?
        //then shutdown() would never be called,
        //and we'd leave our workers hanging.

        //so, we should submit our tasks to our executor
        //inside a try block
        //and then call shutdown()
        //inside a finally block:

        //there's no way to restart an executor, so
        //to test this, we need to re-instantiate it:
        executor1 = Executors.newFixedThreadPool(2);

        try {
            executor1.submit(() -> {
                System.out.println( Thread.currentThread().getName() );
            });
            //submit
        }finally {
            executor1.shutdown();
        }
        //try-finally

        //an even better way to organize our code
        //would be to wrap ALL the executor-related code
        //inside a try-with-resources statement, like this:

        //first, put the executor declaration, instantiation and initialization inside the resources block.
        //second, put the job submissions and all extra executor-related code inside the try block.
        //last (optional), add the appropriate catch blocks for whatever exception(s) might happen.

        //since we initialized the executor in the resources block
        //and the ExecutorService interface extends the AutoCloseable interface
        //we don't need to explicitly call shutdown() inside a finally block.

        //there's an example of that in the next lesson.
        //also, see our executor wrapper methods at the top.

        //KEEP IN MIND:
        //executors and thread pools only simplify thread management
        //but they DON'T prevent race conditions nor visibility problems.
        //we still have to deal with those manually.

        wrappedSleep(800);

        System.out.println("\n----------------------------------------");
        System.out.println("Callables and Futures\n");

        //Callable is a functional interface very similar to Runnable.

        //simply put, a Callable is a Runnable that actually returns something.

        //so, where Runnables return void,
        //Callables return a value of some generic type <V>.

        //just like Runnables have the functional method run(),
        //Callables have call().

        //so, if we add a return statement to a Runnable lambda
        //it becomes a Callable lambda.

        //if we pass a Callable into the executor.submit() method
        //some worker will run the call() method (or lambda) of the Callable,
        //and then return the return value of it.

        //however, the return value of a Callable,
        //as returned by the worker,
        //will be wrapped into something called a "Future".
        //Future is another interface in the java.util.concurrent package.

        //so, if some Callable call() method or lambda
        //returns, say, an Integer,
        //then the worker that ran it will in fact return
        //an object of the datatype Future<Integer>.

        //Future is commonly implemented by the FutureTask class.

        //let's do an example.

        Future<Integer> future1 = null;

        try(
            //initialize the executor here so it auto shuts down. no need for a finally block!
            ExecutorService executor2 = Executors.newFixedThreadPool(3);
        ) {

            //job submissions (passing a Runnable or Callable to submit()) go inside the try block:
            future1 = executor2.submit(() -> {
                System.out.println("running long task on " + Thread.currentThread().getName());
                LongTask.simulate(5); //this makes us wait 3 seconds for future1 to actually get a value
                return 1;
            });
            //submit

            //at this point, we've instantly got our future1 return value
            //so the submit() method doesn't make us wait for it,
            //no matter how long the Callable takes to finish (in this case 5 seconds).

            //so, we get a future representing a job that hasn't finished yet.
            //meaning: a job that will finish IN THE FUTURE.

            //however, our future1 variable does give us a handle
            //to monitor the running job it represents.

            //for example, we can know which state it's in:
            System.out.println("Future returned from Callable is " + future1.state().name() + " right now!");

            //if the task has finished execution, we can know by calling:
            System.out.println("is the task cancelled? " + future1.isCancelled()); //true if manually aborted. false otherwise.
            System.out.println("is the task done? " + future1.isDone() + "\n"); //true if worker has finished correctly or aborted. false otherwise.

            //to abort a job means to send an interrupt signal to the worker running it.
            //to manually abort a running job, we may call (uncomment these to test them):
            //future1.cancel(false); //only aborts if job hasn't started and is waiting for a worker in the pool.
            //future1.cancel(true);  //forcefully aborts, even if is running.
            //System.out.println( "is the task cancelled? " + future1.isCancelled() );

            //keep in mind the cancel() method sends an interrupt signal,
            //so we better handle the InterruptedException inside the Callable's call() method,
            //in case it includes any kind of sleep() or wait().

            //we can also force the thread to abort with IllegalStateException by calling:
            //future1.exceptionNow();

            //GETTING A RESULT VALUE FROM A CALLABLE/FUTURE:

            //the naive way to get the result value from the Future is by running:
            //future1.resultNow();
            //however, if the job hasn't finished, we'd get a runtime crash with IllegalStateException.

            //to safely get the actual result value, we need to wait until the worker
            //finishes the execution of our job.

            //we do so with the Future.get() method.
            //with it, we make the caller thread (this one, the main thread)
            //wait until the job is finished. It's very similar to the Thread.join() method.

            //get() should be called in ITS OWN try-block
            //this is confusing, since we could call it right here
            //which is inside a try-with-resources block
            //but that won't work reliably.

            //first, we declare a variable to hold the result value
            int wrapped_value = 0;

            //second, we open the inner try-block:
            try {

                //there are two overloads of get():

                //the simplest overload takes no args.
                //it just waits indefinitely and returns the result value as soon
                //as the job is finished:

                //wrapped_value = future1.get(); //uncomment this to test it

                //the second overload lets us specify a timeout.
                //a timeout means to abort the thread if it's taken more than we're willing to wait for.
                //it could indicate that the task will never finish,
                //or that it has crashed, or that something is broken somewhere.
                //we specify the timeout as an amount of time and a unit of time
                //(as a constant by the TimeUnit enum).

                //when the timeout time has been reached, the get() method will crash
                //at runtime, throwing a TimeoutException.
                //at this point, we won't care about the task,
                //so we better make sure it stops running.

                //we should catch the TimeoutException exception,
                //and then call cancel(true) to send an interrupt signal to the worker.
                //(see the catch block for TimeoutException below)

                //then, somewhere inside the Callable's call() method
                //we should have some way to catch the interrupt signal
                //and make the task end immediately.
                //(see the interruption handlers in our LongTask class)

                wrapped_value = future1.get(4, TimeUnit.SECONDS); //uncomment this to test it

                //get() throws InterruptedException and ExecutionException,
                //both of which are checked exceptions, so the catch blocks below are mandatory.
                //if we specify a timeout in the get() method, catching the TimeoutException is also mandatory.
            }catch(InterruptedException e) {
                System.out.println("thread was interrupted while sleeping!");
            }catch(ExecutionException e) {
                System.out.println("something crashed at the callable's runtime!");
            }catch(TimeoutException e) {
                System.out.println("time out! we have waited too long!");
                //delegate the timeout to an interrupt signal
                future1.cancel(true);
            }
            //try-catch

            System.out.println( "\nFuture returned from Callable is " + future1.state().name() + " right now!" );
            System.out.println( "is the task cancelled? " + future1.isCancelled() );
            //isDone() returns true if finished or aborted, and false if still running:
            System.out.println( "is the task still running? " + !future1.isDone() );
            System.out.println( "the return value is " + wrapped_value ); //resultNow() would work here if the job finished correctly.
        }catch(Exception e) { //exceptions are handled in the catch block(s)
            e.printStackTrace();
        }
        //try-with-resources

        System.out.println("\n----------------------------------------");
        System.out.println("Asynchronous Programming\n");

        //just like when we used the Thread.join() method,
        //the Future.get() method makes the caller thread
        //wait for another thread.

        //as we know, waiting is bad because
        //we lose the advantage of concurrency
        //and we might introduce freezes
        //which are seen as a bad user experience.

        //this is called synchronous programming.
        //now let's consider asynchronous programming.

        System.out.println("\n----------------------------------------");
        System.out.println("Completable Futures\n");

        //the CompletableFuture class is another implementation
        //of the Future interface.

        //it also implements the CompletionStage interface.

        //basically, it means that with it, we'll see
        //operations as divisible into steps.
        //every computational step of a larger operation
        //can be treated as a separate operation.

        //CompletionStage lets us pipeline said steps
        //in a similar way to using streams.
        //with the added benefit of making all steps
        //run in separate threads and never having to wait
        //for their completion in a synchronous way.

        System.out.println("\n----------------------------------------");
        System.out.println("Creating a Completable Future\n");

        //there are many ways to create a CompletableFuture object.
        //some let you specify the thread pool to be used,
        //and some use a default common thread pool.

        //said default common thread pool can be accessed via:
        //ForkJoinPool.commonPool();

        //tasks run as Completable Futures require no boilerplate.
        //no executor initializations nor shutdowns,
        //and no try-catch-finally or try-with-resources blocks.

        //for these examples, we'll create some simple lambdas:
        Runnable runnableTask = () -> System.out.println("I'm a Runnable");
        Supplier<Integer> supplierTask = () -> 42;
        int suppliedResult = 0;

        //the simplest factory static method to create a CompletableFuture is runAsync()
        //which takes a Runnable and runs on the common pool:
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(runnableTask);

        //if we need something that will produce a return value, like a Callable,
        //we may notice a Callable is exactly like a Supplier.
        //both have a functional method which takes no args and returns a generic value.
        //so, to run a Callable is functionally identical to running a Supplier in a separate thread.
        //we do so with the supplyAsync() method,
        //which takes a Supplier and runs on the common pool:
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(supplierTask);

        //just like any implementation of the Future interface
        //we could call the get() method on our future3 variable:
        try {
            suppliedResult = future3.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println( "supplied result is " + suppliedResult );

        System.out.println("\n----------------------------------------");
        System.out.println("Asynchronous APIs\n");

        //Here's an example of blocking, synchronous code:

        MailService service = new MailService();
        System.out.println("Sending mail synchronously...");
        service.sendSync(); //this uses our LongTask.simulate() method

        //this has to wait until sendSync() finishes!
        System.out.println("\nhello after sendSync");

        //we can make it asynchronous and non-blocking
        //by wrapping the sendSync() method
        //into a Runnable or Supplier
        //that is then ran by a CompletableFuture:

        service.sendAsync();

        //this doesn't wait for sendAsync()!
        System.out.println("\nhello after sendAsync... wait this isn't AFTER sendAsync yet!");

        //in fact our hello message probably appeared first in our output.

        //at this point the main thread will finish
        //(if running only this lesson)
        //and with it, the output will also stop working.
        //so let's put a sleep here, just to see sendAsync()'s full output:
        wrappedSleep(5000);

        //so the morale is that by wrapping synchronous code
        //inside asynchronous code, we convert it to asynchronous code,
        //and also reuse it (not re-implement it) asynchronously.

        System.out.println("\n----------------------------------------");
        System.out.println("Running Code on Completion\n");

        //sometimes, we will want to respond to the completion of a CompletableFuture
        //by running some block of code.

        //there are a ton of methods to do so, every possibility is covered, see here:
        //https://docs.oracle.com/en/java/javase/20/docs/api/java.base/java/util/concurrent/CompletableFuture.html

        //FOR SUPPLIERS,
        //USING supplyAsync():
        //AND FOR RUNNABLES,
        //USING thenRun() AND thenRunAsync():

        //for example, consider this very simple task that just returns 1:
        CompletableFuture<Integer> future4 = CompletableFuture.supplyAsync(() -> {
            System.out.println( "supplyAsync() for future4 running on " + Thread.currentThread().getName() );
            return 1;
        });
        //supplyAsync

        //remember that CompletableFuture implements the CompletionStage interface?
        //this means our future4 object can call CompletionStage methods, like:

        //thenRunAsync(), which runs a Runnable on a separate thread
        //right after the previous task (in this case supplyAsync()) finishes:
        future4.thenRunAsync(() -> {
            System.out.println( "thenRunAsync() from future4 running on " + Thread.currentThread().getName() );
        }); //this returns a CompletableFuture<Void>, we could catch it if we wanted...
        //thenRunAsync

        //or:

        //thenRun(), which runs a Runnable on this main thread
        //right after the previous task (in this case supplyAsync()) finishes:
        future4.thenRun(() -> {
            System.out.println( "thenRun() from future4 running on " + Thread.currentThread().getName() );
        }); //this returns a CompletableFuture<Void>, we could catch it if we wanted...
        //thenRun

        System.out.println();

        //the important part is, thenRun() and thenRunAsync() (and many other CompletionStage methods)
        //return another CompletableFuture object, which means they're chainable!

        //now the name of CompletionStage makes sense, right?
        //every object implementing CompletionStage is a stage in a method chain!

        //in fact we could organize our code like this, to run all 3 methods in succession:
        CompletableFuture<Void> future5 = CompletableFuture.supplyAsync(() -> {
            System.out.println( "supplyAsync() for future5 running on " + Thread.currentThread().getName() );
            return 42;
        }).thenRunAsync(() -> {
            System.out.println( "thenRunAsync() from future5 running on " + Thread.currentThread().getName() );
        }).thenRun(() -> {
            System.out.println( "thenRun() from future5 running on " + Thread.currentThread().getName() );
        });
        //CompletableFuture

        //notice the difference in the generic type:

        //in our first example (future4) we declare a CompletableFuture<Integer>
        //because the statement ends returning an Integer.

        //in our second example (future5) we declare a CompletableFuture<Void>
        //because the statement is a whole chain, which ends returning void.

        System.out.println();

        //FOR CONSUMERS,
        //USING thenAccept() AND thenAcceptAsync():

        //remember Consumers?
        //Consumer is a functional interface, whose functional method is accept().
        //it takes one arg and returns void.

        //the CompletableFuture.thenAccept() and thenAcceptAsync() methods
        //are similar to thenRun() and thenRunAsync(), except,
        //thenRun() and thenRunAsync() run a Runnable, while
        //thenAccept() and thenAcceptAsync() run a Consumer.

        //both thenAccept() and thenAcceptAsync() take as arg
        //the return value of the previous stage in the chain.

        CompletableFuture<Void> future6 = CompletableFuture.supplyAsync(() -> {
            System.out.println( "supplyAsync() for future6 running on " + Thread.currentThread().getName() );
            return 64;
        }).thenAcceptAsync(result -> {
            System.out.println( "thenAcceptAsync() from future6 running on " + Thread.currentThread().getName() + " with result " + result );
        });
        //CompletableFuture

        wrappedSleep(1000);
        System.out.println();

        //FOR FUNCTIONS,
        //USING thenApply() AND thenApplyAsync():

        //in a similar way, thenApply() and thenApplyAsync()
        //take a Function and run its apply() functional method.
        //which takes a value as arg and returns a value.

        //here's an example of a fully async CompletionStage chain made of:
        //Supplier -> Function -> Consumer -> Runnable:

        CompletableFuture<Void> future7 = CompletableFuture.supplyAsync(() -> {
            System.out.println( "supplyAsync() for future7 running on " + Thread.currentThread().getName() );
            return 64;
        }).thenApplyAsync(result -> {
            System.out.println( "thenApplyAsync() from future7 running on " + Thread.currentThread().getName() + " with result " + result );
            return result + 64;
        }).thenAcceptAsync(result -> {
            System.out.println( "thenAcceptAsync() from future7 running on " + Thread.currentThread().getName() + " with result " + result );
        }).thenRunAsync(() -> {
            System.out.println( "thenRunAsync() from future7 running on " + Thread.currentThread().getName() );
        });
        //CompletableFuture

        wrappedSleep(1000);

        System.out.println("\n----------------------------------------");
        System.out.println("Handling Exceptions\n");

        //what if a task running inside a CompletableFuture
        //crashes at runtime?

        //let's test that by manually throwing an exception
        //inside a separate thread:

        //let's simulate a service which takes a Supplier for getting the current temperature:
        CompletableFuture<Integer> future8 = CompletableFuture.supplyAsync(() -> {

            System.out.println("Getting the current temperature...");

            //for these next lines, test by uncommenting one and commenting the other one out:
            //throw new IllegalStateException("this was manually thrown...");
            return 25;
        });
        //CompletableFuture

        //at this point, if we run our code
        //the IllegalStateException will not in fact crash our whole program
        //it will only crash its thread.

        //to see that, we need to query our CompletableFuture (future8)
        //for the value it has gotten out of the Supplier.

        //we do so with the get() method:

        //first we'll declare two variables for the return value:
        int unsafe_temperature = 0; //not protected against a runtime crash
        int safe_temperature   = 0; //protected with a default value in case of a runtime crash

        //then we get() the temperature and catch the possible exceptions:
        try {

            //we know future8 represents a task
            //that might have crashed.

            //the naive way to query for its value is just calling get():

            unsafe_temperature = future8.get(); //uncomment this to test the caught exceptions

            //the smart way to do so, is to first
            //provide a method which returns a default value
            //in case the task has crashed.
            //we do so by chaining the exceptionally() method before calling get():

            safe_temperature = future8.exceptionally(e -> 10).get(); //uncomment this to test the exceptionally() method
        }catch(InterruptedException e) {
            System.out.println("I got an InterruptedException!");
            e.printStackTrace();
        }catch(ExecutionException e) {
            System.out.println("I got an ExecutionException!");
            e.printStackTrace();
        }
        //try-catch

        //the exceptionally() method takes as arg
        //a Function<Throwable,T> where T is the same generic type
        //used by the CompletableFuture, in this case Integer.
        //which means the Function arg should be a method taking a Throwable
        //and returning an Integer.

        //exceptionally() returns another CompletableFuture<Integer>.
        //that's why it's chainable.

        //finally, print the values we got:

        //we expect 0 if we didn't get the temperature value for some reason,
        //10 if we got the default value (e.g. the last temperature we got correctly),
        //and 25 if we got the actual, correct temperature value.

        System.out.println( "temperature gotten unsafely: " + unsafe_temperature );
        System.out.println( "temperature gotten safely: " + safe_temperature );

        System.out.println("\n----------------------------------------");
        System.out.println("Transforming Completable Futures\n");

        //let's do processing on the data we got from some async operation. for example,
        //imagine we got our temperature in celsius from the earlier temperature service.
        //how can we convert it to fahrenheit?

        //these are used in the formula: celsius * factor + offset = fahrenheit
        final float CONVERSION_FACTOR = 1.8f;
        final byte  CONVERSION_OFFSET = 32;

        float fahrenheit_temp = 0;

        //converting to fahrenheit will yield decimal numbers, so we need a Float datatype:
        CompletableFuture<Float> future9 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Getting the current temperature in celsius...");
            return 20;
        }).thenApply(t -> {
            return t * CONVERSION_FACTOR + CONVERSION_OFFSET;
        });
        //CompletableFuture

        try {
            fahrenheit_temp = future9.get();
        }catch(InterruptedException | ExecutionException e) {
            System.out.println("something crashed!");
        }
        //try-catch

        System.out.println( "the temp in fahrenheit is: " + fahrenheit_temp );

        System.out.println();

        //that works!

        //however, the error-prone get() method
        //and the try-catch block wrapping it
        //are kinda ugly.

        //if we think about it, we'd use get() only if we truly need
        //to hold the result value after processing the whole async operation chain.

        //but in this case, we're only printing the value as soon
        //as it emerges from the chain.

        //so it's better to simply chain a Consumer at the end of the chain
        //to print the value.

        //to call the Consumer, we'll use the thenAccept() method we already know
        //and said method will wait for the value and get it.
        //which is exactly what get() does, but without the boilerplate!

        fahrenheit_temp = 0;

        //since this time we're not returning the temperature,
        //we don't need to return anything, so the datatype is void:
        CompletableFuture<Void> future10 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Getting the current temperature in celsius...");
            return 20;
        }).thenApplyAsync(t -> { //we can make every stage async, except the last one
            return t * CONVERSION_FACTOR + CONVERSION_OFFSET;
        }).thenAccept(t -> {
            System.out.println( "the temp in fahrenheit is: " + t );
        });
        //CompletableFuture

        System.out.println("\n----------------------------------------");
        System.out.println("Composing Completable Futures\n");

        //let's imagine we know the ID of a user,
        //and with it, we'll get their email,
        //and with their email, we'll get a playlist they've made on some streaming website.

        //so we have two mapping methods:
        //from ID to email
        //from email to playlist

        //moreover, the second task can run only after the first one finishes.

        //since we probably need to query some external resource to do the mappings,
        //this scenario is ideal for a CompletableFuture chain of CompletedStage's.

        //however, this is different from our previous examples.
        //previously, we've always started a stage chain with supplyAsync().

        //the supplyAsync() method (and other supply-like methods in the CompletableFuture class)
        //is suitable to kickstart a chain, because:
        ////// it's static, meaning we don´t need a previous CompletedStage to call it from, and...
        ////// it takes no args, so it can run from nothing.

        //but this time we need to start from an ID we already have,
        //so we need a static method which runs a Function, not a Supplier
        //so we can pass it the ID as arg.

        //the problem is, there is no such method in the CompletableFuture class!
        //there are only the thenApply() and thenApplyAsync() methods,
        //which require a previous CompletedStage.

        //luckily, we can mock a dummy CompletedStage,
        //via the CompletableFuture.completedStage() method.

        //it returns a CompletedStage which takes a value
        //and then returns that same value, unchanged.
        //starting from it, we can chain more CompletedStage's and CompletableFuture methods!

        //keep in mind, EVERY CompletionStage<T> or CompletableFuture<T> object
        //will wrap a return value v of datatype T (can be void)
        //and will pass it as arg to the next method on the chain.

        //in other words,
        //if a chainable method expects an arg,
        //it MUST be returned by the previous method in the chain!

        String id1 = "ABC";
        CompletableFuture
            .completedStage( id1 ) //takes the id, returns the id
            .thenApplyAsync( _id -> "abc@abc.abc" ) //takes the returned id, returns the email
            .thenApplyAsync( _email -> "songA...songB...songC" ) //takes the returned email, returns the playlist
            .thenAccept( _playlist -> System.out.println( "user with id " + id1 + " has playlist: " + _playlist ) );
        //CompletableFuture

        //COMPOSITION:

        //CompletableFutures also have the thenCompose() and thenComposeAsync() methods.

        //they take one arg, and return an explicitly instantiated CompletionStage
        //meaning they expect to run a Function<T, CompletionStage>.

        //to demonstrate, we'll extract the logic of the previous example into standalone static methods:
        //the getStartStage(), idToEmailAsync() and emailToPlaylistAsync() methods (see above).
        //these methods take a string value (the id or the email) and return the next value we want,
        //already computed and wrapped inside a CompletionStage, so they can be put on the chain!

        //idToEmailAsync() takes the id and returns a CompletionStage with the computed email
        //emailToPlaylistAsync() takes the email and returns a CompletionStage with the computed playlist

        //now, we'll compose those two methods using thenComposeAsync() to get the same result as before:

        String id2 = "XYZ";
        getStartStage( id2 )
            .thenComposeAsync( ExecutorExamples::idToEmailAsync ) //takes the id, returns the email
            .thenComposeAsync( ExecutorExamples::emailToPlaylistAsync ) //takes the returned email, returns the playlist
            .thenAccept( _playlist -> System.out.println( "user with id " + id2 + " has playlist: " + _playlist ) );
        //CompletableFuture

        //the first way to do it is in fact more efficient,
        //since it doesn't create additional CompletionStage objects,
        //it just needs those returned by the then-like methods.

        System.out.println("\n----------------------------------------");
        System.out.println("Combining Completable Futures\n");

        //let's say we want to calculate how many Euros
        //we'd pay for a product sold in Dollars.

        //let's also say that we have web services we can query
        //for the info we'll need (we'll mock those services).

        //we want the result on the main thread, but the necessary work
        //should be done asynchronously.

        //we begin by specifying the two tasks.
        //which will be:

        //task A: getting the price in dollars (let's say it takes extra processing steps!):
        CompletionStage<Float> price_task = CompletableFuture
            .supplyAsync(() -> "20USD") //imagine the web service gives us a string with extra text
            .thenApplyAsync( price_str -> price_str.replace("USD", "") ) //remove the extra text
            .thenApplyAsync( Float::parseFloat ); //parse a float from it
        //CompletableFuture

        //task B: getting the exchange rate factor from Dollars to Euros:
        CompletionStage<Float> exchange_rate_task = CompletableFuture.supplyAsync(() -> 0.9f);

        //notice the previous two tasks ran 100% asynchronously.

        //two different tasks A and B,
        //running on different CompletableFutures, after they both finish,
        //can combine their return values via some combination BiFunction
        //which runs on a third CompletableFuture AB, finally producing some final result.

        //we do that with the thenCombine() and thenCombineAsync() methods
        //which run from the CompletionStage of task A, and take two args:
        //the CompletableFuture of task B, and a BiFunction
        //which is the combination BiFunction.

        //the combination BiFunction also takes two args,
        //which are the return values of our two tasks.

        //here we'll combine both tasks asynchronously.
        //only the final step should be synchronous with the main thread:

        //so, we combine A and B like:
        price_task.thenCombineAsync( //task A finishes and returns price, and afterwards it calls thenCombineAsync()
            exchange_rate_task, //task B finishes and returns exchange_rate, and then the following BiFunction is run
            (price, exchange_rate) -> price * exchange_rate //this is the combination BiFunction
        ).thenAccept( euros -> System.out.println("the price in Euros is: " + euros ) );
        //thenCombineAsync

        System.out.println("\n----------------------------------------");
        System.out.println("Waiting for Many Tasks\n");

        //in the last lesson we combined two tasks
        //and in the final step of the combination,
        //we made the main thread wait for the final result.

        //we'll do a more general version of that, this time for 3 tasks,
        //using the allOf() method.

        //we'll create CompletableFutures for 3 separate asynchronous tasks:
        CompletableFuture<Integer> taskA = CompletableFuture.supplyAsync(() -> 1);
        CompletableFuture<Integer> taskB = CompletableFuture.supplyAsync(() -> 2);
        CompletableFuture<Integer> taskC = CompletableFuture.supplyAsync(() -> 3);

        //the allOf() method takes zero or more args.
        //and they are all CompletableFuture's (not CompletionStage's!).

        //it returns a new CompletionStage<Void> when all tasks finish.

        CompletionStage<Void> allTasks = CompletableFuture.allOf(taskA, taskB, taskC);

        //now that all three tasks have finished, let's do something with them.
        //because of how the scope of lambdas works,
        //the three tasks are available inside the following lambda:

        //this is the last step, so we make it synchronous
        //by calling thenRun() instead of thenRunAsync():
        allTasks.thenRun( () -> {

            //all three tasks have finished while inside the allOf() method
            //so at this point we can guarantee they're finished!
            //(although we cannot guarantee if they've finished correctly or crashed...)

            //this means, at this point we don't actually have to do any waiting
            //inside the get() method.

            //it is GOOD DESIGN to leave only the very last task to be synchronous
            //and have it run only code that involves no waiting!

            try {
                System.out.println( "result of taskA: " + taskA.get() );
                System.out.println( "result of taskB: " + taskB.get() );
                System.out.println( "result of taskC: " + taskC.get() );
            }catch(InterruptedException | ExecutionException e) {
                System.out.println("something crashed...");
            }
            //try-catch

            System.out.println("all tasks done!");
        });
        //thenRun

        System.out.println("\n----------------------------------------");
        System.out.println("Waiting for the First Task\n");

        //let's say again that we could use web services to get some information
        //and this time we have two options that give us the same information, in case one fails.

        //as soon as one gives us the correct result
        //we should abort the other one.

        //we do so in a very elegant way by calling the anyOf() method.

        //anyOf() is equal to allOf() in terms of args, but not in terms of logic:
        //it will run all tasks only until one of them finishes,
        //and then it will return that task, leaving the rest of them still running pointlessly.

        //the return value is also different.
        //allOf() returns a CompletionStage<Void>,
        //anyOf() returns a CompletionStage<Object>.

        //to demonstrate, we'll simulate two long-running async tasks,
        //one longer than the other:

        CompletableFuture<Integer> taskX = CompletableFuture.supplyAsync(() -> {
            LongTask.simulate(3);
            System.out.println("taskX finished.");
            return 10;
        });

        CompletableFuture<Integer> taskY = CompletableFuture.supplyAsync(() -> {
            LongTask.simulate(2);
            System.out.println("taskY finished.");
            return 10;
        });

        //we get the faster task of the two, once it's finished:
        CompletionStage<Object> fasterTask = CompletableFuture.anyOf(taskX, taskY);

        //whichever task won, will have its return value already computed
        //wrapped, and ready to be passed to the next stage on the chain.

        //so the arg of this thenAccept() call will be the return value of the faster task!
        fasterTask.thenAccept( value -> System.out.println( "the value is: " + value ) );

        //no async tasks will be able to print their output past this sleep:
        wrappedSleep(3000);

        System.out.println("\n----------------------------------------");
        System.out.println("Handling Timeouts\n");

        //we already know the overload for the get() method
        //which lets us specify a timeout.

        //there's a similar method of CompletableFutures
        //called orTimeout(), which takes an amount of time and a unit of time,
        //just like get().

        //if our task times out, a TimeoutException will be thrown
        //by our CompletableFuture in its own thread.

        //at that point, we may call isCompletedExceptionally() on
        //our CompletableFuture and it would return true.

        //we could also chain an exceptionally() call
        //inbetween orTimeout() and get() to avoid crashing
        //by introducing a default value.

        System.out.println( "Testing first example:" );

        int result = -1;

        CompletableFuture<Integer> future11 = CompletableFuture.supplyAsync(() -> {
            LongTask.simulate(3);
            return 1;
        });
        //CompletableFuture

        try {
            result = future11 //this takes 3 seconds to finish
                .orTimeout(2, TimeUnit.SECONDS) //test this with a shorter (2) or longer (4) timeout
                //uncomment this block to introduce a default in case of timeout:
                .exceptionally(e -> {
                    System.out.println( "time out! getting default value..." ); //print a message to indicate timeout
                    return 0;
                })
                .get();
            //result
        }catch(InterruptedException | ExecutionException e) {
            System.out.println( "something crashed..." );
            System.out.println( "did it crash? " + future11.isCompletedExceptionally() );
        }
        //try-catch

        //finally we print the result:
        //-1 if our task never finished or timed out,
        //1 if it finished correctly,
        //0 if it crashed or timed out with a default value.

        System.out.println( "got the result: " + result );

        wrappedSleep(3500);
        System.out.println();
        System.out.println( "Testing second example:" );

        //however, there is a more elegant way to handle such a scenario.
        //there is the completeOnTimeout() method
        //which is kind of a combined exceptionally() and onTimeout().
        //it takes the default value, the timeout amount and the timeout unit.

        //we'll also use the return value inside the chain with thenAccept()
        //instead of extracting it from the chain with get().
        //this lets us avoid the try-catch block entirely!

        CompletableFuture
            .supplyAsync(() -> {
                LongTask.simulate(3);
                return 1;
            }).completeOnTimeout(0, 2, TimeUnit.SECONDS) //can't print a message here!
            .thenAccept(v -> System.out.println( "got the result: " + v ) );
        //CompletableFuture

        //while our second example is a more elegant solution,
        //it has the disadvantage of not being able to
        //pass a lambda into the completeOnTimeout() method,
        //while we could do so with the exceptionally() method.

        //that's why only the first example prints a message
        //to indicate that the task has timed out.

        wrappedSleep(3500);

        //uncommented block ends here:
        */

        System.out.println("\n----------------------------------------");
        System.out.println("Project: Best Price Finder\n");

        //in this exercise, we must query a flight price comparator website
        //for some flights and their prices.

        //we'll simulate the comparator querying 3 flight seller sites
        //and getting a quote from each of them.

        //first we'll instance three flight seller sites:
        //(prices are randomly generated)
        FlightSeller site1 = new FlightSeller("site1");
        FlightSeller site2 = new FlightSeller("site2");
        FlightSeller site3 = new FlightSeller("site3");

        try(
            //we'll initialize our executor here so it's autoclosed at the end
            ExecutorService ex = Executors.newFixedThreadPool(5);
        ) {

            //get the instant we started
            var start_time = LocalTime.now();

            //declare async jobs for getting all 3 quotes and printing them:
            //print statements should be in the individual chains, to print the quote as soon as we get it

            CompletableFuture<Void> site1_future = CompletableFuture
                .supplyAsync( site1::getQuote, ex ) //this is the unpredictably long part, so run it async in our executor
                .thenAccept(System.out::println);   //this is just printing afterwards, this can be synchronous
            //site1_future

            CompletableFuture<Void> site2_future = CompletableFuture
                .supplyAsync( site2::getQuote, ex ) //this is the unpredictably long part, so run it async in our executor
                .thenAccept(System.out::println);   //this is just printing afterwards, this can be synchronous
            //site2_future

            CompletableFuture<Void> site3_future = CompletableFuture
                .supplyAsync( site3::getQuote, ex ) //this is the unpredictably long part, so run it async in our executor
                .thenAccept(System.out::println);   //this is just printing afterwards, this can be synchronous
            //site3_future

            //run those 3 jobs, and wait (asynchronously!) for them to finish:
            CompletableFuture<Void> all_sites = CompletableFuture.allOf(site1_future, site2_future, site3_future);

            //after all 3 jobs are finished and quotes are printed, calculate the elapsed time:
            all_sites.thenRun( () -> {
                var elapsed_time = Duration.between(start_time, LocalTime.now());
                System.out.println( "\nRetrieved all quotes in " + elapsed_time.toMillis() + " msec" );
            });
            //all_sites
        }
        //try

        System.out.println("\n----------------------------------------");
        System.out.println("THE COURSE IS OVER!\n");
    }
    //runThreadsExamples
}
//ExecutorExamples

//eof
