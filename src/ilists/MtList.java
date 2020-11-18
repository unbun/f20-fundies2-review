package ilists;

public class MtList<T> implements IList<T> {

    public <U> U accept(IListVisitor<T, U> visitor) {
        return visitor.visitEmpty(this);// let this visitor handle this IList as an MTList
    }

    //doesn’t make any changes to the empty list
    public void append(IList<T> that) {
        return ; }

    //helps append by returning a new rest
    public IList<T> appendHelp(IList<T> that) {
        return that; }

    //computes the length of this empty list
    public int length() { return 0; }

}