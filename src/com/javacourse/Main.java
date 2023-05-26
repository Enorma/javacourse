package com.javacourse;

//CONTENTS:
import com.javacourse.gettingstarted.*; //section 1-1, driver method: GettingStarted.exampleStuff();
import com.javacourse.types.*;          //section 1-2, driver method: Types.exampleStuff();
import com.javacourse.controlflow.*;    //section 1-3, driver method: ControlFlow.controlFlowExamples();
import com.javacourse.cleancode.*;      //section 1-4, driver method: CleanCode.cleanCodeExamples();
import com.javacourse.debuganddeploy.*; //section 1-5, driver method: Debug.debugExamples();
import com.javacourse.classes.*;        //section 2-2, driver method: Classes.exampleOOPStuff();
import com.javacourse.refactoroop.*;    //section 2-3, driver method: MortgageWrapper.MortgageOOP();
import com.javacourse.inheritance.*;    //section 2-4, driver method: Inheritance.inheritanceExamples();
import com.javacourse.interfaces.*;     //section 2-5, driver method: Interfaces.interfaceExamples();
import com.javacourse.exceptions.*;     //section 3-2, driver method: ExceptionsDemo.exceptionExamples();
import com.javacourse.generics.*;       //section 3-3, driver method: GenericsExamples.genericsWrapper();
import com.javacourse.collections.*;    //section 3-4, driver method: CollectionsExample.collectionsWrapper();
import com.javacourse.lambdas.*;        //section 3-5, driver method: LambdaExamples.runLambdaExamples();
import com.javacourse.streams.*;        //section 3-6, driver method: StreamsExamples.runStreamsExamples();
import com.javacourse.concurrency.*;    //section 3-7, driver method: ThreadsExamples.runThreadsExamples();
import com.javacourse.executors.*;      //section 3-8, driver method: ExecutorExamples.runExecutorExamples();

public class Main {

    public static void main(String[] args) {

        //every section is a package, inside which
        //there's a class with all its lessons
        //and a driver method that runs them.

        //write here what section you want to test like:
        //<Class>.<drivermethod>();
        //or with the full path:
        //com.javacourse.<package>.<Class>.<drivermethod>();

        ExecutorExamples.runExecutorExamples();
        System.out.println();
    }
    //main
}
//Main

//eof
