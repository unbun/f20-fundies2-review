import tester.Tester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Pair<A, B> {
    A left;
    B right;

    Pair(A left, B right) {
        this.left = left;
        this.right = right;
    }
}

class TrickyMutation {
    <A, B> Pair<B, A> switchPairs(Pair<A, B> first,
                                  Pair<A, B> second,
                                  A a,
                                  B b) {
        A placeHolder = second.left;
        second.left = a;
        first.left = placeHolder;
        first.right = b;
        second = first;
        first = second;
        return new Pair<>(first.right, second.left);
    }


    /**
     * @param a
     * @param b
     * @return
     */
    List<Integer> switchAroundSomeLists(ArrayList<Integer> a, ArrayList<Integer> b) {
        // using methods to swap the contents of the two lists.
        List<Integer> placeHolder = new ArrayList<>(a);
        a.clear();
        a.addAll(b);
        b.clear();
        b.addAll(placeHolder);

        a = b;
        // this assignment does not change the reference of 'first' and
        // 'second' in the examples class
        List<Integer> result = new ArrayList<>();
        for (int ii = 0; ii < Math.min(a.size(), b.size()); ii++) {
            result.add(a.get(ii) + b.get(ii));
        }
        return result;
    }
}

class TestMutation {
    TrickyMutation tricky = new TrickyMutation();

    void testSwitchPairs(Tester t) {
        Pair<Integer, String> fiddyFive = new Pair<>(55, "55");
        Pair<Integer, String> twenty2 = new Pair<>(22, "22");
        Pair<String, Integer> result = tricky.switchPairs(fiddyFive, twenty2, 23,
                "twentyThree");

        // test result
        t.checkExpect(result.left, "twentyThree");
        t.checkExpect(result.right, 22);

        // test fiddyFive
        t.checkExpect(fiddyFive.left, 22);
        t.checkExpect(fiddyFive.right, "twentyThree");

        // test twentyTwo
        t.checkExpect(twenty2.left, 23);
        t.checkExpect(twenty2.right, "22");
    }

    void testSwitchLists(Tester t) {
        ArrayList<Integer> first = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ArrayList<Integer> second = new ArrayList<>(Arrays.asList(100, 101));
        List<Integer> result = tricky.switchAroundSomeLists(first, second);

        t.checkExpect(result, Arrays.asList(2, 4, 6, 8, 10));

        t.checkExpect(first, Arrays.asList(100, 101));
        t.checkExpect(second, Arrays.asList(1, 2, 3, 4, 5));
    }
}

