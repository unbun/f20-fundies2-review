package solved;

import lists.ConsList;
import lists.IList;
import lists.MtList;
import tester.Tester;

@SuppressWarnings("ALL")
public class Q3AppendTestSolved {

    IList<Integer> mt = new MtList<Integer>();

    IList<Integer> ints1 = new ConsList<Integer>(1, mt);

    IList<Integer> ints2 = new ConsList<Integer>(2,
        new ConsList<Integer>(3, mt));

    IList<Integer> ints3 = new ConsList<Integer>(4,
        new ConsList<Integer>(5, mt));

    IList<Integer> ints4 = new ConsList<Integer>(6,
        new ConsList<Integer>(7, mt));

    //Fill in the blanks
    void testAppend(Tester t) {
        t.checkExpect(ints1.length(), 1);
        t.checkExpect(ints2.length(), 2);
        t.checkExpect(ints3.length(), 2);

        ints1.append(ints2);

        t.checkExpect(ints1.length(), 3); // TODO: fill in the blank

        ints2.append(ints3);

        t.checkExpect(ints1.length(), 5); // TODO: fill in the blank

        ints2.append(ints4);

        t.checkExpect(ints2.length(), 6); // TODO: fill in the blank

        ints4.append(ints4);

        // t.checkExpect(ints4.length(), -1); // TODO: fill in the blank
    }

}
