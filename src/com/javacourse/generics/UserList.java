package com.javacourse.generics;

//this class is only briefly used in lesson 2

public class UserList {

    //---------------------------------------------------------------------------------
    //fields

    private User[] items = new User[10];
    private int count;

    //---------------------------------------------------------------------------------
    //methods

    public void add(User item) {

        items[count++] = item;
    }
    //add

    public User get(int index) {

        return items[index];
    }
    //get
}
//UserList

//eof
