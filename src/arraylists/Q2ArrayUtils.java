package arraylists;

import java.util.ArrayList;
import java.util.Arrays;
import tester.Tester;

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



/*

       source                     seq
[1, 2, 3, 3,   4, 5, 6, 7]      [3, 4, 5]   -> true
      2   2+1  2+2               0  1  2

for (each element in sequence)
   source.contains(element)      [1, 2, 3, 6, 4, 5, 6, 7]      [3, 4, 5]   -> true, but should be false

for(each element in source)
   If this element (startPoint), is the first element of seq,
   This could be the start of the sub-sequence in source

  for(n = [0, sequence.length])
     if the n-th element of sequence exists at
        source's (startPoint) + n, then we are still in a possible sequence
 */

// break : exit the for-loop
// continue: skip everything in the for-loop that is after that line, but continue iterating

public class Q2ArrayUtils {

    public boolean containsSequence(ArrayList<Integer> source, ArrayList<Integer> sequence) {
        // if the source is smaller than the sequence, return false
       for(int srcIdx = 0; srcIdx < source.size(); srcIdx++) {
            // if this element (in source)
            // is a potential starting point (i.e. the start of sequence)

            int startPoint = srcIdx;

            if(source.get(startPoint).equals(sequence.get(0))){
                for(int seqIdx = 0; seqIdx < sequence.size(); seqIdx++) {

                    // check that startPoint + seqIdx will work
                    // OR in outer-for loop, change end condition
                    if(startPoint + seqIdx >= source.size()) {
                        return false;
                    }

                    if(!source.get(startPoint + seqIdx).equals(sequence.get(seqIdx))){
                        break;
                    } else if (seqIdx == sequence.size() - 1){
                        // if we made it to the end of the sequence without breaking, we
                        // have a full sub-sequence
                        return true;
                    }
                }
            }
        }

        return false;
    }
}

class ExamplesQ2 {

    Q2ArrayUtils au = new Q2ArrayUtils();
    ArrayList<Integer> ints1 = new ArrayList<>(
        Arrays.asList(1, 2, 3, 4, 3, 4, 5)
    );

    ArrayList<Integer> ints2 = new ArrayList<>(
        Arrays.asList(3, 4, 5)
    );

    ArrayList<Integer> ints3 = new ArrayList<>(
        Arrays.asList(1, 2, 4)
    );


    void testContainsSeq(Tester t) {
        t.checkExpect(au.containsSequence(ints1, ints2), true);
        t.checkExpect(au.containsSequence(ints1, ints3), false);

        t.checkExpect(au.containsSequence(ints2, ints1), false);
        t.checkExpect(au.containsSequence(ints2, ints3), false);
    }

}
