package utils.dataStructures.stack;

import java.util.Stack;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * <p>
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * <p>
 * Note:
 * <p>
 * Division between two integers should truncate toward zero.
 * The given RPN expression is always valid. That means the expression would always evaluate to a result and there
 * won't be any divide by zero operation.
 */
public class _150_EvaluateReversePolishNotation {

    public static int evaluate(String[] operations) {
        Stack<Integer> numbers = new Stack<>();

        for (int i = 0; i < operations.length; i++) {
            try {
                int number = Integer.parseInt(operations[i]);
                numbers.push(number);
            } catch (Exception e) {
                switch (operations[i]) {
                    case "+":
                        numbers.push(numbers.pop() + numbers.pop());
                        break;
                    case "*":
                        numbers.push(numbers.pop() * numbers.pop());
                        break;
                    case "-":
                        // notice the minus
                        numbers.push(-numbers.pop() + numbers.pop());
                        break;
                    case "/":
                        Integer second = numbers.pop();
                        numbers.push(numbers.pop() / second);
                        break;
                }
            }
        }

        return numbers.pop();
    }
}
