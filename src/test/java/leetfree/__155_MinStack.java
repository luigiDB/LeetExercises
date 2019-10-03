package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * <p>
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 * <p>
 * <p>
 * Example:
 * <p>
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 */
public class __155_MinStack {

    @Test
    public void given() {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        Assert.assertEquals(-3, minStack.getMin());
        minStack.pop();
        Assert.assertEquals(0, minStack.top());
        Assert.assertEquals(-2, minStack.getMin());
    }

    class MinStack {

        private final LinkedList<Pair> stack;

        public MinStack() {
            stack = new LinkedList<>();
        }

        public void push(int x) {
            Pair p = new Pair();
            p.value = x;
            p.minSoFar = (stack.isEmpty()) ? x : Math.min(stack.getFirst().minSoFar, x);
            stack.addFirst(p);
        }

        public void pop() {
            stack.removeFirst();
        }

        public int top() {
            return stack.getFirst().value;
        }

        public int getMin() {
            return stack.getFirst().minSoFar;
        }

        class Pair {
            int value;
            int minSoFar;
        }
    }
}
