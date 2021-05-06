package utils.dataStructures.stack;

import java.util.Stack;

/*
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock),
 * design an algorithm to find the maximum profit.
 * Note that you cannot sell a stock before you buy one.
 */
public class _121_BestTimeToBuyAndSellStock {

    public int maxGain(int[] prices) {
        //build an ascending stack
        int maxGain = 0;
        Stack<Integer> stack = new Stack<>();

        for (int price : prices) {
            while (!stack.isEmpty() && stack.peek() > price) {
                stack.pop();
            }
            stack.push(price);
            maxGain = Math.max(maxGain, stack.peek() - stack.get(0));
        }

        return maxGain;
    }
}
