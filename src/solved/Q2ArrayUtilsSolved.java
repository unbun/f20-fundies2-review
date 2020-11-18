package solved;

import java.util.ArrayList;
import utils.Utils;

/**
 * Design the method:
 *
 * boolean containsSequence(ArrayList<Integer> source, ArrayList<Integer> sequence);
 *
 * which checks if source contains a sub-sequence of the integers in sequence.
 * The sub-sequence should contain the same numbers as sequence in the same order.
 * You may assume that neither list is null.
 *
 * Do not use recursion; use appropriate loop forms instead.
 */
public class Q2ArrayUtilsSolved {

    public static boolean containsSequence(ArrayList<Integer> source, ArrayList<Integer> sequence) {

        // optional
        if(sequence.size() <= 0 ) return false;

        int lastPossibleStart = source.size() - sequence.size();

        for(int srcIdx = 0; srcIdx <= lastPossibleStart; srcIdx++) {


            if(source.get(srcIdx).equals(sequence.get(0))) {
                //srcIdx is a possible index where the sub-sequence could start at
                for(int seqIdx = 0; seqIdx < sequence.size(); seqIdx++) {
                    if(source.get(srcIdx + seqIdx) != sequence.get(seqIdx)) {
                        break; // sub-sequence does not match
                    } else if(seqIdx == sequence.size() - 1) {
                        // sub-sequence matches and we are at the last element of the sequence
                        return true;
                    }
                }
            }
        }

        return false;
    }
}

class ExamplesQ2 {
    public static void main(String args[]) {
        ArrayList<Integer> ints1 = Utils.makeList(1,2,3,4,5,6);
        ArrayList<Integer> ints2 = Utils.makeList(2,3, 4);
        ArrayList<Integer> mt = Utils.makeList();

        Utils.printObject("Test 1", Q2ArrayUtilsSolved.containsSequence(ints1, ints2));
        Utils.printObject("Test 2", Q2ArrayUtilsSolved.containsSequence(ints2, ints1));
        Utils.printObject("Mt Test 1", Q2ArrayUtilsSolved.containsSequence(ints2, mt));
        Utils.printObject("Mt Test 1", Q2ArrayUtilsSolved.containsSequence(mt, ints2));
        Utils.printObject("Mt Test 1", Q2ArrayUtilsSolved.containsSequence(mt, mt));




    }

}
