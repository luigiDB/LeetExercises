package utils.dataStructures.stack;

import java.util.Stack;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 */
public class _85_MaximalRectangle {

    public static int maxRectangle(int[][] input) {
        int max = -1;
        int[] support = new int[input[0].length];

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                support[j] = (input[i][j] == 1) ? support[j] + 1 : 0;
            }
            max = Math.max(max, maxRectangleOnHistogram(support));
        }


        return max;
    }

    private static int maxRectangleOnHistogram(int[] support) {
        int max = -1;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        //We are creating a stack of monotonic increasing heights
        for (int i = 0; i < support.length; i++) {
            while (stack.peek() != -1 && support[stack.peek()] >= support[i]) {
                max = Math.max(max, support[stack.pop()] * ((i - 1) - stack.peek()));
            }
            stack.push(i);
        }

        while (stack.peek() != -1) {
            max = Math.max(max, support[stack.pop()] * (support.length - stack.peek() - 1));
        }

        return max;
    }
}
