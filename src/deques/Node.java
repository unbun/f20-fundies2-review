package deques;

import java.util.function.Predicate;

public class Node<T> extends ANode<T> {
    T data;

    Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public Node(T data, ANode<T> next, ANode<T> prev) {
        if (next == null || prev == null) {
            throw new IllegalArgumentException("Cannot construct Node with explicit null next or prev");
        }
        this.data = data;
        this.next = next;
        next.prev = this;
        this.prev = prev;
        prev.next = this;
    }

    @Override
    public int size() {
        return 1 + this.next.size();
    }

    @Override
    public T removeFromList() {
        this.prev.next = this.next;
        this.next.prev = this.prev;
        return this.data;
    }

    @Override
    public ANode<T> find(Predicate<T> pred) {
        if (pred.test(this.data)) {
            return this;
        } else {
            return this.next.find(pred);
        }
    }

    @Override
    public String asString() {
        return data.toString() + " "
            + this.next.asString();
    }

    @Override
    public String asStringRev() {
        return data.toString() + " "
            + this.prev.asStringRev();
    }
}
