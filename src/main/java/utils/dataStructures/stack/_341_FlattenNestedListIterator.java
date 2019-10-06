package utils.dataStructures.stack;

import java.util.Stack;

/**
 * Given a nested list of integers, implement an iterator to flatten it.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 */
public class _341_FlattenNestedListIterator {

    private int[][] input;
    private final Stack<Integer> stack;
    private int counter;

    public _341_FlattenNestedListIterator(int[][] input) {
        this.input = input;
        stack = new Stack<>();
        counter = 0;
    }

    public boolean hasNext() {
        return !stack.isEmpty() || counter != input.length;
    }

    public int next() {
        if (stack.isEmpty()) {
            for (int i = input[counter].length - 1; i >= 0; i--) {
                stack.add(input[counter][i]);
            }
            counter += 1;
        }
        return stack.pop();
    }
}
