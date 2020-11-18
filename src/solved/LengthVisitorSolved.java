package solved;


// TASK:
// Find the combined lengths of all strings inside a List<String>

import lists.ConsList;
import lists.IList;
import lists.IListVisitor;
import lists.MtList;

public class LengthVisitorSolved implements IListVisitor<String, Integer> {

    @Override
    public Integer visitEmpty(MtList<String> t) {
        return 0;
    }

    @Override
    public Integer visitCons(ConsList<String> t) {
        return t.first.length() + this.apply(t.rest);
    }

    @Override
    public Integer apply(IList<String> stringIList) {
        return stringIList.accept(this);
    }
}


