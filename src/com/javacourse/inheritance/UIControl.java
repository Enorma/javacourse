package com.javacourse.inheritance;

public abstract class UIControl {

    //--------------------------------------------------------
    //fields

    private boolean isEnabled = true;

    //--------------------------------------------------------
    //constructors

    public UIControl() {

        //this is a default constructor
        //because it doesn't take parameters

        //this is called every time a UIControl is instantiated without arguments
        //but it's ALSO called every time a subclass of UIControl is instantiated
        //either explicitly by calling super() or implicitly

        //so superclass constructors are called before subclass constructors

        this.isEnabled = true;
        System.out.println("created a UIControl object with default values");
    }
    //UIControl

    public UIControl(boolean _is_enabled) {

        //this is NOT a default constructor
        //because it takes parameters

        //this is called every time a UIControl is instantiated with arguments
        //but it's ALSO called every time a subclass of UIControl is instantiated
        //explicitly by calling super(_is_enabled)

        //so superclass constructors are called before subclass constructors

        this.isEnabled = _is_enabled;
        System.out.println("created and initialized a UIControl object");
    }
    //UIControl

    //--------------------------------------------------------
    //methods

    public final void enable() {

        this.isEnabled = true;
    }
    //enable

    public final void disable() {

        this.isEnabled = false;
    }
    //disable

    //render() purposefully left empty
    //because how to render every kind of UIControl
    //depends of the actual kind of UIControl, like
    //TextBox, CheckBox, etc
    //so this method is intended to be overridden by
    //every subclass of UIControl
    public abstract void render();

    //--------------------------------------------------------
    //getters

    public boolean isEnabled() {

        return this.isEnabled;
    }
    //isEnabled
}
//UIControl

//eof
