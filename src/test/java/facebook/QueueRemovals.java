package facebook;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You're given a list of n integers arr, which represent elements in a queue (in order from front to back). You're
 * also given an integer x, and must perform x iterations of the following 3-step process:
 * Pop x elements from the front of queue (or, if it contains fewer than x elements, pop all of them)
 * Of the elements that were popped, find the one with the largest value (if there are multiple such elements, take
 * the one which had been popped the earliest), and remove it
 * For each one of the remaining elements that were popped (in the order they had been popped), decrement its value
 * by 1 if it's positive (otherwise, if its value is 0, then it's left unchanged), and then add it back to the queue
 * Compute a list of x integers output, the ith of which is the 1-based index in the original array of the element
 * which had been removed in step 2 during the ith iteration.
 */
public class QueueRemovals {

    @Test
    public void a() {
        int x_1 = 5;
        int[] arr_1 = {1, 2, 2, 3, 4, 5};
        int[] expected_1 = {5, 6, 4, 1, 2 };
        int[] output_1 = findPositions(arr_1, x_1);
        Assert.assertArrayEquals(expected_1, output_1);
    }

    int[] findPositions(int[] arr, int x) {
        // Write your code here
        Queue<Pair> main = new LinkedList<>();
        Queue<Integer> deletes = new LinkedList<>();

        for(int i=0; i< arr.length; i++) {
            main.add(Pair.of(arr[i], i+1));
        }

        for(int counter = 0; counter < x; counter ++) {
            Queue<Pair> supp = new LinkedList<>();
            int maxIndex = 0;
            int max = 0;

            int limit = Math.min(x, main.size());
            for(int i = 0; i < limit; i ++) {
                Pair tmp = main.remove();
                if(tmp.getLeft() > max) {
                    max = tmp.getLeft();
                    maxIndex = i;
                }
                supp.add(tmp);
            }

            limit = supp.size();
            for(int i = 0; i < limit; i++) {
                Pair tmp = supp.remove();
                if(i != maxIndex){
                    tmp = Pair.of(
                            Math.max(0, tmp.getLeft()-1),
                            tmp.getRight());
                    main.add(tmp);
                } else
                    deletes.add(tmp.getRight());
            }
        }

        int[] res = new int[deletes.size()];
        for(int i = 0; i < res.length; i++) {
            res[i]= deletes.remove();
        }
        return res;
    }


    private static class Pair {

        private final int left;
        private final int right;

        public Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }

        public static Pair of(int left, int right) {
            return new Pair(left, right);
        }

        public int getLeft() {
            return left;
        }

        public int getRight() {
            return right;
        }
    }
}
