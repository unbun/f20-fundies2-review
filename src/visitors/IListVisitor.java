package visitors;

import other.ConsList;
import other.IList;
import other.MtList;

import java.util.function.Function;
import tester.Tester;

/**
 * @author unbun
 *
 * TODO: write a SumVisitor visitor that gets the sum of every Integer in an Integer IList
 *
 */

public interface IListVisitor<T, U> extends Function<IList<T>, U> {
    U visitEmpty(MtList<T> t);

    U visitCons(ConsList<T> t);
}

class LengthVisitor implements IListVisitor<String, Integer> {

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

class ExamplesListVisits {

    IList<String> strs = new ConsList<>("aaa",
        new ConsList<>("bbbb",
            new ConsList<>("c",
                new MtList<>())));

    LengthVisitor lenVisit = new LengthVisitor();

    void testLengthVisitor(Tester t) {
        t.checkExpect(strs.accept(lenVisit), 8);
        // equivalent:
        t.checkExpect(lenVisit.apply(strs), 8);

    }

}

