package com.javacourse.lambdas;

/*
    //wrapper class for Course 3 Section 5: Lambdas

    //import to Main class with:
    import com.javacourse.lambdas.LambdaExamples; //or...
    import com.javacourse.lambdas.*;

    //call from main() method with:
    com.javacourse.lambdas.LambdaExamples.runLambdaExamples(); //or...
    LambdaExamples.runLambdaExamples();
*/

import java.util.List;
import java.util.function.*;

public class LambdaExamples {

    //---------------------------------------------------------------------------------
    //fields

    public String someInstanceField = " world"; //as opposed to " friend"
    public static String infix = " world";

    //---------------------------------------------------------------------------------
    //static methods

    public static void greet(Printable printer) {

        //the greet method calls the one method (print)
        //of the Printable interface

        //some object of some class
        //must implement said method for this to work

        //technically the method is implemented
        //but we also say that the interface is implemented.

        printer.print("hello");
    }
    //greet

    public static void greetZero(PrintableZero printer) {

        printer.print();
    }
    //greetZero

    public static void greetMore(PrintableMore printer) {

        printer.print("hello", "world");
    }
    //greetMore

    public static Printable closureExample(String prefix) {

        //prefix is an arg of this function
        //and therefore it is within scope of anything inside the function
        //including the lambda

        //in a similar way, this postfix variable
        //is also within scope of the lambda
        var postfix = "!!!";

        //finally, there is a static variable
        //from this class called infix (see the top of this file).
        //again, it is outside the lambda but within its scope.

        //we say the lambda has "captured" all 3 variables,
        //even though both infix, prefix and postfix are OUTSIDE the lambda's method implementation!!!

        //now we do some lambda
        //and inside its method implementation, we use the variables
        Printable somelambda = message -> System.out.println( prefix + "lambda #7 says: " + message + infix + postfix );

        //at this point we've already created 3 closures,
        //because infix, prefix and postfix will be accessed
        //whenever/wherever the lambda's print() method is called,
        //and by extension, whenever/wherever the greet() method is called,
        //even though they weren't declared inside of the lambda.

        //try it, by passing the lambda to the greet() method
        //here instead of returning it.

        //we can try it here, by uncommenting this line:
        //greet(somelambda);

        //finally, we return the Printable instantiated from the lambda.
        //so we can test the closures outside of here.
        return somelambda;
    }
    //closureExample

    public static void staticPrintForwardedZero() {

        System.out.println("i'm a message from a static method with no args");
    }
    //staticPrintForwardedZero

    public static void staticPrintForwardedOne(String msg) {

        System.out.println("i'm a message from a static method with one arg: " + msg);
    }
    //staticPrintForwardedOne

    public static void staticPrintForwardedMore(String msg1, String msg2) {

        System.out.println("i'm a message from a static method with these args: " + msg1+" and "+msg2);
    }
    //staticPrintForwardedMore

    public static Printable createPrinter() {

        //keep this as a lambda
        //do not convert to method reference
        //since this is from before that lesson

        return x -> System.out.print(x);
    }
    //createPrinter

    //---------------------------------------------------------------------------------
    //instance methods

    public void showThisInClosure() {

        //in this example, look at the value of this.someInstanceField:

        //here, outside the AIC, this.someInstanceField is " world" (as declared at the top of this class).
        //inside the following AIC, this.someInstanceField is " friend".

        greet( new Printable() {

            public String someInstanceField = " friend"; //as opposed to " world"

            @Override
            public void print(String message) {

                //here, "this" is the Printable object
                System.out.println( "anonymous inner Printable 2 says: " + message + this.someInstanceField );
            }
            //print
        });
        //new Printable

        //here, "this" is the LambdaExamples object
        greet( message -> System.out.println( "lambda #8 says: " + message + this.someInstanceField ) );
    }
    //showThisInClosure

    public void instancePrintForwardedZero() {

        System.out.println("i'm a message from an instance method with no args");
    }
    //instancePrintForwardedZero

    public void instancePrintForwardedOne(String msg) {

        System.out.println("i'm a message from an instance method with one arg: " + msg);
    }
    //instancePrintForwardedOne

    public void instancePrintForwardedMore(String msg1, String msg2) {

        System.out.println("i'm a message from an instance method with these args: " + msg1+" and "+msg2);
    }
    //instancePrintForwardedMore

    //---------------------------------------------------------------------------------
    //examples:

    public static void runLambdaExamples() {

        System.out.println("\n----------------------------------------");
        System.out.println("Functional Interfaces\n");

        //let's state a hypothetical scenario:

        //imagine we only care about one method,
        //not about classes, interfaces, objects, other methods or anything else.
        //also, we only care about calling that method once,
        //so we don't care about its reusability.

        //that's the point of this whole section.

        //a functional interface
        //is any interface with exactly one abstract method.
        //which means, its only purpose is to declare (but not implement) one function.

        //henceforth, the only abstract method in a functional interface
        //will be known as the functional method.

        //we have seen two examples already:
        //the Comparable interface, which provides the compareTo() method,
        //the Comparator interface, which provides the compare() method.
        //in both cases, we only cared about calling those 2 methods.

        //as an example,
        //We have created the Printable functional interface
        //which declares its functional method print(),
        //the ConsolePrinter class, which implements it,
        //and the greet() method, which takes a ConsolePrinter and calls print():

        greet(new ConsolePrinter());

        //so, at this point:
        //we created a whole class: ConsolePrinter,
        //just to implement a single functional method: print(),
        //and we have a reusable implementation of it.

        //having reusable code is usually an advantage,
        //but what if we don't want that?
        //what if creating a class is too overkill?

        //this happens when, in our design,
        //some functional interface is intended
        //to be implemented only once
        //and used for only one single purpose:
        //to call one functional method only once.

        //are we forced to write a whole class
        //just to implement one method and only use it once?
        //makes that class feel like boilerplate code, right?
        //let's call that "the boilerplate problem".

        //the boilerplate problem is two-fold:
        //we care about ONLY ONE method.
        //we want that method to be NOT REUSABLE.

        //let's solve the problem by stating that
        //a class doesn't have to be reusable
        //if we don't need it to.

        //keep in mind, the ConsolePrinter class
        //and any other class like the ones we've seen so far
        //are only reusable because we can identify them BY NAME.

        System.out.println("\n----------------------------------------");
        System.out.println("The Boilerplate Problem, Part 1\n");

        //let's begin by solving one half of the boilerplate problem:
        //caring about only one method.

        //we need a class that MUST implement exactly one FUNCTIONAL INTERFACE.
        //and therefore, implement only the functional method from said interface.
        //only one method! this solves one half of the boilerplate problem!

        //it's genius, if you think about it:

        //1) at the time the class is declared,
        //it identifies BY NAME the functional interface it will implement.
        //we've done that already! check the ConsolePrinter class:
        //public class ConsolePrinter implements Printable {...}

        //2) also, we need a class with NO OTHER methods implemented
        //apart from the functional method overridden from the functional interface.
        //that's exactly the case with our ConsolePrinter class!

        //3) so, if the functional interface and its functional method
        //that we're implementing, are identified by name
        //and there's no other method to implement/call
        //then there's NO AMBIGUITY!

        //let's take advantage of that.
        //in our case, we want to call the print() method:

        //we can be sure that:
        //the ConsolePrinter class identifies the Printable interface by name.
        //the ConsolePrinter class implements the Printable interface and no other interface.
        //the ConsolePrinter class implements no other method apart from those overridden from Printable.
        //the Printable interface declares the print() functional method and no other method.
        //therefore: the ConsolePrinter class implements the print() method and no other method!

        //that's the key part: we didn't have to check that fact.
        //we deduced it logically. because there is no ambiguity. there can't be any other case.
        //the ConsolePrinter class implements the print() method and no other method.
        //that just HAS TO BE TRUE. we're safe assuming that.

        System.out.println("\n----------------------------------------");
        System.out.println("The Boilerplate Problem, Part 2\n");

        //now let's solve the other half of the boilerplate problem:
        //avoiding the reusability of the method.

        //turns out, that's super easy:
        //we remove our ability to identify BY NAME
        //the class that implements the method!

        //we just need a class without a name!
        //an anonymous class is any class without a name.
        //so, by definition, it isn't reusable.

        //so... anonymous classes provide NO REUSABILITY!

        //now, if only our ConsolePrinter class had no name...

        //look at ConsolePrinter.java,
        //it's... rather simple, right?
        //it just identifies the Printable interface by name
        //and then implements the print() method.
        //that's it. that's all it does.

        //in fact it's so little it could be
        //declared in this file, not in its own separate file.

        //turns out, we can do exactly that.

        //we can declare a whole class in the same place
        //where one of its objects would be used.

        //and here's the magic step:
        //in those cases, Java allows us to
        //NOT WRITE THE NAME of the class.

        //and there it is:
        //anonymous classes which implement a functional interface
        //are the solution to the boilerplate problem.

        System.out.println("\n----------------------------------------");
        System.out.println("Anonymous Classes\n");

        //notice how in our ConsolePrinter class
        //there's no explicit constructor.
        //BUT... it's not an abstract class, so it does have an implicit default constructor.
        //meaning: ConsolePrinter can produce objects!

        //by the same token,
        //all anonymous classes can produce objects
        //via an implicit default constructor.

        //anonymous classes are
        //declared, implemented, instanced and used
        //all in the same block of code!

        //they are declared and instanced by:
        //1) writing the "new" keyword,
        //2) followed by the functional interface's name (not the class's!),
        //   which must be in the form of a constructor call (with no args in the parentheses),
        //3) followed by the class body, delimited by curly braces,
        //   which must include the implementation of the functional method,
        //   and nothing else!

        //So the syntax goes like this:
        //new <functional interface>() {...}
        //or, in our example:
        //new Printable() {...}
        //where "{...}" includes only the implementation of the print() method.

        //the implementation of the functional method (in our case print())
        //is exactly the same as in a normal class:
        /*
        @Override
        public void print(String message) {
            System.out.println( "ConsolePrinter says: " + message );
        }
        //print
        */

        //at this point, in runtime, we effectively have an object
        //produced by the anonymous class.
        //We must use it exactly then and there, by:
        //storing it in a variable, or
        //wrapping it inside some other expression.

        //for example, here we store it in a variable:
        //var p = new Printable() {...};

        //and here we wrap it inside the greet() method:
        //greet( new Printable() {...} );

        //here's the whole syntax for implementing
        //our anonymous class, and storing it inside a variable:
        var p = new Printable() {
            @Override
            public void print(String message) {

                System.out.println( "anonymous Printable says: " + message );
            }
            //print
        };
        //new Printable

        //so... yes, the "new" keyword works on interface names
        //even though interfaces cannot have instances
        //because, technically, we're not instantiating an interface,
        //we're instantiating an anonymous class that implements the interface!

        //at this point we have a new variable called p.
        //p is an object that implements Printable.
        //and therefore, other parts of the code
        //can assume that p.print() is a valid method call.

        //p can be passed to other functions, just like a regular object/variable!
        greet(p);

        //in conclusion, anonymous classes are indeed classes
        //and can produce objects
        //but instead of using a class name,
        //they use their implemented interface name.

        System.out.println("\n----------------------------------------");
        System.out.println("Anonymous Inner Classes (AIC's)\n");

        //here's a cool scenario. let's instance our ConsolePrinter:
        var cp = new ConsolePrinter(); //this instantiation is "stored", because the resulting object is stored inside a variable.

        //stored instantiations produce objects we can identify by name
        //so they are reusable.

        //now, we can call greet() with an instance of ConsolePrinter
        //whether it is stored in a variable:
        greet(cp);
        //or not:
        greet(new ConsolePrinter()); //this instantiation is "inline". it's not stored and therefore not reusable.
        //and they both work!

        //now, let's consider the Printable object p we created earlier.
        //p was instantiated and then stored.

        //by the same token, if greet() works with the stored Printable p:
        greet(p);

        //then an inline instantiation of a Printable will work as well:
        greet( new Printable() {
            @Override
            public void print(String message) {

                System.out.println( "anonymous inner Printable says: " + message );
            }
            //print
        });
        //new Printable

        //in that last case, we've created an anonymous INNER class (AIC).
        //any anonymous class instantiated inline is an AIC.

        //notice how after the greet() method runs,
        //we can't access our AIC anymore.
        //we can't reuse it.
        //but we didn't want to, so it's totally fine.

        //AIC's are the most direct way to solve the boilerplate problem.
        //they call exactly one method exactly once, with no reusability,
        //and they don't even create an extra variable,
        //while greet(p) does require an extra variable p.

        //at this point we could delete our ConsolePrinter class!
        //it's been totally obsoleted by much shorter and leaner code!

        //the moral of the story:
        //sometimes, all we want to do is just calling one method once.
        //we can do that, thanks to functional interfaces and AIC's!

        System.out.println("\n----------------------------------------");
        System.out.println("Lambda Expressions\n");

        //we've established that AIC's are useful.
        //but the code they make is too big to be used inline.

        //it could be much shorter and cleaner.
        //which is why Java provides a shorthand syntax for them.
        //it's called a lambda expression.

        //this is possible given how many assumptions we can safely make
        //because functional interfaces and AIC's avoid all ambiguity.

        //the first key assumption we can make
        //is which functional interface is being invoked
        //and by extension, which method.

        //in case of our greet() method,
        //we can see it takes a Printable as its only arg.
        //so we can assume greet() will expect a Printable,
        //or, an implementation of the print() method.

        //therefore, we can assume that the greet() method
        //will internally call the print() method
        //of the object we pass as arg.

        //BUT, instead of passing the Printable object as arg,
        //we can just pass the implementation of print()!
        //though, the implementation of print() must be in the lambda syntax.

        //lambda expressions are just syntax sugar for AIC's.
        //at the bytecode level, Java interprets the expression in lambda syntax
        //and converts it to a regular AIC.

        //the lambda syntax is like this:
        /*
        (<args>) -> {
            <method body>
        }
        */

        //so, the args, between parentheses,
        //followed by a right arrow,
        //followed by the method body.

        //we'll call everything to the left of the arrow: the lambda args
        //and everything to the right: the lambda body

        //that's the basic form of a lambda.
        //however it can be simplified even further,
        //depending on the method.

        //see how our lambda becomes more and more compact and clean:

        //here's our greet method in lambda syntax:
        greet( (String message) -> {
            System.out.println( "lambda #1 says: " + message );
        });
        //greet

        //so, just by providing a lambda implementation of print(),
        //Java can deduce the rest of the AIC
        //thanks to all the assumptions it can make safely.

        //it assumes:
        //the name of the interface (greet expects Printable, so it's Printable),
        //the name of the method (Printable only has print(), so it's print() ),
        //that the method body we provided is the implementation of print(),
        //the amount and types of the method's args (print() takes one String, so the lambda should too. anything else crashes at compile-time),
        //the return type of the method (print() returns void, so the lambda should too. anything different crashes at compile-time),
        //and the @Override annotation.

        //so, when instantiating an object
        //from an AIC's or a lambda,
        //Java will deduce everything it needs to know,
        //except the implementation of the method we want to run!

        //the implementation of print() is the ONLY THING Java doesn't know!
        //it will rightly assume EVERYTHING ELSE!

        //every piece of syntax that can be assumed,
        //becomes redundant and can be removed.
        //for example:

        //REMOVING THE ARG TYPES:
        //since the type of the args can be assumed, we can remove it.
        //because print() establishes it takes a String, so
        //if message is not a String, this will crash at compile-time:
        greet( (message) -> {
            System.out.println( "lambda #2 says: " + message );
        });
        //greet

        //REMOVING THE ARG PARENTHESES:
        //the amount of args is also assumed.
        //(print() takes a single arg, so only implementations that take a single arg will work,
        //any other amount of args crashes at compile-time)
        //try it by passing zero args or two args, like:
        //greet( () -> {...} ); //zero args, crashes
        //greet( (foo, bar) -> {...} ); //two args, crashes

        //in cases (like ours) where the method takes a single arg,
        //the parentheses for the args can be removed, like:
        greet( message -> {
            System.out.println( "lambda #3 says: " + message );
        });
        //greet

        //REMOVING THE LINE BREAKS:
        //just like in any Java code, we can remove the line breaks
        //if we prefer:
        greet( message -> { System.out.println( "lambda #4 says: " + message ); });

        //REMOVING THE CURLY BRACES:
        //and if the lambda body is a single line,
        //we can remove the curly braces AND the inner semicolon:
        greet( message -> System.out.println( "lambda #5 says: " + message ) );

        //see how compact it is now?
        //compare it against the first lambda
        //or against our AIC.
        //that's the beauty of being able to make safe assumptions.

        //REMOVING THE RETURN KEYWORD:
        //print() doesn't return a value (it's void).
        //but, in case of a method that does return something
        //if the lambda body is a single line,
        //we can remove the return keyword.

        //for example let's assume that
        //instead of a greet() method which expects a Printable for calling print(message),
        //we had a sum() method which expects a Calculatable for calling calculate(a,b).

        //this calculate() method:      calculate(int a, int b) { return a+b; }
        //would become this lambda:     (a,b) -> a+b

        //so we'd call sum() like this: sum( (a,b) -> a+b );
        //which actually returns the result of a+b
        //but omits the "return" keyword.

        //one important thing to note:
        //a lambda expression, on its own, like this:
        //message -> System.out.println( "lambda #5 says: " + message );
        //it's just the implementation of some method.
        //BUT neither us nor Java know which method it is, nor which interface it comes from
        //(which is why, if written like that,
        //Java sees an incomplete statement and crashes at compile-time)

        //it is only AFTER that, that our lambda is given to
        //something that expects an implementation of
        //some method of some interface (both known by name)
        //like here, the lambda is given to the greet() method which knows the interface by name:
        //greet( message -> System.out.println( "lambda #5 says: " + message ) );

        //at that point, Java can check the compatibility
        //of the provided lambda, and the method it's supposed to implement
        //according to the method signature in the interface.
        //(in this case, greet expects a Printable, so the lambda is supposed to implement print())
        //if they're not compatible, we get a compile-time crash.

        System.out.println("\n----------------------------------------");
        System.out.println("Storing A Lambda In A Variable\n");

        //as we saw, Java can actually instantiate a new object from a lambda.

        //in case you're wondering, yes. you can store them in variables.
        //all you need to provide is the lambda, and the functional interface:
        Printable printer = message -> System.out.println( "lambda #6 says: " + message );
        greet(printer);

        //in this case, using var instead of Printable won't work
        //since Java needs to be told what is the functional interface at play.

        //we've just created a new object called printer from a lambda.
        //what's the actual class of our printer object?
        //the answer is we don't know, don't need to know and don't care.
        //it is an object capable of executing some implementation of print().
        //that's all that matters to us.

        System.out.println("\n----------------------------------------");
        System.out.println("Scope, Variable Captures And Closures\n");

        //as we've seen before, the scope of a code block
        //is which variables and methods (members) it has access to.
        //as general rules:

        /*
        block A {
            v1 is declared here / v1 is accessible here
            block B {
                v2 is declared here / v1,v2 are accessible here
                block C {
                    v3 is declared here / v1,v2,v3 are accessible here
                }
            }
            block D{
                v4 is declared here / v1,v4 are accessible here
            }
        }
        */

        //any code block can access those members
        //from inside it...
        //but not from inside its inner nested code blocks
        //(B can access members in B except those inside C).

        //any code block can access those members
        //from inside the outer code block that wraps it...
        //but not from inside other code blocks nested at the same level
        //(B can access members in A except those inside D).

        //anything else is not accessible
        //(C can access members in A,B,C except those inside D).

        //this enables us to do closures.

        //a closure is when two conditions happen:
        //1) some member is declared in an outer code block,
        //   but, because of scope, it is accessible inside an inner code block.
        //2) all members declared in the inner block are final.

        //that's the case with AIC's and lambdas:
        //all code declared inside them is final.

        //inside AIC's and lambdas we have a method body
        //and, from inside that body, we have access to outer members
        //because of the scope.

        //if we reference those members explicitly inside the method body,
        //we say they become "captured" by the AIC/lambda,
        //because they become members of the AIC/lambda
        //and they become final.

        //and then, when the method is called,
        //(whenever and wherever it's called),
        //the method call might be outside the captured member's scope,
        //but the variable will still be accessed,
        //which seemingly goes against the rules of scope!

        //so closures are a way for us to circumvent the limitations of scope!

        //here's an example:
        //we've created the method closureExample() (see above),
        //it returns a lambda with 3 closures inside it,
        //they are 3 variables called infix, prefix and postfix.

        //at this point we will create a lambda from closureExample()
        //and also call greet() with that lambda.
        //so this is the scope of both the lambda and the method call.

        //other relevant scopes for this example are
        //the scope of greet()'s declaration
        //and the scope of closureExample()'s declaration
        //(see both above).

        //we pass a value to the prefix arg,
        //just to test that it works, even though
        //it was declared here, very far from closureExample's scope.
        Printable somelambda = closureExample("¡¡¡");

        //this class defines a static field called infix, and
        //closureExample() defines a variable called postfix.
        //these are accessible inside the greet() method
        //even though both greet()'s declaration and this call here
        //are very far away from both closureExample's scope.
        greet(somelambda);

        //see how all 3 variables: prefix, infix and postfix work fine!

        System.out.println("\n----------------------------------------");
        System.out.println("The this Object Inside Closures\n");

        //even though lambdas are mostly syntax sugar over AIC's
        //they have some functional differences.

        //first one is how they interpret the "this" object.

        //in a nutshell:
        //"this" inside an AIC represents the object being instantiated by it.
        //"this" inside a lambda body represents an object of the outer class that wraps the lambda.

        //by the way, both cases are forms of closures.

        //check the showThisInClosure() method above for an example:
        var le = new LambdaExamples();
        le.showThisInClosure();

        System.out.println("\n----------------------------------------");
        System.out.println("Fields Inside AIC's\n");

        //second difference is:
        //AIC's have a block of code outside the method implementation
        //which allows for the declaration of fields, like in any other class:

        greet( new Printable() {

            //in this space, we're inside the AIC
            //but we're outside the implementation of print().
            //here we can declare fields, like in any normal class

            //keep in mind this space doesn't exist in lambda expressions,
            //so lambdas can't own fields.
            public static String some_inner_field = ", I'm an AIC!"; //a field

            @Override
            public void print(String message) {

                //here, "this" is the Printable object
                System.out.println( "anonymous inner Printable 3 says: " + message + some_inner_field );
            }
            //print
        });
        //new Printable

        //therefore we say lambdas are stateless while AIC's are stateful.
        //which means, both can access their scope plus their closures
        //but only AIC's can own fields.

        System.out.println("\n----------------------------------------");
        System.out.println("Returning A Lambda\n");

        //so, a lambda can be passed onto another method or expression,
        //as long as that method or expression
        //expects an object of the functional interface the lambda implements.

        //a lambda can also be stored into a variable,
        //as long as that variable
        //is of the functional interface the lambda implements.

        //finally, a lambda can also be returned from a method,
        //as long as the return type of the method
        //is the functional interface the lambda implements.

        //for example:
        //check the createPrinter() method on the top.
        //it creates a valid object of the Printable interface
        //and returns it
        //so we can, for example, pass it to greet():
        greet(createPrinter());

        //important note:
        //if the functional interface has a generic datatype
        //then that type should also match wherever the lambda is being returned.

        System.out.println();

        System.out.println("\n----------------------------------------");
        System.out.println("Method References (MR's)\n");

        //consider this:
        //every lambda defines the implementation of a method.
        //but, inside that implementation, many things could happen
        //and some of those things are extremely simple.
        //this topic addresses one of those cases:

        //sometimes all we do in the lambda's inner implementation
        //is taking some value x, and forwarding it to some method, like:
        //x -> someMethod(x)
        //which can happen in two forms:
        //x -> ClassName.someStaticMethod(x)
        //x -> objectName.someInstanceMethod(x)

        //avoid confusion: x is the value that becomes the method's arg
        //and objectName/ClassName are the method owners or callers.

        //there's a similar, but even simpler scenario,
        //when the method takes no args:
        //() -> someMethod()

        //in those cases, there are more assumptions we can make
        //and therefore those expressions can be simplified even further
        //through a technique called method references (MR's).

        //the syntax goes like this:
        //<method owner>::<method name>
        //with no parentheses on the method name,
        //and not a single mention of the args.

        //for these next examples
        //we use an instance of this class itself:
        LambdaExamples le1 = new LambdaExamples();

        //we'll also use our greet(), greetZero() and greetMore() methods
        //and our Printable, PrintableZero and PrintableMore interfaces.

        //scenario #1: forwarding zero args to a static method:
        //this lambda: () -> ClassName.someStaticMethod()
        //becomes this MR: ClassName::someStaticMethod

        greetZero( () -> LambdaExamples.staticPrintForwardedZero() );
        greetZero( LambdaExamples::staticPrintForwardedZero );

        //scenario #2: forwarding 1 arg to a static method:
        //this lambda: x -> ClassName.someStaticMethod(x)
        //becomes this MR: ClassName::someStaticMethod

        greet( x -> LambdaExamples.staticPrintForwardedOne(x) );
        greet( LambdaExamples::staticPrintForwardedOne );

        //scenario #3: forwarding several args to a static method:
        //this lambda: (x,y) -> ClassName.someStaticMethod(x,y)
        //becomes this MR: ClassName::someStaticMethod

        greetMore( (x,y) -> LambdaExamples.staticPrintForwardedMore(x,y) );
        greetMore( LambdaExamples::staticPrintForwardedMore );

        //scenario #4: forwarding zero args to an instance method:
        //this lambda: () -> objectName.someInstanceMethod()
        //becomes this MR: objectName::someInstanceMethod

        greetZero( () -> le1.instancePrintForwardedZero() );
        greetZero( le1::instancePrintForwardedZero );

        //scenario #5: forwarding 1 arg to an instance method:
        //this lambda: x -> objectName.someInstanceMethod(x)
        //becomes this MR: objectName::someInstanceMethod

        greet( x -> le1.instancePrintForwardedOne(x) );
        greet( le1::instancePrintForwardedOne );

        //scenario #6: forwarding several args to an instance method:
        //this lambda: (x,y) -> objectName.someInstanceMethod(x,y)
        //becomes this MR: objectName::someInstanceMethod

        greetMore( (x,y) -> le1.instancePrintForwardedMore(x,y) );
        greetMore( le1::instancePrintForwardedMore );

        //run all of those to see that MR's are equivalent to their lambdas.

        //as we can see, it doesn't matter how many args the method asks for
        //we can use the MR without specifying them at all
        //and it will work fine, as long as Java determines that the MR
        //and the method taking it, are compatible.

        //for example, greetMore() calls the print() method
        //from the PrintableMore interface.
        //greetMore() takes le1::instancePrintForwardedMore as arg.
        //print() and instancePrintForwardedMore() both take two strings,
        //and both return void, so they are compatible!

        //there are more valid scenarios for MR's
        //on top of those we've seen.
        //we'll explore them in the next lessons.

        System.out.println("\n----------------------------------------");
        System.out.println("Method References In Variables\n");

        //the previous examples used methods like greet()
        //but it is also possible to store the MR's in variables
        //instead of passing them to a method immediately:

        //here's all the previous MR's, stored into variables:

        PrintableZero spz = LambdaExamples::staticPrintForwardedZero;
        Printable     spo = LambdaExamples::staticPrintForwardedOne;
        PrintableMore spm = LambdaExamples::staticPrintForwardedMore;
        PrintableZero ipz = le1::instancePrintForwardedZero;
        Printable     ipo = le1::instancePrintForwardedOne;
        PrintableMore ipm = le1::instancePrintForwardedMore;

        //so now... we could call the greet()-like methods
        //on the stored MR's, like greet(spo);
        //or we could use the MR's
        //to call their implemented print() methods directly:

        //this lets us provide our own args:
        spz.print();
        spo.print("foo");
        spm.print("foo", "bar");
        ipz.print();
        ipo.print("foo");
        ipm.print("foo", "bar");

        //this works, because all those variables we just created
        //are of a datatype which identifies some functional interface by name
        //and therefore, the MR's become implementations of
        //the functional method inside the interface.
        //and if they're not compatible, we'd have a compile-time error.

        //for example, in the case of the spm variable:
        //it is of the type PrintableMore, a functional interface
        //which declares the functional method print(), which takes two strings and returns void,
        //and it stores the MR LambdaExamples::staticPrintForwardedMore;
        //which matches its signature, since the staticPrintForwardedMore() method
        //also takes two strings and returns void.
        //so, staticPrintForwardedMore() is compatible with print(),
        //so, it becomes an implementation of it.

        //at this point one more thing becomes apparent:
        //an implementation for a functional method can come from ANYWHERE
        //not only from a class that explicitly implements the functional interface.

        //for example, in the previous cases,
        //we used methods from this class (LambdaExamples)
        //as implementations of the Printable-like interfaces,
        //even though LambdaExamples doesn't explicitly implement them, like:
        //"public interface LambdaExamples implements Printable"

        System.out.println("\n----------------------------------------");
        System.out.println("Method References In Constructors\n");

        //Consider the case where
        //the implementation of the lambda's method
        //is just a constructor call without args:
        //() -> new ClassName()

        //sometimes, inside the implementation of a functional method
        //we forward args not to a regular method, but to a constructor

        //and sometimes, that constructor could be a valid implementation
        //of the functional method.

        //That happens when the functional method
        //returns an object of the class being constructed.
        //and also, the args on both match each other.

        //there's an MR syntax for that as well.

        //take our Constructable interface and Dummy class as an example.
        //we'll declare a Constructable variable:
        Constructable c;

        //and now, this lambda:
        c = x -> new Dummy(x);
        //becomes this MR:
        c = Dummy::new;

        //and now, our Constructable/Dummy object c
        //can call a working implementation of its printMsg() method:
        c.printMsg("I came here through a constructor method reference!");

        System.out.println("\n----------------------------------------");
        System.out.println("Instance Methods Called From The Args\n");

        //normally we reference an object as the owner/caller of an instance method
        //but we can get some (weird) interesting behavior with a class instead:

        //consider this lambda:
        //(a,b,c) -> ClassName.instanceMethod(a,b,c);
        //it makes no sense, since the class has no access to the instanceMethod method.
        //classes can only access static methods, not instance methods!

        //this next MR seemingly comes from the above lambda.
        //what does it do? does it make no sense as well?:
        //ClassName::instanceMethod

        //surprisingly, it won't crash.
        //instead, it will assume the MR comes from this lambda:
        //(a,b,c) -> a.instanceMethod(b,c);
        //where a (the first arg) is assumed to be an object of the ClassName class,
        //and the rest of the args are forwarded to instanceMethod().

        //why does that happen?
        //MR's behave this way to enable a very useful trick:

        //take this lambda for example (assume str is a String):
        //str -> str.length()
        //this is a lambda that calls an instance method, however
        //it is not the usual situation where we forward the arg into another method
        //in this case, str is the arg, and we don't forward it into anything
        //instead, in the lambda body, the arg (str) calls an instance method of itself.

        //since we want it to be possible to use an MR in such a case as well,
        //the weird behavior described above now actually does exactly what we want:
        //String::length

        //as we can see, it makes sense:
        //the str variable (the first arg) in the lambda is assumed to be of the String class
        //and the body of the lambda calls an instance method (length()) of str.
        //any other args would be forwarded to length() (although in this case there are none).

        //in both cases, after the lambda or MR is declared
        //(which provides an implementation to some functional method),
        //the call to the functional method should provide an appropriate value as arg.

        //let's do an example. We'll use our CanGetLength interface
        //which has a getLength() method that takes a String and returns an int.
        //we'll test both syntaxes and see they're equivalent:

        //first, provide an implementation of the getLength() method:
        CanGetLength strlen1 = str -> str.length();
        CanGetLength strlen2 = String::length;

        //next, call the (now implemented) getLength() method and provide a valid arg for it:
        System.out.println( strlen1.getLength("hola") ); //lambda
        System.out.println( strlen2.getLength("hola") ); //MR
        System.out.println( strlen1.getLength("mundo") ); //lambda
        System.out.println( strlen2.getLength("mundo") ); //MR
        System.out.println( strlen1.getLength("soy un string!") ); //lambda
        System.out.println( strlen2.getLength("soy un string!") ); //MR

        //long story short:
        //any functional method implementation
        //where one of the args calls one of its instance methods
        //can be expressed both as lambda and as MR.

        System.out.println("\n----------------------------------------");
        System.out.println("Common Functional Interfaces\n");

        //Java defines lots of functional interfaces we can use
        //in the java.util.function package.

        //these include:

        //Consumer
        //its functional method is accept()
        //it matches any implementation that takes one arg and returns nothing.

        //Supplier
        //its functional method is get()
        //it matches any implementation that takes no args and returns something.

        //Function
        //its functional method is apply()
        //it matches any implementation that takes one arg and returns something.

        //Operator
        //its functional method is apply(), among others
        //it matches any implementation that takes one arg of some type and returns a value of that same type.

        //Predicate
        //its functional method is test()
        //it matches any implementation that takes one arg and returns a boolean.

        //...among many others. however, the rest are just variations of the 5 types above:
        //any interface with "Bi" on its name takes two args, not one.
        //any interface with a primitive type on its name takes a value of that type as arg.
        //any interface with "To<Type>" on its name returns a value of said Type.
        //variations with primitive values are offered to avoid boxing if unnecessary.

        //also keep in mind that
        //all basic forms of the 5 interfaces above
        //are generic, so they require an explicit generic datatype declaration.

        System.out.println("\n----------------------------------------");
        System.out.println("The Consumer Functional Interface\n");

        //the easiest way to demonstrate the Consumer interface
        //is by calling the forEach() method.

        //the forEach() method (not to be confused with the foreach loop)
        //performs some operation and returns nothing,
        //for each element in a collection.
        //That's exactly the same thing that the accept() method of the Consumer interface does.
        //Which is why the forEach() method takes a Consumer as arg.

        //the Consumer interface's functional method is accept().
        //in these examples, we will not call it explicitly
        //because the forEach() method calls it for us.

        //our vanilla print statement conforms to the accept() method's signature
        //(it takes one value and returns nothing)
        //so it is a valid implementation of it.
        //here we express it as an MR and store it in a Consumer variable.
        //also, notice how Consumer needs a generic datatype, in this case <Integer>
        Consumer<Integer> printer_consumer = System.out::print;

        //now we create a collection of Integer's to be consumed by our Consumer:
        List<Integer> numbers = List.of(1,2,3,4,5,6);

        //next we call the forEach() method (every collection object has it)
        //and pass our Consumer object to it,
        //so we iterate through the list, running the accept() method
        //which in turn runs a print(), for each element:
        System.out.println("number consumer:");
        numbers.forEach(printer_consumer);

        System.out.println("\n");

        //CHAINING CONSUMERS:

        //Consumers are chainable through the andThen() method of the Consumer interface.
        //andThen() is not an abstract method so Consumer can have it and the accept() method
        //and still be a valid functional interface.

        //andThen() is a default method, which means it already has implementation.
        //so we can trust the Java standard library and simply call it.
        //cases like these are the only ones where static/private/default stuff in interfaces is a good practice,
        //that is, when all the code is already done by Java.
        //it is still a very bad practice to code static/private/default stuff in interfaces ourselves!

        //to demo the andThen() method, we'll create a list of strings:
        List<String> letters = List.of("a","b","c","d","e");

        //then we'll create two simple lambdas:
        Consumer<String> string_printer    = System.out::print;
        Consumer<String> uppercase_printer = str -> System.out.print(str.toUpperCase());

        //notice how we could turn the first lambda into an MR
        //but not the second one, because, unlike the first one,
        //the second one is not just a straightforward forwarding of str,
        //it also calls the toUpperCase() method.

        //now, to call them both in a chain fashion, first we need some method
        //that takes a Consumer. forEach() is fine.
        //then, we pass our first consumer (string_printer) to it.
        //but indeed, string_printer IS a consumer, so it has the andThen() method
        //we call andThen() inline, which takes another consumer, so we pass our second one:
        System.out.println("letter consumer, 2 steps, 1 line:");
        letters.forEach(string_printer.andThen(uppercase_printer));

        System.out.println("\n");

        //as a best practice, we should clean our code
        //by splitting the chain into separate lines:
        System.out.println("letter consumer, 2 steps, 2 lines:");
        letters.forEach(
            string_printer
            .andThen(uppercase_printer
        ));

        System.out.println("\n");

        //notice how our second consumer IS also a consumer
        //so we can keep chaining!
        System.out.println("letter consumer, 3 steps, 3 lines, inner chain:");
        letters.forEach(
            string_printer
            .andThen(uppercase_printer
            .andThen(string_printer
        )));

        System.out.println("\n");

        //our consumer objects (string_printer and uppercase_printer) are Consumers.
        //But the return values of the andThen() method calls are also Consumers!
        //so we can also chain every andThen() call on the outside of the parentheses,
        //which is equivalent, but cleaner:
        System.out.println("letter consumer, 3 steps, 3 lines, outer chain:");
        letters.forEach(
            string_printer
            .andThen(uppercase_printer)
            .andThen(string_printer)
        );

        System.out.println();

        //variants of the Consumer interface:

        // METHOD                  | 1ST ARG            | 2ND ARG            | RETURNS            |
        //------------------------------------------------------------------------------------------
        // Consumer<T>             | object of type <T> | ~                  | ~                  |
        //------------------------------------------------------------------------------------------
        // IntConsumer             | primitive int      | ~                  | ~                  |
        // LongConsumer            | primitive long     | ~                  | ~                  |
        // DoubleConsumer          | primitive double   | ~                  | ~                  |
        //------------------------------------------------------------------------------------------
        // BiConsumer<T,U>         | object of type <T> | object of type <U> | ~                  |
        //------------------------------------------------------------------------------------------
        // ObjIntConsumer<T>       | object of type <T> | primitive int      | ~                  |
        // ObjLongConsumer<T>      | object of type <T> | primitive long     | ~                  |
        // ObjDoubleConsumer<T>    | object of type <T> | primitive double   | ~                  |
        //------------------------------------------------------------------------------------------
        // where <T>, <U> and <R> are any arbitrary object types.

        System.out.println("\n----------------------------------------");
        System.out.println("The Supplier Functional Interface\n");

        //the Supplier interface is useful for generating a new value out of thin air.

        //here we're creating a variable for a Supplier of Double
        //and we'll provide an implementation for the Supplier's get() method
        //that should not take any args
        //and return a value of the generic datatype (in this case Double)
        //and then store it in the variable.

        //we'll use the Math.random() method since it conforms to that definition
        //plus it returns a double (which auto-boxes itself into a Double object).
        //so, we now have a method capable of generating a new value out of thin air:
        Supplier<Double> getRandom = Math::random;

        //we can test that our implemented Supplier works by calling its get() method:
        System.out.println( getRandom.get() );

        //as a bonus, we can test one of the variants of Supplier, called DoubleSupplier
        //which doesn't need the generic datatype and assumes it'll work with a primitive double:
        DoubleSupplier getRandomDouble = Math::random;

        //instead of the get() method, the DoubleSupplier interface has the getAsDouble() method:
        System.out.println( getRandomDouble.getAsDouble() );

        //variants of the Supplier interface:

        // METHOD                  | 1ST ARG            | 2ND ARG            | RETURNS            |
        //------------------------------------------------------------------------------------------
        // Supplier<R>             | ~                  | ~                  | object of type <R> |
        //------------------------------------------------------------------------------------------
        // BooleanSupplier         | ~                  | ~                  | primitive bool     |
        // IntSupplier             | ~                  | ~                  | primitive int      |
        // LongSupplier            | ~                  | ~                  | primitive long     |
        // DoubleSupplier          | ~                  | ~                  | primitive double   |
        //------------------------------------------------------------------------------------------
        // where <T>, <U> and <R> are any arbitrary object types.

        System.out.println("\n----------------------------------------");
        System.out.println("The Function Functional Interface\n");

        //The Function interface, no matter how generic it's name may be,
        //follows a specific form: taking one value of some datatype as arg
        //and returning a value of some datatype.
        //The datatypes of the arg and the return value should not necessarily be the same.

        //The CanGetLength interface we made earlier follows the same form as the Function interface.
        //we'll make a similar one here, using the Function interface,
        //a function that takes a String and returns its length (an Integer):
        Function<String,Integer> length_of_string = String::length;
        System.out.println( length_of_string.apply("hola") );
        System.out.println( length_of_string.apply("mundo") );
        System.out.println( length_of_string.apply("soy un string!") );

        System.out.println();

        //CHAINING FUNCTIONS:

        //In a similar way to how we chained Consumers
        //we can run chained Functions.

        //with consumers, every Consumer object has an andThen() method.
        //Function objects also have an andThen() method.

        //andThen() works like this:
        //given a caller Function, and another Function in its args:
        //like this: callerFunction.andThen(argFunction)

        //andThen() first runs callerFunction.apply(argFunction)
        //producing a result1 value
        //and then runs argFunction.apply(result1)
        //producing a result2 value.

        //finally, andThen() returns a Function f that does all the steps above.
        //theoretically, f.apply(<some args>) would return result2 for those args.

        //the Function interface's functional method is apply().
        //since apply() does not return another Function object,
        //the call to it must be at the very end of the chain.

        //we'll demonstrate with this simple example:
        //given a string with a colon in the middle
        //we'll replace the colon with an equals sign
        //and then wrap it with curly braces.
        //so: "foo:bar" becomes "foo=bar" becomes "{foo=bar}".

        //for that, let's create two function objects:
        Function<String,String> colonToEquals = str -> str.replace(':','=');
        Function<String,String> wrapInBraces  = str -> "{" + str + "}";

        //those lambdas can't be turned into MR's
        //because we've put some arbitrary chars inside the lambda bodies,
        //and arbitrary stuff is unpredictable and can't be assumed.

        //when we used Consumers, we used the forEach() method,
        //which, on the inside, calls the functional method (accept()) of the Consumer interface.
        //that is why we didn't ever call accept() explicitly.
        //in the case of Functions, we will call the functional method (apply()) explicitly.

        //unlike with Consumers,
        //we cannot chain on the inside of the parentheses (on our Function objects),
        //only on the outside (on the return value of the andThen() method),
        //because the andThen() method from the Function interface returns another Function object
        //but the apply() method does not.

        //here's an example using both our Function objects
        //and using one of them twice:
        System.out.println(
            colonToEquals
            .andThen(wrapInBraces)
            .andThen(wrapInBraces)
            .apply("foo:bar")
        );

        //how did that work?
        //since andThen() returns a Function object, we can understand the chain like this:

        //apply() is called on "colonToEquals.andThen(wrapInBraces).andThen(wrapInBraces)",
        //which is a Function chain of 3 Function's.
        //so the args of the final apply("foo:bar") call
        //get propagated into "colonToEquals.andThen(wrapInBraces).andThen(wrapInBraces)".

        //whenever there's a Function chain, it is resolved left-to-right,
        //in this case, starting with colonToEquals.

        //colonToEquals.apply("foo:bar") is called inside colonToEquals.andThen(wrapInBraces)
        //which returns "foo=bar" and forwards it to the first wrapInBraces.

        //the first wrapInBraces.apply("foo=bar") is called inside the second andThen(wrapInBraces)
        //which returns "{foo=bar}" and forwards it to the second wrapInBraces.

        //the final apply() call becomes the call to the second wrapInBraces.apply("{foo=bar}")
        //and returns "{{foo=bar}}", and then hits the end of the chain.

        //the resulting value "{{foo=bar}}" is then returned to outside the entire chain.

        //COMPOSING FUNCTIONS

        //in a way much similar to chaining, we can compose Function's.
        //this is easily understood as a chain that resolves in the reverse order (right-to-left)
        //composition uses the compose() method instead of the andThen() method.

        //compose() works like this:
        //given a caller Function, and another Function in its args:
        //like this: callerFunction.compose(argFunction)

        //compose() first runs argFunction.apply(callerFunction)
        //producing a result1 value
        //and then runs callerFunction.apply(result1)
        //producing a result2 value.

        //finally, andThen() returns a Function f that does all the steps above.
        //theoretically, f.apply(<some args>) would return result2 for those args.

        //once again, the call to apply() must be at the end of the chain.

        //here's an example using both our Function objects
        //and using one of them twice:
        System.out.println(
            colonToEquals
            .compose(wrapInBraces)
            .compose(wrapInBraces)
            .apply("foo:bar")
        );

        //how did that work?
        //since compose() returns a Function object, we can understand the composition like this:

        //apply() is called on "colonToEquals.compose(wrapInBraces).compose(wrapInBraces)",
        //which is a Function composed of 3 Function's.
        //so the args of the final apply("foo:bar") call
        //get propagated into "colonToEquals.compose(wrapInBraces).compose(wrapInBraces)".

        //whenever there's a Function composition, it is resolved right-to-left,
        //in this case, starting with the second wrapInBraces.

        //the second wrapInBraces.apply("foo:bar") is called inside the second compose(wrapInBraces)
        //which returns "{foo:bar}" and forwards it to the first wrapInBraces.

        //the first wrapInBraces.apply("{foo:bar}") is called inside colonToEquals.compose(wrapInBraces)
        //which returns "{{foo:bar}}" and forwards it to colonToEquals.

        //the final apply() call becomes the call to colonToEquals.apply("{{foo:bar}}")
        //and returns "{{foo=bar}}", and then hits the end of the composition.

        //the resulting value "{{foo=bar}}" is then returned to outside the entire composition.

        //variants of the Function interface:

        // METHOD                  | 1ST ARG            | 2ND ARG            | RETURNS            |
        //------------------------------------------------------------------------------------------
        // Function<T,R>           | object of type <T> | ~                  | object of type <R> |
        //------------------------------------------------------------------------------------------
        // IntFunction<R>          | primitive int      | ~                  | object of type <R> |
        // LongFunction<R>         | primitive long     | ~                  | object of type <R> |
        // DoubleFunction<R>       | primitive double   | ~                  | object of type <R> |
        //------------------------------------------------------------------------------------------
        // ToIntFunction<T>        | object of type <T> | ~                  | primitive int      |
        // ToLongFunction<T>       | object of type <T> | ~                  | primitive long     |
        // ToDoubleFunction<T>     | object of type <T> | ~                  | primitive double   |
        //------------------------------------------------------------------------------------------
        // IntToLongFunction       | primitive int      | ~                  | primitive long     |
        // IntToDoubleFunction     | primitive int      | ~                  | primitive double   |
        // LongToIntFunction       | primitive long     | ~                  | primitive int      |
        // LongToDoubleFunction    | primitive long     | ~                  | primitive double   |
        // DoubleToIntFunction     | primitive double   | ~                  | primitive int      |
        // DoubleToLongFunction    | primitive double   | ~                  | primitive long     |
        //------------------------------------------------------------------------------------------
        // BiFunction<T,U,R>       | object of type <T> | object of type <U> | object of type <R> |
        //------------------------------------------------------------------------------------------
        // ToIntBiFunction<T,U>    | object of type <T> | object of type <U> | primitive int      |
        // ToLongBiFunction<T,U>   | object of type <T> | object of type <U> | primitive long     |
        // ToDoubleBiFunction<T,U> | object of type <T> | object of type <U> | primitive double   |
        //------------------------------------------------------------------------------------------
        // where <T>, <U> and <R> are any arbitrary object types.

        System.out.println("\n----------------------------------------");
        System.out.println("The Operator Functional Interface\n");

        //Operator is not an actual interface. It doesn't exist in the Java standard library.
        //It is a special variant of the Function interface, so all Operator's are in fact Function's.
        //this means Operator's have the apply(), andThen() and compose() methods too.

        //Operators are special because their apply() method
        //uses THE SAME DATATYPE for all its args and its return value.

        //Unary operators take 1 arg. Binary operators take 2.

        //let's make two UnaryOperator's for arithmetic operations:
        UnaryOperator<Integer> duplicate = n -> n*2;
        UnaryOperator<Integer> square    = n -> n*n;

        //let's apply those to some input integer through both chaining and composition:
        var chain_result = duplicate.andThen(square).apply(3); //(n*2)^2
        var comp_result  = duplicate.compose(square).apply(3); //(n^2)*2

        //this lets us see the difference between chaining and composition
        System.out.println( "chained: " + chain_result );
        System.out.println( "composed: " + comp_result );

        //boxing and unboxing consumes processing power, RAM and time.
        //to avoid unnecessary auto-boxing/unboxing, we could use primitive specializations,
        //like IntUnaryOperator, instead.

        //to demonstrate the primitive and the binary specializations
        //let's do a similar example with the IntBinaryOperator interface.

        IntBinaryOperator sum     = (a,b) -> a+b; //don't turn into method reference, it will use the Integer class, which will autobox the args
        IntBinaryOperator product = (a,b) -> a*b;

        //unfortunately, binary primitive specializations of operators
        //can't be chained nor composed so we'll just run them:
        var sum_result  = sum.applyAsInt(3,2);
        var mult_result = product.applyAsInt(3,2);

        //just print the results:
        System.out.println( "sum: " + sum_result );
        System.out.println( "product: " + mult_result );

        //variants of the Operator interface:

        // METHOD                  | 1ST ARG            | 2ND ARG            | RETURNS            |
        //------------------------------------------------------------------------------------------
        // UnaryOperator<T>        | object of type <T> | ~                  | object of type <T> |
        //------------------------------------------------------------------------------------------
        // IntUnaryOperator        | primitive int      | ~                  | primitive int      |
        // LongUnaryOperator       | primitive long     | ~                  | primitive long     |
        // DoubleUnaryOperator     | primitive double   | ~                  | primitive double   |
        //------------------------------------------------------------------------------------------
        // BinaryOperator<T>       | object of type <T> | object of type <T> | object of type <T> |
        //------------------------------------------------------------------------------------------
        // IntBinaryOperator       | primitive int      | primitive int      | primitive int      |
        // LongBinaryOperator      | primitive long     | primitive long     | primitive long     |
        // DoubleBinaryOperator    | primitive double   | primitive double   | primitive double   |
        //------------------------------------------------------------------------------------------
        // where <T>, <U> and <R> are any arbitrary object types.

        System.out.println("\n----------------------------------------");
        System.out.println("The Predicate Functional Interface\n");

        //Predicate is a functional interface used to validate some condition on its input.
        //its functional method is test() which takes one arg and returns a boolean.

        //its most common use is to make filters for data.

        //is has a two-arg specialization called BiPredicate
        //and also several primitive specializations.

        //here's a simple example. this test checks if a string has more than 5 characters:
        Predicate<String> isLongerThan5 = str -> str.length() > 5;
        System.out.println( isLongerThan5.test("sky") );
        System.out.println( isLongerThan5.test("marios") );

        System.out.println();

        //COMBINING PREDICATES

        //in a similar way to the composition and chaining we saw before
        //Predicates can be combined through logic operations (AND / OR / NOT)
        //After all, they deal with booleans.

        //here's a more complex example.
        //this test checks separately for surrounding curly braces on a string:

        Predicate<String> hasLeftBrace  = str -> str.startsWith("{");
        Predicate<String> hasRightBrace = str -> str.endsWith("}");

        //Then we combine both tests into one using the and() / or() methods:
        Predicate<String> hasTwoBraces = hasLeftBrace.and(hasRightBrace);
        Predicate<String> hasOneBrace  = hasLeftBrace.or(hasRightBrace);

        //let's test some strings:
        System.out.println( hasTwoBraces.test("{foo}") );
        System.out.println( hasTwoBraces.test("{fo}o") );
        System.out.println( hasTwoBraces.test("fo}o") );
        System.out.println();
        System.out.println( hasOneBrace.test("{foo}") );
        System.out.println( hasOneBrace.test("{fo}o") );
        System.out.println( hasOneBrace.test("fo}o") );

        System.out.println();

        //the negate() method is unary, but works as expected.
        System.out.println( hasTwoBraces.negate().test("{foo}") );
        System.out.println( hasTwoBraces.negate().test("fo}o") );

        //variants of the Predicate interface:

        // METHOD                  | 1ST ARG            | 2ND ARG            | RETURNS            |
        //------------------------------------------------------------------------------------------
        // Predicate<T>            | object of type <T> | ~                  | primitive bool     |
        //------------------------------------------------------------------------------------------
        // IntPredicate            | primitive int      | ~                  | primitive bool     |
        // LongPredicate           | primitive long     | ~                  | primitive bool     |
        // DoublePredicate         | primitive double   | ~                  | primitive bool     |
        //------------------------------------------------------------------------------------------
        // BiPredicate<T,U>        | object of type <T> | object of type <U> | primitive bool     |
        //------------------------------------------------------------------------------------------
        // where <T>, <U> and <R> are any arbitrary object types.
    }
    //runLambdaExamples
}
//LambdaExamples

//eof
