package com.javacourse.generics;

/*
    //wrapper class for Course 3 Section 3: Generics

    //import to Main class with:
    import com.javacourse.generics.GenericsExamples; //or...
    import com.javacourse.generics.*;

    //call from main() method with:
    com.javacourse.generics.GenericsExamples.genericsWrapper(); //or...
    GenericsExamples.genericsWrapper();
*/

public class GenericsExamples {

    public static void genericsWrapper() {

        System.out.println("\n----------------------------------------");
        System.out.println("How can an object store a primitive\n");

        //every primitive type has a corresponding wrapper class:
        //byte    -> Byte
        //short   -> Short
        //int     -> Integer
        //long    -> Long
        //float   -> Float
        //double  -> Double
        //char    -> Character
        //boolean -> Boolean

        //BOXING:
        //any code that expects a wrapper object
        //can take its corresponding primitive type

        //UNBOXING:
        //any code that expects a primitive type
        //can take its corresponding wrapper class

        //both boxing and unboxing are implicit and automatic

        int     primitive_value1 = 10;
        Integer wrapper_box      = primitive_value1; //boxing
        int     primitive_value2 = wrapper_box; //unboxing

        System.out.println(primitive_value1 + ", " + wrapper_box + ", " + primitive_value2);

        //no explicit instantiation or casting needed!

        System.out.println("\n----------------------------------------");
        System.out.println("Why are generics needed\n");

        //we create a List class which creates a list of INTEGERS
        //by encapsulating an array of integers

        //since it holds integers only,
        //we would need separate List classes for storing
        //other kinds of data

        //this doesn't scale
        //and leads to code duplication
        //so we modify the List class to work with OBJECTS instead.

        System.out.println("\n----------------------------------------");
        System.out.println("A naive solution without actual generics\n");

        //this technique is called "naive generic" throughout this section
        //it involves coding up a class so that it works with the Object class
        //so it is intentionally not type-specific
        //(if something expects Object, it can take anything!)

        //naive generic list without actual generics
        var list = new List();

        //at this point List holds objects
        //so it can hold anything, primitive or reference typed
        list.add(1); //int or Integer
        list.add("lol"); //String
        list.add(new User(10)); //User

        //this approach has big problems, for example:

        //to extract an item from the list into a variable,
        //we always need explicit casting:
        //int some_number =      list.get(0); //this crashes
        int some_number   = (int)list.get(0); //this works

        //since we need to do casting, errors can happen
        //(and they are only detectable at runtime, like ClassCastException)
        //if the casted object can't be casted to the intended target datatype:
        //int other_number = (int)list.get(1); //attempted String to int, crashes

        //the code to access an item inside the list
        //and do some work with it, could be too convoluted:
        System.out.println( ( (User)list.get(2) ).getPoints() );

        //so it's better to limit the datatype of a collection (like a list)
        //to a single specific datatype
        //the more specific, the better!

        System.out.println("\n----------------------------------------");
        System.out.println("Creating a generic list with generics\n");

        //COMMENT FROM LESSON 11:
        //there's a lot of commented-out code here
        //because of the compile-time errors
        //due to the different constraints applied to the GenericList class
        //to test the examples from before lesson 11, undo this next comment block:

        /*
        //we create a generic list of integers
        //actually using generics

        //different (equivalent) ways to instantiate a generic class:
        GenericList<Integer> gl1 = new GenericList<Integer>();
        GenericList<Integer> gl2 = new GenericList<>();
        var gl3 = new GenericList<Integer>();

        //now the datatype of the items allowed in the list
        //is specific, so we know what to expect, and we can control it
        //if we attempt to add something incompatible, we get a compile-time error:
        gl1.add(1); //expects integer, takes integer, works
        //gl1.add("hello") //expects integer, takes String, crashes

        //also there's no explicit casting needed since
        //the datatype is known and enforced at every stage
        //if we attempt to extract an item into an incompatible variable,
        //we get a compile-time error:
        var num = gl1.get(0); //stores an int into an int variable, works
        //String text = gl1.get(0); //stores an int into a String variable, crashes

        //compile-time errors are MUCH better than runtime exceptions!

        System.out.println("\n----------------------------------------");
        System.out.println("Constraints and bounds\n");

        //the generic datatype <T> means:
        //any arbitrary class T or any subclass of T.

        //<T> by itself can mean anything, no matter how un-specific,
        //like for example if T was Object
        //it could take Object or any subclass of Object
        //which means it works with anything

        //that is not specific at all, and thus, a bad practice
        //so it's better to be more specific

        //by typing <T extends C>
        //where C is some arbitrary class other than Object
        //we constrain the type of T,
        //now it cannot be Object
        //it can only be C or any subclass of C

        //so this way we ensure T is specific
        //only to the classes and subclasses we want, and no more than that

        //rule of thumb: the more specific T is, the better

        //for example, by doing <T extends Number>
        //T can only be of class Number
        //or any subclass of Number (Byte, Short, Integer, Long, Float, Double, etc)

        //ALSO
        //it's possible to bound T with an interface instead of a class
        //so T can only be of a class that implements that interface
        //for example: <T extends Comparable>

        //ALSO!
        //it's possible to bound T with SEVERAL interfaces
        //so T can only be of a class that implements all of those interfaces
        //for example: <T extends Comparable & Cloneable>

        //we say that "T is bounded" or "T is constrained"

        //if we attempt to instantiate a generic class with a type out of its bounds
        //we get a compile-time error
        //for example, assuming our GenericList takes only the Number class, like:
        //public class GenericList<T extends Number> {...} //see GenericList.java

        //this crashes at compile-time
        //because String is not Number nor a subclass of Number
        //var gl4 = new GenericList<String>();

        //this works fine
        //because Integer is a subclass of Number:
        var gl4 = new GenericList<Integer>();

        //also, we can store only Integers
        //(there's no subclass of Integer, it's a final class)
        gl4.add(5);
        gl4.add(10);
        gl4.add(15);

        gl4.printList();
        */ //THIS CODE WAS COMMENTED IN LESSON 11

        System.out.println("\n----------------------------------------");
        System.out.println("Bytecode and type erasure\n");

        //at the bytecode level,
        //the naive generic class from lesson 3
        //and an actual generic class with constraint <T>
        //are exactly the same, since the type for both is Object

        //the same phenomenon happens at the bytecode level
        //with, for example, a naive generic class that takes Number
        //and an actual generic class with constraint <T extends Number>
        //they're exactly the same under the hood.

        //so... are generics just a syntax sugar over the naive implementation?
        //almost, but no. There's a key advantage of using actual generics:

        //by using actual generics
        //we explicitly declare the classes/subclasses we intend to use
        //which means our crashes, if any,
        //will happen at compile-time, not runtime!

        //under the hood, a constraint with two or more interfaces
        //will be interpreted as the leftmost interface
        //for example: <T extends Comparable & Cloneable>
        //at the bytecode level, the type will be Comparable.

        //so in any case, type T will be erased
        //and so will every interface in the constraints but the leftmost one
        //and some actual class/interface will be the actual datatype at work
        //this is called type erasure

        System.out.println("\n----------------------------------------");
        System.out.println("The comparable interface\n");

        //we want our User class to implement the Comparable interface
        //so User objects can compare themselves against each other.
        //the Comparable interface forces our User class
        //to implement the compareTo() method for doing just that.

        //create two users
        var u1 = new User(10);
        var u2 = new User(20);

        //compare them
        int result = u1.compareTo(u2);

        //print something according to the result
        if(result > 0) {
            System.out.println("u1 has more points: " + u1.getPoints());
        }else if(result < 0) {
            System.out.println("u2 has more points: " + u2.getPoints());
        }else {
            System.out.println("It's a tie!");
        }
        //if-else

        //by the way, every subclass of Number
        //implements the Comparable interface.

        System.out.println("\n----------------------------------------");
        System.out.println("Generic methods\n");

        //here we call the (generic) max() method from the (not generic) Utils class.
        //both of its args are of type T,
        //which is unknown until we declare the actual type of either arg,
        //which makes java expect that same type in the other arg.
        //if we give it args of different types, we get a compile-time error

        //here we compare two objects of class User, works fine
        var m = Utils.max(u1,u2);

        //here we compare a User and a String, crashes
        //var m = Utils.max(u1,"hello");

        System.out.println("max is " + m.toString());

        System.out.println("\n----------------------------------------");
        System.out.println("Multiple generics of different types\n");

        //we can declare multiple generic parameters as a type constraint
        //which are independent of each other
        //and therefore allowed to be of different types

        //we can have multiple generic parameters
        //in generic classes (see the KeyValuePair class)
        //and also in generic methods (see the printKeyValue() method in the Utils class)

        //IN CLASSES:
        //we can instantiate said class freely, so that
        //every object has a different-typed key and value!

        //IN METHODS:
        //the method can be called freely, with args of any type!

        //examples with both a generic class and a generic method:

        //User, int
        var kvp1 = new KeyValuePair<User, Integer>(u1, 100);
        Utils.printKeyValue(kvp1.getKey(), kvp1.getValue());

        //double, String
        var kvp2 = new KeyValuePair<Double, String>(50.50, "holamundo");
        Utils.printKeyValue(kvp2.getKey(), kvp2.getValue());

        //boolean, Object
        var kvp3 = new KeyValuePair<Boolean, Object>(true, new Object());
        Utils.printKeyValue(kvp3.getKey(), kvp3.getValue());

        //in contrast, naive implementations are limited
        //for example in the printKeyValue() method in the Utils class
        //we can test all the naive implementations we made,
        //they all work, but they're limited in type-versatility

        System.out.println("\n----------------------------------------");
        System.out.println("Some polymorphism-related phenomena\n");

        //ABOUT INHERITANCE AND POLYMORPHISM:

        //let's assume some subclass S inherits some class C
        //we'll also assume s is an object of class S
        //and c is an object of class C

        //in both compile-time and runtime:
        //any code that expects c and takes c will work fine
        //any code that expects c and takes s will work fine
        //any code that expects s and takes s will work fine
        //any code that expects s and takes c will crash!

        //HOWEVER, there's a tricky catch to consider:

        //let's assume we do a polymorphic instantiation, like:
        //C obj = new S();

        //in COMPILE-TIME, obj will be treated as c,
        //therefore...
        //any code that expects c and takes obj(c) will work fine
        //any code that expects s and takes obj(c) will crash!

        //in RUNTIME, obj will be treated as s,
        //therefore...
        //any code that expects c and takes obj(s) will work fine
        //any code that expects s and takes obj(s) will work fine

        //notice that all crashes happen when we expect s,
        //while expecting c always works fine.
        //that's a good thing to know!

        //also, notice that all the aforementioned crashes
        //all happen at compile-time.
        //that's a great thing to know!

        //DEMO CODE:

        //let's demonstrate with the class User
        //and its subclass Instructor:
        User       c   = new User(30);       //this is a User
        User       obj = new Instructor(40); //this is a User in compile-time and an Instructor in runtime
        Instructor s   = new Instructor(50); //this is an Instructor

        //these method calls expect a user, and they all work fine
        //in both compile-time and runtime:
        Utils.printUser(c);   //expects User, takes User
        Utils.printUser(obj); //expects User, takes User in compile-time and Instructor in runtime, both are fine
        Utils.printUser(s);   //expects User, takes Instructor

        System.out.println();

        //these method calls expect an instructor,
        //the first two crash at compile-time!
        //Utils.printInstructor(c);   //expects Instructor, takes User
        //Utils.printInstructor(obj); //expects Instructor, takes User in compile-time and Instructor in runtime, both are fine
        Utils.printInstructor(s);     //expects Instructor, takes Instructor

        //THE MORAL OF THE STORY:
        //to leverage code reusability across a class and its subclasses,
        //and to have compile-time type checking,
        //it is preferable to write code that expects the CLASS, not the subclass

        System.out.println("\n----------------------------------------");
        System.out.println("Inheritance in the generic constraints\n");

        //the aforementioned polymorphism-related phenomena
        //can apply to the type constraint of a generic class.

        //for these next lessons we'll constrain the GenericList class
        //like <T extends User> (see GenericList.java)
        //so that T can mean User or any subclass of User
        //so we can instantiate it like:
        //new GenericList<User>() or new GenericList<Instructor>()

        //we choose to instantiate
        //expecting Users, not Instructors,
        //because of the reasons stated above
        var gl5 = new GenericList<User>();

        //we add both user and instructor objects to the user list
        //all three work fine in both compile-time and runtime!
        gl5.add(c);   //expects User, takes User
        gl5.add(obj); //expects User, takes User in compile-time and Instructor in runtime
        gl5.add(s);   //expects User, takes Instructor

        //in the GenericList class we have a printList() method
        //we can use it to print all Users in the list
        //even though there's both Users and Instructors among them.
        //also, notice it will print each user's class at RUNTIME:
        gl5.printList(); //it works fine!

        System.out.println("\n----------------------------------------");
        System.out.println("Inheritance of generic classes\n");

        //we'll go one step further:

        //we know that
        //any code expecting a User
        //can take an Instructor

        //but...
        //if it expects a GenericList<User>,
        //can it take a GenericList<Instructor>???

        //sadly, no.
        //in that case, we'd have two instances of the same generic class
        //where their constraints are superclass and subclass respectively.
        //that's not enough to establish an inheritance relationship
        //between the two GenericList's!

        //there needs to be an inheritance relationship
        //between the CLASSES, not the CONSTRAINTS.

        //a possible way that would work
        //is by inheriting the whole GenericList<User> class
        //in a class that's bounded like <T extends Instructor>.

        //for that purpose, we've created a class:
        //SubGenericList<T extends Instructor> extends GenericList<User>
        //so now SubGenericList<Instructor> will inherit GenericList<User>.

        //let's test all of that:

        //we already have gl5, a GenericList<User>
        //here we create gl6, a GenericList<Instructor>
        var gl6 = new GenericList<Instructor>();
        //and here we create gl7, a SubGenericList<Instructor>
        var gl7 = new SubGenericList<Instructor>();

        //the printUserList() method from the Utils class
        //expects a GenericList<User>:
        Utils.printUserList(gl5);   //takes GenericList<User>, works fine
        System.out.println();
        Utils.printUserList(gl7);   //takes SubGenericList<Instructor>, works fine
        //Utils.printUserList(gl6); //takes GenericList<Instructor>, crashes in compile-time

        //the SubGenericList<Instructor> idea works...
        //however, at that point,
        //the design has probably become too convoluted,
        //so we'll not explore that idea any further.

        //what we really wanted was to keep using the same class
        //and be able to use a GenericList<Instructor>
        //in code that expects a GenericList<User>
        //that would produce much more straightforward code...

        System.out.println("\n----------------------------------------");
        System.out.println("Wildcards\n");

        //to solve the aforementioned problem,
        //we use wildcards.

        //we've made an alternative print method in the Utils class
        //called wildPrintUserList()
        //it takes a GenericList that
        //has the wildcard character '?' in its constraint

        //the wildcard is an unknown type in compile-time, but
        //it is type-checked in compile-time regarding
        //the datatype of its arg (in this case GenericList)

        //for example, it won't accept a String (since it's not a GenericList)
        //Utils.wildPrintUserList("hello"); //crashes in compile-time

        //it is also type-checked regarding the constraint of its arg
        //in this case User
        //however this time it's a permissive check
        //meaning it allows for subclasses of the constraint
        //in this case any subclass of User.

        //for example it won't accept a GenericList<String>
        //since String is not User nor subclass of User
        //Utils.wildPrintUserList( new GenericList<String>() ); //we can't even create such a GenericList...

        //but it will accept both a GenericList<User>
        //and a GenericList<Instructor>
        //unlike printUserList() which strictly
        //only allowed for GenericList<User>.

        //then our wildPrintUserList() method
        //will work in the above examples
        //even where printUserList() failed!
        Utils.wildPrintUserList(gl5); //takes GenericList<User>, works fine
        System.out.println();
        Utils.wildPrintUserList(gl6); //takes GenericList<Instructor>, works fine

        System.out.println("\n----------------------------------------");
        System.out.println("Bounded wildcards\n");

        //in the above code, the wildcard in the wildPrintUserList() method
        //is bounded implicitly even though it appears as <?>
        //because the underlying class (GenericList) is bounded
        //so the wildcard follows the same constraint.

        //but we can also bound a wildcard explicitly
        //like:
        //<? extends User> for only reading from the arg(s)
        //<? super User> for reading and writing in the arg(s)

        //we can test both with the methods in the Utils class:
        Utils.experimentWithExtends(gl5); //uses <? extends User>
        System.out.println();
        Utils.experimentWithSuper(gl5); //uses <? super User>
    }
    //genericsWrapper
}
//GenericsExamples

//eof
