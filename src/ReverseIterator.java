import tester.Tester;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Problem Statement:
 * Design an iterator that takes in an Iterator<T> when constructed, and returns each element from the given iterator in reverse order
 * Example: iterator1 -> 1, 2, 3
 * ReverseIterator(iterator1) -> 3, 2, 1
 */

public class ReverseIterator<T> implements Iterator<T> {
    Stack<T> contents;

    public ReverseIterator(Iterator<T> toReverse) {
        this.contents = new Stack<>();
        while (toReverse.hasNext()) {
            this.contents.push(toReverse.next());
        }
    }

    public boolean hasNext() {
        return !contents.empty();
    }

    public T next() {
        return contents.pop();
    }
}


class ExamplesReverse {
    void testReverse(Tester t) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        ReverseIterator<Integer> reverse = new ReverseIterator<>(list.iterator());
        int expected = 6;
        while (reverse.hasNext()) {
            t.checkExpect(reverse.next(), expected);
            expected -= 1;
        }
        t.checkExpect(0, expected);
    }
}
