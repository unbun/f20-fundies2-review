package deques;

import java.util.function.Predicate;

public class Sentinel<T> extends ANode<T> {
    public Sentinel() {
        this.next = this;
        this.prev = this;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T removeFromList() {
        throw new RuntimeException("Cannot remove item from empty list");
    }

    @Override
    public ANode<T> find(Predicate<T> pred) {
        return this;
    }

    @Override
    public String asString() {
        return "]";
    }

    @Override
    public String asStringRev() {
        return "]";
    }

}