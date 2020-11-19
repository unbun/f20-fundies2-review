package other;

import java.util.function.*;
import java.util.Comparator;
import tester.Tester;

// BiFunction<T,U,R>
//Represents a function that accepts two arguments and produces a result.

//Predicate<T>
//Represents a predicate (boolean-valued function) of one argument.

//Function<T,R>
//Represents a function that accepts one argument and produces a result.

//Comparator<T>
//Represents a function that accepts two objects
// and produces the integer difference between them

// A functional object is any object with one method
class Foo {
    public String bar(){
        return "bar";
    }
}

// given an integer, triple it, and return it as a double
class TripleFunction implements Function<Integer, Double> {

    @Override
    public Double apply(Integer integer) {
        return integer * 3.0;
    }
}

// given 2 integers, subtract them as doubles
class SubtractBiFunc implements BiFunction<Integer, Integer, Double> {

    @Override
    public Double apply(Integer int1, Integer int2) {
        return (int1 - int2) * 1.0;
    }
}


class IsUnnas implements Predicate<String> {

    @Override
    public boolean test(String s) {
        return s.equals("Unnas");
    }
}

class CompareStringsByLength implements Comparator<String> {

    // if positive -> "o1 beat o2"
    // if negative -> "o2 beat o1"
    // if 0 --> "they died
    @Override
    public int compare(String o1, String o2) {
        return o1.length() - o2.length();
    }
}

class ExamplesFuncs {
    Comparator<String> strByLength1 = new CompareStringsByLength();
    // basically calling a constructor
    Comparator<String> strByLength2 = ((s1,s2) -> s1.length() - s2.length());

    Predicate<String> isUnnas1 = new IsUnnas();
    Predicate<String> isUnnas2 = (s -> s.equals("Unnas"));

    Function<Integer, Double> trip1 = new TripleFunction();
    Function<Integer, Double> trip2 = (i -> i * 3.0);

    void testFuncs(Tester t) {
        t.checkExpect(isUnnas1.test("Unnas"), true);
        t.checkExpect(isUnnas2.test("Unnas"), true);

        t.checkExpect(isUnnas1.test("not unnas :("), false);
        t.checkExpect(isUnnas2.test("not unnas :("), false);
    }
}



