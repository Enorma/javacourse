package com.javacourse.collections;

import java.util.Collection;
import java.util.NoSuchElementException;

public class GenericQueue<E> extends GenericDeque<E> {

    //---------------------------------------------------------------------------------
    //constructors

    public GenericQueue() {

        this.setItems();
    }
    //GenericQueue

    public GenericQueue(Collection<E> _items) {

        this.setItems(_items);
    }
    //GenericQueue

    //---------------------------------------------------------------------------------
    //methods

    @Override
    public GenericDeque<E> clone() {

        return new GenericQueue<E>( this.getItems().clone() );
    }
    //clone

    //---------------------------------------------------------------------------------
    //FIFO methods

    public E unsafeFront() {
        if(this.isEmpty()) throw new NoSuchElementException( "Can't read item from empty Queue." );
        return this.getItems().getFirst();
    }
    //unsafeFront

    public E safeFront() {
        if(this.isEmpty()) return null;
        return this.getItems().peekFirst();
    }
    //safeFront

    public E unsafeDequeue() {
        if(this.isEmpty()) throw new NoSuchElementException( "Can't dequeue item from empty Queue" );
        return this.getItems().removeFirst();
    }
    //unsafeDequeue

    public E safeDequeue() {
        if(this.isEmpty()) return null;
        return this.getItems().pollFirst();
    }
    //safeDequeue

    public void unsafeEnqueue(E item) {
        if(item == null) throw new NullPointerException( "Can't enqueue a null item." );
        this.getItems().addLast(item);
    }
    //unsafeEnqueue

    public boolean safeEnqueue(E item) {
        if(item == null) return false;
        this.getItems().offerLast(item);
        return true;
    }
    //safeEnqueue

    public void unsafeEnqueueAll(Collection<E> _items) {

        if( _items == null || _items.contains(null) ) throw new NullPointerException("GenericQueue can't contain null elements.");

        for(var item : _items) {
            this.getItems().addLast(item);
        }
        //for
    }
    //unsafeEnqueueAll

    public boolean safeEnqueueAll(Collection<E> _items) {

        if( _items == null || _items.contains(null) ) return false;

        for(var item : _items) {
            this.getItems().offerLast(item);
        }
        //for

        return true;
    }
    //safeEnqueueAll

    //---------------------------------------------------------------------------------
    //rejected methods

    //these only make sense in a stack
    ////// boolean offerFirst(E e)
    ////// void    addFirst(E e)
}
//GenericQueue

//eof
