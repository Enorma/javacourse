package com.javacourse.classes;

/*
    //wrapper class for Course 2 Section 2: Classes

    //import to Main class with:
    import com.javacourse.classes.Classes; //or...
    import com.javacourse.classes.*;

    //call from main() method with:
    com.javacourse.classes.Classes.exampleOOPStuff(); //or...
    Classes.exampleOOPStuff();
*/

public class Classes {

    public static int calculateWageProcedural(int baseSalary, int extraHours, int hourlyRate) {

        //since this is procedural, we need THREE arguments in this method
        //that's a lot of arguments!

        return baseSalary + (extraHours * hourlyRate);
    }
    //calculateWage

    public static void exampleOOPStuff() {

        System.out.println("\n----------------------------------------");
        System.out.println("Creating classes & objects\n");

        //instantiate a new object from the TextBox class
        //use var instead of the datatype in a variable declaration whenever
        //what's stored in the variable is of an unambiguous datatype
        //in this case, it's the TextBox datatype
        var text_box_1 = new TextBox();

        //but it's still possible to do a verbose instantiation:
        //TextBox text_box1 = new TextBox();

        //this prints null (the default value of a String object)
        System.out.println(text_box_1.text);

        //this next line is allowed in compile-time
        //because text is a String,
        //and toUpperCase is a method of the String class
        //but it crashes because in runtime text is null
        //therefore it is dangerous to not initialize class fields to something safe
        //System.out.println(text_box_1.text.toUpperCase());

        System.out.println();

        //set a value to the text field and print it
        text_box_1.setText("Box 1");
        System.out.println(text_box_1.text);
        System.out.println(text_box_1.text.toUpperCase());

        System.out.println();

        //instantiate another new object from the TextBox class
        //we can have unlimited instances
        var text_box_2 = new TextBox();
        text_box_2.setText("Box 2");
        System.out.println(text_box_2.text);
        System.out.println(text_box_2.text.toUpperCase());

        System.out.println("\n----------------------------------------");
        System.out.println("The stack & the heap\n");

        //heap only stores objects and other reference types
        //stack only stores primitive variables and references (addresses) of objects

        //in this example, the actual text_box objects (and their contents)
        //are stored on the heap
        //but the variables text_box_1 and text_box_2 (which only store the addresses of said objects)
        //are stored on the stack
        //so every object variable in the stack points to somewhere in the heap

        //to prove the text_box variables are reference types
        //let's make them reference the same object:
        var text_box_3 = text_box_1;
        //and now their contents are the same:
        System.out.println(text_box_1.text);
        System.out.println(text_box_3.text);

        System.out.println("\n----------------------------------------");
        System.out.println("The garbage collector\n");

        //de-referencing an object means having some variable store it's heap address
        //and then overwriting that variable with something else

        //so, there would be no references to that object anymore
        //therefore, java deems that object unnecessary and deletes it from the heap
        //in order to free up some memory

        //this happens to all de-referenced objects in the scope of a method
        //as soon as that method returns

        //this is called garbage collection

        //example: create two objects, give them different values
        var text_box_4 = new TextBox(); //OBJECT A
        text_box_4.setText("I'm OBJECT A");
        var text_box_5 = new TextBox(); //OBJECT B
        text_box_5.setText("I'm OBJECT B");

        //check how both objects are different:
        System.out.println(text_box_4.text);
        System.out.println(text_box_5.text);

        System.out.println();

        //then, de-reference OBJECT B stored in text_box_5
        //by overwriting it with the heap address of OBJECT A stored in text_box_4
        text_box_5 = text_box_4;

        //check how text_box_5 is now referencing OBJECT A:
        System.out.println(text_box_4.text);
        System.out.println(text_box_5.text);

        //now nothing is referencing OBJECT B
        //so the garbage collector will delete it from the heap

        //also, as soon as a method returns, its scope ends
        //and ANY variable of ANY type declared in said scope
        //will be deleted from the stack too

        //this includes reference-type variables,
        //which means all the objects they reference
        //will become de-referenced and thus, garbage-collected.

        System.out.println("\n----------------------------------------");
        System.out.println("What's wrong with procedural programming\n");

        //in procedural programming you basically just assign variables
        //and call a lot of functions with a lot of parameters
        //this means more code, that's bad

        //procedural code is:
        //less reusable,
        //less maintainable,
        //less elegant,
        //harder to debug,
        //tightly coupled (you change one thing and many things break),
        //has many arguments per function,
        //in short, it becomes spaghetti code

        //for example, a function for calculating an employee's wage:

        int baseSalary = 50_000;
        int extraHours = 10;
        int hourlyRate = 20;

        int wage = calculateWageProcedural(baseSalary, extraHours, hourlyRate);
        System.out.println(wage);

        System.out.println("\n----------------------------------------");
        System.out.println("Encapsulation\n");

        //this code comes from lesson 7, but is affected by the following lessons
        //hence all the commented sections...

        //in oop we place the values relevant to the problem we're solving
        //and also the methods which operate on those values
        //inside a single unit, called an object
        //this is encapsulation

        //create an employee object
        var employee1 = new Employee();

        //give the employee it's values
        //(commented out after making these fields private in lesson 8)
        //employee1.baseSalary = 50_000;
        //employee1.hourlyRate = 20;
        //(these getters are needed after lesson 8)
        employee1.setBaseSalary(50_000);
        employee1.setHourlyRate(20);

        //calculate the wage and print it
        wage = employee1.calculateWage(10);
        System.out.println(wage);

        System.out.println("\n----------------------------------------");
        System.out.println("Getters, setters & access modifiers\n");

        //setters & access modifiers protect our objects
        //from improper modifications

        //this line triggers a compile-time error
        //because we cannot directly overwrite a private field
        //employee1.baseSalary(50_000);

        //this line triggers a runtime error
        //because we're purposefully giving an invalid value
        //to a setter which does data validation
        //employee1.setBaseSalary(-1);

        //we need a getter to read a private field
        System.out.println(employee1.getBaseSalary());
        System.out.println(employee1.getHourlyRate());

        System.out.println("\n----------------------------------------");
        System.out.println("Abstraction & coupling\n");

        //a coupling point is any line of code in some class A
        //which instantiates or calls a method of some class B

        //so class A is dependent on class B (they're coupled)
        //and any change in class B may break class A
        //so we would need to modify class A or at least recompile it

        //these are the points where this class
        //is coupled to the Employee class:

        //if the Employee constructor is changed
        //so that it now requires an argument,
        //we would need to change this line:
        var employee2 = new Employee();

        //if more fields are added to the Employee class,
        //we may need to call more setters here:
        employee2.setBaseSalary(50_000);
        employee2.setHourlyRate(20);

        //if the calculateWage method is changed
        //so that it now requires more/less/different arguments,
        //we would need to change this line:
        wage = employee2.calculateWage(10);

        System.out.println("\n----------------------------------------");
        System.out.println("Reducing coupling\n");

        var browser = new Browser();

        //we can only call the navigate method here
        //all other methods are private,
        //this reduces our chances to couple this code to the Browser class
        browser.navigate("lol");

        System.out.println("\n----------------------------------------");
        System.out.println("Constructors\n");

        var employee3 = new Employee();

        //(this next comment is from lesson 12,
        //BEFORE we coded explicit constructors,
        //so the calculateWage method was supposed to return a bad result)

        //the freshly instantiated employee3's fields have default values:
        //baseSalary = 0 and hourlyRate = 0
        //at this point we would need to set employee3's fields to valid values
        //but since we don't, employee3 is in a bad state
        //not ready to call calculateWage()
        wage = employee3.calculateWage(20);

        //that was bad design.
        //we can't expect a programmer to write specific lines in a specific order
        //because sooner or later that will crash
        //so try and reduce the guesswork done by other programmers or users

        //a constructor sets the fields of an object
        //to valid initial values at the time of its creation

        //default constructors are autogenerated
        //they take no arguments
        //and set all numeric fields to zero
        //all booleans to false
        //and all reference-type variables to null
        //they only exist if we don't explicitly code a constructor

        //so now let's call our explicitly-coded constructor:
        var employee4 = new Employee(50_000, 20);
        //and now we can call calculateWage safely:
        wage = employee4.calculateWage(5);
        System.out.println(wage);

        System.out.println("\n----------------------------------------");
        System.out.println("Method overloading\n");

        //since the calculateWage method is overloaded
        //we can call it with or without the extraHours argument:

        wage = employee4.calculateWage(5);
        System.out.println(wage);

        wage = employee4.calculateWage();
        System.out.println(wage);

        System.out.println("\n----------------------------------------");
        System.out.println("Constructor overloading\n");

        //we can use these different, overloaded constructors and methods for...

        //a standard employee
        var employee5 = new Employee(50_000, 20);
        wage = employee5.calculateWage(10);
        System.out.println(wage);

        //an employee who doesn't do extra hours
        var employee6 = new Employee(50_000);
        wage = employee6.calculateWage();
        System.out.println(wage);

        //an employee who only works by the hour
        var employee7 = new Employee(0, 10);
        wage = employee7.calculateWage(30);
        System.out.println(wage);

        //an employee without a fixed salary or work hours
        var employee8 = new Employee();
        wage = employee8.calculateWage();
        System.out.println(wage);

        System.out.println("\n----------------------------------------");
        System.out.println("Static members\n");

        //if a concept can be represented by a class field/method
        //because it is tightly related to that class
        //but it can't be tied to each instance of the class
        //it should be a static field/method of that class

        //to use them, we don't have to instantiate an object
        //we can call them directly from the class name

        System.out.println( Employee.numberOfEmployees ); //read a static field
        Employee.printNumberOfEmployees(); //call a static method
    }
    //exampleOOPStuff
}
//ControlFlow

//eof
