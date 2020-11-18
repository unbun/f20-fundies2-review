package solved;

import java.util.Iterator;
import utils.Utils;

public class Q5AlternatingSolved<T> implements Iterable<T>{
    private Iterator<T> iter1, iter2;

    Q5AlternatingSolved(Iterator<T> iter1, Iterator<T> iter2) {
        this.iter1 = iter1;
        this.iter2 = iter2;
    }

    @Override
    public Iterator<T> iterator() {
        return new AlternatingIterator1<T>(iter1, iter2);
    }
}

class AlternatingIterator1<T> implements Iterator<T> {

    private Iterator<T> currIter, nextIter;

    AlternatingIterator1(Iterator<T> iter1, Iterator<T> iter2) {
        this.currIter = iter1;
        this.nextIter = iter2;
    }

    @Override
    public boolean hasNext() {
        return currIter.hasNext();
    }

    @Override
    public T next() {
        //1. get current result (hasNext should have guaranteed this "works")
        T result = currIter.next();

        //2. prep next result;
        if(nextIter.hasNext()) {
            Iterator<T> temp = currIter;
            currIter = nextIter;
            nextIter = temp;
        }

        return result;
    }
}

class ExamplesAltIterator {

    public static void main(String[] args) {
        Iterator<Character> odds = new ECIteratorSolved1("135");
        Iterator<Character> evens = new ECIteratorSolved1("246789");
        Q5AlternatingSolved<Character> numbers = new Q5AlternatingSolved<>(odds, evens);

        Utils.printIterable("Testing numbers iter", numbers);

        Iterator<Character> letters1 = new ECIteratorSolved1("aceghi");
        Iterator<Character> letters2 = new ECIteratorSolved1("bdf");
        Q5AlternatingSolved<Character> letters = new Q5AlternatingSolved<>(letters1, letters2);

        Utils.printIterable("Testing letters iter", letters);

        Iterator<Character> empty1 = new ECIteratorSolved1("");
        Iterator<Character> empty2 = new ECIteratorSolved1("");
        Q5AlternatingSolved<Character> empty = new Q5AlternatingSolved<>(empty1, empty2);

        Utils.printIterable("Testing empty iter", empty);
    }
}