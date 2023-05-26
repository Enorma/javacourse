package com.javacourse.collections;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

public abstract class GenericDeque<E> implements Iterable<E>, Cloneable {

    //---------------------------------------------------------------------------------
    //fields

    private ArrayDeque<E> items;

    //---------------------------------------------------------------------------------
    //methods

    @Override
    public String toString() {

        return this.items.toString();
    }
    //toString

    public int size() {

        return this.items.size();
    }
    //size

    public boolean isEmpty() {

        return this.items.isEmpty();
    }
    //isEmpty

    public void clear() {

        this.items.clear();
    }
    //clear

    public boolean contains(E item) {

        return this.items.contains(item);
    }
    //contains

    public Object[] toArray() {

        return this.items.toArray();
    }
    //toArray

    public E[] toArray(E[] target_array) {

        return this.items.toArray(target_array);
    }
    //toArray

    public void forEach(Consumer<? super E> action) {

        this.items.forEach(action);
        this.clear();
    }
    //forEach

    @Override
    public Iterator<E> iterator() {

        return new GenericDequeIterator(this);
    }
    //iterator

    @Override
    public abstract GenericDeque<E> clone();

    //---------------------------------------------------------------------------------
    //FIFO/LIFO methods

    private E safeRemove() {
        if(this.isEmpty()) return null;
        return this.items.pollFirst();
    }
    //safeRemove

    //---------------------------------------------------------------------------------
    //getters

    public ArrayDeque<E> getItems() {

        return this.items;
    }
    //getItems

    //---------------------------------------------------------------------------------
    //setters

    public void setItems() {

        this.items = new ArrayDeque<E>();
    }
    //setItems

    public void setItems(Collection<E> _items) {
        if( _items == null || _items.contains(null) ) throw new NullPointerException("GenericDeque can't contain null elements.");
        this.items = new ArrayDeque<E>(_items);
    }
    //setItems

    //---------------------------------------------------------------------------------
    //nested iterator class

    private class GenericDequeIterator implements Iterator<E> {

        //---------------------------------------------------------------------------------
        //fields

        private GenericDeque<E> deque;

        //---------------------------------------------------------------------------------
        //constructors

        public GenericDequeIterator(GenericDeque<E> _deque) {

            this.deque = _deque;
        }
        //GenericDequeIterator

        //---------------------------------------------------------------------------------
        //methods

        @Override
        public boolean hasNext() {

            return !( this.deque.isEmpty() );
        }
        //hasNext

        @Override
        public E next() {

            return this.deque.safeRemove();
        }
        //next
    }
    //GenericDequeIterator

    //---------------------------------------------------------------------------------
    //rejected methods

    //the ArrayDeque class has the functionality that we need
    //to implement a vanilla Stack/Queue,
    //but it also has a lot more functionality we don't need.

    //so, by wrapping an ArrayDeque object
    //as a private field inside this class,
    //we can choose to expose only some of its methods
    //by wrapping them inside public methods of this class.

    //this allows us to effectively make a class
    //that is a reduced version of another class.
    //as opposed to inheriting the class,
    //which can only extend its functionality
    //while keeping all public methods exposed

    //for various reasons,
    //in order to achieve the limited functionality of a vanilla Stack/Queue
    //we chose to keep these methods (from the ArrayDeque class)
    //hidden from the exterior of this class
    //by not wrapping them in any public method:

    //obsoleted by safePushAll, unsafePushAll, safeEnqueueAll & unsafeEnqueueAll:
    ////// boolean addAll(Collection<? extends E> c)

    //obsoleted by safePush, unsafePush, safeEnqueue & unsafeEnqueue:
    ////// boolean add(E e);
    ////// boolean offer(E e);
    ////// void    push(E e);

    //obsoleted by safePop, unsafePop, safeDequeue & unsafeDequeue:
    ////// E remove();
    ////// E poll();
    ////// E pop();

    //obsoleted by safePeek, unsafePeek, safeFront & unsafeFront:
    ////// E element();
    ////// E peek();

    //makes no sense to operate on the tail end of a stack/queue
    ////// E peekLast();
    ////// E pollLast();
    ////// E removeLast();
    ////// E getLast();

    //makes no sense to iterate a stack/queue tail-to-head
    ////// Iterator<E> descendingIterator()

    //makes no sense to search/filter in a stack/queue
    //without removing items at the head end permanently
    ////// boolean remove(Object o)
    ////// boolean removeIf(Predicate<? super E> filter)
    ////// boolean removeFirstOccurrence(Object o)
    ////// boolean removeLastOccurrence(Object o)
    ////// boolean removeAll(Collection<?> c)
    ////// boolean retainAll(Collection<?> c)

    //I need to understand spliterators first...
    ////// Spliterator<E> spliterator()
}
//GenericDeque

//eof
