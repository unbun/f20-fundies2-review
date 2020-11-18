package deques;

import java.util.function.Predicate;

public abstract class ANode<T> {
    public ANode<T> next;
    public ANode<T> prev;

    abstract int size();

    public void insertBetween(ANode<T> prev, ANode<T> next) {
        this.next = next;
        next.prev = this;
        this.prev = prev;
        prev.next = this;
    }

    /**
     * EFFECT: insert the other node after this one
     */
    public void insertAfter(ANode<T> other) {
        this.next = other;
        other.prev = this;
    }

    public abstract T removeFromList();

    public abstract ANode<T> find(Predicate<T> pred);

    public abstract void print();

    public abstract void printBackwards();
}
