package solved;


// TASK:
// Find the combined lengths of all strings inside a List<String>

import ilists.ConsList;
import ilists.IList;
import ilists.IListVisitor;
import ilists.MtList;

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


