package utils.dataStructures.stack;

import java.util.Stack;

/*
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water
 * it is able to trap after raining.
 */
public class _42_TrappingRainWater {

    public static int waterVolume(int[] heights) {
        int waterVolume = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] > heights[stack.peek()]) {
                int top = stack.pop();    //drop the head and verify distance with the one below
                if(stack.isEmpty())
                    break;
                //Min between the current height and the second previous element in stack minus the height in the middle
                int boundedHeight = Math.min(heights[i], heights[stack.peek()]) - heights[top];
                int distance = i - stack.peek() - 1;
                waterVolume += boundedHeight * distance;
            }
            stack.push(i);
        }


        return waterVolume;
    }
}
