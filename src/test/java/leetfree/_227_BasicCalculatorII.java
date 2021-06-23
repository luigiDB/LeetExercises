package leetfree;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * This is an implementation of the Shunting-yard {@link utils.numeric.ShuntingYard}
 */
public class _227_BasicCalculatorII {

    @Test
    public void a() {
        assertEquals(7, calculate("3+2*2"));
    }

    @Test
    public void b() {
        assertEquals(5, calculate(" 3+5 / 2 "));
    }

    @Test
    public void c() {
        assertEquals(1, calculate("3/ 2 "));
    }

    public int calculate(String s) {
        String cleanInput = s.replaceAll("\\s+", "");

        //Obtain reverse polish notation
        Stack<Character> operations = new Stack<>();
        Queue<String> polish = new LinkedList<>();
        for (char c : cleanInput.toCharArray()) {
            if (Character.isDigit(c)) {
                polish.offer(String.valueOf(c));
            } else {
                while (!operations.isEmpty() && prio(operations.peek()) > prio(c)) {
                    polish.offer(String.valueOf(operations.pop()));
                }
                operations.push(c);
            }
        }
        while (!operations.isEmpty()) {
            polish.offer(String.valueOf(operations.pop()));
        }

        //evaluate expression in polish notation
        Stack<Integer> evaluation = new Stack<>();
        while (!polish.isEmpty()) {
            String poll = polish.poll();
            try {
                Integer i = Integer.parseInt(poll);
                evaluation.push(i);
            } catch (Exception e) {
                Integer op2 = evaluation.pop();
                Integer op1 = evaluation.pop();
                int res = 0;
                switch (poll) {
                    case "+":
                        res = op1 + op2;
                        break;
                    case "-":
                        res = op1 - op2;
                        break;
                    case "*":
                        res = op1 * op2;
                        break;
                    case "/":
                        res = op1 / op2;
                        break;
                }
                evaluation.push(res);
            }
        }

        return evaluation.pop();
    }

    private int prio(char op) {
        switch (op) {
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                throw new RuntimeException("");
        }
    }
}
