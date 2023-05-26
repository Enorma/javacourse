package com.javacourse.interfaces.uiwidget;

public class Dragger {

    //this class could supposedly be used to drag a UI window

    //by lesson 9 no class actually implements the interfaces
    //so I made some "window" classes that do so

    public void dragUIWindow(Draggable window) {

        //this method changed throughout lesson 9...

        //comments from when it used the CanUIWidget interface:
        //(this method's argument was CanUIWidget window)
        //this class depends on the CanUIWidget interface
        //however this is bad design because
        //CanUIWidget has 3 methods
        //yet only ONE (drag()) is relevant here
        //since the other 2 methods deal with entirely different concerns
        //so the other 2 are unnecessary coupling points

        //comments from when it used the Draggable interface:
        //this is better design, since
        //only the relevant concern is a coupling point

        window.drag();
        System.out.println("dragging done!");
    }
    //widget
}
//Dragger

//eof
