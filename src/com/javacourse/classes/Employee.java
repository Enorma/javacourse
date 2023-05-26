package com.javacourse.classes;

public class Employee {

    //---------------------------------------------------------------------------------
    //fields

    //(started as public, then became private in lesson 8)

    private int baseSalary;
    private int hourlyRate;
    public static int numberOfEmployees;

    //---------------------------------------------------------------------------------
    //constructors

    //here we make 3 overloaded constructors
    //including one with no arguments,
    //so that examples from previous lessons (before lesson 12) don't crash

    public Employee(int _baseSalary, int _hourlyRate) {

        //we use the setters because those do validation

        this.setBaseSalary(_baseSalary);
        this.setHourlyRate(_hourlyRate);
        numberOfEmployees++; //this wasn't here before lesson 15
    }
    //Employee

    public Employee(int _baseSalary) {

        //this is the current object,
        //but when called as a method, it is the constructor

        //we set hourlyRate to its minimum valid value
        //since it was not provided explicitly

        this(_baseSalary, 0);
    }
    //Employee

    public Employee() {

        this(0, 0);
    }
    //Employee

    //---------------------------------------------------------------------------------
    //static methods

    public static void printNumberOfEmployees() {

        //no need to use the this keyword
        //since numberOfEmployees doesn't belong to an instance

        //also since this is a static method
        //we cannot access instance methods from here

        System.out.println(numberOfEmployees); //static field
    }
    //printNumberOfEmployees

    //---------------------------------------------------------------------------------
    //methods

    //overloaded methods are separate implementations of methods
    //with the same name but different parameters and/or return type

    //there's not one "base" form of a method
    //all its forms are equally overloads

    //it's best to use overloads
    //when a method might do different things
    //based on the DATATYPE of the arguments
    //and those could vary a lot, like:
    //one overload takes two ints
    //another takes an Employee object
    //otherwise, don't overuse overloading

    public int calculateWage(int extraHours) {

        //since this is oop, we don't need three arguments in this method anymore!

        //this takes extraHours as an argument
        //because it's likely to change every time this method is called

        //this takes baseSalary and hourlyRate from the object's fields
        //because those rarely change

        return this.baseSalary + (this.hourlyRate * extraHours);
    }
    //calculateWage

    public int calculateWage() {

        //this is an example of an overload
        //which calls another overload of itself
        //(since it's not exactly the same method, it's not recursion)

        return calculateWage(0);
    }
    //calculateWage

    //---------------------------------------------------------------------------------
    //getters

    //in lesson 10 these were deleted
    //because they weren't used in the course's examples
    //so they were unnecessary coupling points to external classes

    //but I'm keeping them here because they are the first examples of getters
    //and I actually use them

    public int getBaseSalary() {
        return this.baseSalary;
    }
    //getBaseSalary

    public int getHourlyRate() {
        return this.hourlyRate;
    }
    //getHourlyRate

    //---------------------------------------------------------------------------------
    //setters

    //in lesson 12 these were made private
    //since the course only uses them in the constructor
    //so they were unnecessary coupling points to external classes

    //but I'm keeping them as public because they are used in previous lessons
    //so all that code doesn't crash

    public void setBaseSalary(int _baseSalary) {
        //best practice: make private fields with public setters
        //we can do data validation inside a setter
        if(_baseSalary < 0) throw new IllegalArgumentException("Salary cannot be negative");
        this.baseSalary = _baseSalary;
    }
    //setBaseSalary

    public void setHourlyRate(int _hourlyRate) {
        //best practice: make private fields with public setters
        //we can do data validation inside a setter
        if(_hourlyRate < 0) throw new IllegalArgumentException("Hourly rate cannot be negative");
        this.hourlyRate = _hourlyRate;
    }
    //setHourlyRate
}
//Employee

//eof
