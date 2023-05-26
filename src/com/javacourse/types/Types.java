package com.javacourse.types;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.awt.*;

/*
    //wrapper class for Course 1 Section 2: Types

    //import to Main class with:
    import com.javacourse.types.Types; //or...
    import com.javacourse.types.*;

    //call from main() method with:
    com.javacourse.types.Types.exampleStuff(); //or...
    Types.exampleStuff();
*/

public class Types {

    public static void exampleStuff() {

        System.out.println("\n----------------------------------------");
        System.out.println("Primitive datatypes\n");

        //Primitive Datatypes

        //Integer types (and their max values):
        byte    age        = 127;
        short   donuts     = 32_767;
        int     atoms      = 2_147_483_647;
        long    viewsCount = 9_223_372_036_854_775_807L; //literal long's need 'L' at the end

        //Floating-point types (are NOT precise past a few decimal places):
        float   price      = 0.000_000_1F; //7 decimal places max //literal float's need 'F' at the end
        double  asymptote  = 0.000_000_000_000_001; //15 decimal places max

        //Character type:
        char    letter     = 'A'; //chars use single quotes

        //Logic type:
        boolean isEligible = false;

        System.out.println(age);
        System.out.println(donuts);
        System.out.println(atoms);
        System.out.println(viewsCount);
        System.out.println(price);
        System.out.println(asymptote);
        System.out.println(letter);
        System.out.println(isEligible);

        System.out.println("\n----------------------------------------");
        System.out.println("Max values for numeric types\n");

        //constants for numeric min/max values:
        System.out.println(Byte.MIN_VALUE);
        System.out.println(Byte.MAX_VALUE);
        System.out.println(Short.MIN_VALUE);
        System.out.println(Short.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MIN_VALUE);
        System.out.println(Long.MAX_VALUE);
        System.out.println(Float.MIN_VALUE);
        System.out.println(Float.MAX_VALUE);
        System.out.println(Double.MIN_VALUE);
        System.out.println(Double.MAX_VALUE);

        System.out.println("\n----------------------------------------");
        System.out.println("Reference datatypes\n");

        //Reference data types

        //A Reference Datatype or Class
        Date now = new Date();
        System.out.println(now);
        System.out.println(now.getTime());

        System.out.println("\n----------------------------------------");
        System.out.println("Pass by value / by reference\n");

        //primitives pass by value
        byte x = 1;
        byte y = x; //y stores a copy of x
        x = 2; //change x
        System.out.println(y); //y is not affected

        //references pass by reference
        Point point1 = new Point(1, 1);
        System.out.println(point1); //print the x,y coords of point1
        Point point2 = point1; //point2 stores the memory address of point1
        point1.x = 2; //change point1
        System.out.println(point2); //point2 is affected as well
        point2.y = 3; //change point2
        System.out.println(point1); //point1 is affected as well

        System.out.println("\n----------------------------------------");
        System.out.println("Strings\n");

        //Strings are reference-types
        String message1 = new String("Hello world"); //actual way to declare strings
        String message2 = "Hello man"; //shorthand, recommended
        var message3 = "Hello again"; //even shorter!
        System.out.println(message1 + " " + message2 + " " + message3); //concatenation

        //cool methods of strings:
        //but strings are immutable so we should catch the return values of these:
        String message0 = "Hello World";
        message0.length(); //with strings, length() is a METHOD
        message0.startsWith("H");
        message0.endsWith("H");
        message0.indexOf("o");
        message0.toUpperCase();
        message0.toLowerCase();
        message0.replace("o", "*");
        message0.trim();

        //Escape chars:
        System.out.println(" \n \t \\ \" ");

        System.out.println("\n----------------------------------------");
        System.out.println("Arrays\n");

        //ARRAYS (fixed length at the moment of declaration/initialization)

        //old way to declare them
        int[] numbers = new int[5]; //all 5 values default to 0
        numbers[0] = 1; //write to a specific index
        numbers[1] = 2;

        //new way to declare them / shorthand
        int[] numbers2 = {2,3,5,1,4}; //declaration + population
        System.out.println(numbers2.length); //with arrays, length is a FIELD
        Arrays.sort(numbers2); //this sorts in-place, no need to catch return value

        //this prints the memory address of the array:
        System.out.println(numbers2);
        //this gets the string representation of an array and prints it:
        var arr_str = Arrays.toString(numbers2);
        System.out.println(arr_str);

        System.out.println("\n----------------------------------------");
        System.out.println("Multi-dimensional arrays\n");

        //multidimensional arrays
        //let's make a 3D array for instance (2x3x5)
        int[][][] numbercube = new int[2][3][5];
        numbercube[0][0][0] = 1; //write to a specific i,j,k index

        //this prints the memory address of each sub array
        System.out.println(Arrays.toString(numbercube));
        //this prints the 3D array
        System.out.println(Arrays.deepToString(numbercube));

        //2D array with shorthand syntax
        int[][] numbermatrix = {
            {1,2,3},
            {4,5,6}
        };
        //this prints the 2D array
        System.out.println(Arrays.deepToString(numbermatrix));

        System.out.println("\n----------------------------------------");
        System.out.println("Constants\n");

        //Constants
        final float PI = 3.14F;
        System.out.println(PI);

        System.out.println("\n----------------------------------------");
        System.out.println("Pre/post increment/decrement\n");

        //arithmetic

        /* division result types:

        double / double = double
        double / float  = double
        double / long   = double
        double / int    = double
        double / short  = double
        double / byte   = double
        float  / double = double
        long   / double = double
        int    / double = double
        short  / double = double
        byte   / double = double

        float  / float  = float
        float  / long   = float
        float  / int    = float
        float  / short  = float
        float  / byte   = float
        long   / float  = float
        int    / float  = float
        short  / float  = float
        byte   / float  = float

        long   / long   = long
        long   / int    = long
        long   / short  = long
        long   / byte   = long
        int    / long   = long
        short  / long   = long
        byte   / long   = long

        int    / int    = int
        int    / short  = int
        int    / byte   = int
        short  / int    = int
        short  / short  = int
        short  / byte   = int
        byte   / int    = int
        byte   / short  = int
        byte   / byte   = int
        */

        int a = 1;
        System.out.println(a++); //postincrement
        a = 1;
        System.out.println(++a); //preincrement
        int b = 2;
        System.out.println(b--); //postdecrement
        b = 2;
        System.out.println(--b); //predecrement

        //compound assignments in java:
        // arithmetic: ( += -= *= /= %= )
        // bitwise: ( &= ^= |= <<= >>= >>>= )
        //see: https://www.baeldung.com/java-compound-operators

        System.out.println("\n----------------------------------------");
        System.out.println("Order of operators\n");

        //order of operators
        System.out.println(10+3*2); //16
        System.out.println((10+3)*2); //26

        System.out.println("\n----------------------------------------");
        System.out.println("Casting\n");

        //casting (implicit on ints)
        //this happens with byte/short/int/long
        short littlevalue = 1;
        int biggervalue = littlevalue + 2;
        System.out.println(biggervalue);

        //casting (implicit on ints & floats)
        //this happens with (int types)/float/double
        double bigdecimal = 1.1;
        int anothervalue = 2;
        double r_decimal = bigdecimal + anothervalue;
        System.out.println(r_decimal);

        //casting (explicit)
        //this happens whenever you want to use it
        int r_int = (int)bigdecimal + anothervalue;
        System.out.println(r_int);

        //casting (explicit string to int)
        String numstr = "5";
        int r_casted_sum = Integer.parseInt(numstr) + 2;
        System.out.println(r_casted_sum);

        System.out.println("\n----------------------------------------");
        System.out.println("Truncation & rounding\n");

        //explicit (int) casting from float/double
        //does implicit TRUNCATION instead of rounding
        double up_rounder       = 3.99999; //would round to 4
        double down_rounder     = 2.00001; //would round to 2
        double mid_rounder_down = 6.5;     //would round to 7
        double mid_rounder_up   = 7.5;     //would round to 8
        System.out.println( (int)up_rounder );
        System.out.println( (int)down_rounder );
        System.out.println( (int)mid_rounder_down );
        System.out.println( (int)mid_rounder_up );

        System.out.println();

        //rounding is explicit and needs the Math class:

        //Math.round() rounds to nearest integer
        //except in case of X.5 or -X.5 it rounds towards +infinity
        //Math.round(double) implicitly casts to LONG
        //Math.round(float)  implicitly casts to INT
        System.out.println( Math.round(up_rounder) );
        System.out.println( Math.round(down_rounder) );
        System.out.println( Math.round(mid_rounder_down) );
        System.out.println( Math.round(mid_rounder_up) );

        System.out.println();

        //Math.ceil  always rounds towards positive infinity
        //Math.floor always rounds towards negative infinity
        //they both don't do any implicit casting,
        //but can be explicitly casted to int
        System.out.println( (int)Math.ceil(1.9F) );
        System.out.println( (int)Math.ceil(1.1F) );
        System.out.println( (int)Math.floor(1.9F) );
        System.out.println( (int)Math.floor(1.1F) );

        System.out.println("\n----------------------------------------");
        System.out.println("Min & max\n");

        //min & max
        int minresult = Math.min(1,2);
        System.out.println(minresult);
        int maxresult = Math.max(1,2);
        System.out.println(maxresult);

        System.out.println("\n----------------------------------------");
        System.out.println("Next & previous numbers\n");

        //for some number, find its closest representable floats
        System.out.println( Math.nextDown(1.0) );
        System.out.println( Math.nextUp(1.0) );

        System.out.println("\n----------------------------------------");
        System.out.println("Random numbers\n");

        //establish lower and upper bounds:
        double lbound = 1.0;
        double ubound = 2.0;

        //get random double between 0 (inclusive) and 1 (exclusive)
        double rand = Math.random();

        //get random double between 0 (inclusive) and upper bound (exclusive)
        //then round it and/or cast it to int
        double ubounded_exc_double = rand * ubound;
        int ubounded_exc_trunc = (int)ubounded_exc_double;
        int ubounded_exc_round = (int)Math.round(ubounded_exc_double);

        //get random int between the bounds (both inclusive)
        int bounded_inc_int = (int)( lbound + (int)( rand * (ubound - lbound + 1) ) );

        System.out.println(rand);
        System.out.println(ubounded_exc_double);
        System.out.println(ubounded_exc_trunc);
        System.out.println(ubounded_exc_round);
        System.out.println(bounded_inc_int);

        System.out.println("\n----------------------------------------");
        System.out.println("Number formatting\n");

        //factory methods, they create new objects:
        //can't instantiate a NumberFormat with new because it's an abstract class
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        NumberFormat percent  = NumberFormat.getPercentInstance();

        //get formatted currency string from number
        String currencystr = currency.format(1234567.819);
        System.out.println(currencystr);

        //get formatted percent string from number
        String percentstr = percent.format(0.1);
        System.out.println(percentstr);

        System.out.println("\n----------------------------------------");
        System.out.println("User Input\n");

        //Reading input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Age? ");
        //next lee hasta el primer espacio,
        //nextLine lee hasta el primer newline
        String edad = scanner.nextLine().trim();
        System.out.println("You are "+edad);
    }
    //exampleStuff
}
//Types

//eof
