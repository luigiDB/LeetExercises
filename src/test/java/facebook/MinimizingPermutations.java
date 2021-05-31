package facebook;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * In this problem, you are given an integer N, and a permutation, P of the integers from 1 to N, denoted as
 * (a_1, a_2, ..., a_N). You want to rearrange the elements of the permutation into increasing order, repeatedly making
 * the following operation:
 * Select a sub-portion of the permutation, (a_i, ..., a_j), and reverse its order.
 * Your goal is to compute the minimum number of such operations required to return the permutation to increasing order.
 */
public class MinimizingPermutations {
    @Test
    public void a() {
        int[] arr_1 = {1, 2, 5, 4, 3};
        assertEquals(1, minOperations(arr_1));
    }

    @Test
    public void b() {
        int[] arr_2 = {3, 1, 2};
        assertEquals(2, minOperations(arr_2));
    }

    // Add any helper functions you may need here

    private void reverse(int[] arr, int left, int right) {
        Stack<Integer> s = new Stack<>();
        for (int i = left; i <= right; i++) {
            s.push(arr[i]);
        }
        for (int i = left; i <= right; i++) {
            arr[i] = s.pop();
        }
    }

    int minOperations(int[] arr) {
        // Write your code here

        int[] t = Arrays.copyOf(arr, arr.length);
        Arrays.sort(t);
        String target = toComp(t);

        Queue<int[]> queue = new LinkedList<>();
        queue.add(arr);

        int distance = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int k = 0; k < levelSize; k++) {
                int[] curr = queue.poll();
                for (int i = 0; i < arr.length - 1; i++) {
                    for (int j = i + 1; j < arr.length; j++) {
                        int[] copy = Arrays.copyOf(curr, curr.length);
                        reverse(copy, i, j);

                        if(toComp(copy).equals(target))
                            return distance+1;
                        else
                            queue.offer(copy);
                    }
                }
            }
            distance++;
        }

        return -1;
    }

    private String toComp(int[] copy) {
        StringBuilder sb = new StringBuilder();
        for (int i : copy)
            sb.append(i);
        return sb.toString();
    }


}
