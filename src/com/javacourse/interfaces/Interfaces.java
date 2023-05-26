package com.javacourse.interfaces;

import com.javacourse.interfaces.mytube.*;
import com.javacourse.interfaces.taxcalculator.*;
import com.javacourse.interfaces.uiwidget.*;

/*
    //wrapper class for Course 2 Section 5: Interfaces

    //import to Main class with:
    import com.javacourse.interfaces.Interfaces; //or...
    import com.javacourse.interfaces.*;

    //call from main() method with:
    com.javacourse.interfaces.Interfaces.interfaceExamples(); //or...
    Interfaces.interfaceExamples();
*/

public class Interfaces {

    public static void interfaceExamples() {

        System.out.println("\n----------------------------------------");
        System.out.println("Why we need interfaces\n");

        //we use an interface as a bridge between two coupled classes
        //by doing this, those classes become loosely coupled
        //loose coupling is preferable for maintainability

        //if A depends on B
        //any change to B will require a change to A
        //and/or at least recompile A

        //if A depends on interface I
        //and B implements I
        //the coupling becomes loose
        //and we can change the internal implementation of B
        //without introducing breaking changes into A

        //as oppossed to classes
        //which actually implement methods
        //(they care about the what and the how)
        //interfaces have no implemented methods
        //they only care about the what, not the how
        //(kinda like an abstract class with:
        //only abstract methods,
        //no static methods,
        //no fields at all,
        //and no inheritance)

        System.out.println("\n----------------------------------------");
        System.out.println("Tightly coupled code\n");

        //in this lesson (3) we haven't created an interface
        //we have a tight coupling situation where
        //class TaxReport depends on class TaxCalculator2018

        //this next code works at the time of lesson 3
        //and its purpose is to make changes to TaxCalculator2018
        //to test the consequences of loose coupling
        //var report = new TaxReport();
        //report.show();

        System.out.println("\n----------------------------------------");
        System.out.println("Creating an interface\n");

        //this works fine, because
        //an interface is a valid datatype for a variable
        CanCalculateTax cct = null;

        //but this crashes at compile-time, because
        //interfaces cannot be instantiated!
        //cct = new CanCalculateTax();

        //so, we can have an interface-typed variable
        //but we cannot use new on an interface
        //so how do we populate an interface variable?

        //an interface variable can store
        //an instance of a class that implements the interface
        //in this case TaxCalculator2018 implements CanCalculateTax
        cct = new TaxCalculator2018(20_000);

        //and it behaves just like an actual TaxCalculator2018 object!
        System.out.println( cct.calculateTax() );

        System.out.println("\n----------------------------------------");
        System.out.println("Dependency Injection\n");

        //at this point (lesson 5) we have...
        //a dependency class (TaxCalculator2018)
        //a dependant class (TaxReport)
        //an interface (CanCalculateTax)

        //the interface is already being implemented by the dependency class
        //but it is still not used by the dependant class
        //so this is incomplete, because the interface should bridge both classes

        //the process that solves this issue is called dependency injection
        //it means to inject an object of the dependency class into the dependant class

        //at this point we need to define "separation of concerns"
        //creating (instantiating) an object and actually using it
        //are two different concerns and need to be kept separate

        //as of now the dependency is INSTANTIATED (not injected!) INSIDE the dependant class
        //this means we're doing something like:
        //new TaxCalculator2018();
        //inside TaxReport
        //that's tight coupling, bad practice

        //to inject it means instantiating the dependency OUTSIDE the dependant class
        //and then pass the resulting object as an argument into a method of the dependant class
        //that's loose coupling, good practice!

        //so the rule of thumb here is:
        //classes should NEVER instantiate their dependencies.
        //or, in other words:
        //the instantiation of a dependency class
        //should never be inside the dependant class

        //how to apply dependency injection:
        //step 1: in some external place (for example main() )
        //an object of the dependency class is instantiated
        //and stored in an interface-typed variable, like:
        CanCalculateTax tc2018 = new TaxCalculator2018(100_000);
        //so at this point tc2018 is our dependency object

        //step 2: the dependant class should have some method (known as the injected method)
        //that can take the dependency object as an argument
        //this method could be:

        //A PUBLIC CONSTRUCTOR
        //assuming a dependency object is in its arguments
        //like the following constructor declaration in TaxReport:
        //public TaxReport(CanCalculateTax calculator) {...}

        //A PUBLIC SETTER
        //assuming the dependency is a field of the dependant class
        //like the following field and setter declarations in TaxReport:
        //private CanCalculateTax calculator;
        //public setCalculator(CanCalculateTax _calculator) {...}

        //SOME OTHER PUBLIC METHOD
        //assuming a dependency object is in its arguments
        //like the following method declaration in TaxReport:
        //public doSomething(CanCalculateTax calculator) {...}

        //step 3: the injected method can be called
        //from some external place (for example main() ), like:

        System.out.println("\nConstructor injection:");
        var tr = new TaxReport(tc2018);

        System.out.println("\nSetter injection:");
        tr.setCalculator(tc2018);

        System.out.println("\nMethod injection:");
        tr.show(tc2018);

        //so now, the dependency class (TaxCalculator2018)
        //has no idea where it's gonna be used
        //and the dependant class (TaxReport)
        //never refers TaxCalculator2018 directly by name
        //so they don't know each other
        //they only know the interface (CanCalculateTax) which bridges them
        //that's loose coupling!

        //the dependency object can be stored in a variable
        //of either the class datatype or the interface datatype
        //the interface datatype is used in this example for clarity
        //but they both work in the same way

        System.out.println("\n----------------------------------------");
        System.out.println("Dynamic dependencies\n");

        //any class that implements our interface
        //is now a valid dependency of our dependant class

        //for example, we create a dependency object of a different class:
        CanCalculateTax tc2019 = new TaxCalculator2019(100_000);

        //and we can inject it just as easily as another compatible dependency:

        System.out.println();
        tr.setCalculator(tc2018); //set a stored dependency
        tr.show(); //show tax with stored dependency
        tr.show(tc2019); //show tax with some other dependency

        System.out.println();
        tr.setCalculator(tc2019); //replace the stored dependency
        tr.show(); //show tax with new stored dependency
        tr.show(tc2018); //show tax with some other dependency

        //constructor injection is used
        //to store a dependency A in an object
        //while we're creating and initializing that object

        //afterwards, we can call methods of the object
        //based on the initial dependency A

        //setter injection is used
        //to replace the stored dependency A with some other dependency B
        //at some point during the lifetime of the object

        //afterwards, we can call methods of the object
        //based on the new dependency B

        //method injection is used
        //to temporarily disregard the stored dependency B
        //and treat an arbitrarily provided dependency C as the stored dependency
        //only during a method's execution

        //which lets us call the method
        //based on the provided dependency C

        //afterwards, we can call methods of the object
        //based on the old dependency B

        System.out.println("\n----------------------------------------");
        System.out.println("Interface segregation\n");

        //so now we can't break the TaxReport class
        //by changing TaxCalculator2018 or TaxCalculator2019,
        //but we can still break everything
        //by changing the CanCalculateTax interface

        //for example,
        //if we add a method to it, we would need to implement it
        //in TaxCalculator2018 and TaxCalculator2019.
        //if we change the signature of one of its methods
        //we might need to change the method call in TaxReport.

        //so it's important to keep interfaces
        //small, simple, lightweight and not likely to change

        //interface segregation is to split a big interface
        //into smaller ones
        //to keep the possibility of breaking changes to a minimum

        //also, any single interface should deal with only a single concern
        //it may have several methods, but all of them should be related

        //if not, you risk causing breaking changes in a class
        //that wouldn't ever need to use what was changed anyway

        //we have 3 segregated interfaces: Draggable, Resizeable and Renderable
        //we can obtain the drag() method through classes that implement the Draggable interface

        //implements only draggable
        var dw = new DraggableWindow();

        //implements all 3 segregated interfaces directly
        var cw = new UIWidgetWindowComposite();

        //implements CanUIWidget interface which inherits all 3 segregated interfaces
        var sw = new UIWidgetWindowSingle();

        //to test this, we make some object
        //for which the Draggable interface is a dependency
        var dragobj = new Dragger();

        //now we can call a method that takes a Draggable object
        //by injecting any of the 3 implementations from above
        dragobj.dragUIWindow(dw);
        dragobj.dragUIWindow(cw);
        dragobj.dragUIWindow(sw);

        //and all 3 work fine

        System.out.println("\n----------------------------------------");
        System.out.println("MyTube video project\n");

        //test the User class by creating a new user with an email
        var user = new User("john@domain.com");

        //create a new video object (could have set the fields via setters afterwards)
        var video = new Video(user, "Jennifer's Birthday", "birthday.mp4");

        //instantiate all 3 utility classes which implement our interfaces
        var encoder = new Encoder();
        var storage = new Storage();
        var notifier = new Notifier();

        //create a new video processor and inject all 3 dependencies
        var processor = new VideoProcessor(encoder, storage, notifier);

        //process the video
        processor.process(video); //using the stored dependencies
        System.out.println();
        processor.process(video, encoder, storage, notifier); //injecting temporary dependencies

        System.out.println("\n----------------------------------------");
        System.out.println("Recent changes to interfaces\n");

        //it is now possible to declare fields in an interface
        //which are always static and final
        //they are accessible in all classes that implement the interface
        //this is bad practice because those fields would be IMPLEMENTATION detail
        //and interfaces should be anything BUT implementation!

        //it's also possible to declare static methods in an interface
        //they are accessible in all classes that implement the interface
        //this is also a bad practice, because if you wish to reuse code
        //across all implementations of the interface, then
        //you should create an abstract class which implements the interface
        //and also implements the common method(s) you want to reuse
        //(with the protected access modifier, which is also bad practice)
        //and then leave the implementation of the rest of the interface
        //to the classes that inherit from the abstract class

        //finally it's possible to declare private methods in an interface
        //they are only accessible from within the interface
        //and they are supposed to be called by the interface's static methods
        //which makes them implementation detail on top of implementation detail
        //this is obviously not a good practice on interfaces.

        //these new features effectively enable us to do multiple inheritance
        //so just avoid them.

        System.out.println("\n----------------------------------------");
        System.out.println("Interfaces vs abstract classes\n");

        //they both in principle declare non-implemented methods
        //and force their extending/implementing classes to implement them
        //but they are different in that:

        //interfaces provide loose coupling and dependency injection
        //they make it possible to provide several implementations of the same concern
        //and they enable us to make code changes on the dependencies
        //without breaking the dependant classes
        //they also enable us to unit test a class in isolation
        //because we want to test the class, not its dependencies
        //we do this by injecting dummy dependencies which we control

        //abstract classes provide code reusability
        //and enable us to view our concerns from a high-level abstraction
        //to the point where we know some functionality should exist
        //but we don't worry how it will exist
        //so we let the inheriting classes do the implementation
    }
    //interfaceExamples
}
//Interfaces

//eof
