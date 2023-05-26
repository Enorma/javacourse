package com.javacourse.collections;

/*
    //wrapper class for Course 3 Section 4: Collections

    //import to Main class with:
    import com.javacourse.collections.CollectionsExample; //or...
    import com.javacourse.collections.*;

    //call from main() method with:
    com.javacourse.collections.CollectionsExample.collectionsWrapper(); //or...
    CollectionsExample.collectionsWrapper();
*/

import java.util.*;
import java.util.function.Consumer;

public class CollectionsExample {

    public static <E> void printPQueue(PriorityQueue<E> pq) {

        //custom method with manual iteration since
        //the PriorityQueue class's built-in toString()
        //actually returns the queue's elements
        //in a RANDOM, unpredictable order.

        //that's because, internally,
        //PriorityQueue doesn't store its elements
        //in a linear array-like structure
        //but in a heap!
        //and there's no ordered traversal algorithm for heaps!

        //we remove each element after printing it
        //so we need a disposable clone
        //or we'd clear the original queue

        //instantiate a PriorityQueue
        //passing another PriorityQueue to its constructor
        //to create a clone of it
        var clone_pq = new PriorityQueue<E>(pq);

        System.out.print("[");
        while( !clone_pq.isEmpty() ) {
            System.out.print( clone_pq.poll() );
            if( !clone_pq.isEmpty() ) System.out.print(", ");
        }
        System.out.println("]");
    }
    //printPQueue

    public static void printMap(Map<String,Customer> m) {

        var mstr = m.toString();
        mstr = mstr.replaceAll("\\{", "{\n    ");
        mstr = mstr.replaceAll("}", "\n}");
        mstr = mstr.replaceAll("], ", "],\n    ");
        mstr = mstr.replaceAll("=\\[", ":{\n        ");
        mstr = mstr.replaceAll(",", ",\n        ");
        mstr = mstr.replaceAll("]", "\n    }");
        mstr = mstr.replaceAll("\\n\s+\\n", "\n");
        mstr = mstr.replaceAll(":", " : ");

        System.out.println(mstr);
    }
    //printMap

    public static void printCustomer(Customer c) {

        if(c==null) {
            System.out.println("null");
            return;
        }
        //if

        var cstr = c.toString();

        cstr = cstr.replaceAll("\\[", "{\n    ");
        cstr = cstr.replaceAll("]", "\n}");
        cstr = cstr.replaceAll(",", ",\n    ");
        cstr = cstr.replaceAll(":", " : ");

        System.out.println(cstr);
    }
    //printCustomer

    public static void collectionsWrapper() {

        System.out.println("\n----------------------------------------");
        System.out.println("The collections hierarchy\n");

        //the Iterable interface is the parent of all,
        //it enables iterators and the foreach loop

        //the Collection interface inherits from the Iterable interface,
        //it declares operations like add(), remove(), clear(), contains(), size(), etc
        //but it doesn't support indexing

        //the List, Queue and Set interfaces inherit from the Collection interface

        //the List interface arranges the collection elements
        //into an ordered and indexed sequence,
        //it allows us to insert, read and delete an element by index

        //the Queue interface arranges the collection elements
        //into an ordered sequence with a FIFO behavior,
        //it allows us to enqueue and dequeue elements
        //and peek at the element at the front of the queue

        //the Set interface arranges the collection elements
        //into an unordered group without duplicates,
        //it allows us to do set operations like union, intersection, etc

        //These are all generic, so they're implemented like:
        //Iterable<E>, Collection<E>, List<E>, Queue<E>, Set<E>

        //from now on, the term "collection class" or "collection object"
        //may refer to a class coded by us that
        //implements the Iterable or Collection interface
        //and/or is planned to have collection-like properties and behavior

        System.out.println("\n----------------------------------------");
        System.out.println("The need for collections\n");

        //why don't we just use arrays?

        //simply put, we need to be able to operate with a collection of objects
        //from outside the class it was implemented in
        //knowing the operations that are possible with it
        //but not knowing the actual internal implementation of them

        //in other words, we need to create collections that can be loosely coupled.

        System.out.println("\n----------------------------------------");
        System.out.println("The Iterable and Iterator interfaces\n");

        //Let's not confuse the Iterable and Iterator interfaces

        //the Iterable interface is to be implemented by
        //collection classes which should generate their own iterator
        //it provides a method for generating said iterator

        //the Iterator interface provides methods
        //to traverse the collection that generated it

        //here's how we can traverse a collection
        //(in this case, a GenericList)
        //using an iterator:

        //we instantiate a new list:
        var list = new GenericList<String>();

        //we populate it:
        list.add("hola");
        list.add("mundo");
        list.add("qué");
        list.add("pedo");
        list.add("roflmfaol");

        //now, to create the iterator
        //we call the iterator() method of the GenericList class
        //which returns an object of the class ListIterator (which we coded ourselves)
        //which implements the Iterator interface
        var iterator = list.iterator();

        //notice we created the iterator BEFORE adding these two elements
        list.add("omfg");
        list.add("wtfbbq1");
        //it doesn't matter! the iterator can access them no problem!
        //it means the iterator has access to the GenericList by REFERENCE

        //if our class GenericList wasn't actually iterable
        //both loops below would crash

        //let's traverse the list using the iterator:
        while (iterator.hasNext()) {

            System.out.println(iterator.next());
        }
        //while

        System.out.println();

        //now, let's traverse the list using a foreach loop:
        for (String s : list) {

            System.out.println(s);
        }
        //foreach

        //the foreach loop is just syntax sugar over the iterator mechanism
        //so both of the loops above are identical at the bytecode level

        System.out.println("\n----------------------------------------");
        System.out.println("The Collection interface\n");

        //a collection has methods like add(), remove() and contains()

        //we create an ArrayList<String> object
        //and store it in a Collection<String> variable
        //even though Collection is an interface and ArrayList is a class
        //we can do this because ArrayList implements Collection
        Collection<String> my_collection = new ArrayList<>();

        //we can populate it via the add() method
        //which is declared in Collection and overriden in ArrayList
        my_collection.add("a");
        my_collection.add("b");
        my_collection.add("c");
        my_collection.add("d");

        System.out.println("\nforeach loop:");
        //we can iterate through it with a foreach
        //because Collection extends Iterable
        for (var item : my_collection) {
            System.out.println(item);
        }
        //foreach

        System.out.println("\nprint whole collection:");
        //we can also print the whole thing,
        //because ArrayList overrides its toString() method
        System.out.println(my_collection);

        System.out.println("\nadd all:");
        //there is a different class called Collections
        //(in plural, since it's a utility class)
        //it has a method addAll() which takes a collection
        //and one or more items
        //and adds all those items to the collection.
        //notice, the second argument of addAll() is called "...elements"
        //the "..." syntax means it can take one or more values
        //of the same type (in this case, String)
        Collections.addAll(my_collection, "e", "f", "g");
        System.out.println(my_collection);

        System.out.println("\nget size:");
        //we can get the amount of elements inside the collection with size()
        System.out.println("our collection has " + my_collection.size() + " elements");

        System.out.println("\nremove items:");
        //remove an item with the remove() method
        //(removes the first occurrence of the item, searching from left to right)
        my_collection.add("b"); //since there's another b, this one is safe
        System.out.println(my_collection);
        my_collection.remove("b"); //the first b will be deleted
        System.out.println(my_collection);
        //(every subsequent item is shifted one place to the left)

        //remove() doesn't crash if it can't find an item:
        my_collection.remove("z");

        //also, remove() just deletes an item,
        //it does NOT read it nor return it!

        //in fact, Collection does NOT provide
        //any way of retrieving an item!

        System.out.println("\nsearch for items:");
        //we can search for an item within the collection with contains()
        //this just returns if it was found or not:
        System.out.println(my_collection.contains("a"));
        System.out.println(my_collection.contains("z"));

        System.out.println("\nconvert to array:");
        //we can convert a collection to a regular array with toArray()
        //however, this can be tricky:

        //if all we want is a plain Object array, we do:
        var obj_arr = my_collection.toArray();
        //in this case, even though we had a collection of strings,
        //we get an array of objects.

        //the method Arrays.toString() is useful for printing plain arrays
        System.out.println(Arrays.toString(obj_arr));

        //if we want to keep the original datatype,
        //first we create a string array of size zero
        //(that's because toArray() cares only about the datatype, not the size)
        String[] str_arr1 = new String[0];

        //then, we call a different overload of toArray(),
        //pass our new array to it (so that it knows the datatype),
        //and store the result into some other string array variable
        String[] str_arr2 = my_collection.toArray(str_arr1);
        System.out.println(Arrays.toString(str_arr2));

        //of course, we could do all that in a single step:
        var str_arr = my_collection.toArray(new String[0]);
        System.out.println(Arrays.toString(str_arr));

        System.out.println("\nclone the collection:");
        //we can copy every element in our collection
        //into a new collection:

        //first, create a new collection:
        Collection<String> other_collection = new ArrayList<>();
        System.out.println(other_collection);

        //before we used the addAll() method from the Collections class
        //now, we'll use the addAll() method from the Collection interface
        //which takes another collection as its argument
        other_collection.addAll(my_collection);
        System.out.println(other_collection);

        System.out.println("\ncompare collections:");
        //now, we can check if both collections contain the same elements:

        //the equals operator compares by memory address, so in this case it's false:
        System.out.println(my_collection == other_collection);

        //the equals() method is overridden by the ArrayList class
        //so now it returns true because both collections have the same elements:
        System.out.println(my_collection.equals(other_collection));

        //let's make other_collection have the same elements as my_collection
        //but in a different order, then compare them:
        //we'll see that the equals() method actually returns true if and only if
        //both collections have the same elements in the same order:
        other_collection.clear();
        Collections.addAll(other_collection, "c", "a", "d", "e", "f", "g", "b");
        System.out.println(my_collection.equals(other_collection));

        //IMPORTANT NOTE:

        //in the last step we simply swapped the positions of the first two items ("a" and "c")
        //but that was a convoluted way to do it.

        //we might think that both collections are in fact ArrayList's
        //so they should provide an easier way to do it (and in fact, they do)
        //but remember: both of our collections are of the Collection datatype at compile-time
        //and of the ArrayList datatype at runtime.
        //this happened because we stored both ArrayList's (polymorphically)
        //in Collection-type variables.

        //so we can't, at compile-time, access the ArrayList-exclusive functionality (like indexing),
        //but only the overridden methods from the Collection interface
        //(plus the methods inherited from Object, like toString() or equals(),
        //since those are available anywhere).

        //so it might seem like a bad idea to store objects in interface-type variables
        //but remember, using interfaces is a good practice
        //which leads to loose coupling and compile-time type safety.

        System.out.println("\nclear the array:");
        //we can remove all items with clear()
        my_collection.clear();
        System.out.println(my_collection);

        System.out.println("\ncheck for emptiness:");
        //we can check if a collection is empty with isEmpty()
        System.out.println(my_collection.isEmpty());

        System.out.println("\n----------------------------------------");
        System.out.println("The List interface\n");

        System.out.println("\ncreate and populate a new List:");
        //we can also create an ArrayList and store it in a List variable
        //because ArrayList implements List:
        List<String> my_list = new ArrayList<>();

        //we still have access to all methods from the Collection interface
        //because List inherits from Collection
        my_list.add("a"); //list is empty, appends a at the end (index 0)
        my_list.add("b"); //appends b at the end (index 1)
        my_list.add("c"); //appends c at the end (index 2)
        //notice that add() just appends a new item at the end of the Collection

        //we still can use the addAll() method from Collections:
        Collections.addAll(my_list, "d", "e", "f"); //appends d,e,f at the end (indexes 3,4,5)
        System.out.println(my_list);

        System.out.println("\nadd by index:");
        //BUT we also have new functionality
        //for example, List introduces a new overload for the add() method
        //which lets us add at some index:
        my_list.add(0, "z"); //forcefully adds z at index 0
        //(every subsequent item is index-shifted one place to the right)
        System.out.println(my_list);

        System.out.println("\nretrieve by index:");
        //now we can actually retrieve and read an item
        //using the get() method, which takes an index:
        System.out.println(my_list.get(3));

        System.out.println("\nreplace by index:");
        //we can also replace (or overwrite)
        //the item at some index,
        //with another item of the same type
        //using the set() method, which takes an index and an item
        my_list.set(3, "k");
        System.out.println(my_list);

        System.out.println("\nremove by index:");
        //we can still delete the first occurrence of an item
        //like in the Collection interface
        my_list.remove("e"); //removes e
        System.out.println(my_list);

        //but now we have a new overload for remove()
        //which lets us delete the item at some index:
        my_list.remove(2); //removes b
        System.out.println(my_list);

        System.out.println("\nsearch for an item:");
        //let's add another a for the next examples:
        my_list.add("a"); //appends a at the end
        System.out.println(my_list);

        //we can still search for the first occurrence of an item
        //like in the Collection interface
        System.out.println(my_list.contains("a"));

        //but now we have a new method, indexOf()
        //which lets us find the first occurrence of an item
        //and return its index:
        System.out.println(my_list.indexOf("a"));

        //we also have lastIndexOf(),
        //which is similar, but searches from right to left:
        System.out.println(my_list.lastIndexOf("a"));

        //both of the above return -1 when the item is not found:
        System.out.println(my_list.indexOf("y"));
        System.out.println(my_list.lastIndexOf("y"));

        System.out.println("\nget a sublist:");
        //we can also get a portion of a list
        //with the subList() method
        //which takes a start index (inclusive)
        //and an end index (exclusive):
        var portion = my_list.subList(1, 4); //this will return [a,k,d]
        System.out.println(portion);

        //subList returns a new list, so the original is not affected:
        System.out.println(my_list);

        System.out.println("\n----------------------------------------");
        System.out.println("The Comparable and Comparator interfaces\n");

        //for these examples, we created a Customer class
        //and had it override the toString() method.

        //we create a List of Customer's and populate it:
        List<Customer> customer_list = new ArrayList<>();
        customer_list.add(new Customer("b", "z@mail.com"));
        customer_list.add(new Customer("a", "x@mail.com"));
        customer_list.add(new Customer("c", "y@mail.com"));

        //at this point, our list is NOT sorted alphabetically
        System.out.println(customer_list);

        //an important fact:
        //the most common sorting algorithms do comparisons
        //so, they sort comparable things

        //let's assume we should be able to compare Customer's by name

        //the Collections class provides a method for sorting a List
        //but it needs to be a List of some class that implements the Comparable interface
        //at this point, our Customer class does not implement it
        //hence the following compile-time error:
        Collections.sort(customer_list); //this crashed before implementing Comparable

        //now, our list is sorted alphabetically
        System.out.println(customer_list);

        //comparing Customer's by name works fine
        //but now the compareTo() method is limited to comparing by name
        //what if we want to compare by some other criteria?

        //let's assume we should be able to compare Customer's by email
        //we can either re-implement the compareTo() method we just coded
        //and lose the ability to compare by name...

        //or we can keep the comparison by name as a default criteria
        //and have separate custom comparators for other criteria, like by email:
        var email_comp = new EmailComparator();

        //now, our custom comparator implements the compare method
        //which takes two objects of the Customer class
        //and compares them by email:
        Collections.sort(customer_list, email_comp); //overloaded sort() from Collections

        //now, our list is sorted by email
        System.out.println(customer_list);

        //for this next example,
        //let's go back to the list sorted by name
        Collections.sort(customer_list);
        //now, our list is sorted by name
        System.out.println(customer_list);

        //there's an alternate way to sort by email
        //now that we have our email comparator,
        //every List object has a sort() method
        //which only takes a custom comparator
        customer_list.sort(email_comp);

        //now, our list is sorted by email
        System.out.println(customer_list);

        System.out.println("\n----------------------------------------");
        System.out.println("The Queue interface and the ArrayDeque class\n");

        //the most common classes that implement the Queue interface
        //are PriorityQueue and ArrayDeque

        //the ArrayDeque class implements the Deque interface
        //which in turn implements the Queue interface.
        //there used to be a Stack class, but it is deprecated.

        //Deque's behavior is a hybrid of FIFO and LIFO
        //it doesn't support indexing and any read/write/delete operations
        //can only happen at the ends of the Deque.

        //in this lesson we'll instantiate an ArrayDeque
        //but we'll only test methods of the Queue interface.

        //we create a new ArrayDeque and populate it
        Queue<String> my_queue = new ArrayDeque<>();

        //the ArrayDeque is dynamic which means
        //it's size is unlimited
        //so we can't crash it at runtime by filling it up
        //and adding more to it.
        //it's gonna work just fine.

        //add() would crash in that scenario
        //if the queue's size was limited
        //and it was already full
        //when we attempted to add to it
        my_queue.add("c");
        my_queue.add("a");
        my_queue.add("b");

        //offer() does the same as add()
        //but it doesn't crash in said scenario
        //it just returns true/false depending on
        //if it was possible to add the item or not
        my_queue.offer("d");
        my_queue.offer("f");
        my_queue.offer("e");

        //check the order in which the elements are:
        System.out.println(my_queue); //it's the order in which they were added

        //use or comment this to test the element() method:
        //my_queue.clear();

        //we can check the item at the front of the queue
        //without extracting it from the queue
        //using the element() method:
        System.out.println(my_queue.element());

        //use or comment this to test the peek() method:
        //my_queue.clear();

        //however, if the queue is empty, element() crashes at runtime
        //to prevent this, there's the peek() method
        //which returns null if the queue is empty:
        System.out.println(my_queue.peek());

        //use or comment this to test the remove() method:
        //my_queue.clear();

        //queues typically let us remove only the item at the front
        //and also they let us check it, all in a single step
        //this is done with the remove() method:
        System.out.println(my_queue); //before removal
        System.out.println(my_queue.remove()); //removal
        System.out.println(my_queue); //after removal

        //use or comment this to test the poll() method:
        //my_queue.clear();

        //in a similar way, if the queue is empty, remove() crashes at runtime
        //to prevent this, there's the poll() method
        //which returns null if the queue is empty:
        System.out.println(my_queue); //before removal
        System.out.println(my_queue.poll());
        System.out.println(my_queue); //after removal

        System.out.println("\n----------------------------------------");
        System.out.println("The PriorityQueue class\n");

        //the PriorityQueue class
        //is another built-in implementation of the Queue interface

        //it stores elements of one same datatype
        //and it will auto-order them by their default natural ordering
        //or by some provided Comparator.compare() method

        //the "natural ordering" of any datatype is any logic that,
        //for any two items of the same type,
        //can determine which one is bigger
        //or if both are equal.

        //so... a PriorityQueue DOES NOT behave like
        //a traditional FIFO.
        //there is no such thing as "insertion order"
        //built into it.

        //in this case we'll do a PriorityQueue of String's
        //using its default ordering: alphabetical

        var my_pqueue = new PriorityQueue<String>();

        //notice how, after every insertion,
        //the queue auto-sorts itself z-a:
        printPQueue(my_pqueue);
        my_pqueue.add("g");
        printPQueue(my_pqueue);
        my_pqueue.add("r");
        printPQueue(my_pqueue);
        my_pqueue.add("e");
        printPQueue(my_pqueue);
        my_pqueue.add("x");
        printPQueue(my_pqueue);
        my_pqueue.add("y");
        printPQueue(my_pqueue);
        my_pqueue.add("b");
        printPQueue(my_pqueue);
        my_pqueue.add("w");
        printPQueue(my_pqueue);

        System.out.println();

        //now let's make one with a custom ordering
        //by providing a comparator method
        //in this case, a reverse alphabetical ordering:

        var reverse_pqueue = new PriorityQueue<String>(Collections.reverseOrder());

        //notice how, after every insertion,
        //the queue auto-sorts itself a-z:
        printPQueue(reverse_pqueue);
        reverse_pqueue.add("g");
        printPQueue(reverse_pqueue);
        reverse_pqueue.add("r");
        printPQueue(reverse_pqueue);
        reverse_pqueue.add("e");
        printPQueue(reverse_pqueue);
        reverse_pqueue.add("x");
        printPQueue(reverse_pqueue);
        reverse_pqueue.add("y");
        printPQueue(reverse_pqueue);
        reverse_pqueue.add("b");
        printPQueue(reverse_pqueue);
        reverse_pqueue.add("w");
        printPQueue(reverse_pqueue);

        //the following methods also work in the PriorityQueue class:
        //add(), offer(), poll(), peek(), clear(), contains(), size(), toArray()

        System.out.println("\n----------------------------------------");
        System.out.println("Our own generic Queue and Stack classes\n");

        //these insert all items from a null-free collection and return true
        //or attempt to insert all items from a collection with nulls and return false
        ////// GenericQueue.safeEnqueueAll(Collection<E> _items)
        ////// GenericStack.safePushAll(Collection<E> _items)

        //these insert all items from a null-free collection
        //or attempt to insert all items from a collection with nulls and crash at runtime
        ////// GenericQueue.unsafeEnqueueAll(Collection<E> _items)
        ////// GenericStack.unsafePushAll(Collection<E> _items)

        //these insert a non-null and return true
        //or attempt to insert a null and return false:
        ////// GenericQueue.safeEnqueue(E item)
        ////// GenericStack.safePush(E item)

        //these insert a non-null
        //or attempt to insert a null and crash at runtime:
        ////// GenericQueue.unsafeEnqueue(E item)
        ////// GenericStack.unsafePush(E item)

        //these read and remove an item from a non-empty queue/stack and return said item
        //or attempt to do so from an empty queue/stack and return null
        ////// GenericQueue.safeDequeue()
        ////// GenericStack.safePop()

        //these read and remove an item from a non-empty queue/stack and return said item
        //or attempt to do so from an empty queue/stack and crash at runtime
        ////// GenericQueue.unsafeDequeue()
        ////// GenericStack.unsafePop()

        //these read an item from a non-empty queue/stack and return said item
        //or attempt to do so from an empty queue/stack and return null
        ////// GenericQueue.safeFront()
        ////// GenericStack.safePeek()

        //these read an item from a non-empty queue/stack and return said item
        //or attempt to do so from an empty queue/stack and crash at runtime
        ////// GenericQueue.unsafeFront()
        ////// GenericStack.unsafePeek()

        var my_custom_queue = new GenericQueue<String>();
        var my_custom_stack = new GenericStack<String>();

        //queue demo:
        System.out.println("QUEUE:");
        System.out.println("<-- " + my_custom_queue + " <--");

        //queue insertions:
        my_custom_queue.safeEnqueue("a");
        System.out.println("<-- " + my_custom_queue + " <--");
        my_custom_queue.safeEnqueue("b");
        System.out.println("<-- " + my_custom_queue + " <--");
        my_custom_queue.safeEnqueue("c");
        System.out.println("<-- " + my_custom_queue + " <--");
        my_custom_queue.safeEnqueue("d");
        System.out.println("<-- " + my_custom_queue + " <--");

        //queue removals:
        my_custom_queue.safeDequeue();
        System.out.println("<-- " + my_custom_queue + " <--");
        my_custom_queue.safeDequeue();
        System.out.println("<-- " + my_custom_queue + " <--");
        my_custom_queue.safeDequeue();
        System.out.println("<-- " + my_custom_queue + " <--");
        my_custom_queue.safeDequeue();
        System.out.println("<-- " + my_custom_queue + " <--");

        System.out.println();

        //stack demo:
        System.out.println("STACK:");
        System.out.println("<--> " + my_custom_stack);

        //stack insertions:
        my_custom_stack.safePush("a");
        System.out.println("<--> " + my_custom_stack);
        my_custom_stack.safePush("b");
        System.out.println("<--> " + my_custom_stack);
        my_custom_stack.safePush("c");
        System.out.println("<--> " + my_custom_stack);
        my_custom_stack.safePush("d");
        System.out.println("<--> " + my_custom_stack);

        //stack removals:
        my_custom_stack.safePop();
        System.out.println("<--> " + my_custom_stack);
        my_custom_stack.safePop();
        System.out.println("<--> " + my_custom_stack);
        my_custom_stack.safePop();
        System.out.println("<--> " + my_custom_stack);
        my_custom_stack.safePop();
        System.out.println("<--> " + my_custom_stack);

        System.out.println();

        //other methods available in both
        //GenericQueue and GenericStack:
        //toString(), size(), isEmpty(), clear(), contains()

        //for the following examples we'll repopulate the collections:
        my_custom_queue.safeEnqueueAll( Arrays.asList("w","x","y","z") );
        my_custom_stack.safePushAll( Arrays.asList("w","x","y","z") );

        //Transform both structures into plain arrays:

        //to Object array using toArray():
        var obj_queue_arr = my_custom_queue.toArray();
        var obj_stack_arr = my_custom_stack.toArray();

        //to String array using toArray(E[] dummy_array):
        //we need to provide a dummy array (meaning, an array of length zero)
        //of the same generic datatype of our GenericQueue<E> and GenericStack<E>
        //in this case <String>
        var str_queue_arr = my_custom_queue.toArray(new String[0]);
        var str_stack_arr = my_custom_stack.toArray(new String[0]);

        //print all four freshly-made arrays:
        System.out.println( "queue as object array: " + Arrays.toString(obj_queue_arr) );
        System.out.println( "queue as string array: " + Arrays.toString(str_queue_arr) );
        System.out.println( "stack as object array: " + Arrays.toString(obj_stack_arr) );
        System.out.println( "stack as string array: " + Arrays.toString(str_stack_arr) );

        //empty the queue and the stack
        my_custom_queue.clear();
        my_custom_stack.clear();

        System.out.println();

        //for the following examples we'll repopulate the collections:
        my_custom_queue.safeEnqueueAll( Arrays.asList("i","j","k","l") );
        my_custom_stack.safePushAll( Arrays.asList("i","j","k","l") );

        //We can clone a GenericQueue or GenericStack
        //using the clone() method
        //however, keep in mind they return a GenericDeque...
        var cloned_queue_deque = my_custom_queue.clone();
        var cloned_stack_deque = my_custom_stack.clone();

        //since those are GenericDeque's at compile time
        //we can't access the basic methods of queues/stacks,
        //so these clones are useless...
        //cloned_queue_deque.safeEnqueue("m"); //compile-time error!
        //cloned_stack_deque.safePush("m"); //compile-time error!

        //UNLESS!! we cast them into their actual types!
        GenericQueue<String> cloned_queue = (GenericQueue<String>)cloned_queue_deque;
        GenericStack<String> cloned_stack = (GenericStack<String>)cloned_stack_deque;

        //now these work as expected!
        cloned_queue.safeEnqueue("m");
        cloned_stack.safePush("m");

        System.out.println( "original queue: " + my_custom_queue );
        System.out.println( "cloned queue:   " + cloned_queue );
        System.out.println( "original stack: " + my_custom_stack );
        System.out.println( "cloned stack:   " + cloned_stack );

        System.out.println();

        //both the queue and stack support iterators
        //we'll test them manually and with the foreach loop
        //notice how both operate on the proper end of the queue or stack
        //and they do remove items as they traverse it

        var queue_iterator = my_custom_queue.iterator();
        var stack_iterator = my_custom_stack.iterator();

        //test the queue iterator manually:
        System.out.print("queue iterator: [");
        while(queue_iterator.hasNext()) {
            System.out.print(queue_iterator.next());
        }
        //while

        //the queue is empty afterwards, so let's refill it
        System.out.println( "] queue: " + my_custom_queue );
        my_custom_queue.safeEnqueueAll( Arrays.asList("i","j","k","l") );

        //test the queue iterator via foreach loop:
        System.out.print("queue foreach:  [");
        for(var item : my_custom_queue) {
            System.out.print(item);
        }
        //for

        //the queue is empty afterwards
        System.out.println( "] queue: " + my_custom_queue );

        //test the stack iterator manually:
        System.out.print("stack iterator: [");
        while(stack_iterator.hasNext()) {
            System.out.print(stack_iterator.next());
        }
        //while

        //the stack is empty afterwards, so let's refill it
        System.out.println( "] stack: " + my_custom_stack );
        my_custom_stack.safePushAll( Arrays.asList("i","j","k","l") );

        //test the stack iterator via foreach loop:
        System.out.print("stack foreach:  [");
        for(var item : my_custom_stack) {
            System.out.print(item);
        }
        //for

        //the stack is empty afterwards
        System.out.println( "] stack: " + my_custom_stack );

        //at this point we might wanna learn what's a spliterator

        System.out.println();

        //for the following examples we'll repopulate the collections:
        my_custom_queue.safeEnqueueAll( Arrays.asList("r","s","t","u") );
        my_custom_stack.safePushAll( Arrays.asList("r","s","t","u") );

        //WARNING: this next step is explained in the NEXT section: Lambdas

        //using functional interfaces we get access to the forEach() method
        //(notice in this case forEach() is a METHOD, not a loop!)

        //first we declare some variable of the Consumer<E> interface
        //whose generic datatype is the same as in our GenericQueue<E> and GenericStack<E>
        //in this case, we'll define a very simple consumer that just prints a value
        //the shorthand syntax for it is:

        Consumer<String> printer = System.out::print;

        //at this point, printer is a Consumer,
        //but it can be understood as a function
        //that takes a value and prints it.

        //the forEach() method takes a consumer,
        //in this case, by passing printer into forEach()
        //every item in the collection will be printed:

        System.out.print("queue forEach method: ");
        my_custom_queue.forEach(printer);
        System.out.println();

        System.out.print("stack forEach method: ");
        my_custom_stack.forEach(printer);
        System.out.println();

        System.out.println("\n----------------------------------------");
        System.out.println("The Set interface and the HashSet class\n");

        //A set is a collection without a guaranteed order
        //and without duplicate elements

        //the HashSet class implements the Set interface
        Set<String> some_set = new HashSet<>();

        //populate the set
        some_set.add("a");
        some_set.add("n");
        some_set.add("x");
        some_set.add("n");
        some_set.add("a");

        //notice the random order and lack of duplicates
        System.out.println(some_set);

        System.out.println();

        //using a set to remove duplicates from another collection:

        //given some collection:
        Collection<String> some_list = new ArrayList<>();
        Collections.addAll(some_list, "a", "n", "x", "n", "a");
        System.out.println(some_list);

        //create a set and populate it with the items in said collection
        Set<String> list_without_dupes = new HashSet<>(some_list);
        System.out.println(list_without_dupes);

        //return it to the collection variable:
        //now it is an ArrayList again, without duplicates
        //but the order was not preserved
        some_list = new ArrayList<>(list_without_dupes);
        System.out.println(some_list);

        System.out.println("\n----------------------------------------");
        System.out.println("Our own GenericSet class\n");

        //Here we'll use our own GenericSet class
        //which uses a HashSet internally
        //but it has the set operations ready to use

        //create a GenericSet from a variable amount of arguments:
        var set1 = new GenericSet<String>( Arrays.asList("a", "b", "c") );
        var set2 = new GenericSet<String>( Arrays.asList("d", "b", "c") );

        //toString() works...
        System.out.println("Original sets:");
        System.out.println("set1: " + set1);
        System.out.println("set2: " + set2);

        //add() and addAll() work...
        set1.add("z");
        set2.addAll( Arrays.asList("y", "x") );

        System.out.println("\nafter add() and addAll():");
        System.out.println("set1: " + set1);
        System.out.println("set2: " + set2);

        //remove() and removeAll() work...
        set1.remove("z");
        set2.removeAll( Arrays.asList("y", "x") );

        System.out.println("\nafter remove() and removeAll():");
        System.out.println("set1: " + set1);
        System.out.println("set2: " + set2);

        //contains() and containsAll() work...
        System.out.println( "\nset1 contains a? " + set1.contains("a") );
        System.out.println( "set1 contains z? " + set1.contains("z") );
        System.out.println( "set2 contains b,c? " + set2.containsAll( Arrays.asList("b", "c") ) );
        System.out.println( "set2 contains b,x? " + set2.containsAll( Arrays.asList("b", "x") ) );

        //retainAll() works...
        set1.retainAll( Arrays.asList("b", "c") );

        System.out.println("\nafter retainAll():");
        System.out.println("set1: " + set1);
        System.out.println("set2: " + set2);

        //size() works...
        System.out.println( "\nsize of set1 is " + set1.size() );
        System.out.println( "size of set2 is " + set2.size() );

        //clear() and isEmpty() work...
        set1.clear();

        System.out.println("\nafter clear():");
        System.out.println("set1: " + set1);
        System.out.println("set2: " + set2);

        System.out.println( "\nset1 is empty? " + set1.isEmpty() );
        System.out.println( "set2 is empty? " + set2.isEmpty() );

        System.out.println();

        //repopulate set1 for the next examples:
        set1.addAll( Arrays.asList("a", "b", "c") );

        //both overloads of toArray() work...
        var set_to_obj_arr = set1.toArray();
        System.out.println( Arrays.toString(set_to_obj_arr) );
        var set_to_str_arr = set1.toArray(new String[0]);
        System.out.println( Arrays.toString(set_to_str_arr) );

        System.out.println();

        //the forEach method works...
        //(this uses the Consumer printer from the previous lesson)
        System.out.print("set forEach method: ");
        set1.forEach(printer);
        System.out.println();

        System.out.println();

        //both loops using an iterator work:

        System.out.print("set foreach loop (iterator): ");
        for(var item : set1) {
            System.out.print(item);
        }
        //for

        System.out.println();

        System.out.print("set manual loop (iterator): ");
        var set_iter = set1.iterator();
        while(set_iter.hasNext()) {
            System.out.print(set_iter.next());
        }
        //while

        System.out.println();
        System.out.println();

        //aquí te quedaste
        //luego cala los 5 métodos de sets

        //testing the set operations:

        System.out.println("before the set operations:");
        System.out.println("set1: " + set1);
        System.out.println("set2: " + set2);

        System.out.println();

        System.out.println("set operations:");
        System.out.println( "union: " + set1.union(set2) );
        System.out.println( "intersection: " + set1.intersection(set2) );
        System.out.println( "left difference: " + set1.leftDifference(set2) );
        System.out.println( "right difference: " + set1.rightDifference(set2) );
        System.out.println( "symmetric difference: " + set1.symmetricDifference(set2) );

        //something to keep in mind:
        //usually, the above methods are not wrapped like that
        //see their internal implementations on the GenericSet class.
        //our custom methods RETURN a new set, and don´t modify the input sets
        //while the built-in implementations do modify the caller set in-place.

        System.out.println("\n----------------------------------------");
        System.out.println("The Map interface and the HashMap class\n");

        //key-value mappings in Java are represented by the Map interface
        //and implemented by the HashMap and HashTable classes
        //HashMap is the more modern one, while HashTable is considered legacy.

        //just so it is known: HashTable is auto-synchronized,
        //so it is thread-safe, but it has worse performance,
        //also it doesn't allow any NULL values.

        //in these examples we'll use the HashMap class:

        //we'll create a key-value mapping from a String key to a Customer value
        Map<String, Customer> my_map = new HashMap<>();

        //we need to create some Customers first
        var customer1 = new Customer("aaa", "e@ma.il");
        var customer2 = new Customer("bbb", "co@rr.eo");

        //with put() we insert the Customers to the map
        //and define their keys
        my_map.put("customer1", customer1);
        my_map.put("customer2", customer2);

        //custom function to print our mapping
        printMap(my_map);

        System.out.println();

        //query the mapping to get a value with get()
        //by passing the key
        printCustomer( my_map.get("customer1") );

        System.out.println();

        //try to query for a nonexistent key
        printCustomer( my_map.get("customer3") ); //get() returns null if the key doesn't exist

        System.out.println();

        //we'll create a dummy customer as a default
        var defaultcustomer = new Customer("default", "dummy@e.mail");

        //if there's a risk that the key won't exist
        //we can use getOrDefault() to provide a fallback value
        printCustomer( my_map.getOrDefault("customer2", defaultcustomer) ); //does exist
        printCustomer( my_map.getOrDefault("customer3", defaultcustomer) ); //doesn't exist

        System.out.println();

        //we can search for a key by querying the map for it and see if it returns null
        //BUT, there's a chance the key exists and null is its actual value
        //so it's an ambiguous method for searching

        //instead, we use containsKey()
        System.out.println( my_map.containsKey("customer1") ); //does exist
        System.out.println( my_map.containsKey("customer3") ); //doesn't exist

        System.out.println();

        //let's create updated versions of our 2 Customer's
        var customer1a = new Customer("a++", "e@ma.il");
        var customer2a = new Customer("b--", "co@rr.eo");

        //we can overwrite the value of some key with put() or replace()
        //but replace() does nothing if the key doesn't exist

        //attempt to overwrite a Customer with put()
        my_map.put("customer1", customer1a);
        //attempt to overwrite a Customer with replace()
        my_map.replace("customer2", customer2a);

        printMap(my_map);

        System.out.println();

        //neither Map nor HashMap inherit or implement the Iterable interface
        //so we can't iterate on them directly with a foreach loop
        //but we can extract its keys with the keySet() method
        //keySet() returns a Set, which is iterable, so we can do:

        //get the values via keySet() and get()
        for( var key : my_map.keySet() ) {

            printCustomer( my_map.get(key) );
        }
        //foreach

        System.out.println();

        //get the values directly with values()
        //which returns a Collection:
        for( var value : my_map.values() ) {

            printCustomer(value);
        }
        //foreach

        System.out.println();

        //a HashMap, internally, actually stores the key-value pairs
        //as objects of the class Entry<K,V>

        //we can also iterate through these using entrySet():
        for( var pair : my_map.entrySet() ) {

            //an Entry is a key-value pair
            //from a pair, we can getKey() and getValue():

            System.out.println( pair.getKey() );
            printCustomer( pair.getValue() );
        }
        //foreach

        //other methods for HashMap's include
        //clear(), isEmpty(), size(), remove(),
        //containsValue(), forEach(), putAll(), etc.
    }
    //collectionsWrapper
}
//CollectionsExample

//eof
