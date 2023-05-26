package com.javacourse.collections;

import java.util.Collection;
import java.util.NoSuchElementException;

public class GenericStack<E> extends GenericDeque<E> {

    //---------------------------------------------------------------------------------
    //constructors

    public GenericStack() {

        this.setItems();
    }
    //GenericStack

    public GenericStack(Collection<E> _items) {

        this.setItems(_items);
    }
    //GenericStack

    //---------------------------------------------------------------------------------
    //methods

    @Override
    public GenericDeque<E> clone() {

        return new GenericStack<E>( this.getItems().clone() );
    }
    //clone

    //---------------------------------------------------------------------------------
    //LIFO methods

    public E unsafePeek() {
        if(this.isEmpty()) throw new NoSuchElementException( "Can't read item from empty Stack." );
        return this.getItems().getFirst();
    }
    //unsafePeek

    public E safePeek() {
        if(this.isEmpty()) return null;
        return this.getItems().peekFirst();
    }
    //safePeek

    public E unsafePop() {
        if(this.isEmpty()) throw new NoSuchElementException( "Can't pop item from empty Stack." );
        return this.getItems().removeFirst();
    }
    //unsafePop

    public E safePop() {
        if(this.isEmpty()) return null;
        return this.getItems().pollFirst();
    }
    //safePop

    public void unsafePush(E item) {
        if(item == null) throw new NullPointerException( "Can't push a null item." );
        this.getItems().addFirst(item);
    }
    //unsafePush

    public boolean safePush(E item) {
        if(item == null) return false;
        this.getItems().offerFirst(item);
        return true;
    }
    //safePush

    public void unsafePushAll(Collection<E> _items) {

        if( _items == null || _items.contains(null) ) throw new NullPointerException("GenericStack can't contain null elements.");

        for(var item : _items) {
            this.getItems().addFirst(item);
        }
        //for
    }
    //unsafePushAll

    public boolean safePushAll(Collection<E> _items) {

        if( _items == null || _items.contains(null) ) return false;

        for(var item : _items) {
            this.getItems().offerFirst(item);
        }
        //for

        return true;
    }
    //unsafePushAll

    //---------------------------------------------------------------------------------
    //rejected methods

    //these only make sense in a queue
    ////// boolean offerLast(E e)
    ////// void    addLast(E e)
}
//GenericStack

//eof
