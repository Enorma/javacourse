package com.javacourse.collections;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.function.Consumer;

public class GenericSet<E> implements Iterable<E> {

    //---------------------------------------------------------------------------------
    //fields

    private HashSet<E> items;

    //---------------------------------------------------------------------------------
    //constructors

    public GenericSet() {

        this.items = new HashSet<E>();
    }
    //GenericSet

    public GenericSet(Collection<E> _items) {

        this.items = new HashSet<E>(_items);
    }
    //GenericSet

    //---------------------------------------------------------------------------------
    //methods

    @Override
    public String toString() {

        return this.items.toString();
    }
    //toString

    public boolean add(E item) {

        return this.items.add(item);
    }
    //add

    public boolean addAll(Collection<E> _items) {

        return this.items.addAll(_items);
    }
    //addAll

    public boolean remove(E item) {

        return this.items.remove(item);
    }
    //remove

    public boolean removeAll(Collection<E> _items) {

        return this.items.removeAll(_items);
    }
    //removeAll

    public boolean contains(E item) {

        return this.items.contains(item);
    }
    //contains

    public boolean containsAll(Collection<E> _items) {

        return this.items.containsAll(_items);
    }
    //containsAll

    public boolean retainAll(Collection<E> _items) {

        return this.items.retainAll(_items);
    }
    //retainAll

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
    }
    //forEach

    @Override
    public Iterator<E> iterator() {

        return new GenericSetIterator(this);
    }
    //iterator

    //---------------------------------------------------------------------------------
    //set operations

    public GenericSet<E> union(GenericSet<E> other) {
        HashSet<E> result = new HashSet<>(this.items);
        result.addAll(other.getItems());
        return new GenericSet<E>(result);
    }
    //union

    public GenericSet<E> intersection(GenericSet<E> other) {
        HashSet<E> result = new HashSet<>(this.items);
        result.retainAll(other.getItems());
        return new GenericSet<E>(result);
    }
    //intersection

    public GenericSet<E> leftDifference(GenericSet<E> other) {
        HashSet<E> result = new HashSet<>(this.items);
        result.removeAll(other.getItems());
        return new GenericSet<E>(result);
    }
    //leftDifference

    public GenericSet<E> rightDifference(GenericSet<E> other) {
        HashSet<E> result = new HashSet<>(other.getItems());
        result.removeAll(this.items);
        return new GenericSet<E>(result);
    }
    //rightDifference

    public GenericSet<E> symmetricDifference(GenericSet<E> other) {
        GenericSet<E> ld = this.leftDifference(other);
        GenericSet<E> rd = this.rightDifference(other);
        return ld.union(rd);
    }
    //symmetricDifference

    //---------------------------------------------------------------------------------
    //getters

    public HashSet<E> getItems() {

        return this.items;
    }
    //getItems

    //---------------------------------------------------------------------------------
    //setters

    public void setItems(Collection<E> _items) {

        this.items = new HashSet<>(_items);
    }
    //setItems

    //---------------------------------------------------------------------------------
    //nested iterator class

    private class GenericSetIterator implements Iterator<E> {

        //---------------------------------------------------------------------------------
        //fields

        private GenericSet<E> set;
        private Iterator<E> iter;

        //---------------------------------------------------------------------------------
        //constructors

        public GenericSetIterator(GenericSet<E> _set) {

            this.set = _set;
            this.iter = this.set.getItems().iterator();
        }
        //GenericSetIterator

        //---------------------------------------------------------------------------------
        //methods

        @Override
        public boolean hasNext() {

            return this.iter.hasNext();
        }
        //hasNext

        @Override
        public E next() {

            return this.iter.next();
        }
        //next
    }
    //GenericSetIterator
}
//GenericSet

//eof
