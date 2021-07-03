package utils.numeric;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ShuntingYard {

    public int eval(String expr) {

        Queue<String> expression = new LinkedList<>();
        Stack<Character> operations = new Stack<>();
        int index = 0;
        while (index < expr.length()) {
            if (Character.isDigit(expr.charAt(index))) {
                String operator = "";
                int endNumber = index;
                while (endNumber < expr.length() && Character.isDigit(expr.charAt(endNumber))) {
                    endNumber++;
                }
                operator = expr.substring(index, endNumber);
                index = endNumber;

                expression.add(operator);
            } else {
                char operator = expr.charAt(index);
                index++;

                while (!operations.isEmpty() && prio(operations.peek()) >= prio(operator)) {
                    expression.add(String.valueOf(operations.pop()));
                }
                operations.push(operator);
            }
        }
        while (!operations.isEmpty()) {
            expression.add(String.valueOf(operations.pop()));
        }

        Stack<Integer> partials = new Stack<>();
        for (String op : expression) {
            try {
                int value = Integer.parseInt(op);
                partials.add(value);
            } catch (Exception e) {
                Integer second = partials.pop();
                Integer first = partials.pop();
                switch (op) {
                    case "+":
                        partials.add(second + first);
                        break;
                    case "*":
                        partials.add(second * first);
                        break;
                    case "/":
                        partials.add(first / second);
                        break;
                    case "-":
                        partials.add(first - second);
                        break;
                }
            }
        }

        return partials.pop();
    }

    private static int prio(char op) {
        switch (op) {
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                throw new IllegalStateException(String.format("operator %c not supported", op));
        }
    }
}
