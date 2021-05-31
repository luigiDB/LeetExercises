package facebook;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertFalse;

/**
 * A bracket is considered to be any one of the following characters: (, ), {, }, [, or ].
 * We consider two brackets to be matching if the first element is an open-bracket, e.g., (, {, or [, and the second
 * bracket is a close-bracket of the same type, e.g., ( and ), [ and ], and { and } are the only pairs of matching
 * brackets.
 * Furthermore, a sequence of brackets is said to be balanced if the following conditions are met:
 * The sequence is empty, or
 * The sequence is composed of two, non-empty, sequences both of which are balanced, or
 * The first and last brackets of the sequence are matching, and the portion of the sequence without the first and
 * last elements is balanced.
 * You are given a string of brackets. Your task is to determine whether each sequence of brackets is balanced. If a
 * string is balanced, return true, otherwise, return false
 */
public class BalanceBrackets {

    @Test
    public void a() {
        assertFalse(isBalanced("{[(])}"));
    }

    boolean isBalanced(String s) {
        // Write your code here
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < s.length() ; i++) {
            char c = s.charAt(i);

            if(c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                switch(c) {
                    case ')':
                        if(stack.peek() == '(')
                            stack.pop();
                        else
                            return false;
                        break;
                    case ']':
                        if(stack.peek() == '[')
                            stack.pop();
                        else
                            return false;
                        break;
                    case '}':
                        if(stack.peek() == '{')
                            stack.pop();
                        else
                            return false;
                        break;
                }
            }
        }
        return stack.isEmpty();
    }
}
