package deques;

import tester.Tester;

/**
 * You should have your own implementation of the Deque class, and it should be added to this
 * folder.
 * 
 * This class extends that Deque class, with the only addition being
 * the append() method.
 * I have it set up this way so that it is clear what has been done
 * for this problem specifically.
 */
public class Q4Deque<T> extends Deque<T>{

    Q4Deque() {
        super();
    }

    Q4Deque(Sentinel<T> header) {
        super(header);
    }

    /**
     * EFFECT: append all the items of the given Deque onto the current one
     * and remove the items from that given Deque.
     */
    void append(Q4Deque<T> other) {

        if(other.header == this.header) {
            return;
        }

        while(other.size() > 0) {
            this.addAtTail(other.removeFromHead());
        }
    }


    /**
     * EFFECT: append the nodes in the other deque to the end of this one
     * This doesn't accomplish everything the question asks for, but does
     * technically append two deques
     */
    void justAppend(Q4Deque<T> other) {

        // end of this deque: this.header.prev
        // beg of other deque: other.header.next

        ANode<T> endOfMe = this.header.prev;
        ANode<T> begOfOther = other.header.next;

        endOfMe.next = begOfOther;
        begOfOther.prev = endOfMe;

        return;
    }
}

class ExamplesDeques {
    Q4Deque<String> deque1;
    Q4Deque<String> deque2;
    Q4Deque<String> deque3;

    boolean initData(Tester t) {
        try {
            this.deque1 = new Q4Deque<String>();
            Sentinel<String> sortedHeader = new Sentinel<String>();
            Node<String> abc = new Node<String>("abc", sortedHeader, sortedHeader);
            Node<String> bcd = new Node<String>("bcd", sortedHeader, abc);
            Node<String> cde = new Node<String>("cde", sortedHeader, bcd);
            new Node<String>("def", sortedHeader, cde);

            this.deque2 = new Q4Deque<String>(sortedHeader);
            Sentinel<String> unsortedHeader = new Sentinel<String>();
            Node<String> xyz = new Node<String>("xyz", unsortedHeader, unsortedHeader);
            Node<String> mno = new Node<String>("mno", unsortedHeader, xyz);
            Node<String> qrs = new Node<String>("qrs", unsortedHeader, mno);
            Node<String> jkl = new Node<String>("jkl", unsortedHeader, qrs);
            new Node<String>("tuv", unsortedHeader, jkl);

            this.deque3 = new Q4Deque<String>(unsortedHeader);
            return true;
        } catch (Exception e) {
            t.checkExpect(false, true);
            return false;
        }
    }

    void testSize(Tester t) {
        this.initData(t);
        t.checkExpect(this.deque1.size(), 0);
        t.checkExpect(this.deque2.size(), 4);
        t.checkExpect(this.deque3.size(), 5);
    }

    void testPrintAppend(Tester t) {
        this.initData(t);

        t.checkExpect(this.deque2.toString(), "[abc bcd cde def ]");

        t.checkExpect(this.deque3.toString(), "[xyz mno qrs jkl tuv ]");

        this.deque2.append(deque3);

        t.checkExpect(this.deque2.toString(),
            "[abc bcd cde def xyz mno qrs jkl tuv ]");

        t.checkExpect(this.deque2.toStringRev(),
            "[tuv jkl qrs mno xyz def cde bcd abc ]");

        t.checkExpect(this.deque3.toString(), "[]");
    }

    void testPrintAppend2(Tester t) {
        this.initData(t);

        // appending to itself will create different behaviors
        this.deque2.append(deque2);
        t.checkExpect(this.deque2.toString(), "[abc bcd cde def ]");

    }
}