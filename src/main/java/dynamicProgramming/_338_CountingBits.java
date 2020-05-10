package dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's
 * in their binary representation and return them as an array.
 *
 * Example 1:
 *
 * Input: 2
 * Output: [0,1,1]
 * Example 2:
 *
 * Input: 5
 * Output: [0,1,1,2,1,2]
 * Follow up:
 *
 * It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear
 * time O(n) /possibly in a single pass?
 * Space complexity should be O(n).
 * Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any
 * other language.
 */
public class _338_CountingBits {
    public int[] counting(int top) {
        switch (top) {
            case 0:
                return new int[]{0};
            case 1:
                return new int[]{0, 1};
            case 2:
                return new int[]{0, 1, 1};
            case 3:
                return new int[]{0, 1, 1, 2};
        }
        //These are the values for 2 & 3
        List<Integer> countBits = new ArrayList<>();
        countBits.add(1);
        countBits.add(2);


        // This creates the values by slices of the power of two
        // The new slice of values can be simply evaluated: the first half is the repetition of what we have until now
        // without 0 and 1 and the second half is what we have plus 1.
        for (int pow = 2; top > Math.pow(2, pow); pow++) {
            List<Integer> newList = new ArrayList<>(countBits);
            for (Integer bit : countBits) {
                newList.add(bit + 1);
                if (countBits.size() + newList.size() + 1 >= top)
                    break;
            }
            countBits.addAll(newList);
        }

        countBits.add(0, 0);
        countBits.add(1, 1);

        int[] resultArray = new int[top + 1];
        for (int i = 0; i < top + 1; i++)
            resultArray[i] = countBits.get(i);
        return resultArray;
    }
}
