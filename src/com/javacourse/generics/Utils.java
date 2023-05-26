package com.javacourse.generics;

public class Utils {

    //by convention:
    //classes are usually named in singular (List, User, Number, String, etc)
    //referring to any single object created from the class.
    //this Utils class is intended for providing only utility methods, not instances
    //hence the class name in plural (Utils, not Util), referring to those methods.

    //by convention, all methods in a utility class should be static

    //a non-generic class can have generic methods, for example
    //this class itself is not generic, but several of its methods are.

    //to create a generic method, we give it a generic datatype
    //we write it like:
    //1: the access modifier (public or private)
    //2: (optional), the static keyword
    //3: (optional), the final keyword
    //4: the generic datatype(s) (<T>) //syntax for this can vary a lot, see below
    //5: the return type (or void)
    //6: the method name
    //7: the arguments (if any) //syntax for this can vary a lot, see below
    //8: the method body

    //a simple example would be:
    //  1      2      3    4  5     6              7           8
    //public static final <T> T methodName(T arg1, int arg2) {...}

    //any generic datatype specified in the method signature
    //can be used freely in the args, the return type and the body

    public static <T extends Comparable<T>> T max(T first, T second) {

        //in lesson 9, this started as an int-only naive implementation

        //this method uses comparison-based logic
        //between args of unknown types
        //there is only one way to implement it safely
        //and that is by calling the same method every time,
        //no matter the type of the args
        //that's the compareTo() method,
        //and that's what the Comparable interface is for.

        //hence, this method should be bounded
        //so that it can only apply to objects
        //that implement the Comparable interface

        //plus, since Comparable is a generic interface,
        //the reference to it should also be bounded
        //to the type we're working with (in this case T)

        //hence the generic datatype:
        //<T extends Comparable<T>>

        return(first.compareTo(second) > 0) ? first : second;
    }
    //max

    public static <K,V> void printKeyValue(K key, V value) {

        //at first (lesson 10) this was a naive int-only implementation

        //then we did (T key, int value) and (int key, T value)
        //which is limiting and therefore also naive

        //then we made it generic, but with args of the same type
        //also pretty naive

        //afterwards, we used two generic values <K,V>
        //so this behaves like a proper type-agnostic key-value mapping

        System.out.println(key.toString() + ": " + value.toString());
    }
    //printKeyValue

    public static void printUser(User user) {

        System.out.println(user);
    }
    //printUser

    public static void printInstructor(Instructor instructor) {

        System.out.println(instructor);
    }
    //printInstructor

    public static void printUserList(GenericList<User> users) {

        //the datatype expected by the arg is NOT User,
        //it's actually GenericList<User>, which is not the same!

        //this means it CANNOT take an arg of type:
        //User, Instructor, nor GenericList<Instructor>

        //it can only take GenericList<User>
        //or a subclass of it,
        //for example, SubGenericList<Instructor>.

        users.printList();
    }
    //printUserList

    public static void wildPrintUserList(GenericList<?> users) {

        //the wildcard character is bounded by the constraint
        //of the GenericList.

        //since GenericList is bounded like:
        //GenericList<T extends User>
        //the wildcard can accept a GenericList of User or
        //of any subclass of User

        //that means this will accept
        //a GenericList<User> or a GenericList<Instructor>,
        //which is all we wanted

        //HOWEVER:

        //without explicit casting,
        //we can only store the values of our users arg
        //inside a variable of the same datatype
        //of the arg's constraint

        //meaning, we can only store what we extract from the users arg
        //in a variable of type User.
        //otherwise, we get a compile-time error.

        User got0 = users.get(0); //stores a User inside a User variable, works
        User got1u = users.get(1); //stores an Instructor inside a User variable, works
        //Instructor got1i = users.get(1); //stores an Instructor inside an Instructor variable, crashes
        Instructor got1i = (Instructor)users.get(1); //same as above, but it's fixed by explicit casting

        //so reading values from the users arg is possible
        //albeit a bit convoluted

        //unfortunately, writing a value into it
        //is downright impossible
        //since the wildcard becomes an inaccessible "capture of ?" class
        //at the bytecode level
        //and we cannot ever instantiate such a class:

        //new capture of ? //this just can't work
        //users.add(/*there's nothing we can place here*/); //we can't satisfy the args of the add method

        //IMPORTANT NOTE:

        //if GenericList wasn't bounded
        //like GenericList<T>
        //then the wildcard would represent the Object class
        //so the storing code from above would
        //need explicit casting almost everywhere.
        //that's a bad practice.

        users.printList();
    }
    //printUserList

    public static void experimentWithExtends(GenericList<? extends User> elements) {

        //behaviour of <? extends User>:

        //since the datatype of ? extends User,
        //we can READ an element from the elements arg
        //and store it in a User variable
        User x = elements.get(0);

        //with explicit casting, we can also store
        //values of a subclass of User:
        Instructor i = (Instructor)elements.get(1); //would crash without casting

        //be careful with casting
        //the value extracted from the elements arg
        //is unpredictable and could be impossible to cast to Instructor
        //which would throw ClassCastException at runtime

        //but since the actual datatype of ? is "capture of ?",
        //we cannot WRITE to the elements arg,
        //not even with the User datatype or its subclasses
        //because capture is not an actual class
        //and so we can't instantiate it like "new capture()"
        //elements.add(new User(5));
        //elements.add(new Instructor(6)); //instructor extends User

        //print for testing
        System.out.println("got user: " + x.toString());
        System.out.println("got instructor: " + i.toString());
    }
    //experimentWithExtends

    public static void experimentWithSuper(GenericList<? super User> elements) {

        //behaviour of <? super User>:

        //since the datatype of ? is the parent of User (Object), not User,
        //we can READ an element from the list
        //and store it in an Object variable:
        Object x = elements.get(0);

        //since GenericList is itself bounded to User
        //we can also store a read value in a User variable:
        User u = elements.get(0);

        //with explicit casting, we can also store
        //values of a subclass of User:
        Instructor i = (Instructor)elements.get(1); //would crash without casting

        //be careful with casting
        //the value extracted from the elements arg
        //is unpredictable and could be impossible to cast to Instructor
        //which would throw ClassCastException at runtime

        //since the actual datatype of ? is "capture of ? super User"
        //which means the parent of User (Object)
        //we can WRITE to the list with the User datatype or its subclasses
        elements.add( new User(103) );
        elements.add( new Instructor(104) ); //instructor extends User

        //test reading the elements we just added
        User       u1 =             elements.get(3);
        Instructor i1 = (Instructor)elements.get(4); //would crash without casting

        //print for testing
        System.out.println("got object: "     + x.toString());
        System.out.println("got user: "       + u.toString());
        System.out.println("got instructor: " + i.toString());
        System.out.println("got user: "       + u1.toString());
        System.out.println("got instructor: " + i1.toString());
    }
    //experimentWithSuper
}
//Utils

//eof
