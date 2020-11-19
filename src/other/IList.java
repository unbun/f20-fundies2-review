package other;

import visitors.IListVisitor;

public interface IList<T> {
    // a visitor's "entry point" into a list
    <U> U accept(IListVisitor<T, U> visitor);

    //EFFECT: this list has the items of that list added to the end
    void append(IList<T> that);

    //helps append by returning a new rest of this list
    IList<T> appendHelp(IList<T> that);

    //computes the size of this list
    int length();
}
