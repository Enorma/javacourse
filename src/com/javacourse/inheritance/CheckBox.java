package com.javacourse.inheritance;

public final class CheckBox extends UIControl {

    public CheckBox() {

        //default constructor
        //calls superclass default constructor implicitly

        System.out.println("created a CheckBox object with default values");
    }
    //CheckBox

    @Override
    public void render() {
        //overrides original implementation from superclass UIControl
        System.out.println("Render CheckBox");
    }
    //render
}
//CheckBox

//eof
