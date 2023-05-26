package com.javacourse.generics;

//this started as an int-only list in lesson 2:
//private int[] items = new int[10];
//then in lesson 3 it became an Object list

public class List {

    //---------------------------------------------------------------------------------
    //fields

    private Object[] items = new Object[10];
    private int count;

    //---------------------------------------------------------------------------------
    //methods

    public void add(Object item) {

        items[count++] = item;
    }
    //add

    public Object get(int index) {

        return items[index];
    }
    //get
}
//List

//eof
