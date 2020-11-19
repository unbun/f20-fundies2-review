package iterators;

import java.util.Iterator;
import tester.Tester;

// TASK:
// Design a higher order Iterator that is given two Iterators when constructed
// and alternates producing items from each of them.

class Q5AlternatingIterator<T> implements Iterator<T> {

    // currIter is always the iterator i am going to use
    // nextIter is always the other one
    private Iterator<T> currIter, nextIter;

    Q5AlternatingIterator(Iterator<T> iter1, Iterator<T> iter2) {
        this.currIter = iter1;
        this.nextIter = iter2;
    }

    @Override
    public boolean hasNext() {
        // currIter is always the iterator i am going to use
        return currIter.hasNext();
    }

    @Override
    public T next() {
        //1. get current result (hasNext should have guaranteed this "works")
        T result = currIter.next();

        //2. prep next result;
        // currIter is always the iterator i am going to use
        if(nextIter.hasNext()) {
            Iterator<T> temp = currIter;
            currIter = nextIter;
            nextIter = temp;
        }

        //3. return the result
        return result;
    }
}

class ExamplesAltIterate {
    void testAlternating(Tester t){
        EachCharacterIterator alpha1 = new EachCharacterIterator("ace");
        EachCharacterIterator alpha2 = new EachCharacterIterator("bdfgh");

        Q5AlternatingIterator alt = new Q5AlternatingIterator(alpha1, alpha2);

        t.checkExpect(alt.hasNext(), true);
        t.checkExpect(alt.next(), 'a');

        t.checkExpect(alt.hasNext(), true);
        t.checkExpect(alt.next(), 'b');

        t.checkExpect(alt.hasNext(), true);
        t.checkExpect(alt.next(), 'c');

        t.checkExpect(alt.hasNext(), true);
        t.checkExpect(alt.next(), 'd');

        t.checkExpect(alt.hasNext(), true);
        t.checkExpect(alt.next(), 'e');

        t.checkExpect(alt.hasNext(), true);
        t.checkExpect(alt.next(), 'f');

        t.checkExpect(alt.hasNext(), true);
        t.checkExpect(alt.next(), 'g');

        t.checkExpect(alt.hasNext(), true);
        t.checkExpect(alt.next(), 'h');

        t.checkExpect(alt.hasNext(), false);
    }


}



