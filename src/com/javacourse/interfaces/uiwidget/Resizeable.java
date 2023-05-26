package com.javacourse.interfaces.uiwidget;

public interface Resizeable {

    //there's no need to segregate this interface into multiple interfaces
    //since all these methods deal with the same concern

    void resize(); //default
    void resize(int size); //resize up to a final size
    void resize(int x, int y); //resize from 0,0 to x,y
    void resizeTo(CanUIWidget window); //resize to match the size of another window
}
//Resizeable

//eof
