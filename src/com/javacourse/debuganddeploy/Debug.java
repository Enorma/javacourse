package com.javacourse.debuganddeploy;

/*
    //wrapper class for Course 1 Section 5: Debug & Deploy

    //import to Main class with:
    import com.javacourse.debuganddeploy.Debug; //or...
    import com.javacourse.debuganddeploy.*;

    //call from main() method with:
    com.javacourse.debuganddeploy.Debug.debugExamples(); //or...
    Debug.debugExamples();
*/

public class Debug {

    public static void printNumbers(int limit) {

        //add a watch for any variable, method or expression
        //by clicking on the watches panel in the debugger and hitting the (insert) key

        for(int i=0; i<limit; i+=2) {
            System.out.println(i);
        }
        //for

        //step out of a method at any point with (Shift + F8)
    }
    //printNumbers

    public static void debugExamples() {

        //to test this, set a breakpoint below
        //and then run from main() in debug mode (Alt + Shift + F9)
        //re-run debugger at any point using (Ctrl + R)

        System.out.println("\n----------------------------------------");
        System.out.println("Debugging a method with a bug\n");

        //bug: printNumbers() should print 01234 but only prints 02

        System.out.println("Start"); //set breakpoint here (Ctrl + F8)

        //step over (F8) this method call to see the bug
        //step into (F7) this method to investigate it deeply
        printNumbers(4);

        System.out.println("Finish");
    }
    //debugExamples
}
//Debug

//eof
