package deques;

import java.util.function.Predicate;

public class Deque<T> {
    public Sentinel<T> header;

    public Deque() {
        this.header = new Sentinel<T>();
    }

    public Deque(Sentinel<T> header) {
        this.header = header;
    }

    public int size() {
        return this.header.next.size();
    }

    public void addAtHead(T data) {
        Node<T> dataNode = new Node<T>(data);
        dataNode.insertBetween(this.header, this.header.next);
    }

    public void addAtTail(T data) {
        Node<T> dataNode = new Node<T>(data);
        dataNode.insertBetween(this.header.prev, this.header);
    }

    public T removeFromHead() {
        return this.header.next.removeFromList();
    }

    public T removeFromTail() {
        return this.header.prev.removeFromList();
    }

    public ANode<T> find(Predicate<T> pred) {
        return this.header.next.find(pred);
    }

    public void removeNode(ANode<T> node) {
        if (node != this.header) {
            node.removeFromList();
        }
    }

    public void printDeque(){
        System.out.print("[");
        this.header.next.print();
    }

    public void printDequeBackwards(){
        System.out.print("[");
        this.header.prev.printBackwards();
    }
}
