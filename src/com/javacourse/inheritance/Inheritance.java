package com.javacourse.inheritance;

/*
    //wrapper class for Course 2 Section 4: Inheritance

    //import to Main class with:
    import com.javacourse.inheritance.Inheritance; //or...
    import com.javacourse.inheritance.*;

    //call from main() method with:
    com.javacourse.inheritance.Inheritance.inheritanceExamples(); //or...
    Inheritance.inheritanceExamples();
*/

public class Inheritance {

    public static void show(UIControl control) {

        //at compile time this method only sees a UIControl object
        //and inside here we treat the object strictly as a UIControl
        //so we get compile-time errors
        //if we try to call other class's (like TextBox) methods from it

        //but at runtime, it could be an upcasted subclass of UIControl, like TextBox.
        //to be able to work with the actual class of it, we use downcasting explicitly
        //so we can now access methods from TextBox, like setText():

        if(control instanceof TextBox) { //only an actual TextBox can be downcasted to TextBox
            //otherwise this downcasting crashes at runtime:
            var downcasted_text_box = (TextBox)control; //downcasting
            //now we can use TextBox-specific methods:
            downcasted_text_box.setText("I was expected to be a UIControl...");
            //System.out.println(downcasted_text_box); //we can print it as a downcasted_text_box...
        }
        //if

        //...or we can print it as a control
        //and we get the same result because
        //both control and downcasted_text_box
        //point to the same heap address (the same object)
        //which means it's easier to just print it as a control.
        System.out.println(control);
    }
    //show

    public static void inheritanceExamples() {

        System.out.println("\n----------------------------------------");
        System.out.println("Testing the UIControl class\n");

        //before lesson 4 this called UIControl's default constructor
        //before lesson 10 UIControl wasn't abstract so we could instantiate it

        //var control = new UIControl();
        //control.disable();
        //System.out.println(control.isEnabled());

        System.out.println("\n----------------------------------------");
        System.out.println("Inheriting the UIControl class\n");

        var text_box1 = new TextBox(); //before lesson 4 this called UIControl and TextBox's default constructor
        text_box1.disable();
        System.out.println(text_box1.isEnabled());
        text_box1.setText("hello world");
        System.out.println(text_box1.getText());

        System.out.println("\n----------------------------------------");
        System.out.println("The Object class\n");

        var obj = new Object();
        System.out.println(obj.toString());
        System.out.println(obj.hashCode());

        System.out.println();

        //checking out methods inherited from the Object class
        var text_box2 = new TextBox(); //before lesson 4 this called UIControl and TextBox's default constructor
        var text_box2_clone = text_box2;
        System.out.println(text_box2.hashCode());
        System.out.println(text_box2_clone.hashCode());
        System.out.println(text_box2.equals(text_box2_clone)); //equals() compares the hashcode

        System.out.println("\n----------------------------------------");
        System.out.println("Constructors\n");

        //testing the different constructors of the UIControl and TextBox classes
        //before lesson 10 UIControl wasn't abstract so we could instantiate it

        //superclass only, default constructor
        //var uic1 = new UIControl();
        //System.out.println();

        //superclass only, custom constructor
        //var uic2 = new UIControl(true);
        //System.out.println();

        //both classes, default constructors
        var tb1 = new TextBox();
        System.out.println();

        //both classes, superclass default constructor and subclass custom constructor
        var tb2 = new TextBox("hola wey");
        System.out.println();

        //both classes, custom constructors
        var tb3 = new TextBox("hola dude", true);

        System.out.println("\n----------------------------------------");
        System.out.println("Access modifiers\n");

        //public: available anywhere
        //private: available only within its same class

        //trying to access a private field/method from outside its class
        //produces a compile-time error, like:
        //System.out.println( uic1.isEnabled );

        //private attributes and methods are NOT inherited,
        //there's an example of this in the TextBox class.

        //about the weird access modifiers:

        //the ""/default/package-private access modifier means
        //the field/method will be treated as public
        //if called from its same package,
        //and as private otherwise.

        //the "protected" access modifier means
        //the same as the default access modifier
        //but classes in other packages
        //which inherit the field/method
        //will be able to access it.

        //both are bad practice. DON'T use them.

        System.out.println("\n----------------------------------------");
        System.out.println("Method overriding\n");

        //testing the inherited (original) toString() method in the Object class:
        System.out.println( new Object().toString() );

        //testing the overriden toString() method in the TextBox class:
        System.out.println(tb3.toString());

        //print() and println()
        //implicitly call toString() on its arguments
        //so the previous example is equivalent to:
        System.out.println(tb3);

        System.out.println("\n----------------------------------------");
        System.out.println("Up/down casting\n");

        //upcasting: casting an object to one of its superclasses
        //downcasting: casting an object to one of its subclasses

        //the show() method expects a UIControl object
        //but let's give it both a UIControl and a TextBox:

        //before lesson 10 UIControl wasn't abstract so we could instantiate it
        //var uic3 = new UIControl(true);
        //var tb4 = new TextBox("calando el show", true);

        //show(uic3); //this works as intended
        //show(tb4); //this works too! because every TextBox IS A UIControl.

        //the "IS A" relationship represents inheritance.
        //we say the TextBox object was implicitly UPCASTED to UIControl, its parent type.

        //however, the overriden toString() of TextBox was called
        //so at runtime we're still dealing with a TextBox, not a UIControl
        //so UPCASTING can be unpredictable at runtime

        //There are further problems at compile-time
        //since show() expects a UIControl object
        //inside show() we treat the object strictly as a UIControl
        //so we get compile-time errors if we try to call TextBox methods from it

        //see solutions for both problems in the show() method

        System.out.println("\n----------------------------------------");
        System.out.println("Comparing objects\n");

        //these two points have the same coordinates so they should be equal
        var point1 = new Point(1,2);
        var point2 = new Point(1,2);

        //we wanna compare points by their coordinates
        System.out.println(point1 == point2); //we're comparing heap addresses here

        //by default we're also comparing heap addresses here
        //but later we overrode the equals() method of the Point class
        //and now it compares points as intended!
        System.out.println( point1.equals(point2) );

        //we can test the overriden hashCode() method too:
        System.out.println(point1.hashCode());
        System.out.println(point2.hashCode());

        System.out.println("\n----------------------------------------");
        System.out.println("Polymorphism\n");

        UIControl[] controls = { new TextBox(), new CheckBox() };

        //the UIControl's in the array will take SEVERAL DIFFERENT FORMS
        //which is exactly what polymorphism means

        for(var control_obj : controls) {
            //polymorphism: each subclass calls its overriden render() method
            control_obj.render();
        }
        //for

        System.out.println("\n----------------------------------------");
        System.out.println("Abstract classes and methods\n");

        //in the polymorphism example
        //we overrode the render() method in the subclasses of UIControl
        //but it was possible to create a new subclass of UIControl
        //that didn't override the render() method

        //this is dangerous, because we get unpredictable behavior
        //if we called render() on an object of said subclass

        //by abstracting the original render() method on the UIControl class
        //and also the UIControl class itself
        //we force every subclass of UIControl to override the render() method
        //or else we get a compilation error

        System.out.println("\n----------------------------------------");
        System.out.println("Final classes and methods\n");

        //abstract classes cannot be instantiated (they should be extended)
        //final classes cannot be extended (they can't have subclasses)

        //abstract methods cannot be implemented (they should be overridden)
        //final methods cannot be overridden

        //use final when you've made important assumptions which should always hold true
        //so you wouldn't want those assumptions to be broken by the subclasses or the method overrides

        System.out.println("\n----------------------------------------");
        System.out.println("Deep inheritance hierarchies\n");

        //NEVER do inheritance hierarchies deeper than 3 levels
    }
    //inheritanceExamples
}
//Inheritance

//eof
