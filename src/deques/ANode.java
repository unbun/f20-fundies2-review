package deques;

import java.util.function.Predicate;

public abstract class ANode<T> {
    public ANode<T> next;
    public ANode<T> prev;

    abstract int size();

    /**
     * EFFECT: insert this node in between the two given nodes
     */
    public void insertBetween(ANode<T> prev, ANode<T> next) {
        this.insertAfter(next);
        this.insertBefore(prev);
    }

    /**
     * EFFECT: insert the other node after this one
     */
    public void insertAfter(ANode<T> other) {
        this.next = other;
        other.prev = this;
    }

    /**
     * EFFECT: insert the other node before this one
     */
    public void insertBefore(ANode<T> other) {
        this.prev = other;
        other.next = this;
    }

    public abstract T removeFromList();

    public abstract ANode<T> find(Predicate<T> pred);

    public abstract String asString();

    public abstract String asStringRev();
}
