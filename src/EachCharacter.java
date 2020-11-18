import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import solved.EachCharacterSolved;
import tester.*;

// Task:
// Design the class EachCharacter.
// EachCharacter should be an iterable class that given a string,
// will iterate over each character of the string
class EachCharacter implements Iterable<Character> {

    String toIterate; // what we pass to our Iterator

    public EachCharacter(String s) {
        this.toIterate = s;
    }

    public Iterator<Character> iterator() {
        return new EachCharacterIterator(toIterate);
    }

    // hasNext() and next()
}

// Given a string, will iterate over each character of the string
class EachCharacterIterator implements Iterator<Character> {

    String toIterateOver; // what the Iterable passed me
    int currIndex;  // help us know where in our iteration we are

    EachCharacterIterator(String toIterateOver) {
        this.toIterateOver = toIterateOver;
        this.currIndex = 0; // start at 0
    }

    @Override
    // when this is true, "our current state works"
    // when this is false, "our current state fails"
    public boolean hasNext() {
        // end at toIteraterOver.length() - 1 (5)

        return currIndex < toIterateOver.length(); // option a

        // a. currIndex < toIterateOver.length(); length = 6, currIndex = 5: true, //  currIndex = 6: false

        // b. this.toIterateOver.length() >= currIndex; length = 6, currIndex = 5: true, // currIndex = 6: true

        // c. currIndex < toIterateOver.length() - 1; length = 6, currIndex = 5: false,  // currIndex = 6: false
    }

    @Override
    public Character next() {
        //1. get current result (hasNext should have guaranteed this "works")
        Character curr = this.toIterateOver.charAt(currIndex);

        //2. prep for next result
        currIndex++;

        //3. return current result
        return curr;
    }
}


class ExamplesEC {

    EachCharacter emptyEC;
    EachCharacter fooEC;

    void initData(){
        emptyEC = new EachCharacter("");
        fooEC = new EachCharacter("foo bar");
    }

    void testEmpty(Tester t) {
        initData();
        ArrayList<Character> chars = new ArrayList<>();
        t.checkExpect(emptyEC.iterator().hasNext(), false);
    }

    void testFoo(Tester t) {
        initData();
        ArrayList<Character> chars = new ArrayList<>(
            Arrays.asList('f', 'o', 'o', ' ', 'b', 'a', 'r'));

        int ii =0;
        for(Character exp : fooEC) {
            System.out.println(
                String.format("[%d] Testing: %s == %s",
                    ii,
                    chars.get(ii).toString(),
                    exp.toString()));

            //exp: used fooEC.next()
            t.checkExpect(exp, chars.get(ii));
            ii++;
        }
        //fooEC.hasNext() returned false, so we exited


    }

    /*
    void testFoo2(Tester t) {
        Iterator<Character> fooIterator = new EachCharacterIterator("foo bar");
        // now you can manually check next() and hasNext()
    }
     */


}
