package com.javacourse.interfaces.uiwidget;

//originally called UIWidget in lesson 9
//renamed here to follow interface naming convention

public interface CanUIWidget extends Draggable, Resizeable, Renderable {

    //any class that implements this interface
    //must implement all methods from here
    //plus all methods from the inherited interfaces

    //any method that uses an object that implements this interface
    //will have access to all these methods
    //and all the inherited methods

    //originally (lesson 9)
    //this interface didn't inherit anything
    //and had these 3 methods:
    //void drag();
    //void resize();
    //void render();
}
//CanUIWidget

//eof
