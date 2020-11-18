import java.util.function.Predicate;
import tester.Tester;

public class Q4Deque<T> {
    Sentinel<T> header;

    Q4Deque() {
        this.header = new Sentinel<T>();
    }

    Q4Deque(Sentinel<T> header) {
        this.header = header;
    }

    int size() {
        return this.header.next.size();
    }

    void addAtHead(T data) {
        Node<T> dataNode = new Node<T>(data);
        dataNode.insertBetween(this.header, this.header.next);
    }

    void addAtTail(T data) {
        Node<T> dataNode = new Node<T>(data);
        dataNode.insertBetween(this.header.prev, this.header);
    }

    T removeFromHead() {
        return this.header.next.removeFromList();
    }

    T removeFromTail() {
        return this.header.prev.removeFromList();
    }

    ANode<T> find(Predicate<T> pred) {
        return this.header.next.find(pred);
    }

    void removeNode(ANode<T> node) {
        if (node != this.header) {
            node.removeFromList();
        }
    }

    /**
     * EFFECT: append the nodes in the other deque to the end of this one
     */
    void append(Q4Deque<T> other) {
        // put the other's first after this's last
        this.header.prev.insertAfter(other.header.next);

        // put this header after other's last
        other.header.prev.insertAfter(this.header);
    }

    void printDeque(){
        System.out.print("[");
        this.header.next.print();
    }

    void printDequeBackwards(){
        System.out.print("[");
        this.header.prev.printBackwards();
    }
}

abstract class ANode<T> {
    ANode<T> next;
    ANode<T> prev;

    abstract int size();

    void insertBetween(ANode<T> prev, ANode<T> next) {
        this.next = next;
        next.prev = this;
        this.prev = prev;
        prev.next = this;
    }

    /**
     * EFFECT: insert the other node after this one
     */
    void insertAfter(ANode<T> other) {
        this.next = other;
        other.prev = this;
    }

    abstract T removeFromList();

    abstract ANode<T> find(Predicate<T> pred);

    abstract void print();

    abstract void printBackwards();
}

class Node<T> extends ANode<T> {
    T data;

    Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    Node(T data, ANode<T> next, ANode<T> prev) {
        if (next == null || prev == null) {
            throw new IllegalArgumentException("Cannot construct Node with explicit null next or prev");
        }
        this.data = data;
        this.next = next;
        next.prev = this;
        this.prev = prev;
        prev.next = this;
    }

    int size() {
        return 1 + this.next.size();
    }

    T removeFromList() {
        this.prev.next = this.next;
        this.next.prev = this.prev;
        return this.data;
    }

    ANode<T> find(Predicate<T> pred) {
        if (pred.test(this.data)) {
            return this;
        } else {
            return this.next.find(pred);
        }
    }

    @Override
    void print() {
        System.out.print(data.toString() + " ");
        this.next.print();
    }

    @Override
    void printBackwards() {
        System.out.print(data.toString() + " ");
        this.prev.printBackwards();
    }
}

class Sentinel<T> extends ANode<T> {
    Sentinel() {
        this.next = this;
        this.prev = this;
    }

    int size() {
        return 0;
    }

    T removeFromList() {
        throw new RuntimeException("Cannot remove item from empty list");
    }

    ANode<T> find(Predicate<T> pred) {
        return this;
    }

    @Override
    void print() {
        System.out.println("]");
    }

    @Override
    void printBackwards() {
        System.out.println("]");
    }

    String asString() {
        return "<sentinel>";
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