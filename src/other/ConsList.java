package other;

import visitors.IListVisitor;

public class ConsList<T> implements IList<T> {
    public T first;
    public IList<T> rest;

    public ConsList(T first, IList<T> rest) {
        this.first = first;
        this.rest = rest;
    }

    public <U> U accept(IListVisitor<T, U> visitor) {
        return visitor.visitCons(this); // let this visitor handle this IList as a ConsList
    }

    //EFFECT: this list has the items of that list added to the end
    public void append(IList<T> that) {
        this.rest = this.rest.appendHelp(that);
    }

    //helps append by returning a new rest of this non-empty list
    public IList<T> appendHelp(IList<T> that) {
        this.rest = this.rest.appendHelp(that);
        return this;
    }

    //computes the size of this non-empty list
    public int length() {
        return 1 + this.rest.length();
    }
}
