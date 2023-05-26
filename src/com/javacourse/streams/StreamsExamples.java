package com.javacourse.streams;

/*
    //wrapper class for Course 3 Section 6: Streams

    //import to Main class with:
    import com.javacourse.streams.StreamsExamples; //or...
    import com.javacourse.streams.*;

    //call from main() method with:
    com.javacourse.streams.StreamsExamples.runStreamsExamples(); //or...
    StreamsExamples.runStreamsExamples();
*/

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.function.*;

public class StreamsExamples {

    public static void runStreamsExamples() {

        System.out.println("\n----------------------------------------");
        System.out.println("Intro to Streams\n");

        //streams are a capability that all collections have.
        //it allows us to traverse and process the collection
        //element-by-element, in a declarative/functional way.

        //Stream is a class in the Java standard library.
        //all collections have a stream() method which returns
        //a stream object.

        //once created, a stream object can call its methods
        //and the stream will have access to the data in the collection.
        //stream methods allow us to do all kinds of things to that data,
        //like filtering, sorting, reducing or mapping.

        //plus, streams are fully customizable because
        //how each of those operations is done
        //is defined by lambdas/MRs that we provide ourselves.

        //also, many stream operations return another stream
        //which means they are chainable.
        //but at the end of the chain, there needs to be
        //some method that doesn't return another stream.
        //this is a concept known as lazy evaluation,
        //which we'll explain later.

        //regular streams are generic,
        //which means their datatype is something like Stream<T>.
        //however, just like with functional interfaces,
        //there are some primitive specializations, which are:
        //IntStream, LongStream and DoubleStream,
        //which only allow operations taking their respective primitive type as input.

        System.out.println("\n----------------------------------------");
        System.out.println("Filtering\n");

        //in this next example we'll do some filtering.
        //streams have a chainable filter() method which takes a Predicate,
        //and we'll conclude the chain with the count() method
        //which will return how many elements remain in our collection
        //after the filtering.

        //the Predicate is a lambda
        //of the Predicate functional interface we already know.
        //It establishes a boolean condition that
        //each value on the stream could satisfy or not.

        //let's create three instances of our movie class:
        List<Movie> movies = List.of(
            new Movie("a", 10, Genre.COMEDY),
            new Movie("b", 15, Genre.ACTION),
            new Movie("c", 20, Genre.THRILLER)
        );
        //movies

        //let's stream, filter and count:
        var filtered_count = movies.stream()
            .filter(m -> m.getLikes() > 10) //filter applies the Predicate lambda to every item in the collection
            .count(); //only items that satisfied the filter Predicate remain here
        //movies.stream

        System.out.println(filtered_count + " out of " + movies.size() + " movies survived the filter.");

        //filtering and counting are just two examples of
        //operations we can do on streams.
        //there are so many more. We'll look at several
        //in the coming lessons.

        System.out.println("\n----------------------------------------");
        System.out.println("Ways to Create Streams\n");

        //streams can be created from...
        //any Collection (ArrayList, HashSet, HashMap, etc),
        //any vanilla Array, or
        //any unstructured group of any arbitrary number of objects

        //STREAMS FROM COLLECTIONS:

        //streams can be created from collection objects
        //simply calling the stream() method of the collection

        var int_list = new ArrayList<Integer>(List.of(1, 2, 3));

        //keep in mind:
        //streams have the forEach() method we know from before.
        //just like before, it takes a Consumer
        //however, it is optimized because
        //if our data is of a primitive type
        //it will instead take one of the
        //primitive specializations of Consumer.

        int_list.stream()
            .forEach(System.out::print);
        //int_list.stream()

        System.out.println();

        //STREAMS FROM ARRAYS:

        //vanilla arrays don't have the stream() method, but
        //streams can be created from them
        //by calling the stream() method of the Arrays utility class.
        //(regardless of the contents of the array)

        int[] int_arr = {4, 5, 6};

        //since our data here is primitive integers
        //the forEach() method will take an IntConsumer
        //instead of a regular Consumer.

        Arrays.stream(int_arr)
            .forEach(System.out::print);
        //Arrays.stream(int_arr)

        System.out.println();

        //STREAMS FROM ANY AMOUNT OF OBJECTS:

        //the Stream class has a static method called of()
        //which takes any amount of objects of the same type
        //and creates a stream from them.

        Stream.of(7, 8, 9)
            .forEach(System.out::print);
        //Stream.of()

        System.out.println("\n");

        System.out.println("\n----------------------------------------");
        System.out.println("Infinite Streams\n");

        //streams can be finite or infinite.

        //INFINITE STREAMS FROM NOTHING:

        //as we saw in the last section
        //the Supplier interface lets us create
        //values from thin air.

        //another static method in the Stream class
        //is the generate() method.
        //it takes a Supplier, and so it keeps generating
        //new values through the Supplier, without end.

        //here's a Stream capable of generating random numbers
        //but, at this point, it hasn't generated any
        //because it works by lazy evaluation:

        var random_stream = Stream.generate(Math::random);

        //at this point, no numbers have been generated,
        //so no RAM has been consumed by that,
        //which means that Streams are capable of
        //generating infinite amounts of data
        //while collections can't,
        //because collections would hit the RAM limit.

        //due to lazy evaluation,
        //to actually generate random numbers
        //(that is, to actually trigger the Supplier)
        //we need to call another method of our stream.
        //one that doesn't return another stream.

        //for example:
        //every time this forEach() processes one value,
        //it polls the Supplier for a new one,
        //so the data is generated one-by-one on demand
        //that's the beauty of the generate() method:

        //uncomment this next line to test the infinite stream:
        //random_stream.forEach( System.out::println );

        //in order to not have an infinite loop,
        //we can limit the amount of values the stream processes
        //by using the limit() method:

        random_stream
            .limit(5)
            .forEach(System.out::println);
        //random_stream

        //the limit() method returns another stream, so it's chainable,
        //while the forEach() method returns void,
        //so we can use it to terminate the stream method chain
        //and get the final result we expect from it.

        System.out.println();

        //INFINITE STREAMS FROM A SEED:

        //we can also produce data for a stream
        //from some known value instead of from thin air.

        //instead of using the generate() method,
        //we can use the iterate() method
        //which takes an initial "seed" value
        //and a UnaryOperator which will be applied to
        //every value on the stream, starting from the seed.

        //in this example, we create an infinite Stream that,
        //starting from 1, increments the value by 1:

        var increment_stream = Stream.iterate(1, n -> n + 1);

        //the same principle of lazy evaluation applies, too.

        increment_stream
            .limit(3)
            .forEach(System.out::println);
        //increment_stream

        System.out.println("\n----------------------------------------");
        System.out.println("Mapping\n");

        //the map() method of streams allows us to
        //produce one new value from every value in the stream.
        //we say that it maps each value to some mutation of it.

        //the map() method takes a lambda that conforms
        //to the Function interface. As we've seen before,
        //Function's apply() method takes 1 arg and returns 1 value,
        //both from arbitrary types.

        //no matter the resulting type of the mapping,
        //map() always returns another stream,
        //so it is chainable.

        //map() has some primitive specializations as well:
        //mapToInt() which takes a ToIntFunction lambda and returns an IntStream,
        //mapToLong() which takes a ToLongFunction lambda and returns a LongStream, and
        //mapToDouble() which takes a ToDoubleFunction lambda and returns a DoubleStream.

        //let's demonstrate by taking our List of movies,
        //producing a stream from it and then...

        //mapping it to a Stream of their titles:
        movies.stream()
            .map(Movie::getTitle)
            .forEach(System.out::println);
        //movies.stream()

        System.out.println();

        //also, mapping it to an IntStream of their likes:
        movies.stream()
            .mapToInt(Movie::getLikes)
            .forEach(System.out::println);
        //movies.stream()

        System.out.println();

        System.out.println("\n----------------------------------------");
        System.out.println("Flat Maps\n");

        //another technique called flat mapping
        //is used when each element of data in a stream
        //is itself a collection
        //for example here, our stream contains two List's:

        var flatstream = Stream.of(
                List.of(1, 2, 3),
                List.of(4, 5, 6)
        );
        //flatstream

        //by just querying the stream, we get every element
        //(every List) as-is:

        flatstream
            .forEach(System.out::println);
        //flatstream

        //but maybe we want to get inside the Lists
        //and work with their inner values

        //that's what the flatMap() method is for.

        //since we can't reuse a stream we've already processed
        //we need to re-create our stream object:
        flatstream = Stream.of(List.of(1, 2, 3), List.of(4, 5, 6));

        System.out.println();

        //flatMap(), like map(), takes a Function lambda as arg
        //but the arg of the lambda must be a collection
        //and the return value of the Function
        //must be a stream created from the arg collection.

        //So flatMap() will create secondary inner streams
        //from an original stream,
        //and afterwards it will merge them all into a resulting stream.

        //here we call flatMap(), giving it a Function lambda,
        //which takes a List<Integer> as arg
        //and returns a Stream<Integer>:

        flatstream
            .flatMap(List::stream)
            .forEach(System.out::print);
        //flatstream

        System.out.println();

        //since we're working with integers,
        //we might as well test the integer primitive specializations,
        //are those convenient with flatmaps?

        //our stream is of List<Integer>, but
        //sadly, there's no intStream() method of the List interface.
        //so we could work around that by creating a regular Stream
        //and then mapping it into an IntStream:

        //re-create our stream...
        flatstream = Stream.of(List.of(1, 2, 3), List.of(4, 5, 6));

        flatstream
            .flatMapToInt(l -> l // l is a List<Integer>
                .stream() //this is a Stream<Integer>...
                .mapToInt(i -> i)
            )
            .forEach(System.out::print);
        //flatstream

        System.out.println();

        //so, we actually didn't avoid auto-boxing
        //and in fact, made our code dirtier.

        //maybe this is better?

        //re-create our stream...
        flatstream = Stream.of(List.of(1, 2, 3), List.of(4, 5, 6));

        flatstream
            .flatMap(List::stream) //this maps to Stream<Integer>...
            .mapToInt(i -> i)
            .forEach(System.out::print);
        //flatstream

        //this option is cleaner, but still no.
        //We still had to go through some auto-boxing.
        //so sadly, since Collections can only produce regular streams
        //(because they only have the stream() method,
        //there's no intStream(), longStream() or doubleStream() methods),
        //there's no clean and straightforward way to avoid auto-boxing on flatmaps.

        System.out.println();

        System.out.println("\n----------------------------------------");
        System.out.println("Lazy Evaluation\n");

        //all stream methods that return another stream
        //are chainable. And in fact, chaining them further is mandatory.
        //these methods are called intermediate stream operations.
        //the filter() and map() methods are examples of such operations.

        //all chains of stream methods, therefore,
        //must conclude with a method that
        //doesn't return another stream.
        //that is, it returns some resulting data
        //or just does some work and returns void.
        //these are called terminal stream operations.
        //forEach() and count() are examples of these.

        //so, any stream of data in Java is divided into 3 stages:

        //the data in any stream originates from
        //some collection, some array or just a bunch of objects.
        //we'll call that just "the origin".

        //furthermore, we'll call the chain of
        //intermediate stream operations just "the pipeline".

        //finally, there's the terminal method
        //at the end of the stream.

        //knowing that, we can finally define Lazy Evaluation in detail.

        //any single value on the origin does NOT enter the pipeline
        //until the terminal method is run. We say this terminal method
        //"queries" the stream for new data.

        //so, for example, this next stream has no terminal method (yet)
        //and therefore it does absolutely nothing.
        //the data doesn't even get read from the origin:
        var m_stream = movies.stream()
            .filter(m -> m.getLikes() > 10)
            .map(m -> "movie " + m.getTitle() + " has " + m.getLikes() + " likes");
        //movies.stream()

        //however, we did store our stream (and it's pipeline)
        //into a variable called m_stream.
        //that means that, sometime later, we can finally conclude the stream
        //by chaining some terminal operation to it.

        //if we don't eventually do that,
        //our stream is wasted. It's good for nothing.
        //all we have is an unused pipeline.

        //so, at this point the methods in the pipeline have NOT run.
        //they will only be triggered when some terminal method runs on the stream.

        //so, the evaluation of the pipeline methods is "lazy".
        //it only works when needed. Not before.
        //that's lazy evaluation!

        //let's append the forEach() method at the end of the stream
        //to trigger the whole thing:
        m_stream.forEach(System.out::println);

        System.out.println("\n----------------------------------------");
        System.out.println("Cleaning Up Stream Code Blocks\n");

        //a block of code comprising a stream operation chain
        //can get quite convoluted quite easily,
        //especially when the lambdas are complex.

        //so we can store the lambdas on separate variables
        //in order to keep the code clean and simple,
        //and also to have options of what exactly will the stream do to the data.
        //It's like parameterizing a stream.

        //let's demonstrate by:
        //storing a Predicate in a variable and using it in the filter() method,
        //then, storing a Function in a variable and using it in the map() method,
        //and finally, storing a Consumer in a variable and using it in the forEach() method.

        Predicate<Movie> filter_predicate = m -> m.getLikes() > 10;
        Function<Movie, String> map_function = m -> "movie " + m.getTitle() + " has " + m.getLikes() + " likes";
        Consumer<String> foreach_consumer = System.out::println;

        movies.stream()
            .filter(filter_predicate)
            .map(map_function)
            .forEach(foreach_consumer);
        //movies.stream()

        //so most stream methods pair up with some functional interface.
        //by practicing with streams, it will become second nature to know what goes where.

        //it is important to note that the data in our original collection
        //is not affected at all.

        System.out.println("\n----------------------------------------");
        System.out.println("Slicing\n");

        //if we want to get a limited quantity of elements from a stream
        //instead of getting them all,
        //we can get a "slice" of it, like a portion.
        //there's four ways to do so:
        //the methods limit(), skip(), takeWhile() and dropWhile().
        //all four are intermediate operations.

        System.out.println("limit()");

        //limit() lets pass through the pipeline
        //only the first N elements.
        movies.stream()
            .limit(2)
            .forEach(m -> System.out.println(m.getTitle()));
        //movies.stream()

        System.out.println();
        System.out.println("skip()");

        //skip() lets pass through the pipeline
        //only all elements after the first N.
        movies.stream()
            .skip(2)
            .forEach(m -> System.out.println(m.getTitle()));
        //movies.stream()

        System.out.println();
        System.out.println("pagination");

        //let's say we have 100 movies total
        //and we want only every 10 at a time
        //(we say 10 is our page size)
        //in page 1 we'll get movies 0 to 9,
        //in page 2 we'll get movies 10 to 19, etc...
        //a formula for which movies we'll get in page N is:
        //we get the first movie in the page with: (N-1)*page_size <- this is the arg for skip()
        //we'll get page_size movies <- so this is the arg for limit()
        //this works if we run skip() first and then limit().

        //here's a small example
        //assuming we have 3 elements
        //and we want 3 pages of size 1,
        //and we process page 2:

        int page_size = 1;
        int target_page = 2;

        movies.stream()
            .skip((target_page - 1) * page_size)
            .limit(page_size)
            .forEach(m -> System.out.println(m.getTitle()));
        //movies.stream()

        System.out.println();
        System.out.println("takeWhile()");

        //Predicates (lambdas returning bool, like those we use on filters)
        //can also be used to condition
        //how many elements we extract from a stream.

        //the takeWhile() method takes a predicate
        //evaluates it, and, only if it returns true
        //the current element will pass through it:

        movies.stream()
            .takeWhile(m -> m.getLikes() < 20)
            .forEach(m -> System.out.println(m.getTitle()));
        //movies.stream()

        //this is DIFFERENT from the filter() method,
        //because filter() will keep going even after
        //not letting an element through.
        //takeWhile() on the other hand, stops permanently
        //as soon as its predicate evaluates to false.

        System.out.println();
        System.out.println("dropWhile()");

        //dropWhile() is the opposite of takeWhile().
        //it lets an element pass
        //only when its predicate evaluates to FALSE.
        //and as soon as it evaluates to TRUE, it stops permanently.

        movies.stream()
            .dropWhile(m -> m.getLikes() < 20)
            .forEach(m -> System.out.println(m.getTitle()));
        //movies.stream()

        System.out.println("\n----------------------------------------");
        System.out.println("Sorting\n");

        //the order of the elements in a stream is
        //the same order of its origin collection or data source

        //to demonstrate, let's make another list of movies
        //that's not in alphabetical order,
        //nor ordered by likes:
        movies = List.of(
            new Movie("m", 30, Genre.COMEDY),
            new Movie("z", 20, Genre.ACTION),
            new Movie("a", 10, Genre.THRILLER)
        );

        //we can, however, sort the elements in a stream
        //by some arbitrary criteria, using the sorted() method.

        //sorted() uses a comparison-based sorting algorithm, and therefore
        //every element in the stream needs to be comparable somehow.
        //either by belonging to a class implementing the Comparable interface (and the compareTo() method)
        //or by providing some custom object implementing the Comparator interface (and the compare() method)

        //Comparable objects require no args in the sorted() method,
        //they will be sorted by their natural criteria.

        //sorting anything else requires a Comparator
        //as the arg of the sorted() method.

        //let's look at the no-args sorted() first:
        movies.stream()
            .sorted() //by default, movies are sorted by title
            .forEach(System.out::println);
        //movies.stream()

        System.out.println();

        //and now let's look at sorted() with a provided Comparator:
        movies.stream()
            .sorted((m1, m2) -> m1.getLikes() - m2.getLikes()) //we'll do custom sorting by likes
            .forEach(m -> System.out.println(m.getLikes()));
        //movies.stream()

        System.out.println();

        //we just discovered that Comparator is in fact a functional interface
        //and therefore, it can be represented by a simple lambda implementation of the compare() method.

        //furthermore, in this particular situation
        //there's a primitive specialization of a Comparator that we can use:
        //the Comparator.comparingInt() method.
        //it takes any method returning a primitive int (like our getLikes() method of the Movie class)
        //and returns a Comparator that compares every two elements by said int:

        movies.stream()
            .sorted(Comparator.comparingInt(Movie::getLikes))
            .forEach(m -> System.out.println(m.getLikes()));
        //movies.stream()

        System.out.println();

        //if we wanted to compare by title using a custom comparator,
        //a similar method called Comparator.comparing() can be used:

        movies.stream()
            .sorted(Comparator.comparing(Movie::getTitle))
            .forEach(m -> System.out.println(m.getTitle()));
        //movies.stream()

        System.out.println();

        //custom Comparators have a reversed() method
        //in case we want to sort in descending order:

        movies.stream()
            .sorted(Comparator.comparing(Movie::getTitle).reversed())
            .forEach(m -> System.out.println(m.getTitle()));
        //movies.stream()

        System.out.println("\n----------------------------------------");
        System.out.println("Getting Distinct Elements\n");

        //sometimes we only want to output distinct (not repeated)
        //values from a stream.
        //this is done with the distinct() method.
        //it's an easy way to ignore duplicates on a stream.

        //let's demonstrate by adding duplicate values to our list of movies:
        movies = List.of(
            new Movie("m", 30, Genre.COMEDY),
            new Movie("z", 20, Genre.ACTION),
            new Movie("a", 10, Genre.THRILLER),
            new Movie("r", 10, Genre.COMEDY),
            new Movie("t", 20, Genre.ACTION),
            new Movie("s", 30, Genre.THRILLER),
            new Movie("u", 10, Genre.COMEDY)
        );
        //movies

        //now there are several movies with the same number of likes.

        System.out.println(movies);

        //we might print only the likes:
        movies.stream()
            .map(Movie::getLikes)
            .forEach(m -> System.out.print(m + ", "));
        //movies.stream()

        System.out.println();

        //we do the same, but removing the duplicates:
        movies.stream()
            .map(Movie::getLikes)
            .distinct()
            .forEach(m -> System.out.print(m + ", "));
        //movies.stream()

        System.out.println();

        System.out.println("\n----------------------------------------");
        System.out.println("Peeking Elements\n");

        //we may want to do something with every item in the stream
        //but keep chaining operations to the pipeline afterwards.
        //the forEach() method is no good for this, because it's terminal.

        //the peek() method is basically a non-terminal forEach().
        //we can use it to monitor the data inbetween the stages of the pipeline.

        movies.stream()
            .filter(m -> m.getLikes() <= 20)
            .peek(m -> System.out.println("filtered: " + m))
            .map(Movie::getTitle)
            .peek(m -> System.out.println("mapped: " + m))
            .forEach(System.out::println);
        //movies.stream()

        //by running the above peek()'s we can confirm
        //every element flows completely through the whole pipeline
        //before the next one is processed.

        //try using peek() when debugging streams.

        System.out.println("\n----------------------------------------");
        System.out.println("Built-in Reducers\n");

        //we now know these intermediate operations:
        //----map() and flatmap(),
        //----pagination via limit() and skip(),
        //----filter(), sorted(), distinct() and peek().

        //in terms of terminal operations,
        //the most common one is forEach(),
        //but we also know count().

        //as opposed to performing operations repeatedly like forEach(),
        //count() produces one single result value:

        var streamsize = movies.stream().count();
        System.out.println("count: " + streamsize);

        //terminal operations that do this are called REDUCERS.

        //Reducer operations are terminal operations that,
        //no matter how much data is in the stream,
        //return a single value.

        System.out.println();

        //a special kind of reducers are MATCHERS, like:

        //anyMatch(), allMatch() and noneMatch(),
        //which take a predicate and just return a boolean:

        var matches = movies.stream().anyMatch(m -> m.getLikes() > 20);
        System.out.println("anyMatch > 20: " + matches);
        matches = movies.stream().anyMatch(m -> m.getLikes() > 30);
        System.out.println("anyMatch > 30: " + matches);

        matches = movies.stream().allMatch(m -> m.getLikes() > 20);
        System.out.println("allMatch > 20: " + matches);
        matches = movies.stream().allMatch(m -> m.getLikes() > 1);
        System.out.println("allMatch > 1: " + matches);

        matches = movies.stream().noneMatch(m -> m.getLikes() > 1);
        System.out.println("noneMatch > 1: " + matches);
        matches = movies.stream().noneMatch(m -> m.getLikes() > 30);
        System.out.println("noneMatch > 30: " + matches);

        System.out.println();

        //findFirst() and findAny(),
        //which don't take args and just return an item from the stream,
        //(these return an object container called an "Optional")

        var found = movies.stream().findFirst();
        System.out.println("findFirst: " + found);
        found = movies.stream().findAny();
        System.out.println("findAny: " + found);

        System.out.println();

        //or max() and min(),
        //which take a comparator and returns the maximum or minimum
        //item from the stream, based on the comparator.
        //(these also return an "Optional")

        found = movies.stream().max(Comparator.comparingInt(Movie::getLikes));
        System.out.println("max: " + found);
        found = movies.stream().min(Comparator.comparingInt(Movie::getLikes));
        System.out.println("min: " + found);

        System.out.println("\n----------------------------------------");
        System.out.println("Custom Reducers\n");

        //in a similar way to how max() and min() work
        //custom reducers take a lambda which takes two args of the same type
        //and returns one value of the same type
        //i.e.: a BinaryOperator.

        //the logic goes like this:
        //for every two items in the stream,
        //produce a resulting item, and add it to the stream
        //then remove the original two items,
        //then repeat until consuming the whole stream.

        //so, in every iteration, the stream will have one less item
        //and in the end, the last remaining item will have
        //accumulated all the items somehow.
        //this accumulator item is the return value of the reducer.

        //how exactly is the value of the accumulator calculated
        //from the two values that produce it, is defined
        //by the lambda we pass to the reducer.
        //so the reducer's logic is entirely customizable.

        //we use custom reducers through the reduce() method.

        //let's make a simple reducer that just sums the items:

        Optional<Integer> opt_sum = movies.stream()
            .map(Movie::getLikes)
            .reduce(Integer::sum); //sum() takes two ints and returns an int
        //movies.stream()
        System.out.println("sum reducer: " + opt_sum);

        //see what happened?
        //reduce returned an Optional containing an Integer.

        //an Optional is good for when
        //we might get a null value, like for example
        //if applying a sum to reduce an empty stream
        //we might want the result as zero or null
        //but that depends on our preference.

        //An Optional lets us be safe from a null value
        //because Optional's may contain null
        //but they are never null themselves.
        //an Optional containing no value is the same as an Optional containing null.

        //to get the value contained inside an Optional,
        //we have the following methods of the Optional class (among others):
        //get() and orElseThrow() return the value or an exception if it contains null.
        //orElse() returns the value or a provided default if it contains null.
        //orElseGet() returns the value or runs a provided Supplier lambda if it contains null.
        //isEmpty() returns true if it contains null, false otherwise.
        //isPresent() returns false if it contains null, true otherwise.

        //are Optional's too convoluted?
        //there is another way, in which we give the accumulator
        //a default initial (safe) value (called "identity").
        //the identity is treated as yet another value in the stream
        //(it's like the stream was never empty!)
        //so if the stream is empty, we get the identity as a result.
        //this gives us control and predictability over the result.
        //so we don't need an Optional.
        //to do this, use an overload of reduce()
        //giving it the identity as arg, as well as the lambda:

        int int_sum = movies.stream()
            .map(Movie::getLikes)
            .reduce(0, Integer::sum); //sum() takes two ints and returns an int
        //movies.stream()
        System.out.println("sum reducer: " + int_sum);

        //see what happened?
        //we got an int, not an Optional!

        System.out.println("\n----------------------------------------");
        System.out.println("Collectors\n");

        //instead of consuming the end result of a stream pipeline
        //we can collect it into a collection (list, set or map)
        //after all, streams are sequences of data as well!

        //to do that, there's the collect() method of streams,
        //which is a terminal operation,
        //that takes an object of the Collector interface as arg.

        //a Collector is an object used for defining
        //the kind of collection we want to create
        //and the logic applied to populate it with our stream data.

        //in order to create our own custom Collector
        //there's the Collectors utility class.
        //in this class we have lots of methods, which include:
        //toList(), toSet() and toMap().

        //there are many other methods in the Collectors class
        //see: https://docs.oracle.com/en/java/javase/20/docs/api/java.base/java/util/stream/Collectors.html
        //but they all return a Collector, so it is valid to call any of them
        //in the args of the collect() method

        //a bit convoluted? remember:
        //Collectors.<any_method>() returns a Collector
        //<...stream_pipeline>.collect() takes a Collector
        //therefore it is valid to do:
        //<...stream_pipeline>.collect( Collectors.<any_method>() )

        //for example, here we'll stream our collection of movies,
        //filter out those with 10 likes or less,
        //and then produce a new list containing the remaining movies:

        var collected_list = movies.stream()
            .filter( m -> m.getLikes() > 10 )
            .collect( Collectors.toList() );
        //movies.stream()

        System.out.println(collected_list);

        //in the above example, the resulting collection is a List.
        //since List is an interface, we might assume it is in fact an ArrayList,
        //but there's no way to guarantee it. toList() is just not that specific.

        //a similar thing happens if we produced a Set instead:

        var collected_set = movies.stream()
            .filter( m -> m.getLikes() > 10 )
            .collect( Collectors.toSet() );
        //movies.stream()

        System.out.println(collected_set);

        //toList() and toSet() are easy to understand
        //because every item in the stream is simply stored
        //inside the resulting collection.

        //toMap() is more complicated, since maps store both a key and a value
        //for each of its items.

        //in this next example, we'll produce a new Map and
        //we'll store each item in such a way that the movie title becomes the key
        //and the number of likes becomes the value,
        //for every item.

        //both the key and the value can be defined separately
        //by their own lambda (called keyMapper and valueMapper, respectively).
        //both of which must conform to the Function interface, like:

        Function<Movie,String>  keyMapper   = Movie::getTitle;
        Function<Movie,Integer> valueMapper = Movie::getLikes;

        Map<String,Integer> collected_map1 = movies.stream()
            .filter( m -> m.getLikes() > 10 )
            .collect( Collectors.toMap(keyMapper, valueMapper) );
        //movies.stream()

        System.out.println(collected_map1);

        //in case we wanted to store the whole Movie object
        //as the value, there's a method called Function.identity()
        //which can be used as a valueMapper.
        //it returns the same object being passed through the stream.

        Map<String,Movie> collected_map2 = movies.stream()
            .filter( m -> m.getLikes() > 10 )
            .collect( Collectors.toMap( keyMapper, Function.identity() ));
        //movies.stream()

        System.out.println(collected_map2);

        //apart from collecting the stream items themselves,
        //we can also collect reductions of it, like the sum or the average.

        //this example collects a sum-reduction via the summingInt() method
        //which takes a ToIntFunction lambda:

        int sum_reduction = movies.stream()
            .filter( m -> m.getLikes() > 10 )
            .collect( Collectors.summingInt(Movie::getLikes) );
        //movies.stream()

        System.out.println( "total likes: " + sum_reduction );

        //summingLong() and summingDouble() also exist.

        //there's also another kind of Collector called a summarizer.
        //it gives us a bunch of statistics about the values extracted
        //through the provided lambda,
        //in this case, the likes of each movie:

        var summary = movies.stream()
            .filter( m -> m.getLikes() > 10 )
            .collect( Collectors.summarizingInt(Movie::getLikes) );
        //movies.stream()

        System.out.println( summary );

        //summarizingLong() and summarizingDouble() also exist.

        //another kind of collector is the joiner
        //(also called concatenator or combiner)
        //which lets us assemble some string
        //which contains the resulting values from our stream
        //separated by some delimiter string
        //(in this case, a comma-separated string):

        var concatenation = movies.stream()
            .filter( m -> m.getLikes() > 10 )
            .map( Movie::getTitle ) //necessary since joining() expects a stream of strings
            .collect( Collectors.joining(", ") );
        //movies.stream()

        System.out.println( concatenation );

        //just keep in mind that the joining() method
        //expects a stream of strings
        //hence the mapping to each movie's title done before.

        System.out.println("\n----------------------------------------");
        System.out.println("Groupings\n");

        //another kind of Collector we can use is a grouping.
        //a grouping is a classification of the stream data into groups.
        //keep in mind: a grouping is a kind of key-value mapping.

        //we do groupings via the Collectors.groupingBy() method.

        //it takes a Function lambda which returns the value
        //each item will be grouped by.
        //this Function is called the "classifier"

        //in this case, we'll group movies by genre,
        //so our lambda will simply be Movies::getGenre.

        //then, every genre will become a group for the movies to be grouped by.
        //in our case, the groups are ACTION, THRILLER and COMEDY.

        //grouping is mapping. therefore,
        //the resulting collection will be a Map,
        //where each item will have the group as key
        //and a List of those items grouped by it as value:

        var grouped_to_list = movies.stream()
            .collect( Collectors.groupingBy(Movie::getGenre) );
        //movies.stream()

        System.out.println(grouped_to_list);

        //we got a Map that maps from Genre to List<Movie>.
        //which is the default behavior.
        //however, we can specify the desired result collection
        //by using an overload of the groupingBy() method:

        //in this overload, we specify the classifier just like before
        //but also we specify a simple Collector (toList(), toSet() or toMap()).
        //let's demonstrate with toSet():

        var grouped_to_set = movies.stream()
            .collect(Collectors.groupingBy(
                Movie::getGenre,
                Collectors.toSet()
            ));
        //movies.stream()

        System.out.println(grouped_to_set);

        //as you can see, the result was a Map that maps
        //from Genre to Set<Movie>.

        //a simpler thing we might want is to count
        //how many movies are in each group.
        //we can do this with Collectors.counting()
        //which can also be passed to groupingBy():

        var grouped_to_count = movies.stream()
            .collect(Collectors.groupingBy(
                Movie::getGenre,
                Collectors.counting()
            ));
        //movies.stream()

        System.out.println(grouped_to_count);

        //here's a more complicated scenario
        //let's say we want as result
        //a Map from Genre to a string containing the movie titles by the genre

        //we already know we can use the joining() method for that
        //but we don't have the necessary stream of strings.

        //luckily, there's a new Collector for that,
        //called by Collectors.mapping().

        //mapping() is essentially a way to do nested Collectors.
        //or it can also be understood as mapping one Collector to another
        //with some intermediate steps.

        //in this case, we'll do the intermediate step of producing a string value
        //for every item in the stream, giving us the string stream required
        //to call the joining() method.
        //mapping() returns a new Collector.

        //the args of the mapping() method are:
        //a Function returning the desired string as first arg (the intermediate step),
        //and a joining Collector as the second arg (the values we want in our resulting Collector):

        //here we demonstrate the use of two nested Collectors:
        //first we run a grouping Collector like usual (this is the outer Collector, which will produce a final resulting mapping),
        //inside of which we group our movies by Genre (the key of the final resulting mapping)
        //then, we map every Genre to a second Collector (the inner Collector),
        //inside of which, we get the title (a string) of each movie (the intermediate step)
        //and finally, we map every movie title into a comma-separated string (the value of the final resulting mapping):

        var grouped_to_string = movies.stream()
            .collect(Collectors.groupingBy(
                Movie::getGenre,
                Collectors.mapping(
                    Movie::getTitle,
                    Collectors.joining(",")
                )
            ));
        //movies.stream()

        System.out.println(grouped_to_string);

        //so the result is a Map which maps
        //from Genre to String.

        System.out.println("\n----------------------------------------");
        System.out.println("Partitions\n");

        //partitionings are another kind of Collectors
        //very similar to groupings.
        //they also produce a key-value mapping as a final result.

        //while groupings create a new group for every distinct classifier value
        //(for example, every distinct movie genre)
        //partitionings always create exactly two partitions
        //based on some boolean condition:
        //one partition holds the stream items that satisfy the condition
        //and the other one holds those that don't.

        //this means that partitionings take some Predicate lambda as arg.
        //we do partitionings via the Collectors.partitioningBy() method:

        //in this example, we partition our movies into:
        //those with 10 likes or less, and those with more:

        var partitioned_to_list = movies.stream()
            .collect(Collectors.partitioningBy(m -> m.getLikes() > 10));
        //movies.stream()

        System.out.println(partitioned_to_list);

        //by default, the partitioningBy() method
        //produces a Map from boolean to List<Movie>

        //but just like with groupings,
        //there's an overload for specifying the desired collection,
        //for example, here we return a Map from boolean to Set<Movie>:

        var partitioned_to_set = movies.stream()
            .collect(Collectors.partitioningBy(
                m -> m.getLikes() > 10,
                Collectors.toSet()
            ));
        //movies.stream()

        System.out.println(partitioned_to_set);

        //finally, we'll do a partition resulting in a comma-separated string
        //in a similar way to what we did before:

        var partitioned_to_string = movies.stream()
            .collect(Collectors.partitioningBy(
                m -> m.getLikes() > 10,
                Collectors.mapping(
                    Movie::getTitle,
                    Collectors.joining(",")
                )
            ));
        //movies.stream()

        System.out.println(partitioned_to_string);

        //final heads-up:
        //every time we pass a second Collector as arg
        //just to specify the desired kind of resulting mapping value
        //we call it the "downstream" Collector.

        System.out.println("\n----------------------------------------");
        System.out.println("Primitive Streams\n");

        //just like with functional interfaces,
        //streams too have alternate versions for working with primitive values
        //avoiding the performance penalty of auto-boxing.

        //in Java there are the built-in IntStream, LongStream and DoubleStream classes.

        //most methods of those work in the same general way as in vanilla Stream's
        //so all the techniques from this section apply, no problem.

        //however, there are two additional methods we can use
        //to generate a Stream from scratch: range() and rangeClosed()
        //which generate a number sequence from a lower bound to an upper bound
        //(with the only difference being that the upper bound is only inclusive for rangeClosed())

        //here we demonstrate with 1 as the lower bound and 5 as the upper bound:

        IntStream.range(1,5).forEach(System.out::print);
        System.out.println();
        IntStream.rangeClosed(1,5).forEach(System.out::print);

        //range() and rangeClosed() are not available for DoubleStream.

        System.out.println();

        //other methods of primitive streams include
        //average(), sum(), max(), min(), etc.

        //some methods like average() return an Optional
        //however, Optional's have primitive forms as well.
        //here we do the average of a DoubleStream and get an OptionalDouble:

        OptionalDouble avg = DoubleStream.of(91, 84, 87, 95).average();
        System.out.println( avg.orElse(0) );
    }
    //runStreamsExamples
}
//StreamsExamples

//eof
