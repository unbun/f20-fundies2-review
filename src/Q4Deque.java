import deques.*;
import tester.Tester;

/**
 * Set up an abstract class so that you can see exactly what I have added to the Deque class
 * @param <T>
 */
public class Q4Deque<T> extends Deque<T>{

    Q4Deque() {
        super();
    }

    Q4Deque(Sentinel<T> header) {
        super(header);
    }

    /**
     * EFFECT: append the nodes in the other deque to the end of this one
     */
    void append(Q4Deque<T> other) {
        // put the other's first after this's last
        this.header.prev.insertAfter(other.header.next); // added to the ANode class

        // put this header after other's last
        other.header.prev.insertAfter(this.header); // added to the ANode class
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

        System.out.println("\nDeque2: ");
        this.deque2.printDeque();

        System.out.println("\nDeque3: ");
        this.deque3.printDeque();

        this.deque2.append(deque3);
        System.out.println("\nAppended: ");
        this.deque2.printDeque();
        System.out.println("Appended backwards: ");
        this.deque2.printDequeBackwards();

        System.out.println("\nDeque3 after append: ");
        this.deque3.printDeque();
        System.out.println("Deque3 after append backwards: ");
        this.deque3.printDequeBackwards();
    }

    void testPrintAppend2(Tester t) {
        this.initData(t);
        System.out.println("\nDeque2: ");
        this.deque2.printDeque();

        this.deque2.append(deque2);
        System.out.println("\nAppended to itself: ");
        this.deque2.printDeque();
    }
}