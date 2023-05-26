package com.javacourse.collections;

import java.util.Iterator;

public class GenericList<E> implements Iterable<E> {

    //it's better to use <E> (as in Element)
    //rather than <T> (as in Template)
    //if our generic type represents an element inside a collection

    //---------------------------------------------------------------------------------
    //fields

    private E[] items = (E[])new Object[10]; //yes, arrays can be casted
    private int count;

    //---------------------------------------------------------------------------------
    //methods

    @Override
    public Iterator<E> iterator() {

        //this overrides the original method iterator()
        //from the Iterable interface

        //it creates an object that implements the Iterator interface
        //that contains all the elements in this collection

        return new ListIterator(this);
    }
    //iterator

    public void add(E item) {

        this.items[this.count++] = item; //POST increment
    }
    //add

    public E get(int index) {

        return this.items[index];
    }
    //get

    public void printList() {

        if(this.count == 0) System.out.println("the list is empty.");

        for(E item : this.items) {
            if(item != null) System.out.println(item);
        }
        //foreach
    }
    //printList

    //---------------------------------------------------------------------------------
    //getters

    public int getCount() {

        return this.count;
    }
    //getCount

    public E[] getItems() {

        return this.items;
    }
    //getItems

    //---------------------------------------------------------------------------------
    //nested classes

    //WHY DID WE NEST THE ListIterator CLASS:

    //every class which implements the Iterable interface
    //must be able to instantiate an object which implements the Iterator interface
    //and the two must be perfectly made for each other

    //this means: if GenericList is constrained to the datatype E
    //then its ListIterator must be an iterator of the datatype E

    //we made ListIterator a nested private class inside GenericList
    //precisely to ensure that.

    //a key factor here is that
    //ListIterator has access to GenericList's scope
    //because it's nested inside it

    //so, we can refer to the generic datatype E
    //inside the ListIterator class
    //and it will be GenericList's exact same datatype E

    //also, since these two classes are tightly coupled by design
    //it makes sense that any breaking changes are limited to this source file
    //that's a good practice!
    //(to test that, try changing the items array into an ArrayList, see what happens)

    private class ListIterator implements Iterator<E> {

        //WHY IS ListIterator NOT GENERIC (like: ListIterator<E>):

        //even though GenericList is generic,
        //ListIterator is NOT generic itself,
        //because it is tightly related to its surrounding class GenericList.

        //so, GenericList is generic, and we are sure that
        //ListIterator will have its exact same constraint
        //so there's no need to explicitly make it generic,
        //it is implicitly generic.

        //this happens because
        //nested classes have access to the scope of their surrounding class
        //so here we can use the E datatype from the GenericList's constraint
        //and we do use it several times (see below)

        //there's no problem with accessing E
        //because generic datatypes have no access modifier
        //so they can't be private!

        //---------------------------------------------------------------------------------
        //fields

        private GenericList<E> list;
        private int curr_index;

        //---------------------------------------------------------------------------------
        //constructors

        public ListIterator(GenericList<E> _list) {

            //through this constructor
            //we create an instance of ListIterator
            //which becomes the iterator of
            //an instance of GenericList

            //in this use case, we deliberately want them both
            //to be tightly coupled with each other

            //because:
            //every iterable object (which implements the Iterable interface)
            //must be able to generate its own iterator object (which implements the Iterator interface)

            this.list = _list;
            this.curr_index = 0;
        }
        //ListIterator

        //---------------------------------------------------------------------------------
        //methods

        @Override
        public boolean hasNext() {

            //since the constructor of this ListIterator class
            //takes an instance of GenericList as a parameter (this.list)
            //so we have access to its public fields and methods
            //so, we have access to its getCount() getter

            //it's as if we had direct access to GenericList's scope
            //from inside ListIterator!

            return ( this.curr_index < this.list.getCount() );
        }
        //hasNext

        @Override
        public E next() {

            //the E return type of this method
            //is the exact same E type in GenericList's constraint

            //in contrast, if ListIterator was explicitly generic (like: ListIterator<E>)
            //E would mean an entirely different datatype at the bytecode level
            //which would be some unknown type we haven't declared
            //and we'd have a compile-time error here.

            //if curr_index was bigger than the size of the list
            //we would have a runtime index exception here
            //however, we do no validation
            //since the caller of this method is expected
            //to do such validation with the hasNext() method

            return this.list.getItems()[this.curr_index++];
        }
        //next

        public void reset() {

            this.curr_index = 0;
        }
        //reset
    }
    //ListIterator
}
//GenericList

//eof
