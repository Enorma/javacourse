package com.javacourse.exceptions;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/*
    //wrapper class for Course 3 Section 2: Exceptions

    //import to Main class with:
    import com.javacourse.exceptions.ExceptionsDemo; //or...
    import com.javacourse.exceptions.*;

    //call from main() method with:
    com.javacourse.exceptions.ExceptionsDemo.exceptionExamples(); //or...
    ExceptionsDemo.exceptionExamples();
*/

public class ExceptionsDemo {

    public static void show() {

        //in lesson 2 we pass null to sayHello
        //to trigger an exception
        //sayHello(null);

        System.out.println("lol");
    }
    //show

    public static void sayHello(String name) {

        System.out.println(name.toUpperCase());
    }
    //sayHello

    public static void reThrowExceptionExample() throws IOException {

        //we add the "throws..." at the method signature
        //to handle the re-thrown exception

        var account = new Account();

        try {
            account.checkedDeposit(-1); //trigger a checked exception intentionally
        }catch(IOException ex) {

            //we handle the exception in this method, then log it
            //at this point a developer/tester could see the exception and fix it

            //and then we re-throw the exception so that outside this method
            //we present some generic, friendly error message to the user

            System.out.println("here we log the error for dev eyes only: " + ex.getMessage());
            throw ex; //so the caller of this method handles the error however he wants
        }
        //try-catch
    }
    //reThrowExceptionExample

    public static void exceptionExamples() {

        //through this method, we may need to create/delete/read/write files
        //to test the examples

        //files should be in the root folder of the intellij project.

        System.out.println("\n----------------------------------------");
        System.out.println("What are exceptions\n");

        //in lesson 2 the show() method can
        //trigger an exception
        show();

        //exceptions are uncontrolled situations
        //where our program didn't get what it expected
        //and so it doesn't know what to do
        //so it stops abruptly

        //different situations produce different exceptions
        //every exception is an instance of some class

        System.out.println("\n----------------------------------------");
        System.out.println("Types of exceptions\n");

        //checked exceptions
        //are situations where the possibility of a runtime crash is known by the compiler
        //and therefore force us to surround the risky code with some error handling
        //or else we get a compile-time error.
        //according to the java convention, checked exceptions are for
        //situations we could anticipate and recover from
        //like bad user input

        //unchecked exceptions
        //are situations (like the show() method from lesson 2)
        //where the java compiler sees no obvious possibility of a runtime crash
        //however, we programmers could see it
        //so we should surround the risky code with some error handling
        //but there's no compile-time penalty for not doing so
        //HOWEVER...
        //these kinds of exceptions are often due to programming mistakes
        //so the best practice would be to practice safe coding
        //by validating input values before running risky code
        //so the crash is prevented and the error handling isn't necessary.
        //according to the java convention, unchecked exceptions are for
        //situations that fail unrecoverably because of programming mistakes

        //errors
        //are situations entirely out of our control
        //like a stack overflow or insufficient memory
        //we can sometimes prevent these by, for example
        //avoiding infinite recursions
        //but more often than not, we can't prevent these...
        //according to the java convention, errors are for
        //situations that fail unrecoverably because of conditions outside our control

        System.out.println("\n----------------------------------------");
        System.out.println("Checked exceptions\n");

        //a FileReader is risky code
        //because this would crash if given the name of
        //a file that doesn't exist

        //so this next code needs error handling
        //or else we get a compile-time error:
        //var reader = new FileReader("file.txt");

        System.out.println("\n----------------------------------------");
        System.out.println("Exceptions hierarchy\n");

        //the parent class of all exceptions is Throwable
        //it defines a stacktrace for every exception

        //the Exception class inherits from Throwable,
        //it defines the checked exceptions,
        //and all exceptions that inherit from it are checked
        //except:

        //the RuntimeException class inherits from Exception
        //but it defines the unchecked exceptions instead,
        //and all exceptions that inherit from it are unchecked

        //the Error class inherits from Throwable,
        //it defines the Errors that are outside of our control

        System.out.println("\n----------------------------------------");
        System.out.println("The try-catch block\n");

        //we apply error handling with a try-catch block:
        try {
            var reader = new FileReader("file.txt");
            //in this example, an exception occurs if the file doesn't exist
            //next line is skipped if the previous line causes an exception:
            System.out.println("This won't print");
        }catch (FileNotFoundException e) {

            //print a user-friendly error message
            System.out.println("File doesn't exist");
            System.out.println();

            //printing the stacktrace:

            //this prints the stacktrace as an error string:
            //it will likely appear on the terminal at the end of the output
            //e.printStackTrace(); //defaults to System.err

            //this prints the stacktrace as a normal, synchronous string:
            e.printStackTrace(System.out);

            //errors usually are printed using System.err

            //System.out and System.err are different output streams
            //and run in separate threads
            //which is why printStackTrace() usually doesn't sync properly
            //with the System.out.println() calls in the rest of the code
        }
        //try-catch

        System.out.println("We've reached after the error handler!");

        System.out.println("\n----------------------------------------");
        System.out.println("The try-catch block with multiple catch clauses\n");

        //it is possible to have several catch clauses
        //one for each type of exception we want to handle

        try {
            //an exception will occur if the file doesn't exist
            var reader = new FileReader("file.txt");

            //a different exception will occur if the file isn't readable
            var value = reader.read();

            //this third line could throw an exception
            //if we don't give it a string
            //that could be parsed as a date
            new SimpleDateFormat().parse("");
        }catch(FileNotFoundException e) {
            //this exception inherits IOException
            //so we put this catch clause first
            //because it's more specific than IOException
            System.out.println("File doesn't exist"); //handle the first exception
        }catch(IOException e) {
            //we put this catch clause second
            //to handle any other kind of IOException
            System.out.println("File isn't readable"); //handle the second exception
        }catch(ParseException e) {
            //we put this catch clause third
            //simply because the offending line appears third in the try block
            System.out.println("File text is not parseable"); //handle the third exception
        }
        //try-catch

        System.out.println();

        //we can handle several exceptions in the same catch clause, like:

        try {
            //we'll put the three offending lines from the previous example here:
            var reader = new FileReader("file.txt");
            var value = reader.read();
            new SimpleDateFormat().parse("");
        }catch(IOException | ParseException e) { //several exceptions in a single clause
            //FileNotFoundException is not explicitly handled
            //because IOException includes it
            System.out.println("File processing failed"); //handle all 3 exceptions
        }
        //try-catch

        System.out.println("\n----------------------------------------");
        System.out.println("The finally clause\n");

        //if we try to use some OS resource or hardware
        //we could need to revert it to some default state
        //after we finished using it

        //in this case, closing the reader1 object with:
        //reader1.close();
        //to release the file, so it can be read/written
        //by some other process

        //the finally block is the proper place to put that line

        //here's what happens if we place it in several places:

        //first we place these variables in the scope
        //of the whole try-catch-finally block
        FileReader reader1 = null; //needs to be initialized, null is fine
        int value1;

        try {

            reader1 = new FileReader("file.txt");
            value1 = reader1.read();

            //wrong place:
            //in case of exception, this won't be run:
            //reader1.close();
        }catch(IOException e) {
            System.out.println("File not read properly");
        }finally {

            //right place!
            //the finally block is ALWAYS run,
            //whether there was an exception or not
            //so here we can safely release file handles,
            //close database or network connections, etc

            //needs to check if it is null
            //and also needs its own error handler
            //since it can also throw an IOException

            if(reader1 != null) {
                try {
                    reader1.close();
                }catch(IOException e) {
                    System.out.println("Couldn't close reader");
                }
                //try-catch
            }
            //if
        }
        //try-catch-finally

        //wrong place:
        //some other programmer might add code between here
        //and the end of the try-catch-finally block,
        //that code might crash, so this line wouldn't run:
        //reader1.close();

        System.out.println("\n----------------------------------------");
        System.out.println("The try-with-resources block\n");

        //the code in the past example is ugly
        //because is too long and too indented

        //we can do more elegant code to solve the same problem(s)
        //using the try-with-resources block:

        try(
            var reader = new FileReader("file.txt");
            var writer = new FileWriter("otherfile.txt");
        ) {

            //inside the above parentheses,
            //we declare and initialize the resources we are going to use

            //they are in the scope of the whole try-catch block
            //so there's no need to declare them beforehand!

            //also, they are auto-closed at the end
            //so there's no need for a finally block!

            //and we can initialize several resources, not just one!

            var value = reader.read();
            writer.write("writing this...");
        }catch(IOException e) {
            System.out.println("File not read/written properly");
        }
        //try-with-resources

        //HOWEVER
        //the resources we initialize at the try block
        //need to implement the AutoCloseable interface

        System.out.println("\n----------------------------------------");
        System.out.println("Defensive programming\n");

        //defensive programming is applying validation
        //to the method arguments and,
        //if deemed wrong, illegal or dangerous,
        //throwing an exception so the rest of our program
        //is protected from running with those values

        //however this should be done only when
        //building a library or framework
        //or validating user input
        //so don't overuse it

        //because if we do validation logic in every method
        //the code becomes polluted and ugly

        //so let your methods trust each other
        //instead of validating everything all the time

        System.out.println("\n----------------------------------------");
        System.out.println("Throwing exceptions manually\n");

        var account = new Account();

        //this might (and in fact will) crash, but
        //we're NOT forced to handle it:
        try {
            account.uncheckedDeposit(-1); //but we'll handle it anyway
        }catch(IllegalArgumentException ex) {
            System.out.println("I caught an unchecked exception");
        }

        //this might (and in fact will) crash, and
        //we're forced to handle it
        try {
            account.checkedDeposit(-1);
        }catch(IOException e) {
            System.out.println("I caught a checked exception");
        }
        //try-catch

        System.out.println("\n----------------------------------------");
        System.out.println("Re-throwing exceptions\n");

        //every handled exception should,
        //in runtime, be hidden from the user
        //unless it's unrecoverable

        //however, the developers actually want to know
        //when, where and how an exception happened

        //so, we handle an exception first
        //inside the method in which it happened
        //to log it for dev/tester eyes only

        //and then we re-throw it so that we handle it
        //outside the method in which it happened
        //that is, at the method call
        //to display a generic, friendly error message to the user

        try {
            //an exception happens inside this method
            //logging handler is inside this method
            reThrowExceptionExample();
        }catch(IOException e) {
            //here we handle the re-thrown exception
            //and give the user a user-friendly error message
            System.out.println("some error occurred");
        }
        //try-catch

        //we could take this idea further by
        //wrapping the entire body of the main() method
        //inside a try-catch block that catches Throwable
        //so runtime errors would never crash the program
        //but we could still log them

        //or we could create a custom exception
        //to throw after logging the actual exception that crashed
        //and have the main's try-catch catch only our custom exception
        //so logged exceptions won't crash the program
        //but fatal exceptions still will

        /*
        public static void main(String[] args) {
            try {
                //run the entire program here...
            }catch(CustomException e) {
                System.out.println("some error occurred");
            }
            //try-catch
        }
        */

        System.out.println("\n----------------------------------------");
        System.out.println("Custom exceptions\n");

        //here we created a custom checked exception (InsufficientFundsException)
        //and trigger it inside the withdraw_old() method
        //which is why we need to wrap the method call in a try-catch block

        try {
            account.withdraw_old(10);
        }catch(InsufficientFundsException ex) {
            System.out.println(ex.getMessage());
        }
        //try-catch

        System.out.println("\n----------------------------------------");
        System.out.println("Chained exceptions\n");

        //in our example above, the money withdrawal failed
        //because of insufficient funds
        //however, it could have also failed for several other reasons

        //in those cases, we wrap our specific, custom exception (InsufficientFundsException)
        //inside another, more general, custom exception (AccountException)

        //and we rationalize it like:
        //the general exception was caused by some specific exception
        //or in this case:
        //AccountException was caused by InsufficientFundsException

        var fundsEx   = new InsufficientFundsException("low funds");
        var accountEx = new AccountException("account error");

        //every exception object A has a method called initCause()
        //to which you pass another exception object B
        //to signify that B caused A.
        //there's also getCause() for getting the exception cause
        accountEx.initCause(fundsEx);

        try {
            throw accountEx;
        }catch(AccountException ex) {

            ex.printStackTrace(System.out);

            var cause = ex.getCause(); //to get the cause (as Exception object)
            System.out.println(ex.getMessage());
            System.out.println(cause.getMessage());
        }
        //try-catch

        //we can also set the exception cause B
        //as an argument in the constructor of exception A
        System.out.println();

        try {

            //we set one exception as the cause of another
            //inside the withdraw() method
            //which sets the cause using a constructor

            account.withdraw(10);
        }catch(AccountException ex) {

            ex.printStackTrace(System.out);

            var cause = ex.getCause(); //to get the cause (as Exception object)
            System.out.println(ex.getMessage());
            System.out.println(cause.getMessage());
        }
        //try-catch
    }
    //exceptionExamples
}
//ExceptionsDemo

//eof
