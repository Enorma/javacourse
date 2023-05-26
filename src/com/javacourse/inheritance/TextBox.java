package com.javacourse.inheritance;

public class TextBox extends UIControl {

    //--------------------------------------------------------
    //fields

    private String text = "";

    //--------------------------------------------------------
    //constructors

    public TextBox() {

        //this is a default constructor
        //because it doesn't take parameters

        //this is called every time a TextBox is instantiated
        //but before that, the constructor of its superclass (UIControl) is called too
        //so superclass constructors are called before subclass constructors

        //superclass (UIControl) has two constructors,
        //one of them is the default constructor,
        //since none of them is explicitly called here
        //the default constructor is implicitly called
        //so calling super() explicitly would be equivalent

        //we'd have a compile-time error here
        //if the superclass didn't have a default constructor

        //or instead, it would be perfectly valid
        //to explicitly call a non-default superclass constructor here

        this.text = "";
        System.out.println("created a TextBox object with default values");
    }
    //TextBox

    public TextBox(String _text) {

        //this is NOT a default constructor
        //because it takes parameters

        //same as before, we aren't explicitly calling
        //any of the superclass (UIControl) constructors here
        //so the superclass's default constructor is implicitly called
        //so calling super() explicitly would be equivalent

        //or instead, it would be perfectly valid
        //to explicitly call a non-default superclass constructor here

        this.text = _text;
        System.out.println("created and initialized a TextBox object");
    }
    //TextBox

    public TextBox(String _text, boolean _is_enabled) {

        //this is NOT a default constructor
        //because it takes parameters

        //this time, we're calling one of the superclass's constructors explicitly
        //this can only be on the first line of this constructor
        //else we get a compile-time error

        super(_is_enabled);

        //an example on the private access modifier from lesson 5:
        //the isEnabled field of the superclass is private
        //if we try to access it even from here (the subclass)
        //we get a compile-time error:
        //System.out.println( this.isEnabled );

        this.text = _text;
        System.out.println("created and initialized a TextBox object");
    }
    //TextBox

    //--------------------------------------------------------
    //methods

    public void clear() {

        this.text = "";
    }
    //clear

    @Override
    public void render() {
        //overrides original implementation from superclass UIControl
        System.out.println("Render TextBox");
    }
    //render

    @Override
    public String toString() {

        //by using the @Override annotation
        //java checks in compile-time
        //if the signature of the method matches its original implementation
        //and if it isn't, we get a compile-time error

        return this.text;
    }
    //toString

    //--------------------------------------------------------
    //setters

    public void setText(String _text) {

        this.text = _text;
    }
    //setText

    //--------------------------------------------------------
    //getters

    public String getText() {

        return this.text;
    }
    //getText
}
//TextBox

//eof
