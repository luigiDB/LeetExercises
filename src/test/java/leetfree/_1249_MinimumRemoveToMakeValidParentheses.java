package leetfree;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * Given a string s of '(' , ')' and lowercase English characters.
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting
 * parentheses string is valid and return any valid string.
 * Formally, a parentheses string is valid if and only if:
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 */
public class _1249_MinimumRemoveToMakeValidParentheses {

    @Test
    public void a() {assertEquals("lee(t(c)o)de", minRemoveToMakeValid("lee(t(c)o)de)"));}

    @Test
    public void b() {assertEquals("ab(c)d", minRemoveToMakeValid("a)b(c)d"));}

    @Test
    public void c() {assertEquals("", minRemoveToMakeValid("))(("));}

    @Test
    public void d() {assertEquals("a(b(c)d)", minRemoveToMakeValid("(a(b(c)d)"));}

    public String minRemoveToMakeValid(String s) {
        Set<Integer> indexesToRemove = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            }
            if (s.charAt(i) == ')') {
                if (stack.isEmpty())
                    indexesToRemove.add(i);
                else
                    stack.pop();
            }
        }
        // Put any indexes remaining on stack into the set.
        while (!stack.isEmpty()) indexesToRemove.add(stack.pop());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!indexesToRemove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
