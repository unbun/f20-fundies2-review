package solved;

// Task:
// Create an iterable class that, given a string, will iterate over each character of the string

import java.util.Iterator;
import utils.Utils;

class ECIteratorSolved1 implements Iterator<Character> {
    private String mString;
    private int currChar;

    public ECIteratorSolved1(String mString) {
        this.mString = mString;
        this.currChar = 0;
    }

    @Override
    public boolean hasNext() {
        return this.currChar < mString.length();
    }

    @Override
    public Character next() {
        //1. get current result (hasNext should have guaranteed this "works")
        Character c = mString.charAt(currChar);

        //2. prep for next result
        currChar++;

        //3. return current result
        return c;
    }
}

class ECIteratorSolved2 implements Iterator<Character> {

    private String currString;

    public ECIteratorSolved2(String currString) {
        this.currString = currString;
    }

    @Override
    public boolean hasNext() {
        return currString.length() >= 1;
    }

    @Override
    public Character next() {
        //1. get current result (hasNext should have guaranteed this "works")
        Character result = currString.charAt(0);

        // 2. Prep for the next result
        currString = currString.substring(1);

        // 3. Return the result
        return result;
    }
}

public class EachCharacterSolved implements Iterable<Character> {

    private String toIterateOver;

    EachCharacterSolved(String toIterateOver) {
        this.toIterateOver = toIterateOver;
    }

    @Override
    public Iterator<Character> iterator() {
        return new ECIteratorSolved1(toIterateOver);
    }
}

class ExamplesMain {

    public static void main(String[] args) {

        EachCharacterSolved emptyEC = new EachCharacterSolved("");
        EachCharacterSolved happyEC = new EachCharacterSolved("happy");

        /*
        emptyEC.iterator().hasNext() --> false

        happyEC.iterator().next() --> 'h'
        happyEC.iterator().hasNext() --> true
        happyEC.iterator().next() --> 'a'
        happyEC.iterator().hasNext() --> true
        happyEC.iterator().next() --> 'p'
        happyEC.iterator().hasNext() --> true
        happyEC.iterator().next() --> 'p'
        happyEC.iterator().hasNext() --> true
        happyEC.iterator().next() --> 'y'
        happyEC.iterator().hasNext() --> false
         */

        Utils.printIterable("Testing empty iter", emptyEC);
        Utils.printIterable("Testing \"happy\" iter", happyEC);
    }
}
