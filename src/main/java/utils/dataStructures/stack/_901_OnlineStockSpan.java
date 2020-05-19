package utils.dataStructures.stack;

import java.util.LinkedList;
import java.util.Stack;

/*
Write a class StockSpanner which collects daily price quotes for some stock, and returns the span of that stock's price
for the current day.
The span of the stock's price today is defined as the maximum number of consecutive days (starting from today and going
backwards) for which the price of the stock was less than or equal to today's price.
 */
public class _901_OnlineStockSpan {

    private final Stack<Pair> stack;

    public _901_OnlineStockSpan() {
        stack = new Stack<>();
    }

    public int next(int price) {
        int accumulator = 1;
        while (!stack.isEmpty() && stack.peek().stock <= price) {
            Pair pop = stack.pop();
            accumulator += pop.span;
        }
        stack.push(new Pair(price, accumulator));
        return accumulator;
    }

    private class Pair {
        int stock;
        int span;

        public Pair(int stock, int span) {
            this.stock = stock;
            this.span = span;
        }
    }
}
