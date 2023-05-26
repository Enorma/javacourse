package com.javacourse.generics;

//the constraints in this class changed a lot through this section

//in lesson 4 this was constrained with: <T>
//in lesson 6 this was constrained with: <T extends Number>
//in lesson 11 this was constrained with: <T extends User>

//see the possible class declarations below
//change them accordingly to test the examples in each lesson

public class GenericList<T extends User> {

    //the type of the generic argument <T>
    //MUST be a reference type. primitive types will crash.

    //bounding the datatype of T by applying constraints:

    //T can be of whatever class:
    //public class GenericList<T> {}

    //T can only be of class Number or any subclass of Number:
    //public class GenericList<T extends Number> {}

    //T can only be of a class which implements the Comparable interface:
    //public class GenericList<T extends Comparable> {}

    //T can only be of a class which implements both the Comparable and Cloneable interfaces:
    //public class GenericList<T extends Comparable & Cloneable> {}

    //---------------------------------------------------------------------------------
    //fields

    //commented code from lesson 2 (why we need generics):
    //private int[] items = new int[10];

    //commented code from lesson 3 (a naive generic solution without actual generics):
    //private Object[] items = new Object[10];

    //COMMENT FROM LESSON 4 to 6 (with constraint <T>):
    //the bounded type of T is Object by default
    //so the inner array field (items) must be of type: array of Object

    //COMMENT FROM LESSON 6 (with constraint <T extends Number>):
    //given the bounded type of T (in this case Number)
    //the inner array field (items) must be of type: array of Number

    //in short, the class of the items array, before casting
    //must be the same class declared in the constraints
    //so java knows how much memory to allocate to it
    //and then we just cast it to the generic type (array of T)
    private T[] items = (T[])new User[10]; //yes, arrays can be casted
    private int count;

    //---------------------------------------------------------------------------------
    //methods

    public void add(T item) {

        items[count++] = item; //POST increment
    }
    //add

    public T get(int index) {

        return items[index];
    }
    //get

    public void printList() {

        if(count == 0) System.out.println("the list is empty.");

        for(T item : this.items) {
            if(item != null) System.out.println(item);
        }
        //foreach
    }
    //printList
}
//GenericList

//eof
